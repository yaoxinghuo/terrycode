package com.portal.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.data.dao.intf.INewsDao;
import com.portal.data.pojo.RssData;
import com.portal.data.pojo.UserRssData;

/**
 * Developer: Terry DateTime : 2007-12-11 下午03:53:03
 */

public class NewsDaoImpl extends HibernateDaoSupport implements INewsDao {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RssData> getNewsByTypes(final int type1, final int type2,
			final int[] ex_type3, final int firstResult, final int maxResult) {
		return (ArrayList<RssData>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sql = new StringBuffer();
						sql.append("from RssData as r ");
						sql.append("where r.type1=:type1 ");
						if (type2 != 0
								&& !String.valueOf(type2).endsWith("000"))
							sql.append(" and r.type2=:type2 ");
						if (ex_type3.length != 0) {
							sql.append(" and r.type3 not in (");
							for (int i = 0; i < ex_type3.length; i++) {
								if (i != ex_type3.length - 1)
									sql.append(ex_type3[i]).append(", ");
								else
									sql.append(ex_type3[i]).append(" )");
							}
						}
						sql.append(" order by r.time desc ");
						Query query = session.createQuery(sql.toString());
						query.setInteger("type1", type1);
						if (type2 != 0
								&& !String.valueOf(type2).endsWith("000"))
							query.setInteger("type2", type2);
						query.setFirstResult(firstResult);
						query.setMaxResults(maxResult);
						return query.list();
					}
				});
	}

	@Override
	public long getCountNewsByTypes(final int type1, final int type2,
			final int[] ex_type3) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sql = new StringBuffer();
						sql.append("select count(r) from RssData as r ");
						sql.append("where r.type1=:type1 ");
						if (type2 != 0
								&& !String.valueOf(type2).endsWith("000"))
							sql.append(" and r.type2=:type2 ");
						if (ex_type3.length != 0) {
							sql.append(" and r.type3 not in (");
							for (int i = 0; i < ex_type3.length; i++) {
								if (i != ex_type3.length - 1)
									sql.append(ex_type3[i]).append(", ");
								else
									sql.append(ex_type3[i]).append(" )");
							}
						}
						Query query = session.createQuery(sql.toString());
						query.setInteger("type1", type1);
						if (type2 != 0
								&& !String.valueOf(type2).endsWith("000"))
							query.setInteger("type2", type2);
						return query.uniqueResult();
					}
				});
	}

	@Override
	public void saveUserRssData(ArrayList<UserRssData> userRssDatas) {
		HibernateTemplate ht = super.getHibernateTemplate();
		for (int i = 0; i < userRssDatas.size(); i++) {
			ht.save(userRssDatas.get(i));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserRssData> getUserRssNews(final String row_id,
			final int firstResult, final int maxResult) {
		return (ArrayList<UserRssData>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("from UserRssData as r where r.row_id=:row_id order by r.time desc");
						query.setString("row_id", row_id);
						query.setFirstResult(firstResult);
						query.setMaxResults(maxResult);
						return query.list();
					}
				});
	}

	@Override
	public long getCountUserRssNews(final String row_id) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("select count(r) from UserRssData as r where r.row_id=:row_id");
						query.setString("row_id", row_id);
						return query.uniqueResult();
					}
				});
	}

	@Override
	public void deleteUserRssDataByRowId(final String row_id) {
		super.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session
						.createQuery("delete from UserRssData as r where r.row_id=:row_id");
				query.setString("row_id", row_id);
				return query.executeUpdate();
			}
		});
	}

}
