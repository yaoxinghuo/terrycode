package org.ictclas4j.reprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.ictclas4j.segment.Segment;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Sep 7, 2009 10:11:59 PM
 */
public class SingleWords {
	static Logger logger = Logger.getLogger(SingleWords.class);

	private TreeMap<Integer, String> words = new TreeMap<Integer, String>();

	public void addWord(int pos, String s) {
		words.put(pos, s);
	}

	public int getWordsCount() {
		return words.size();
	}

	public ArrayList<Integer[]> getCombineIndexes() {
		Segment segment = Segment.getInstance();

		ArrayList<Integer[]> combineIndexes = new ArrayList<Integer[]>();
		Collection<Integer> keys = new TreeSet<Integer>(words.keySet());
		LinkedList<Integer> akeys = new LinkedList<Integer>();
		for (Integer key : keys) {
			akeys.add(key);
		}
		String source = this.toString();
		outer: for (int i = source.length() - 1; i > 0; i--) {
			if (i <= 0)
				break outer;
			char c = source.charAt(i);
			List<String> words = segment.getWhiteWordDictionary().searchWords(c);
			if (words == null)
				continue outer;
			for (String word : words) {
				int length = word.length();
				if (length < 2)
					continue;
				int start = i - length + 1;
				if (start < 0)
					continue;

				if (source.substring(start, i + 1).equals(word)) {
					logger.info("find '" + word + "' in white list, treat it as a word group(UNKNOW/un)");
					Integer[] combineIndex = new Integer[length];
					for (int j = 0; j < length; j++) {
						int key = i - length + j + 1;
						combineIndex[j] = akeys.get(key);
					}
					combineIndexes.add(combineIndex);
					i = i - word.length();
					break;
				}
			}
		}
		return combineIndexes;
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
