package com.microblog.data.dao.intf;

import java.util.List;

import com.microblog.data.model.Account;

public interface IAccountDao {
	public Account getAccountById(String id);

	public Account getAccountByEmail(String email);

	public Account getAccountByMsn(String msn);

	public boolean updateAccount(Account account);

	public boolean saveAccount(Account account);

	public boolean deleteAccount(Account account);

	public List<Account> getAccounts(int limit);

	public boolean isRegisted(String s, String field);
}
