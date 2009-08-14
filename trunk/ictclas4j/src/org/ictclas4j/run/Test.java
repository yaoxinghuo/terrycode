package org.ictclas4j.run;

import java.util.ArrayList;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.bean.WordResultBean;
import org.ictclas4j.segment.Segment;
import org.ictclas4j.utility.Chineses;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 13, 2009 2:47:40 PM
 */
public class Test {
	public static void main(String[] args) {
		Segment segTag = Segment.getInstance(1);
		try {
			StringBuffer sb = new StringBuffer("");
			String input = "我購買了道具和服裝";
			SegResult seg_res = segTag.split(input);
			ArrayList<WordResultBean> results = seg_res.getResult();
			for (WordResultBean result : results) {
				if (!input.contains(result.getWord()))
					sb.append(Chineses.toFan(result.getWord()));
				else
					sb.append(result.getWord());
				sb.append("/" + result.getProperty()).append(" ");
			}

			String segString = seg_res.getFinalResult();
			System.out.println(sb.toString());
			System.out.println("--------------");
			System.out.println(segString);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
