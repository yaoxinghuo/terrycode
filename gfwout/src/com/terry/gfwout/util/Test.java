package com.terry.gfwout.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 18, 2009 5:36:59 PM
 */
public class Test {
	public static void main(String[] args) throws Exception {
		String s = "http://www.baidu.cn";
		URL url = new URL(s);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("GET");
		String contentType = con.getContentType();
		if (contentType == null)
			contentType = "text/html; charset=GBK";
		System.out.println(contentType);
		BufferedReader reader = new BufferedReader(new InputStreamReader(con
				.getInputStream(), "GBK")); // 读取结果
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		con.disconnect();
		System.out.println(sb.toString());
	}
}
