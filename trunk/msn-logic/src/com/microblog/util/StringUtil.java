package com.microblog.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	private static final NumberFormat nf1 = new DecimalFormat("+00;-00");
	private static final NumberFormat nf2 = new DecimalFormat("00");
	
	public static String changeFloatTimeZoneToString(float tzone) {
		return "GMT" + nf1.format(tzone) + ":"
				+ nf2.format(60 * (tzone - Math.floor(tzone)));
	}

	public static boolean validateContent(String content, int limit) {
		String str = content.replace("<label>", "").replace("</label>", "");
		Pattern p = Pattern.compile(
				"http://([\\w-]+\\.)+[\\w-]+(/[\\w-   ./?%&=]*)?",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(content);
		while (matcher.find()) {
			str = str.replace(matcher.group(), "");
		}
		try {
			if (str.getBytes("Unicode").length > (limit * 4))
				return false;
		} catch (UnsupportedEncodingException e) {
			return true;
		}
		return true;
	}

	public static String trimUrlContent(String content) {
		String str = content;
		Pattern p = Pattern.compile(
				"http://([\\w-]+\\.)+[\\w-]+(/[\\w-   ./?%&=]*)?",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(content);
		while (matcher.find()) {
			str = str.replace(matcher.group(), "");
		}
		return str;
	}

	public static String splitString(String s, int length) {
		s = s.replace("\\n", "").replace("\\t", "");
		String tmp = s;
		s = trimUrlContent(s);

		s = deleteImportantUnderlineSign(s);
		if (s.equals(""))
			s = tmp;
		byte[] bytes;
		try {
			bytes = s.getBytes("Unicode");
		} catch (UnsupportedEncodingException e) {
			return s;
		}
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始
		for (; i < bytes.length && n < length; i++) {
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1) {
				n++; // 在UCS2第二个字节时n加1
			} else {
				// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0) {
					n++;
				}
			}
		}
		// 如果i为奇数时，处理成偶数
		if (i % 2 == 1) {
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0)
				i = i - 1;
			// 该UCS2字符是字母或数字，则保留该字符
			else
				i = i + 1;
		}

		try {
			String cs = new String(bytes, 0, i, "Unicode");
			if (cs.length() != s.length())
				cs += "...";
			return cs;
		} catch (Exception e) {
			return s;
		}
	}

	public static String deleteImportantUnderlineSign(String s) {
		return s.replace("<label>", "【").replace("</label>", "】");
	}

	public static void main(String[] args) {
		System.out.println(splitString("你好你好你好", 2));
	}

}
