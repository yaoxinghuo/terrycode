package com.microblog.data.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IHeadDao;
import com.microblog.data.model.Head;

public class HeadDaoImpl implements IHeadDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@Override
	public Head getHeadById(String headid) {
		return (Head) sessionFactory.getCurrentSession()
				.get(Head.class, headid);

	}

	@Override
	public boolean saveHead(Head head) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(head);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Head> getHeadsByDateAndAccountId(Date date, int field,
			String account_id) {
		StringBuffer sql = new StringBuffer("from Head h ");
		sql
				.append("where h.inputdate>=:date1 and h.inputdate<:date2 and h.account_id=:account_id");
		Query query = sessionFactory.getCurrentSession().createQuery(
				sql.toString());
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		Calendar c = Calendar.getInstance();
		int[] fields = { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,
				Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND };

		for (int f : fields) {
			if (field >= f)
				c.set(f, d.get(f));
			else
				c.set(f, f == Calendar.DAY_OF_MONTH ? 1 : 0);
		}
		query.setTimestamp("date1", c.getTime());
		c.add(field, 1);
		query.setDate("date2", c.getTime());
		query.setString("account_id", account_id);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Head> getNewestHeads(int limit) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select distinct h.account_id,h from Head h group by h.account_id order by h.inputdate desc");
		query.setMaxResults(limit);
		Iterator it = query.iterate();
		List<Head> heads = new ArrayList<Head>();
		while (it.hasNext()) {
			Object[] result = (Object[]) it.next();
			heads.add((Head) result[1]);
		}
		return heads;
	}

}
