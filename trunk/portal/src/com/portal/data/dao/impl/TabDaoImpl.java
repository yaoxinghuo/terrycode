package com.portal.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.data.dao.intf.ITabDao;
import com.portal.data.pojo.Tab;

public class TabDaoImpl extends HibernateDaoSupport implements ITabDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Tab> getTabsByUsername(final String username) {
		return (ArrayList<Tab>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session.createQuery(
								"from Tab as t where t.username='" + username
										+ "' order by t.position desc").list();

					}
				});
	}

	@Override
	public Tab getTabByUsernameAndPosition(final String username,
			final int position) {
		return (Tab) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session.createQuery(
								"from Tab as t where t.username='" + username
										+ "' and t.position=" + position)
								.uniqueResult();
					}
				});
	}

	@Override
	public void saveTab(Tab tab) {
		try {
			super.getHibernateTemplate().saveOrUpdate(tab);
		} catch (org.springframework.dao.DataAccessException e) {
		}
	}

}
