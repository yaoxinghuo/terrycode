package org.ictclas4j.run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.ictclas4j.bean.SegResult;
import org.ictclas4j.bean.WordResultBean;
import org.ictclas4j.reprocess.MatcherWord;
import org.ictclas4j.reprocess.Reprocess;
import org.ictclas4j.reprocess.ReprocessWords;
import org.ictclas4j.segment.Segment;

import com.terry.chineses.Chineses;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 13, 2009 2:47:40 PM
 */
public class Test {

	public static BufferedWriter bw = null;

	public static void main(String[] args) throws Exception {
		File f = new File("E:/Lab/" + new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date()) + ".txt");
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E:/Lab/test.txt"), "UTF-8"));
		StringBuffer str = new StringBuffer("");
		String line;
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			str.append(line).append("\r\n");
		}

		test(str.toString());
		// test("狗蛋打来,倒插着头掉入卫生间的一个大铁桶内，因溺水时间过长死亡");

		bw.flush();
		bw.close();

	}

	private static void log(String s) {
		System.out.println(s);
		try {
			bw.write(s);
			bw.write("\r\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void test(String input) throws Exception {
		long start = System.currentTimeMillis();
		log("Started at:" + new Date() + "\r\n");
		Segment segTag = Segment.getInstance(1);

		SegResult seg_res = segTag.split(input);

		TreeMap<Integer, WordResultBean> results = seg_res.getPosResult();
		ArrayList<ReprocessWords> sws = Reprocess.getReprocessWords(results);

		log("\r\n××××××××××××××××××ictclas4j初始结果××××××××××××××××××");
		// log(seg_res.getFinalResult());
		log(Reprocess.getStringResult(input, results));

		ArrayList<TreeMap<Integer, MatcherWord>> mwss = new ArrayList<TreeMap<Integer, MatcherWord>>();

		for (ReprocessWords sw1 : sws) {
			log("\r\n----------------------" + sw1.toString());
			String googleResult = getGoogleSearchResult(sw1.toString());
			TreeMap<Integer, MatcherWord> mws1 = analytics(googleResult, sw1);
			log("-----合并前的结果-----");
			printMidResult(mws1);
			TreeMap<Integer, MatcherWord> mws2 = Reprocess.filterMatcherWord(mws1);
			log("-----合并后的结果-----");
			printMidResult(mws2);
			mwss.add(mws2);
			Thread.sleep(5000);
		}

		TreeMap<Integer, WordResultBean> finalResult = Reprocess.processResults(results, mwss);
		log("\r\n××××××××××××××××××最终结果××××××××××××××××××");
		log(Reprocess.getStringResult(input, finalResult));
		log("\r\nStatics:");
		statistics(finalResult);
		log("\r\nEnded at:" + new Date());
		log("Times took:" + (System.currentTimeMillis() - start) / 1000 + "s");
	}

	private static void statistics(TreeMap<Integer, WordResultBean> results) {
		HashMap<String, ArrayList<String>> cat = new HashMap<String, ArrayList<String>>();
		for (WordResultBean result : results.values()) {
			String property = result.getProperty().substring(0, 1);
			if (cat.containsKey(property)) {
				ArrayList<String> a = cat.get(property);
				a.add(result.getWord());
				cat.put(property, a);
			} else {
				ArrayList<String> a = new ArrayList<String>();
				a.add(result.getWord());
				cat.put(property, a);
			}
		}

		for (String s : cat.keySet()) {
			StringBuffer sb = new StringBuffer();
			sb.append(s).append("\t");
			ArrayList<String> aa = cat.get(s);
			for (String a : aa) {
				sb.append(a + " ");
			}
			log("\r\n" + sb.toString());
		}
	}

	private static void printMidResult(TreeMap<Integer, MatcherWord> mws) {
		for (MatcherWord mw : mws.values()) {
			log("No. " + mw.getStage() + "\tTimes: " + mw.getCount() + "\t\t"
					+ (mw.getPrefix() == null ? "[null]" : "[" + mw.getPrefix() + "]") + mw.toString()
					+ (mw.getSuffix() == null ? "[null]" : "[" + mw.getSuffix() + "]"));
		}
	}

	public static TreeMap<Integer, MatcherWord> analytics(String result, ReprocessWords sw) {
		Parser parser = Parser.createParser(result, "utf8");

		NodeFilter emFilter = new NodeClassFilter(Div.class);
		NodeList nodelist;
		try {
			nodelist = parser.extractAllNodesThatMatch(emFilter);
		} catch (ParserException e) {
			return null;
		}

		Node[] nodes = nodelist.toNodeArray();

		StringBuffer sb = new StringBuffer("");

		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof Div) {
				Div div = (Div) node;
				if (div.getAttribute("class") != null && div.getAttribute("class").equals("s")) {
					sb.append(div.getStringText()).append("\r\n");
				}
			}
		}

		return sw.getMatcherWords(Chineses.toJian(sb.toString()));

	}

	public static String getGoogleSearchResult(String keyword) throws Exception {
		log("Try to search '" + keyword + " ' using google...");

		try {
			String s = "http://www.google.cn/search?hl=zh-CN&source=hp&q=" + URLEncoder.encode(keyword, "UTF-8")
					+ "&btnG=Google+%E6%90%9C%E7%B4%A2&aq=f&oq=";
			URL url = new URL(s);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "IIC2.0/PC 2.1.0.0");
			con.setRequestProperty("connection", "Close");
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "GBK"));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			con.disconnect();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
