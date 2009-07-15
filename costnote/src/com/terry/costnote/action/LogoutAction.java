package com.terry.costnote.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 15, 2009 3:43:09 PM
 */

@Scope("prototype")
@Component("logoutAction")
public class LogoutAction extends GenericAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4332688502974603372L;

	private String logoutURL;

	@Override
	public String execute() throws Exception {
		request.getSession().invalidate();
		UserService userService = UserServiceFactory.getUserService();
		logoutURL = userService.createLogoutURL("index.action");
		return null;
	}

	public void setLogoutURL(String logoutURL) {
		this.logoutURL = logoutURL;
	}

	public String getLogoutURL() {
		return logoutURL;
	}

}
