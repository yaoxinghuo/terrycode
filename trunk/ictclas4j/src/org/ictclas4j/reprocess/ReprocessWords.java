package org.ictclas4j.reprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Aug 29, 2009 7:49:23 PM
 */
public class ReprocessWords {

	/*
	 * 待处理词语（目前的取法是取单个词语前后一个词(如果有)，不包含标点）
	 */
	static Logger logger = Logger.getLogger(ReprocessWords.class);

	private TreeMap<Integer, String> words = new TreeMap<Integer, String>();

	private int singleWordsCount = 0;

	public void addWord(int pos, String s) {
		words.put(pos, s);
		if (s.length() == 1) {
			singleWordsCount++;
		}
	}

	public TreeMap<Integer, MatcherWord> getMatcherWords(String reference) {
		// reference = com.terry.chineses.Chineses.toJian(reference, true);
		// 最好不要在这里这么用，不然性能不好，如果要可以在调用这个方法的时候转换成简体

		TreeMap<Integer, MatcherWord> results = new TreeMap<Integer, MatcherWord>();
		if (reference == null || reference.trim().equals(""))
			return results;

		ArrayList<MatcherWord> mws = new ArrayList<MatcherWord>();
		Collection<Integer> keys = new TreeSet<Integer>(words.keySet());
		ArrayList<Integer> akeys = new ArrayList<Integer>();
		for (Integer key : keys) {
			akeys.add(key);
		}
		for (int i = 0; i < akeys.size(); i++) {
			for (int j = akeys.size() - 1; j >= i; j--) {
				MatcherWord mw = new MatcherWord(i);
				for (int k = i; k <= j; k++) {
					mw.addWord(akeys.get(k), words.get(akeys.get(k)));
				}
				mw.setPrefix(i != 0 ? words.get(akeys.get(i - 1)) : null);
				mw.setSuffix(j != akeys.size() - 1 ? words.get(akeys.get(j + 1)) : null);
				mws.add(mw);
			}
		}

		for (MatcherWord mw : mws) {
			if (mw.getWords().size() < 2) {
				logger.info("No. " + mw.getStage() + "\tTimes: -1" + "\t\t" + mw.toString()
						+ "\t\tSingle word, ignore....");
				continue;
			}
			Pattern pattern = null;
			try {
				pattern = Pattern.compile(generatePattern(mw.toString()), Pattern.CASE_INSENSITIVE);
			} catch (Exception e) {
				logger.error("error while compile pattern:" + mw.toString() + ". exception:" + e.getMessage());
				continue;
			}
			Matcher matcher = pattern.matcher(reference);
			while (matcher.find()) {
				// String word = matcher.group();
				boolean ok = true;
				if (mw.getPrefix() != null) {
					int spos = matcher.start();
					if (reference.lastIndexOf(mw.getPrefix(), spos) == (spos - mw.getPrefix().length()))
						ok = false;

				}
				if (mw.getSuffix() != null) {
					int epos = matcher.end();
					if (reference.indexOf(mw.getSuffix(), epos) == epos)
						ok = false;
				}
				if (ok)
					mw.setCount(mw.getCount() + 1);

			}
			if (mw.getCount() != 0) {
				MatcherWord mw2 = results.get(mw.getStage());
				if (mw2 == null || mw2.getCount() < mw.getCount()) {
					results.put(mw.getStage(), mw);
					logger.info("No. " + mw.getStage() + "\tTimes: " + mw.getCount() + "\t\t" + mw.toString()
							+ "\t\tIf more times in the no, overwrite the previous one....");
				}
			} else {
				logger.info("No. " + mw.getStage() + "\tTimes: " + mw.getCount() + "\t\t" + mw.toString()
						+ "\t\tignore....");
			}
		}

		return results;
	}

	public static String generatePattern(String s) {
		Pattern pattern = Pattern.compile("[*|^|$|\\|+|?|.|,|\\|]");
		Matcher matcher = pattern.matcher(s);
		StringBuffer sb = new StringBuffer("");
		while (matcher.find()) {
			matcher.appendReplacement(sb, "\\\\" + matcher.group());
		}
		matcher.appendTail(sb);
		return sb.toString();
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
