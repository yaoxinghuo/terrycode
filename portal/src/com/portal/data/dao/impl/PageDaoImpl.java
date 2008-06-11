package com.portal.data.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.data.dao.intf.IPageDao;
import com.portal.data.pojo.Page;

public class PageDaoImpl extends HibernateDaoSupport implements IPageDao {

	@Override
	public Page getPageByUsername(final String username) {
		return (Page) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return (Page) session.createQuery(
								"from Page p where p.username='" + username
										+ "'").uniqueResult();
					}
				});
	}

	@Override
	public boolean savePage(Page page) {
		try {
			super.getHibernateTemplate().update(page);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

}
