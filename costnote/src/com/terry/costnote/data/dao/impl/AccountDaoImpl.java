package com.terry.costnote.data.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.costnote.data.dao.intf.IAccountDao;
import com.terry.costnote.data.model.Account;
import com.terry.costnote.data.model.Friend;
import com.terry.costnote.data.util.EMF;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:05:46 AM
 */

@Component("accountDao")
public class AccountDaoImpl implements IAccountDao {

	EntityManager em = EMF.get().createEntityManager();

	@Override
	public boolean deleteAccount(Account account) {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.remove(account);
			tx.commit();
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
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(account);
			tx.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteFriend(Friend friend) {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.remove(friend);
			tx.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Friend getFriendById(String friendId) {
		Key key = KeyFactory.stringToKey(friendId);
		if (key == null || !key.isComplete())
			return null;

		return em.find(Friend.class, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> getFriendsByEmail(String email) {
		Query query = em.createQuery("SELECT f FROM " + Friend.class.getName()
				+ " f where f.email=:email");
		query.setParameter("email", email);
		return query.getResultList();
	}

	@Override
	public boolean saveFriend(Friend friend) {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(friend);
			tx.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
