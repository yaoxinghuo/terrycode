package org.ictclas4j.segment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ictclas4j.bean.Atom;
import org.ictclas4j.bean.Constants;
import org.ictclas4j.bean.Dictionary;
import org.ictclas4j.bean.MidResult;
import org.ictclas4j.bean.SegNode;
import org.ictclas4j.bean.SegResult;
import org.ictclas4j.bean.Sentence;
import org.ictclas4j.bean.StopWordDictionary;
import org.ictclas4j.bean.WhiteWordDictionary;
import org.ictclas4j.bean.WordResultBean;
import org.ictclas4j.utility.POSTag;
import org.ictclas4j.utility.Utility;

import com.terry.chineses.Chineses;

public class Segment {
	private Dictionary coreDict;

	private Dictionary bigramDict;

	private StopWordDictionary stopWordDictionary;

	private WhiteWordDictionary whiteWordDictionary;

	private PosTagger personTagger;

	private PosTagger transPersonTagger;

	private PosTagger placeTagger;

	private PosTagger lexTagger;

	private static int segPathCount = 1;// �ִ�·������Ŀ

	static Logger logger = Logger.getLogger(Segment.class);

	private static Segment instance = null;

	public static Segment getInstance(int segPathCount) {
		Segment.segPathCount = segPathCount;
		if (instance == null) {
			instance = new Segment();
		}
		return instance;
	}

	public static Segment getInstance() {
		if (instance == null) {
			instance = new Segment();
		}
		return instance;
	}

	private Segment() {
		String path = Constants.DATA_PATH != null && !Constants.DATA_PATH.equals("") ? Constants.DATA_PATH : "./data/";
		if (!path.endsWith("/"))
			path = path + "/";

		logger.info("Load coreDict  ...");
		coreDict = new Dictionary(path + "coreDict.dct");

		logger.info("Load bigramDict ...");
		bigramDict = new Dictionary(path + "bigramDict.dct");

		logger.info("Load stopwords ...");
		stopWordDictionary = new StopWordDictionary(path + "stopwords.txt");

		logger.info("Load whitewords...");
		whiteWordDictionary = new WhiteWordDictionary(path + "whitewords.txt");

		logger.info("Load tagger dict ...");
		personTagger = new PosTagger(Utility.TAG_TYPE.TT_PERSON, path + "nr", coreDict);
		transPersonTagger = new PosTagger(Utility.TAG_TYPE.TT_TRANS_PERSON, path + "tr", coreDict);
		placeTagger = new PosTagger(Utility.TAG_TYPE.TT_TRANS_PERSON, path + "ns", coreDict);
		lexTagger = new PosTagger(Utility.TAG_TYPE.TT_NORMAL, path + "lexical", coreDict);
		logger.info("Load dict complete");
	}

