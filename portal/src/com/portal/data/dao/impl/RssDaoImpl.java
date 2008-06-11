package com.portal.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.data.dao.intf.IRssDao;

/**
 * Developer: Terry DateTime : 2007-12-7 下午02:46:27
 */

public class RssDaoImpl extends HibernateDaoSupport implements IRssDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object[]> getAvailableType3AndCount(final int type1,
			final int type2) {
		return (ArrayList<Object[]>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sql = new StringBuffer();
						sql.append("select r.type3,count(r) ");
						sql.append("from Rss as r ");
						sql.append("where r.type1=:type1 ");
						if (type2 != 0)
							sql.append(" and (r.type2=:type2 or r.type2=:orType2)");
						//sql.append(" and r.inuse=true");
						sql.append(" GROUP by r.type3");
						Query query = session.createQuery(sql.toString());
						query.setInteger("type1", type1);
						if (type2 != 0) {
							query.setInteger("type2", type2);
							query.setInteger("orType2", type1);
						}
						return query.list();
					}
				});
	}

}
