package org.ictclas4j.reprocess;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 29, 2009 7:55:21 PM
 */
public class MatcherWord {
	/*
	 * ProcessWords一次次拆分后的词语,如“狗蛋打来”最后会拆分成若干组类似[“狗蛋打来”，“狗蛋打”，“狗蛋”，“狗”]，
	 * [“蛋打来”，“蛋打”，“蛋“]，[ ”打来“，”打“]，[ ”来“]等MatcherWord
	 */
	private TreeMap<Integer, String> words = new TreeMap<Integer, String>();

	public TreeMap<Integer, String> getWords() {
		return words;
	}

	private int stage;

	public void addWord(int pos, String s) {
		words.put(pos, s);
	}

	public MatcherWord(int stage) {
		this.stage = stage;
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
	
	public Set<Integer> getWordsKeySet(){
		return this.words.keySet();
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getStage() {
		return stage;
	}
}
