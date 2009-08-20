package org.ictclas4j.run;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

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
		String input = "最近金山云安全中心截获了一个很牛X的病毒";
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
			System.out.println(getGoogleSearchResult(sw1.toString()));
		}
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
		try {
			keyword = "java";
			String s = "http://www.google.com/search?hl=en&source=hp&q=" + keyword + "&aq=f&oq=&aqi=";
			s = "http://www.google.com/search?hl=en&q=java&&aq=f&oq=&aqi=";
			URL url = new URL(s);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
