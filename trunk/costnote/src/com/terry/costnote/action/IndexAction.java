package com.terry.costnote.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.terry.costnote.constants.Constants;
import com.terry.costnote.data.model.Account;
import com.terry.costnote.data.service.intf.IAccountService;
import com.terry.costnote.data.service.intf.ICostService;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 15, 2009 1:50:25 PM
 */

@Scope("prototype")
@Component("indexAction")
public class IndexAction extends GenericAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3142309910197671075L;

	@SuppressWarnings("unused")
	@Autowired
	private ICostService costService;

	@Autowired
	private IAccountService accountService;

	private String nickname;

	@Override
	public String execute() {
		HttpSession session = request.getSession();
		UserService userService = UserServiceFactory.getUserService();
		if (!userService.isUserLoggedIn())
			return INPUT;
		User user = userService.getCurrentUser();
		Account account = accountService.checkAccount(user.getEmail(), user
				.getNickname());
		if (account == null)
			return INPUT;
		nickname = account.getNickname();
		session.setAttribute(Constants.SESSION_EMAIL, account.getEmail());
		return SUCCESS;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}
}
