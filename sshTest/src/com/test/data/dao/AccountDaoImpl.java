package com.test.data.dao;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.test.data.model.Account;

@Component("accountDao")
public class AccountDaoImpl implements AccountDaoIntf {
	@Autowired
	private SessionFactory sessionFactory;

	public Account loadAccount(String id) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class,
				id);
		// return (Account) super.getHibernateTemplate().get(Account.class, id);
	}

	public boolean addAccount(Account account) {
		try {
			sessionFactory.getCurrentSession().save(account);
			// super.getHibernateTemplate().save(account);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}
	
	@PostConstruct
	public void test(){
		System.out.println("**************Called...");
	}
}
