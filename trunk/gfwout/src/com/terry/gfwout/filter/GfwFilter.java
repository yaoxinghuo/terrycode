package com.terry.gfwout.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 20, 2009 12:13:47 PM
 */
public class GfwFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		String uri = ((HttpServletRequest) arg0).getRequestURI();
		if (isAvaliable(uri))
			arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private boolean isAvaliable(String uri) {
		if (uri == null || uri.equals(""))
			return false;
		String[] as = { "/", "/router", "/gfw", "/index.jsp" };
		for (String a : as) {
			if (uri.equals(a))
				return true;
		}
		return false;
	}

}
