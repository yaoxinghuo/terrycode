package com.terry.costnote.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionSupport;

public class GenericAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6076893161927391902L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected String getCurrentUserEmail() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser().getEmail();
	}

}
