package com.test;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.Segment;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 18, 2009 2:57:30 PM
 */
public class Test3 {
	public static void main(String[] args) {
		Segment segTag = Segment.getInstance(1);
		SegResult seg_res = segTag.split("条纹腰边休闲中裤面料适挺,宽松版 但是显瘦纹吊带裙子+白色T恤。");
		System.out.println("rawResult:"+seg_res.getFinalResult());
	}
}
