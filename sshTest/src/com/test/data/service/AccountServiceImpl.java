package com.test.data.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.data.dao.AccountDaoIntf;
import com.test.data.model.Account;

@Service("demo")
@Transactional(readOnly = true)
@Repository
public class AccountServiceImpl implements AccountServiceIntf {

	@Resource(name="accountDao")
	private AccountDaoIntf accountDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	public String getUsername(String id) {
		Account account = accountDao.loadAccount(id);
		return account.getUsername();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveAccounts(Account[] accounts) {
		for (Account account : accounts) {
			System.out.println(accountDao.addAccount(account));
		}
	}
}