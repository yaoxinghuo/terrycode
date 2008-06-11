package com.portal.data.dao.impl;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.data.dao.intf.IPassportDao;
import com.portal.data.pojo.Passport;

public class PassportDaoImpl extends HibernateDaoSupport implements
		IPassportDao {
	@Override
	public boolean deletePassport(Passport passport) {
		try {
			super.getHibernateTemplate().delete(passport);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public Passport getPassportById(String id) {
		return (Passport) super.getHibernateTemplate().get(Passport.class, id);
	}

	@Override
	public Passport getPassportByUsername(final String username) {
		return (Passport) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return (Passport) session.createQuery(
								"from Passport p where p.username='" + username
										+ "'").uniqueResult();
					}
				});
	}

	@Override
	public boolean savePassport(Passport passport) {
		try {
			super.getHibernateTemplate().saveOrUpdate(passport);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}
}
