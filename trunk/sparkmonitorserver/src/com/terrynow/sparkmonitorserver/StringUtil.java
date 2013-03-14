package com.terrynow.sparkmonitorserver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Jan 31, 2010 1:27:07 AM
 */
public class StringUtil {
	public static boolean isValidIp(String ipAddress) {
		String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}
	
	public static boolean isDigital(String s) {
		String pattern = "[0-9]+(.[0-9]+)?";
		// 对()的用法总结：将()中的表达式作为一个整体进行处理，必须满足他的整体结构才可以。
		// (.[0-9]+)? ：表示()中的整体出现一次或一次也不出现
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		return m.matches();
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
}
