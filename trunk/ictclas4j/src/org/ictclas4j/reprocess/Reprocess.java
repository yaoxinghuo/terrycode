package org.ictclas4j.reprocess;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import org.ictclas4j.bean.WordResultBean;
import org.ictclas4j.utility.Chineses;
import org.ictclas4j.utility.POSTag;
import org.ictclas4j.utility.Utility;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 29, 2009 8:38:27 PM
 */
public class Reprocess {

	public static String getStringResult(String input, TreeMap<Integer, WordResultBean> results) {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < results.size(); i++) {
			WordResultBean result = results.get(i);
			if (result == null)
				continue;
			if (!input.contains(result.getWord()))
				sb.append(Chineses.toFan(result.getWord()));
			else
				sb.append(result.getWord());
			sb.append("/" + result.getProperty());

			if (result.isStopWord())
				sb.append("[s]");
			sb.append(" ");
		}

		return sb.toString();
	}

	public static ArrayList<ReprocessWords> getReprocessWords(TreeMap<Integer, WordResultBean> results) {
		ArrayList<ReprocessWords> sws = new ArrayList<ReprocessWords>();
		ReprocessWords sw = null;
		for (int i = 0; i < results.size(); i++) {
			WordResultBean result = results.get(i);

			if (result.getWord().length() == 1 && isQualified(result)) {
				if (sw == null)
					sw = new ReprocessWords();
				if (sw.getSingleWordsCount() == 0) {
					if (i > 0) {
						if (isQualified(results.get(i - 1)))
							sw.addWord(i - 1, results.get(i - 1).getWord());
					}
				}
				sw.addWord(i, result.getWord());
				if (i != results.size() - 1) {
					WordResultBean nextResult = results.get(i + 1);
					if (nextResult.getWord().length() > 1 && isQualified(nextResult)) {
						sw.addWord(i + 1, nextResult.getWord());
						sws.add(sw);
						sw = null;
					}
				} else {
					sws.add(sw);
					sw = null;
				}
			}

		}
		return sws;
	}

	public static TreeMap<Integer, WordResultBean> processResults(TreeMap<Integer, WordResultBean> results,
			ArrayList<TreeMap<Integer, MatcherWord>> mwss) {
		for (TreeMap<Integer, MatcherWord> mws : mwss) {
			for (MatcherWord mw : mws.values()) {
				TreeMap<Integer, String> words = mw.getWords();
				if (words.size() > 1) {
					Set<Integer> keys = words.keySet();
					for (Integer key : keys) {
						results.remove(key.intValue());
					}
					WordResultBean bean = new WordResultBean();
					bean.setProperty(Utility.posIntToString(POSTag.UNKNOWN));
					bean.setStopWord(false);
					bean.setWord(mw.toString());
					results.put(words.firstKey(), bean);
				}
			}
		}
		return results;
	}

	/*
	 * 如果是标点符号，就不娶
	 */
	private static boolean isQualified(WordResultBean result) {
		if (Utility.posStringToInt(result.getProperty()) == POSTag.PUNC)
			return false;
		return true;
	}

	public static TreeMap<Integer, MatcherWord> filterMatcherWord(TreeMap<Integer, MatcherWord> mws) {
		if (mws == null || mws.size() == 1)
			return mws;
		Set<Integer> keys = mws.keySet();
		ArrayList<Integer> is = new ArrayList<Integer>();
		for (Integer i : keys) {
			is.add(i);
		}
		MatcherWord first = null;
		for (Integer i : is) {
			if (first == null)
				first = mws.get(i);
			else {
				MatcherWord second = mws.get(i);
				int result = compare(first, second);
				if (result == 0) {
					first = second;
				} else if (result == 1) {
					mws.remove(i);
				} else {
					mws.remove(first.getStage());
					first = second;
				}
			}
		}
		return mws;
	}

	/*
	 * 0 not conflict 1 mw1 retains -1 mw1 retains
	 */
	private static int compare(MatcherWord mw1, MatcherWord mw2) {
		if (!contains(mw1.getWordsKeySet(), mw2.getWordsKeySet()))
			return 0;
		else
			return (mw2.getCount() > mw1.getCount() ? -1 : 1);
	}

	private static boolean contains(Set<Integer> s1, Set<Integer> s2) {
		for (Integer i1 : s1) {
			for (Integer i2 : s2) {
				if (i1.intValue() == i2.intValue())
					return true;
			}
		}
		return false;
	}
}
