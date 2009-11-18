package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 17, 2009 3:48:34 PM
 */
public class Test2 {
	public static void main(String[] args) {
		Pattern sp = Pattern.compile("<(div class=\"s\")[^>]*>(.*?)<\\/div>");
		Matcher matcher = sp.matcher(getGoogleSearchResult());
		while (matcher.find()) {
			System.out.println(matcher.group(2));
			System.out.println("----------");
		}
	}

//	public static String[][] splitByReg(String str, String regExp) {
//		String[][] resultList = null;
//		Pattern sp = Pattern.compile("<(div class=\"s\")[^>]*>(.*?)<\\/div>");
//		Matcher matcher = sp.matcher(getGoogleSearchResult());
//		while (matcher.find()) {
//			System.out.println(matcher.group(2));
//			System.out.println("----------");
//		}
//
//	}

	public static String getGoogleSearchResult() {
		try {
			String s = "http://www.google.com/search?as_q=phone&num=100&hl=en&newwindow=1&btnG=Google+Search&as_epq=&as_oq=&as_eq=&lr=&as_ft=i&as_filetype=&as_qdr=all&as_nlo=&as_nhi=&as_occt=any&as_dt=i&as_sitesearch=&as_rights=&safe=images";
			URL url = new URL(s);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "IIC2.0/PC 2.1.0.0");
			con.setRequestProperty("connection", "Close");
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "GBK"));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			con.disconnect();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
