package org.ictclas4j.run;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.Segment;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Sep 22, 2009 12:40:58 PM
 */
public class Test3 {
	public static void main(String[] args) throws Exception {
		Segment segTag = Segment.getInstance(1);
		SegResult seg_res = segTag.split("종합送與您網站內容相關的廣告，讓您網站的收益潛能最大化");
		System.out.println("rawResult:" + seg_res.getFinalResult());
	}
}
