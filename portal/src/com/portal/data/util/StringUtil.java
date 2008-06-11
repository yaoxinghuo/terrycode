package com.portal.data.util;

import org.springframework.web.util.HtmlUtils;

/**
 * Developer: Terry DateTime : 2007-12-3 下午05:43:36
 */

public class StringUtil {
	public static String jsonTrim(String originalJsonString) {
		return originalJsonString.substring(1, originalJsonString.length() - 1)
				.replace("\\\"", "\"");
	}

	public static String filterString(String oStr) {
		return oStr.trim().replace("\n", "<br>").replace("\"", "“");
	}

	public static String filterHtml(String oStr) {
		return HtmlUtils.htmlEscape(oStr).replace(" ", "&nbsp;");
	}
}
