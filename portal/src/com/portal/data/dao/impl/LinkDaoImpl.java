package com.portal.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.data.dao.intf.ILinkDao;
import com.portal.data.pojo.UserLink;

/**
 * Developer: Terry DateTime : 2008-1-14 下午02:12:52
 */

public class LinkDaoImpl extends HibernateDaoSupport implements ILinkDao {

	@Override
	public boolean deleteLink(UserLink link) {
		try {
			super.getHibernateTemplate().delete(link);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public UserLink getLinkById(String id) {
		return (UserLink) super.getHibernateTemplate().get(UserLink.class, id);
	}

	@Override
	public boolean saveLink(UserLink link) {
		try {
			super.getHibernateTemplate().save(link);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateLink(UserLink link) {
		try {
			super.getHibernateTemplate().update(link);
		} catch (org.springframework.dao.DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserLink> getLinksByRowId(final String id) {
		return (ArrayList<UserLink>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session.createQuery(
								"from UserLink as l where row_id='" + id
										+ "' order by l.position").list();
					}
				});
	}

	@Override
	public void deleteUserLinksByRowId(final String row_id) {
		super.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session
						.createQuery("delete from UserLink as u where u.row_id=:row_id");
				query.setString("row_id", row_id);
				return query.executeUpdate();
			}
		});
	}
}
