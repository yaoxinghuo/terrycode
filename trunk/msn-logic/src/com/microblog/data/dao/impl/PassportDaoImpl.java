package com.microblog.data.dao.impl;

import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IPassportDao;
import com.microblog.data.model.Passport;

public class PassportDaoImpl implements IPassportDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@Override
	public Passport getPassportByEmail(String email) {
		return (Passport) sessionFactory.getCurrentSession().createQuery(
				"from Passport p where p.email='" + email + "'").uniqueResult();
	}

	@Override
	public Passport getPassportByAccountId(String account_id) {
		return (Passport) sessionFactory.getCurrentSession().createQuery(
				"from Passport p where p.account_id='" + account_id + "'")
				.uniqueResult();
	}

	@Override
	public boolean updatePassport(Passport passport) {
		try {
			sessionFactory.getCurrentSession().update(passport);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
