package com.microblog.data.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IAccountDao;
import com.microblog.data.model.Account;

public class AccountDaoImpl implements IAccountDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@Override
	public boolean deleteAccount(Account account) {
		try {
			sessionFactory.getCurrentSession().delete(account);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Account getAccountByEmail(String email) {
		return (Account) sessionFactory.getCurrentSession().createQuery(
				"from Account a where a.email='" + email + "'").uniqueResult();
	}

	@Override
	public Account getAccountById(String id) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class,
				id);
	}

	@Override
	public boolean updateAccount(Account account) {
		try {
			sessionFactory.getCurrentSession().update(account);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAccounts(int limit) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Account a order by a.rdate desc");
		if (limit != 0)
			query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public boolean saveAccount(Account account) {
		try {
			sessionFactory.getCurrentSession().save(account);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isRegisted(String s, String field) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(a) from Account a where a." + field + "=:s");
		query.setString("s", s);
		return (Long) query.uniqueResult() == 0 ? false : true;
	}

	@Override
	public Account getAccountByMsn(String msn) {
		return (Account) sessionFactory.getCurrentSession().createQuery(
				"from Account a where a.msn='" + msn + "'").uniqueResult();
	}

}
