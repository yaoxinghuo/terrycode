package com.terry.appmail.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terry.appmail.util.MailSender;
import com.terry.appmail.util.StringUtil;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-5 下午05:50:27
 */
public class MailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6866194651424816490L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String email = req.getParameter("email");
		String sender = req.getParameter("sender");
		String subject = req.getParameter("subject");
		String body = req.getParameter("body");

		if (StringUtil.isEmptyOrWhitespace(email)
				|| StringUtil.isEmptyOrWhitespace(sender)
				|| StringUtil.isEmptyOrWhitespace(subject)
				|| StringUtil.isEmptyOrWhitespace(body)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Parameter: email,sender,subject,body can not be empty.");
			return;
		}
		if (!StringUtil.validateEmail(email)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Invaid email address.");
			return;
		}
		if (email.length() > 100 || sender.length() > 50
				|| subject.length() > 500 || body.length() > 1000) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Parameter: email,sender,subject,body too long.");
			return;
		}
		try {
			MailSender.sendMail(email, sender, subject, body);
			PrintWriter pw = resp.getWriter();
			pw.print("Mail sent to +" + email + ".");
			pw.flush();
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e
					.getMessage());
		}

	}

}
