package org.ictclas4j.run;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.Segment;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 13, 2009 2:47:40 PM
 */
public class Test {
	public static void main(String[] args) {
		Segment segTag = new Segment(1);
		try {
			SegResult seg_res = segTag.split("我购买了道具和服装");
			String segString = seg_res.getFinalResult();
			System.out.println(segString);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