	public SegResult split(String src) {
		src = Chineses.toJian(src);
		SegResult sr = new SegResult(src);// �ִʽ��
		StringBuffer finalResult = new StringBuffer("");

		if (src != null) {
			int index = 0;
			String midResult = null;
			sr.setRawContent(src);
			SentenceSeg ss = new SentenceSeg(src);
			ArrayList<Sentence> sens = ss.getSens();
			ArrayList<WordResultBean> words = new ArrayList<WordResultBean>();

			for (Sentence sen : sens) {
				logger.debug(sen);
				long start = System.currentTimeMillis();
				MidResult mr = new MidResult();
				mr.setIndex(index++);
				mr.setSource(sen.getContent());
				if (sen.isSeg()) {

					// ԭ�ӷִ�
					AtomSeg as = new AtomSeg(sen.getContent());
					ArrayList<Atom> atoms = as.getAtoms();
					mr.setAtoms(atoms);
					logger.info("[atom time]:" + (System.currentTimeMillis() - start));
					start = System.currentTimeMillis();

					// ���ɷִ�ͼ��,�Ƚ��г����ִʣ�Ȼ������Ż��������д��Ա��
					SegGraph segGraph = GraphGenerate.generate(atoms, coreDict);
					mr.setSegGraph(segGraph.getSnList());
					// ���ɶ���ִ�ͼ��
					SegGraph biSegGraph = GraphGenerate.biGenerate(segGraph, coreDict, bigramDict);
					mr.setBiSegGraph(biSegGraph.getSnList());
					logger.info("[graph time]:" + (System.currentTimeMillis() - start));
					start = System.currentTimeMillis();

					// ��N���·��
					NShortPath nsp = new NShortPath(biSegGraph, segPathCount);
					ArrayList<ArrayList<Integer>> bipath = nsp.getPaths();
					mr.setBipath(bipath);
					logger.info("[NSP time]:" + (System.currentTimeMillis() - start));
					start = System.currentTimeMillis();

					for (ArrayList<Integer> onePath : bipath) {
						// �õ����ηִ�·��
						ArrayList<SegNode> segPath = getSegPath(segGraph, onePath);
						ArrayList<SegNode> firstPath = AdjustSeg.firstAdjust(segPath);
						String firstResult = outputResult(firstPath);
						mr.addFirstResult(firstResult);
						logger.info("[first time]:" + (System.currentTimeMillis() - start));
						start = System.currentTimeMillis();

						// ����δ��½�ʣ����Գ��ηִʽ�������Ż�
						SegGraph optSegGraph = new SegGraph(firstPath);
						ArrayList<SegNode> sns = clone(firstPath);
						personTagger.recognition(optSegGraph, sns);
						transPersonTagger.recognition(optSegGraph, sns);
						placeTagger.recognition(optSegGraph, sns);
						mr.setOptSegGraph(optSegGraph.getSnList());
						logger.info("[unknown time]:" + (System.currentTimeMillis() - start));
						start = System.currentTimeMillis();

						// �����Ż���Ľ�������½������ɶ���ִ�ͼ��
						SegGraph optBiSegGraph = GraphGenerate.biGenerate(optSegGraph, coreDict, bigramDict);
						mr.setOptBiSegGraph(optBiSegGraph.getSnList());

						// ������ȡN�����·��
						NShortPath optNsp = new NShortPath(optBiSegGraph, segPathCount);
						ArrayList<ArrayList<Integer>> optBipath = optNsp.getPaths();
						mr.setOptBipath(optBipath);

						// �����Ż���ķִʽ�������Խ�����д��Ա�Ǻ������Ż���������
						ArrayList<SegNode> adjResult = null;
						for (ArrayList<Integer> optOnePath : optBipath) {
							ArrayList<SegNode> optSegPath = getSegPath(optSegGraph, optOnePath);
							lexTagger.recognition(optSegPath);
							String optResult = outputResult(optSegPath);
							mr.addOptResult(optResult);
							adjResult = AdjustSeg.finaAdjust(optSegPath, personTagger, placeTagger);
							String adjrs = outputResult(adjResult, words);
							logger.info("[last time]:" + (System.currentTimeMillis() - start));
							start = System.currentTimeMillis();
							if (midResult == null)
								midResult = adjrs;
							break;
						}
					}
					sr.addMidResult(mr);
				} else
					midResult = sen.getContent();
				finalResult.append(midResult);
				midResult = null;
			}

			sr.setFinalResult(finalResult.toString());
			sr.setResult(words);
			logger.debug(finalResult);
		}

		return sr;
	}

	private ArrayList<SegNode> clone(ArrayList<SegNode> sns) {
		ArrayList<SegNode> result = null;
		if (sns != null && sns.size() > 0) {
			result = new ArrayList<SegNode>();
			for (SegNode sn : sns)
				result.add(sn.clone());
		}
		return result;
	}

	// ���ݶ���ִ�·�����ɷִ�·��
	private ArrayList<SegNode> getSegPath(SegGraph sg, ArrayList<Integer> bipath) {

		ArrayList<SegNode> path = null;

		if (sg != null && bipath != null) {
			ArrayList<SegNode> sns = sg.getSnList();
			path = new ArrayList<SegNode>();

			for (int index : bipath)
				path.add(sns.get(index));

		}
		return path;
	}

	// ���ݷִ�·�����ɷִʽ��
	private String outputResult(ArrayList<SegNode> wrList) {
		return outputResult(wrList, null);
	}

	// ���ݷִ�·�����ɷִʽ��
	private String outputResult(ArrayList<SegNode> wrList, ArrayList<WordResultBean> results) {
		StringBuffer result = new StringBuffer("");
		if (wrList != null && wrList.size() > 0) {
			for (int i = 0; i < wrList.size(); i++) {
				SegNode sn = wrList.get(i);
				if (sn.getPos() != POSTag.SEN_BEGIN && sn.getPos() != POSTag.SEN_END) {
					String property = Utility.posIntToString(sn.getPos());
					result.append(sn.getSrcWord()).append("/").append(property).append(" ");
					if (results != null) {
						WordResultBean r = new WordResultBean();
						r.setProperty(property);
						r.setWord(sn.getSrcWord());
						if (sn.getPos() == POSTag.PUNC// ����Ǳ����ţ�����ͣ�ô�
								|| (stopWordDictionary != null && stopWordDictionary.words.contains(sn.getSrcWord())))
							r.setStopWord(true);
						results.add(r);
					}
				}
			}
		}

		return result.toString();
	}

	public void setSegPathCount(int segPathCount) {
		Segment.segPathCount = segPathCount;
	}

	public static Set<String> loadStopWords(InputStream input) {
		String line;
		Set<String> stopWords = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			while ((line = br.readLine()) != null) {
				if (line.indexOf("//") != -1) {
					line = line.substring(0, line.indexOf("//")).trim();
				}
				if (line.length() != 0)
					stopWords.add(line.toLowerCase());
			}
			br.close();
		} catch (IOException e) {
			logger.error("WARNING: cannot open stop words list!");
		}
		return stopWords;
	}

	public WhiteWordDictionary getWhiteWordDictionary() {
		return whiteWordDictionary;
	}

}
