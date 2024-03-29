package com.terry.costnote.data.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.costnote.data.dao.intf.IAccountDao;
import com.terry.costnote.data.model.Account;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:05:46 AM
 */

@Component("accountDao")
public class AccountDaoImpl implements IAccountDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean deleteAccount(Account account) {
		try {
			em.remove(account);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Account getAccountByEmail(String email) {
		Query query = em.createQuery("SELECT a FROM " + Account.class.getName()
				+ " a where a.email=:email");
		query.setParameter("email", email);
		Account account = null;
		try {
			account = (Account) query.getSingleResult();
		} catch (NoResultException e) {
		}
		return account;
	}

	@Override
	public Account getAccountById(String accountId) {
		Key key = KeyFactory.stringToKey(accountId);
		if (key == null || !key.isComplete())
			return null;

		return em.find(Account.class, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAccounts(int start, int limit) {
		Query query = em.createQuery("SELECT a FROM " + Account.class.getName()
				+ " a");
		query.setFirstResult(start);
		if (limit != 0)
			query.setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public boolean saveAccount(Account account) {
		try {
			em.persist(account);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}