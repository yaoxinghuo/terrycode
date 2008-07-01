package com.test.data.dao;

import com.test.data.model.Account;

public interface AccountDaoIntf {
	public Account loadAccount(String id);

	public boolean addAccount(Account account);
}
