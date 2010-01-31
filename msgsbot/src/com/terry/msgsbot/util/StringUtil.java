package com.terry.msgsbot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 20, 2009 5:50:14 PM
 */
public class StringUtil {
	public static String unicodeEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		return unicodeBytes;
	}

	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	public static String HTMLToTEXT(String html) {
		// html=html.replaceAll("<([^<>]+)>","");
		// html=StringUtils.replace(html, "&nbsp;"," ");
		// html=StringUtils.replace(html, "&#160;"," ");
		// html=StringUtils.replace(html, "&lt;","<");
		// html=StringUtils.replace(html, "&gt;",">");
		// html=StringUtils.replace(html, "&quot;","\"");
		// html=StringUtils.replace(html, "&amp;","&");

		return html.replaceAll("<([^<>]+)>", "");

	}

	public static boolean isEmptyOrWhitespace(String s) {
		if (s == null || s.trim().equals(""))
			return true;
		return false;
	}

	public static boolean validateEmail(String email) {
		if (email == null)
			return false;
		Pattern p = Pattern
				.compile("^[\\w-]+(?:\\.[\\w-]+)*@(?:[\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean validateUrl(String url) {
		if (url == null || url.equals(""))
			return false;
		Pattern p = Pattern
				.compile(
						"(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?",
						Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);
		return matcher.matches();
	}

	public static void main(String[] args) {
		System.out.println(validateUrl("http://www.google.cn/a.txtd&s=123"));
	}
}
