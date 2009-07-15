package com.terry.costnote.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 15, 2009 10:47:04 AM
 */
public class CheckSMSServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7095421214722771567L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		try {
			URL postUrl = new URL(
					"https://fetionlib.appspot.com/restlet/fetion");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			// Read from the connection. Default is true.
			// connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			String content = "mobile=13916416465&password=1qaz2wsx&friend=13916416465&message="
					+ URLEncoder.encode("App Engine Costnote is alive.",
							"utf-8");
			out.writeBytes(content);

			out.flush();
			out.close();

			connection.getResponseCode();

			connection.disconnect();
		} catch (Exception e) {
			// Ignore
		}
	}
}
