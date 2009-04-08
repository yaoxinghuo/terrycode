package com.terry.data.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Apr 8, 2009 11:20:36 PM
 */
public final class PMF {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PMF() {
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
