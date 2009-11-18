package com.test;

import java.util.Set;

import org.ictclas4j.segment.Segment;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 18, 2009 4:31:24 PM
 */
public class Test4 {
	public static void main(String[] args) throws Exception {
		Segment segTag = Segment.getInstance(1);
		final Set<String> stopwords = segTag.getStopWordDictionary().words;
		for (String s : stopwords)
			System.out.println(s);
	}
}
