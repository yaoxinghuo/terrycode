package com.portal.data.service.comm;

/**
 * Developer: Terry 
 * DateTime : 2007-12-18 下午04:54:58
 */

public class Constants {
	// 保存cookie时的cookieName
	public final static String cookieDomainName = "portal";
	// 加密cookie时的网站自定码
	public final static String webKey = "portal";
	// 设置cookie有效期是两个星期，根据需要自定义
	public final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;
	
	public static final String SESSION_USERNAME="_username";
}
