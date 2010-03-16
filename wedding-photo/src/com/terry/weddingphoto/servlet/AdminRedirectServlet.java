package com.terry.weddingphoto.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.terry.weddingphoto.constants.Constants;
import com.terry.weddingphoto.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-16 下午05:31:25
 */
public class AdminRedirectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1854946647850514930L;

	private UserService userService = UserServiceFactory.getUserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute(Constants.SESSION_NAME, userService
				.getCurrentUser().getEmail());
		String url = req.getParameter("url");
		if (!StringUtil.isEmptyOrWhitespace(url)) {
			resp.sendRedirect(url);
		}
	}
}
