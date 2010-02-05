package com.terry.botmail.data.intf;

import com.terry.botmail.model.Account;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-5 上午08:56:49
 */
public interface IAccountDao {
	public boolean saveAccount(Account account);

	public Account getAccountByAccount(String a);
}
