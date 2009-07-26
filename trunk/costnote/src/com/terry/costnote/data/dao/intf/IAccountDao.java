package com.terry.costnote.data.dao.intf;

import java.util.List;

import com.terry.costnote.data.model.Account;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:03:27 AM
 */
public interface IAccountDao {
	public boolean saveAccount(Account account);

	public boolean deleteAccount(Account account);

	public Account getAccountById(String accountId);

	public Account getAccountByEmail(String email);

	public List<Account> getAccounts(int start, int limit);

}
