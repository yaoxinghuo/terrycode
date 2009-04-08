package cn.edu.jiangnan.lab.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.jiangnan.lab.data.dao.intf.IFeedbackDao;
import cn.edu.jiangnan.lab.data.pojo.Feedback;

public class FeedbackDaoImpl extends HibernateDaoSupport implements
		IFeedbackDao {

	@Override
	public boolean deleteFeedback(Feedback feedback) {
		try {
			super.getHibernateTemplate().delete(feedback);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public Feedback getFeedbackById(String id) {
		return (Feedback) super.getHibernateTemplate().get(Feedback.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Feedback> getFeedbacks(final int start, final int limit,
			final boolean pub) {
		return (ArrayList<Feedback>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("from Feedback f");
						if (pub)
							sb.append(" where f.pub is true");
						else
							sb.append(" where f.pub is false");
						sb.append(" order by f.input desc");
						Query query = session.createQuery(sb.toString());
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@Override
	public long getCountFeedbacks(final boolean pub) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("select count(f) from Feedback f");
						if (pub)
							sb.append(" where f.pub is true");
						else
							sb.append(" where f.pub is false");
						return session.createQuery(sb.toString())
								.uniqueResult();
					}
				});
	}

	@Override
	public boolean saveFeedback(Feedback feedback) {
		try {
			super.getHibernateTemplate().saveOrUpdate(feedback);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

}
