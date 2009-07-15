package com.terry.costnote.auth;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.terry.costnote.constants.Constants;

public class AuthorityInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		String email = (String) session.get(Constants.SESSION_EMAIL);
		if (email != null)
			return invocation.invoke();
		return Action.INPUT;
	}
}
