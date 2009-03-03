package com.microblog.data.dao.impl;

import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IUrlDao;
import com.microblog.data.model.Url;

public class UrlDaoImpl implements IUrlDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean deleteUrl(Url url) {
		try {
			sessionFactory.getCurrentSession().delete(url);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Url getUrlByHash(String hash) {
		return (Url) sessionFactory.getCurrentSession().createQuery(
				"from Url u where u.hash='" + hash + "'").uniqueResult();
	}

	@Override
	public Url getUrlByUrl(String url) {
		return (Url) sessionFactory.getCurrentSession().createQuery(
				"from Url u where u.url='" + url + "'").uniqueResult();
	}

	@Override
	public Url getUrlById(String id) {
		return (Url) sessionFactory.getCurrentSession().get(Url.class, id);
	}

	@Override
	public boolean saveUrl(Url url) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(url);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
