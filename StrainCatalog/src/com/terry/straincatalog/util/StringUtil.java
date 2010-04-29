package com.terry.straincatalog.util;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-4-29 下午05:20:57
 */
public class StringUtil {
	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.trim().equals(""))
			return true;
		return false;
	}
}
