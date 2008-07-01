package com.test.data.service;

import com.test.data.model.Account;

public interface AccountServiceIntf {
	public String getUsername(String id);

	public void saveAccounts(Account[] accounts);
}
