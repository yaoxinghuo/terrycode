package com.test;

import java.util.HashMap;
import java.util.Map;

import org.carrot2.core.linguistic.Language;
import org.carrot2.filter.lingo.local.LingoLocalFilterComponent;
import org.carrot2.util.tokenizer.languages.english.English;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 18, 2009 11:16:41 AM
 */
public class ICTCALLingoLocalFilterComponent extends LingoLocalFilterComponent {
	public ICTCALLingoLocalFilterComponent() {
		super(new Language[] { new English(), new ICTCALChineseSimplified() },
				new ICTCALChineseSimplified(), new HashMap<String, String>());
	}

	public ICTCALLingoLocalFilterComponent(Map<String, String> parameters) {
		super(new Language[] { new English(), new ICTCALChineseSimplified() },
				new ICTCALChineseSimplified(), parameters);
	}
}
