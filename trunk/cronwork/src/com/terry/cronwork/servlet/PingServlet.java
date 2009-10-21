package com.terry.cronwork.servlet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Oct 21, 2009 10:21:13 AM
 */
public class PingServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -504418262258354464L;

	private static final int TIME_OUT = 30000;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String url = req.getParameter("url");
		if (url == null || url.equals(""))
			return;
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setConnectTimeout(TIME_OUT);
		connection.setReadTimeout(TIME_OUT);
		connection.connect();
		resp.getWriter().println(connection.getResponseCode());
		connection.disconnect();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}
}
