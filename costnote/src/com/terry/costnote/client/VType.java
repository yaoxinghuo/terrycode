package com.terry.costnote.client;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 31, 2009 11:43:18 PM
 */
public enum VType {
	ALPHABET("^[a-zA-Z_]+$", "字母"), ALPHANUMERIC("^[a-zA-Z0-9_]+$", "字符"), NUMERIC(
			"^[+0-9]+$", "数字"), EMAIL(
			"^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$", "邮件"), MOBILE(
			"^1(([3][456789])|([5][01789])|([8][78]))[0-9]{8}$", "中国移动手机号码");
	String regex;
	String name;

	VType(String regex, String name) {
		this.regex = regex;
		this.name = name;
	}
}
