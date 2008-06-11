package com.portal.dwr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.WebContextFactory;

import com.portal.data.service.comm.Constants;
import com.portal.data.service.intf.IAccountService;

public class Account {
	private IAccountService accountService;

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public boolean login(String username, String password, boolean saveCookie) {
		HttpServletRequest request = WebContextFactory.get()
				.getHttpServletRequest();
		HttpServletResponse response = WebContextFactory.get()
				.getHttpServletResponse();
		if (accountService.login(username, password, saveCookie, request,
				response)) {
			WebContextFactory.get().getSession().setAttribute(
					Constants.SESSION_USERNAME, username);
			return true;
		} else
			return false;
	}

	public boolean cookieLogin() {
		HttpServletRequest request = WebContextFactory.get()
				.getHttpServletRequest();
		HttpServletResponse response = WebContextFactory.get()
				.getHttpServletResponse();
		return accountService.cookieLogin(request, response);
	}

	public void logout() {
		accountService.clearCookie(WebContextFactory.get()
				.getHttpServletResponse());
		WebContextFactory.get().getSession().invalidate();
	}

	/*
	 * 测试，是Session失效，正式要删除
	 */
	public void test() throws Exception {
		WebContextFactory.get().getSession().invalidate();
	}
}
