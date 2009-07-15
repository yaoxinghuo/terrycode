package com.terry.costnote.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionSupport;

public class GenericAction extends ActionSupport implements SessionAware,
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6076893161927391902L;
	protected Map<Object, Object> sessionMap;

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	@SuppressWarnings("unchecked")
	@Override
	public void setSession(Map session) {
		sessionMap = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected String getCurrentUserEmail() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser().getEmail();
	}

}
