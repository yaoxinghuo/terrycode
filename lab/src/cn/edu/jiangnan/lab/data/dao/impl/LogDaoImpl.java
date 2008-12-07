package cn.edu.jiangnan.lab.data.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.jiangnan.lab.data.dao.intf.ILogDao;
import cn.edu.jiangnan.lab.data.pojo.Log;

public class LogDaoImpl extends HibernateDaoSupport implements ILogDao {

	@Override
	public boolean deleteLog(Log log) {
		try {
			super.getHibernateTemplate().delete(log);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public long getCountLogs(final int action, final Date startDate,
			final Date endDate, final String keyword, final String column) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Calendar c = Calendar.getInstance();
						c.setTime(endDate);
						c.add(Calendar.DAY_OF_MONTH, 1);
						StringBuffer sb = new StringBuffer(
								"select count(l) from Log l where l.input<:endDate");
						if (startDate != null)
							sb.append(" and l.input>:startDate");
						if (action != -1)
							sb.append(" and l.action=:action");
						if (!keyword.equals("")) {
							sb.append(" and l.").append(column);
							sb.append(" like :keyword");
						}
						Query query = session.createQuery(sb.toString());
						if (startDate != null)
							query.setDate("startDate", startDate);
						query.setDate("endDate", c.getTime());
						if (action != -1)
							query.setInteger("action", action);
						if (!keyword.equals(""))
							query.setString("keyword", "%" + keyword + "%");
						return query.uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Log> getLogs(final int start, final int limit,
			final int action, final Date startDate, final Date endDate,
			final String keyword, final String column) {
		return (ArrayList<Log>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Calendar c = Calendar.getInstance();
						c.setTime(endDate);
						c.add(Calendar.DAY_OF_MONTH, 1);
						StringBuffer sb = new StringBuffer(
								"from Log l where l.input<:endDate ");
						if (startDate != null)
							sb.append(" and l.input>:startDate");
						if (action != -1)
							sb.append("and l.action=:action ");
						if (!keyword.equals("")) {
							sb.append(" and l.").append(column);
							sb.append(" like :keyword");
						}
						sb.append(" order by l.input desc");
						Query query = session.createQuery(sb.toString());
						if (startDate != null)
							query.setDate("startDate", startDate);
						query.setDate("endDate", c.getTime());
						if (action != -1)
							query.setInteger("action", action);
						if (!keyword.equals(""))
							query.setString("keyword", "%" + keyword + "%");
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Log> getLogsByUserId(final int start, final int limit,
			final String user_id) {
		return (ArrayList<Log>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("from Log l where l.user_id='"
										+ user_id + "' order by l.input desc");
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@Override
	public long getCountLogsByUserId(final String user_id) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session.createQuery(
								"select count(l) from Log l where l.user_id='"
										+ user_id + "'").uniqueResult();
					}
				});
	}

	@Override
	public boolean saveLog(Log log) {
		try {
			super.getHibernateTemplate().saveOrUpdate(log);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public int logClean() {
		return (Integer) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						String sql = "delete from log where log.end<(now() - INTERVAL 1 MONTH)";
						return session.createSQLQuery(sql).executeUpdate();
					}
				});
	}

	@Override
	public Log getLogById(String id) {
		return (Log) super.getHibernateTemplate().get(Log.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Log> getRecentLogsByUserId(final int days,
			final String user_id) {
		return (ArrayList<Log>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("from Log l where l.user_id='"
										+ user_id + "' and l.start>:start");
						Calendar c = Calendar.getInstance();
						c.add(Calendar.DAY_OF_MONTH, days);
						query.setDate("start", c.getTime());
						return query.list();
					}
				});
	}

}
