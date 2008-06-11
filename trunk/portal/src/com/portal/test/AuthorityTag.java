package com.portal.test;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class AuthorityTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doEndTag() throws JspException {
		HttpSession session = pageContext.getSession();
		if (session.getAttribute("SESSION_USER") != null)
			return EVAL_PAGE;
		HttpServletResponse resp = (HttpServletResponse) pageContext
				.getResponse();
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		try {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_PAGE;
	}
}
