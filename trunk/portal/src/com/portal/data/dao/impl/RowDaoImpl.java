package com.portal.data.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.data.dao.intf.IRowDao;
import com.portal.data.pojo.Row;

/**
 * Developer: Terry DateTime : 2007-12-17 下午01:56:23
 */

public class RowDaoImpl extends HibernateDaoSupport implements IRowDao {

	@Override
	public boolean deleteRow(Row row) {
		try {
			super.getHibernateTemplate().delete(row);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public Row getRowById(String id) {
		return (Row) super.getHibernateTemplate().get(Row.class, id);
	}

	@Override
	public boolean saveRow(Row row) {
		try {
			super.getHibernateTemplate().save(row);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateRow(Row row) {
		try {
			super.getHibernateTemplate().update(row);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public String saveAndGetId(Row row) {
		try {
			super.getHibernateTemplate().save(row);
			return row.getId();
		} catch (org.springframework.dao.DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Row> getRowsMapByUsernameAndTab(
			final String username, final String tab_id) {
		ArrayList<Row> list = (ArrayList<Row>) super.getHibernateTemplate()
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session.createQuery(
								"from Row as r where r.username='" + username
								// + "' and r.tab_id='" + tab_id
										+ "'").list();

					}
				});
		HashMap<String, Row> map = new HashMap<String, Row>();
		for (int i = 0; i < list.size(); i++) {
			Row row = list.get(i);
			map.put(row.getId(), row);
		}
		return map;
	}
}
