package com.terry.fbtest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookXmlRestClient;
import com.terry.fbtest.filter.FacebookUserFilter;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 8, 2009 11:43:36 PM
 */
public class FacebookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2630549593946233717L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// String id = req.getParameter("id");
		// String password = req.getParameter("password");
		String status = req.getParameter("status");

		FacebookXmlRestClient client = FacebookUserFilter.getUserClient(req
				.getSession());
		String message = "OK";
		try {
			if (!client.users_setStatus(status))
				message = "Set Status return fasle.";
		} catch (FacebookException e) {
			message = "Error:" + e.getMessage();
			e.printStackTrace();
		}
		PrintWriter pw = resp.getWriter();
		pw.write(message);
		pw.flush();
		pw.close();
		System.out.println("called...");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
