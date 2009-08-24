package org.ictclas4j.run;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.ictclas4j.bean.SegResult;
import org.ictclas4j.bean.WordResultBean;
import org.ictclas4j.segment.Segment;
import org.ictclas4j.utility.Chineses;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 13, 2009 2:47:40 PM
 */
public class Test {
	public static void main(String[] args) throws Exception {
		Segment segTag = Segment.getInstance(1);
		StringBuffer sb = new StringBuffer("");
		String input = "狗蛋打来 倒插着头掉入卫生间的一个大铁桶内，因溺水时间过长死亡";
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
			if (result.isStopWord())
				sb.append("[s]");
			else {
				if (result.getWord().length() == 1) {
					if (sw == null)
						sw = new SingleWords();
					sw.addWord(i, result.getWord());
				} else {
					if (sw != null && sw.getWordsCount() > 1) {
						sws.add(sw);
					}
					sw = null;
				}
			}
			sb.append(" ");
		}

		if (sw != null && sw.getWordsCount() > 1) {
			sws.add(sw);
		}
		sw = null;

		System.out.println(sb.toString());
		System.out.println("----------------------");
		for (SingleWords sw1 : sws) {
			String googleResult = getGoogleSearchResult(sw1.toString());
			System.out.println(getEmString(googleResult));
		}
	}

	public static String getEmString(String result) {
		Parser parser = Parser.createParser(result, "utf8");
		PrototypicalNodeFactory factory = new PrototypicalNodeFactory();
		factory.registerTag(new EmTag());
		parser.setNodeFactory(factory);

		NodeFilter emFilter = new NodeClassFilter(EmTag.class);
		NodeList nodelist;
		try {
			nodelist = parser.extractAllNodesThatMatch(emFilter);
		} catch (ParserException e) {
			return "";
		}

		Node[] nodes = nodelist.toNodeArray();

		StringBuffer sb = new StringBuffer("");

		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof EmTag) {
				EmTag em = (EmTag) node;
				Node parent = em.getParent();
				if (parent instanceof Div) {
					Div div = (Div) parent;
					if (div.getAttribute("class") != null && div.getAttribute("class").equals("s"))
						sb.append(em.getContent()).append("\r\n");
				}
			}
		}
		return sb.toString();
	}

	public static class SingleWords {
		private Hashtable<Integer, String> words = new Hashtable<Integer, String>();

		public void addWord(int pos, String s) {
			words.put(pos, s);
		}

		public int getWordsCount() {
			return words.size();
		}

		@Override
		public String toString() {
			Collection<String> ws = words.values();
			StringBuffer sb = new StringBuffer("");
			for (String w : ws) {
				sb.append(w);
			}
			return sb.reverse().toString();
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

	static class EmTag extends CompositeTag {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3262862026166805969L;

		private static final String[] mIds = new String[] { "EM" };

		private static final String[] mEndTagEnders = new String[] { "DIV" };

		@Override
		public String[] getIds() {
			return (mIds);
		}

		@Override
		public String[] getEnders() {
			return (mIds);
		}

		@Override
		public String[] getEndTagEnders() {
			return (mEndTagEnders);
		}

		public String getContent() {
			return super.getStringText();
		}

	}
}
