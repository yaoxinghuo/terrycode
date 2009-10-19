package org.ictclas4j.run;

import java.util.TreeMap;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.bean.WordResultBean;
import org.ictclas4j.segment.Segment;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Sep 22, 2009 12:40:58 PM
 */
public class Test3 {
	public static void main(String[] args) {
		Segment segTag = Segment.getInstance(1);
		SegResult seg_res = segTag.split("第一件T恤");
		System.out.println("Raw Result:\n" + seg_res.getFinalResult());
		TreeMap<Integer, WordResultBean> results = seg_res.getPosResult();
		StringBuffer sb = new StringBuffer("");
		for (WordResultBean result : results.values()) {
			if (result == null)
				continue;
			sb.append(result.getWord());
			sb.append("/" + result.getProperty());

			if (result.isStopWord())
				sb.append("[s]");
			sb.append(" ");
		}
		System.out.println("Optimized Results:\n" + sb);
	}
}
