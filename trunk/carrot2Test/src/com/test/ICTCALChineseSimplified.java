package com.test;

import org.carrot2.core.linguistic.EmptyStemmer;
import org.carrot2.core.linguistic.LanguageTokenizer;
import org.carrot2.core.linguistic.Stemmer;
import org.carrot2.util.tokenizer.languages.StemmedLanguageBase;
import org.ictclas4j.segment.Segment;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 18, 2009 11:14:39 AM
 */
public class ICTCALChineseSimplified extends StemmedLanguageBase {

	/**
	 * Public constructor.
	 */
	public ICTCALChineseSimplified() {
		Segment segTag = Segment.getInstance(1);
		super.setStopwords(segTag.getStopWordDictionary().words);
	}

	/**
	 * Creates a new instance of a {@link LanguageTokenizer} for this language.
	 * 
	 * @see org.carrot2.util.tokenizer.languages.StemmedLanguageBase#createTokenizerInstanceInternal()
	 */
	@Override
	protected LanguageTokenizer createTokenizerInstanceInternal() {
		return ICTCALWordBasedParserFactory.ChineseSimplified.borrowParser();
	}

	/**
	 * @return Language code: <code>pl</code>
	 * @see org.carrot2.core.linguistic.Language#getIsoCode()
	 */
	public String getIsoCode() {
		return "zh-cn";
	}

	@Override
	protected Stemmer createStemmerInstance() {
		return EmptyStemmer.INSTANCE;
	}
}
