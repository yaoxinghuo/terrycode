package com.terrynow.sparkmonitorserver;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @date 2011-11-9 上午11:30:55
 */
public class ClientTest {
	//http://localhost:8182/spark?username=monitor&password=12345678&host=im.e104.com.cn&to=xinghuo.yao@spark.e104.com.cn&message=test
	public static void main(String[] args) {
		fetchToSendMsg("http://localhost:8182/spark", "im.e104.com.cn",
				"monitor", "12345678", "xinghuo.yao@spark.e104.com.cn",
				"今天的天气好极了");	
	}

	public static boolean fetchToSendMsg(String url, String host,
			String username, String password, String to, String message) {
		try {
			URL postUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			String content = "username=" + username + "&password=" + password
					+ "&host=" + host + "&to=" + URLEncoder.encode(to, "UTF-8")
					+ "&message=" + URLEncoder.encode(message, "UTF-8");
			out.writeBytes(content);

			out.flush();
			out.close();

			int responseCode = connection.getResponseCode();
			System.out.println("responceCode:" + responseCode);
			connection.disconnect();
			if (responseCode == 200) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("go exception: " + e.getMessage());
		}
		return false;
	}
}
