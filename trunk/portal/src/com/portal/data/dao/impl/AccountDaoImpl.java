package com.portal.data.dao.impl;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.data.dao.intf.IAccountDao;
import com.portal.data.pojo.Account;

public class AccountDaoImpl extends HibernateDaoSupport implements IAccountDao {

	@Override
	public boolean deleteAccount(Account account) {
		try {
			super.getHibernateTemplate().delete(account);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public Account getAccountById(String id) {
		return (Account) super.getHibernateTemplate().get(Account.class, id);
	}

	@Override
	public Account getAccountByUsername(final String username) {
		return (Account) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return (Account) session.createQuery(
								"from Account a where a.username='" + username
										+ "'").uniqueResult();
					}
				});
	}

	@Override
	public boolean saveAccount(Account account) {
		try {
			super.getHibernateTemplate().saveOrUpdate(account);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}
}
