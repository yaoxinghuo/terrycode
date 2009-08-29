package org.ictclas4j.run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 13, 2009 2:47:40 PM
 */
public class Test {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File("E:/Lab/test.txt")));
		StringBuffer str = new StringBuffer("");
		String line;
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			str.append(line).append("\r\n");
		}

		test(str.toString());
		// test("狗蛋打来 倒插着头掉入卫生间的一个大铁桶内，因溺水时间过长死亡");
	}

	public static void test(String input) throws Exception {
		Segment segTag = Segment.getInstance(1);

		SegResult seg_res = segTag.split(input);

		TreeMap<Integer, WordResultBean> results = seg_res.getPosResult();
		ArrayList<ReprocessWords> sws = Reprocess.getReprocessWords(results);

		System.out.println("\r\n××××××××××××××××××ictclas4j初始结果××××××××××××××××××");
		// System.out.println(seg_res.getFinalResult());
		System.out.println(Reprocess.getStringResult(input, results));

		ArrayList<TreeMap<Integer, MatcherWord>> mwss = new ArrayList<TreeMap<Integer, MatcherWord>>();

		for (ReprocessWords sw1 : sws) {
			System.out.println("\r\n----------------------");
			String googleResult = getGoogleSearchResult(sw1.toString());
			TreeMap<Integer, MatcherWord> mws1 = analytics(googleResult, sw1);
			System.out.println("-----合并前的结果-----");
			printMidResult(mws1);
			TreeMap<Integer, MatcherWord> mws2 = Reprocess.filterMatcherWord(mws1);
			System.out.println("-----合并后的结果-----");
			printMidResult(mws2);
			mwss.add(mws2);
		}

		TreeMap<Integer, WordResultBean> finalResult = Reprocess.processResults(results, mwss);
		System.out.println("\r\n××××××××××××××××××最终结果××××××××××××××××××");
		System.out.println(Reprocess.getStringResult(input, finalResult));
	}

	private static void printMidResult(TreeMap<Integer, MatcherWord> mws) {
		for (MatcherWord mw : mws.values()) {
			System.out.println("No. " + mw.getStage() + "\tTimes: " + mw.getCount() + "\t\t"
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

		return sw.getMatcherWords(sb.toString());

	}

	public static String getGoogleSearchResult(String keyword) throws Exception {
		System.out.println("Try to search '" + keyword + " ' using google...");

		BufferedReader br = new BufferedReader(new FileReader(new File("E:/Lab/google.txt")));
		StringBuffer str = new StringBuffer("");
		String line;
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			str.append(line).append("\r\n");
		}

		return str.toString();

		// try {
		// String s = "http://www.google.cn/search?hl=zh-CN&source=hp&q=" +
		// URLEncoder.encode(keyword, "UTF-8")
		// + "&btnG=Google+%E6%90%9C%E7%B4%A2&aq=f&oq=";
		// URL url = new URL(s);
		// HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// con.setRequestProperty("User-Agent", "IIC2.0/PC 2.1.0.0");
		// con.setRequestProperty("connection", "Close");
		// con.setDoOutput(true);
		// con.setRequestMethod("GET");
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(con.getInputStream(), "GBK"));
		// StringBuffer sb = new StringBuffer();
		// String line;
		// while ((line = reader.readLine()) != null) {
		// sb.append(line);
		// }
		// reader.close();
		// con.disconnect();
		// return sb.toString();
		// } catch (Exception e) {
		// e.printStackTrace();
		// return null;
		// }
	}
}
