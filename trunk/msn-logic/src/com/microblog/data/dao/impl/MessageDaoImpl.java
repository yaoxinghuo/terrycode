package com.microblog.data.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IMessageDao;
import com.microblog.data.model.Account;
import com.microblog.data.model.Friend;
import com.microblog.data.model.Message;

public class MessageDaoImpl implements IMessageDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@Override
	public Message getMessageById(String id) {
		return (Message) sessionFactory.getCurrentSession().get(Message.class,
				id);
	}

	@Override
	public long getMessagesTotalCountByAccount(Account account) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Message m where m.account=:account");
		query.setEntity("account", account);
		return (Long) query.uniqueResult();
	}

	@Override
	public boolean deleteMessage(Message message) {
		try {
			sessionFactory.getCurrentSession().delete(message);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean saveMessage(Message message) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(message);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public long getMessagesTotalCountByDayAccount(Account account, Date uDate) {
		StringBuffer sb = new StringBuffer(
				"select count(*) from Message m where b.account=:account ");
		if (uDate != null)
			sb.append("and m.udate>=:date1 and m.udate<:date2");
		Query query = sessionFactory.getCurrentSession().createQuery(
				sb.toString());
		if (uDate != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(uDate);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			query.setTimestamp("date1", c.getTime());
			c.add(Calendar.DAY_OF_MONTH, 1);
			query.setDate("date2", c.getTime());
		}
		query.setEntity("account", account);
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getNewestMessages(int limit) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Message m order by m.udate desc");
		if (limit != 0)
			query.setMaxResults(limit);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessagesByAccount(Account account) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Message m where m.account=:account");
		query.setEntity("account", account);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getCommunityMessagesByDate(Date date, int field, int start, int limit) {
		StringBuffer sql = new StringBuffer("from Message m ");
		sql
				.append("where m.im<>3 and m.udate>=:date1 and m.udate<:date2 and m.limit.purview in(8,9,11,12) order by m.udate desc");
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
		query.setMaxResults(limit);
		query.setFirstResult(start);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getOwnMessagesByDate(Date date, int field,
			String accountid, int start, int limit) {
		StringBuffer sql = new StringBuffer("from Message m ");
		sql
				.append("where m.udate>=:date1 and m.udate<:date2 and m.account_id=:account_id and m.limit.purview=-1 and m.im<>3 order by m.udate desc");
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
		query.setString("account_id", accountid);
		query.setMaxResults(limit);
		query.setFirstResult(start);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getOurMessagesByDate(Date date, boolean forum,
			int field, int start, int limit) {
		StringBuffer sql = new StringBuffer("from Message m ");
		sql
				.append("where m.im<>3 and m.udate>=:date1 and m.udate<:date2 and m.limit.purview in(0,1,2,3)");
		sql.append(" and m.limit.forumid is ");
		if (forum)
			sql.append("not ");
		sql.append("null order by m.udate desc");
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
		query.setMaxResults(limit);
		query.setFirstResult(start);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getPublicMessagesByDayAndAccounts(Date date,
			int field, String accountid, List<Friend> friends, int start, int limit) {
		StringBuffer sql = new StringBuffer("from Message m ");
		sql.append("where m.im<>3 and m.udate>=:date1 and m.udate<:date2 ");
		sql.append("and m.limit.purview in(8,9,11,12) ");
		sql.append("and m.account_id in(");
		for (Friend friend : friends)
			sql.append("'").append(friend.getFriendid()).append("',");
		sql.append("'").append(accountid).append("') order by m.udate desc");
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
		query.setMaxResults(limit);
		query.setFirstResult(start);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getForumMessagesByDate(Date date, int field,
			String forumid, int start, int limit) {
		StringBuffer sql = new StringBuffer("from Message m ");
		sql
				.append("where m.im<>3 and m.udate>=:date1 and m.udate<:date2 and m.limit.purview in(0,1,2,3)");
		sql.append(" and m.limit.forumid=:forumid order by m.udate desc");
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
		query.setString("forumid", forumid);
		query.setMaxResults(limit);
		query.setFirstResult(start);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getOwnSubscribeMessagesByDate(Date date, int field,
			String accountid, String subscribeid, int start, int limit) {
		StringBuffer sql = new StringBuffer("from Message m ");
		sql
				.append("where m.udate>=:date1 and m.udate<:date2 and m.account_id=:account_id and m.limit.purview=-1 and m.limit.mto=:subscribeid and m.im=3 order by m.udate desc");
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
		query.setString("account_id", accountid);
		query.setString("subscribeid", subscribeid);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		return query.list();
	}
}
