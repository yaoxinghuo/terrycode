package com.terry.gfwout.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 11, 2009 10:39:57 PM
 */
public class GfwServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3212155045844726975L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String s = req.getRequestURI();
		if (s == null || s.trim().equals("") || s.trim().equals("/Gfwout.html")) {
			resp.sendRedirect("/Gfwout.html");
			return;
		}

		HttpURLConnection con = null;
		try {
			if (s.startsWith("/"))
				s = s.substring(1);
			if (!s.startsWith("http://") || !s.startsWith("https://"))
				s = "http://" + s;
			URL url = new URL(s);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			if (con.getResponseCode() == 200) {
				PrintWriter pw = resp.getWriter();
				resp.setContentType("text/html");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(con.getInputStream())); // 读取结果
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				reader.close();
				con.disconnect();
				pw.write(sb.toString());
				pw.flush();
				pw.close();
			} else {
				resp.sendRedirect("/Gfwout.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("/Gfwout.html");
		} finally {
			if (con != null)
				try {
					con.disconnect();
				} catch (Exception e) {
				}
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
