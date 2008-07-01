package com.test.data.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.test.data.model.Account;

public class AccountDaoImpl extends HibernateDaoSupport implements
		AccountDaoIntf {
	public Account loadAccount(String id) {
		return (Account) super.getHibernateTemplate().get(Account.class, id);
	}

	public boolean addAccount(Account account) {
		try {
			super.getHibernateTemplate().save(account);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}
}
