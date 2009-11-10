package com.terry.cronwork.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Oct 22, 2009 11:22:17 AM
 */
public class FetionLibPingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595534483186456601L;

	private static final int TIME_OUT = 30000;
//	private static boolean b = true;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/plain");
		String responseCode;
//		if (b)
			responseCode = "pingRpcCall: " + pingRpcCall();
//		else
//			responseCode = "pingRestlet: " + pingRestlet();
//		b = !b;
		try {
			resp.getWriter().println(responseCode);
		} catch (IOException e) {
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}

	@SuppressWarnings("unused")
	private int pingRestlet() {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(
					"https://fetionlib.appspot.com/restlet/ping")
					.openConnection();
			connection.setConnectTimeout(TIME_OUT);
			connection.setReadTimeout(TIME_OUT);
			connection.connect();
			int responseCode = connection.getResponseCode();
			connection.disconnect();
			return responseCode;
		} catch (MalformedURLException e) {
			return 500;
		} catch (IOException e) {
			return 500;
		}
	}

	private int pingRpcCall() {
		try {
			URL postUrl = new URL("https://fetionlib.appspot.com/fetion/fetion");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setConnectTimeout(TIME_OUT);
			connection.setReadTimeout(TIME_OUT);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"text/x-gwt-rpc; charset=utf-8");
			connection.setRequestProperty("Pragma", "no-cache");
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			String content = "5|0|4|https://fetionlib.appspot.com/fetion/|63205A28DA3BFFF2B3649BF23A0DEFD1|com.terry.fetionlib.client.FetionService|ping|1|2|3|4|0|";
			out.writeBytes(content);

			out.flush();
			out.close();

			int responseCode = connection.getResponseCode();
			connection.disconnect();
			return responseCode;
		} catch (MalformedURLException e) {
			return 500;
		} catch (ProtocolException e) {
			return 500;
		} catch (IOException e) {
			return 500;
		}
	}
}
