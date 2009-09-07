package org.ictclas4j.reprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Sep 7, 2009 10:11:59 PM
 */
public class SingleWords {
	private TreeMap<Integer, String> words = new TreeMap<Integer, String>();

	public void addWord(int pos, String s) {
		words.put(pos, s);
	}

	public int getWordsCount() {
		return words.size();
	}

	public ArrayList<Integer[]> getCombineIndexes() {
		ArrayList<Integer[]> combineIndexes = new ArrayList<Integer[]>();
		Collection<Integer> keys = new TreeSet<Integer>(words.keySet());
		LinkedList<Integer> akeys = new LinkedList<Integer>();
		for (Integer key : keys) {
			akeys.add(key);
		}
		String source = this.toString();
		outer: for (int i = 0; i < source.length(); i++) {
			if (i >= source.length())
				break outer;
			char c = source.charAt(i);
			List<String> words = searchWords(c);
			for (String word : words) {
				int length = word.length();
				if (length < 2)
					continue;
				int end = length + i;
				if (end > source.length())
					end = source.length();
				if (source.substring(i, end).equals(word)) {
					Integer[] combineIndex = new Integer[length];
					for (int j = 0; j < length; j++) {
						combineIndex[j] = akeys.get(i + j);
					}
					combineIndexes.add(combineIndex);
					i = i + word.length() - 1;
					break;
				}
			}
		}
		return combineIndexes;
	}

	private ArrayList<String> searchWords(Character c) {
		if (String.valueOf(c).equals("µ°")) {
			ArrayList<String> a = new ArrayList<String>();
			a.add("µ°´ò");
			return a;
		}
		return new ArrayList<String>();
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
