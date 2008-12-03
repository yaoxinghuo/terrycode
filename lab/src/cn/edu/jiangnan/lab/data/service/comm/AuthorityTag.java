package cn.edu.jiangnan.lab.data.service.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class AuthorityTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2427686167406007168L;

	@Override
	public int doEndTag() throws JspException {
		HttpSession session = pageContext.getSession();
		if (session.getAttribute(Constants.SESSION_ADMIN_ID) != null)
			return EVAL_PAGE;
		HttpServletResponse resp = (HttpServletResponse) pageContext
				.getResponse();
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		try {
			req.setAttribute(Constants.REQUEST_TIP, "<font color=red>请先登录(您未登录或长时间未操作已退出登录)</font>");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} catch (Exception e) {
			throw new JspException(e);
		}
		return SKIP_PAGE;
	}
}
