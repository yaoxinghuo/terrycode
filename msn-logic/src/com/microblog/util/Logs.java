package com.microblog.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Logs {
	private static Log log;

	static {
		/*
		 * 这样有一个不好，就是打印日志的时候，不知道是哪个Java文件打印出来的，总是会显示"Logs"
		 */
		log = LogFactory.getLog(Logs.class);
	}

	public static Log getLogger() {
		return log;
	}
}
