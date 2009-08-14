package org.ictclas4j.run;

import java.util.ArrayList;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.bean.WordResultBean;
import org.ictclas4j.segment.Segment;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 13, 2009 2:47:40 PM
 */
public class Test {
	public static void main(String[] args) {
		Segment segTag = Segment.getInstance(1);
		try {
			SegResult seg_res = segTag.split("今天的天氣好極了");
			ArrayList<WordResultBean> words = seg_res.getResult();
			for(WordResultBean word:words){
				System.out.println(word.getWord()+"/"+word.getProperty());
			}
			String segString = seg_res.getFinalResult();
			System.out.println("--------------");
			System.out.println(segString);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
