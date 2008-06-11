package com.portal.data.dao.intf;

import com.portal.data.pojo.Account;

public interface IAccountDao {
	public Account getAccountById(String id);

	public Account getAccountByUsername(String username);

	public boolean saveAccount(Account account);

	public boolean deleteAccount(Account account);

}
