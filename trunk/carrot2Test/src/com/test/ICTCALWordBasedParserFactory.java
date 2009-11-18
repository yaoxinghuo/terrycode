package com.test;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.SoftReferenceObjectPool;
import org.carrot2.util.tokenizer.parser.WordBasedParserBase;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 18, 2009 11:10:45 AM
 */
public class ICTCALWordBasedParserFactory {
	/** Chinese tokenizer factory */
	public static final ICTCALWordBasedParserFactory ChineseSimplified = new KellyICTCALWordBasedParserFactory();

	/** Parser pool */
	protected ObjectPool parserPool;

	/** No public constructor */
	private ICTCALWordBasedParserFactory() {
		// No public constructor
	}

	public WordBasedParserBase borrowParser() {
		try {
			return (WordBasedParserBase) parserPool.borrowObject();
		} catch (Exception e) {
			throw new RuntimeException("Cannot borrow a parser", e);
		}
	}

	/**
	 * @param parser
	 */
	public void returnParser(WordBasedParserBase parser) {
		try {
			parserPool.returnObject(parser);
		} catch (Exception e) {
			throw new RuntimeException("Cannot return a parser", e);
		}
	}

	/**
	 * @author Stanislaw Osinski
	 * @version $Revision: 2122 $
	 */
	private static class KellyICTCALWordBasedParserFactory extends
			ICTCALWordBasedParserFactory {
		public KellyICTCALWordBasedParserFactory() {
			parserPool = new SoftReferenceObjectPool(
					new BasePoolableObjectFactory() {
						@Override
						public Object makeObject() throws Exception {
							return new KellyWordSplit();
						}
					});
		}
	}
}
