package com.portal.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.data.dao.intf.IMessageDao;
import com.portal.data.pojo.Message;

/**
 * Developer: Terry DateTime : 2008-1-24 下午12:05:32
 */

public class MessageDaoImpl extends HibernateDaoSupport implements IMessageDao {

	@Override
	public boolean deleteMessage(Message message) {
		try {
			super.getHibernateTemplate().delete(message);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Message> getMessagesByType(final int type) {
		return (ArrayList<Message>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session
								.createQuery(
										"from Message as m where (m.type="
												+ type
												+ " or m.type=0) and inuse=1 order by m.inputDate")
								.list();
					}
				});
	}

	@Override
	public boolean saveMessage(Message message) {
		try {
			super.getHibernateTemplate().save(message);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

}
