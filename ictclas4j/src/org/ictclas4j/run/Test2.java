package org.ictclas4j.run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.ictclas4j.bean.SegResult;
import org.ictclas4j.bean.WordResultBean;
import org.ictclas4j.segment.Segment;
import org.ictclas4j.utility.Chineses;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create£ºAug 13, 2009 2:47:40 PM
 */
public class Test2 {
	public static void main(String[] args) throws Exception {
		Segment segTag = Segment.getInstance(1);
		StringBuffer sb = new StringBuffer("");
		BufferedReader br = new BufferedReader(new FileReader(new File("E:/test.txt")));
		StringBuffer str = new StringBuffer("");
		String line;
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			str.append(line).append("\r\n");
		}

		String input = str.toString();
		SegResult seg_res = segTag.split(input);

		ArrayList<SingleWords> sws = new ArrayList<SingleWords>();
		SingleWords sw = null;

		ArrayList<WordResultBean> results = seg_res.getResult();
		for (int i = 0; i < results.size(); i++) {
			WordResultBean result = results.get(i);
			if (!input.contains(result.getWord()))
				sb.append(Chineses.toFan(result.getWord()));
			else
				sb.append(result.getWord());
			sb.append("/" + result.getProperty());

			if (result.isStopWord()) {
				sb.append("[s]");
				sw = null;
			} else {
				if (result.getWord().length() == 1) {
					if (sw == null)
						sw = new SingleWords();
					if (sw.getSingleWordsCount() == 0) {
						if (i > 0 && !results.get(i - 1).isStopWord())
							sw.addWord(i - 1, results.get(i - 1).getWord());
					}
					sw.addWord(i, result.getWord());
					if (i != results.size() - 1) {
						WordResultBean nextResult = results.get(i + 1);
						if (nextResult.isStopWord() || nextResult.getWord().length() > 1) {
							if (!nextResult.isStopWord())
								sw.addWord(i + 1, nextResult.getWord());
							if (sw.getSingleWordsCount() >= 2)
								sws.add(sw);
							sw = null;
						}
					} else {
						if (sw.getSingleWordsCount() >= 2)
							sws.add(sw);
						sw = null;
					}
				} else {
					sw = null;
				}
			}
			sb.append(" ");
		}

		System.out.println(sb.toString());
		for (SingleWords sw1 : sws) {
			System.out.println("----------------------");
			String googleResult = getGoogleSearchResult(sw1.toString());
			ArrayList<MatcherWord> mws = getEmTags(googleResult, sw1);
			for (MatcherWord mw : mws) {
				System.out.println((mw.getPrefix() == null ? "[null]" : "[" + mw.getPrefix() + "]") + mw.toString()
						+ (mw.getSuffix() == null ? "[null]" : "[" + mw.getSuffix() + "]") + "\t\tTimes:"
						+ mw.getCount());
			}
		}
	}

	public static ArrayList<MatcherWord> getEmTags(String result, SingleWords sw) {
		Parser parser = Parser.createParser(result, "utf8");

		NodeFilter emFilter = new NodeClassFilter(Div.class);
		NodeList nodelist;
		try {
			nodelist = parser.extractAllNodesThatMatch(emFilter);
		} catch (ParserException e) {
			return null;
		}

		Node[] nodes = nodelist.toNodeArray();

		ArrayList<MatcherWord> results = new ArrayList<MatcherWord>();

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

		String s = Chineses.toJian(sb.toString());
		ArrayList<MatcherWord> mws = sw.getMatcherWords();
		for (MatcherWord mw : mws) {
			Pattern pattern = Pattern.compile(mw.toString(), Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(s);
			while (matcher.find()) {
				// String word = matcher.group();
				boolean ok = true;
				if (mw.getPrefix() != null) {
					int spos = matcher.start();
					if (s.lastIndexOf(mw.getPrefix(), spos) == (spos - mw.getPrefix().length()))
						ok = false;

				}
				if (mw.getSuffix() != null) {
					int epos = matcher.end();
					if (s.indexOf(mw.getSuffix(), epos) == epos)
						ok = false;
				}
				if (ok)
					mw.setCount(mw.getCount() + 1);

			}
			results.add(mw);
		}

		return results;
	}

	public static class SingleWords {
		private Hashtable<Integer, String> words = new Hashtable<Integer, String>();

		private int singleWordsCount = 0;

		public void addWord(int pos, String s) {
			words.put(pos, s);
			if (s.length() == 1) {
				singleWordsCount++;
			}
		}

		public ArrayList<MatcherWord> getMatcherWords() {
			ArrayList<MatcherWord> mws = new ArrayList<MatcherWord>();
			Collection<Integer> keys = new TreeSet<Integer>(words.keySet());
			ArrayList<Integer> akeys = new ArrayList<Integer>();
			for (Integer key : keys) {
				akeys.add(key);
			}
			for (int i = 0; i < akeys.size(); i++) {
				for (int j = akeys.size() - 1; j >= i; j--) {
					MatcherWord mw = new MatcherWord();
					for (int k = i; k <= j; k++) {
						mw.addWord(k, words.get(akeys.get(k)));
					}
					mw.setPrefix(i != 0 ? words.get(akeys.get(i - 1)) : null);
					mw.setSuffix(j != akeys.size() - 1 ? words.get(akeys.get(j + 1)) : null);
					mws.add(mw);
				}
			}
			return mws;
		}

		public int getSingleWordsCount() {
			return singleWordsCount;
		}

		@Override
		public String toString() {
			Collection<Integer> keys = new TreeSet<Integer>(words.keySet());
			StringBuffer sb = new StringBuffer("");
			for (Integer key : keys) {
				sb.append(words.get(key));
			}
			return sb.toString();
		}

	}

	public static class MatcherWord {
		private Hashtable<Integer, String> words = new Hashtable<Integer, String>();

		public void addWord(int pos, String s) {
			words.put(pos, s);
		}

		private String prefix;

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		private String suffix;

		private int count;

		@Override
		public String toString() {
			Collection<Integer> keys = new TreeSet<Integer>(words.keySet());
			StringBuffer sb = new StringBuffer("");
			for (Integer key : keys) {
				sb.append(words.get(key));
			}
			return sb.toString();
		}
	}

	public static String getGoogleSearchResult(String keyword) {
		System.out.println("Try to search '" + keyword + " ' using google...");
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
