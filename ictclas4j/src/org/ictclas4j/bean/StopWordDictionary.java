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
 * @version create£ºAug 17, 2009 5:32:26 PM
 */
public class StopWordDictionary {
	public Set<String> words;

	static Logger logger = Logger.getLogger(StopWordDictionary.class);

	public StopWordDictionary() {
		init();
	}

	public StopWordDictionary(String filename) {
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
		HashSet<String> stopWords = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while ((line = br.readLine()) != null) {
				if (line.indexOf("//") != -1) {
					line = line.substring(0, line.indexOf("//"));
				}
				String[] parts = line.split("\\s");
				for (String part : parts)
					if (part.trim().length() != 0)
						stopWords.add(part.trim().toLowerCase());
			}
			br.close();
		} catch (IOException e) {
			System.err.println("WARNING: cannot open stop words list!");
		}
		if (isReset)
			words.clear();
		words = stopWords;
		return true;
	}

	public static void main(String[] args) {
		StopWordDictionary dic = new StopWordDictionary("E:\\workspace\\ictclas4j\\data\\stopwords.txt");
		for (String word : dic.words) {
			System.out.println(word);
		}
	}
}
