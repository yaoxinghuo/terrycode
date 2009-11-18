package com.test;

import java.util.TreeMap;

import org.carrot2.util.tokenizer.parser.jflex.PreprocessedJFlexWordBasedParserBase;
import org.ictclas4j.bean.SegResult;
import org.ictclas4j.bean.WordResultBean;
import org.ictclas4j.reprocess.Reprocess;
import org.ictclas4j.segment.Segment;

import com.terry.chineses.Chineses;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 18, 2009 11:09:20 AM
 */
public class KellyWordSplit extends PreprocessedJFlexWordBasedParserBase {

	@Override
	public String preprocess(String input) {
		Segment segTag = Segment.getInstance(1);
		SegResult seg_res = segTag.split(input);

		TreeMap<Integer, WordResultBean> whiteFilterResult = Reprocess
				.whitePhraseFilterResults(seg_res.getPosResult());
		StringBuffer sb = new StringBuffer("");
		for (WordResultBean result : whiteFilterResult.values()) {
			if (result == null)
				continue;
			if (!input.contains(result.getWord()))
				sb.append(Chineses.toFan(result.getWord()));
			else
				sb.append(result.getWord());
			sb.append(" ");
		}
		return sb.toString().trim();
	}

}
