package com.terry.costnote.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.terry.costnote.constants.Constants;

public class AuthorityTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2427686167406007168L;

	@Override
	public int doEndTag() throws JspException {
		HttpSession session = pageContext.getSession();
		if (session.getAttribute(Constants.SESSION_EMAIL) != null)
			return EVAL_PAGE;
		HttpServletResponse resp = (HttpServletResponse) pageContext
				.getResponse();
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		try {
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		} catch (Exception e) {
			throw new JspException(e);
		}
		return SKIP_PAGE;
	}
}