package org.ictclas4j.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create£ºAug 17, 2009 5:32:26 PM
 */
public class WhiteWordDictionary {
	public TreeMap<Integer, String> words = new TreeMap<Integer, String>();

	public TreeMap<Character, ArrayList<Integer>> indexes = new TreeMap<Character, ArrayList<Integer>>();

	static Logger logger = Logger.getLogger(WhiteWordDictionary.class);

	public WhiteWordDictionary() {
		init();
	}

	public WhiteWordDictionary(String filename) {
		init();
		boolean result = load(filename);
		logger.info("load result:" + result);
	}

	private void init() {
		words = new TreeMap<Integer, String>();
	}

	public ArrayList<String> searchWords(Character c) {
		ArrayList<Integer> index = indexes.get(c);
		if (index == null)
			return null;
		ArrayList<String> arr = new ArrayList<String>();
		for (Integer i : index) {
			arr.add(words.get(i));
		}
		return arr;
	}

	public boolean load(String filename) {
		File file = new File(filename);
		if (!file.canRead())
			return false;// fail while opening the file

		ArrayList<String> wwords = new ArrayList<String>();

		String line;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while ((line = br.readLine()) != null) {
				if (line.indexOf("//") != -1) {
					line = line.substring(0, line.indexOf("//"));
				}
				if (line.length() != 0)
					wwords.add(line.toLowerCase());
			}
			br.close();

			Collections.sort(wwords, new Comparator<String>() {

				public int compare(String arg0, String arg1) {
					int len0 = arg0.length();
					int len1 = arg1.length();
					if (len0 > len1)
						return -1;
					else if (len0 == len1)
						return 0;
					else
						return 1;
				}
			});

			for (int i = 0; i < wwords.size(); i++) {
				String value = wwords.get(i);
				if (value == null || value.equals(""))
					continue;
				words.put(i, value);

				// Create Indexes
				char c = value.charAt(value.length() - 1);
				ArrayList<Integer> index = indexes.get(c);
				if (index == null)
					index = new ArrayList<Integer>();
				index.add(i);
				indexes.put(c, index);
			}
		} catch (IOException e) {
			System.err.println("WARNING: cannot open stop words list!");
		}
		return true;
	}

	public static void main(String[] args) {
		WhiteWordDictionary dic = new WhiteWordDictionary("E:\\workspace\\ictclas4j\\data\\whitewords.txt");
		ArrayList<String> s = dic.searchWords("µ°".charAt(0));
		for(String ss:s){
			System.out.println(ss);
		}
	}
}
