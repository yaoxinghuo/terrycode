package com.terry.costnote.data.service.intf;

import com.terry.costnote.data.model.Account;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 15, 2009 1:59:58 PM
 */
public interface IAccountService {
	public Account checkAccount(String email, String nickname);

	public boolean updateAccount(String email, String account);
}
