package org.ictclas4j.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 17, 2009 5:32:26 PM
 */
public class WhiteWordDictionary {
	/*
	 * 注意，虽然是白名单，如果在StopWord已经定义了，就算StopWord了
	 */
	public Set<String> words;

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
		words = new HashSet<String>();
	}

	public boolean load(String filename) {
		return load(filename, false);
	}

	public boolean load(String filename, boolean isReset) {
		File file = new File(filename);
		if (!file.canRead())
			return false;// fail while opening the file

		String line;
		HashSet<String> wwords = new HashSet<String>();
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
		} catch (IOException e) {
			System.err.println("WARNING: cannot open stop words list!");
		}
		if (isReset)
			words.clear();
		words = wwords;
		return true;
	}

	public boolean contains(String s) {
		if (words == null)
			return false;
		return words.contains(s);
	}

	public static void main(String[] args) {
		WhiteWordDictionary dic = new WhiteWordDictionary("E:\\workspace\\ictclas4j\\data\\whitewords.txt");
		for (String word : dic.words) {
			System.out.println(word);
		}
	}
}
