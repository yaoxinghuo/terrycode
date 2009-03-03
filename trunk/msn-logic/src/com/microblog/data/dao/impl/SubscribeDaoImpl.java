package com.microblog.data.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.ISubscribeDao;
import com.microblog.data.model.Subscribe;

public class SubscribeDaoImpl implements ISubscribeDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Subscribe> getSubscribesByAccountId(String accountid) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Subscribe s where s.account_id=:account_id");
		query.setString("account_id", accountid);
		return query.list();
	}

	@Override
	public Subscribe getSubscribeByAccountIdAndMessageId(String accountid,
			String messageid) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Subscribe s where s.account_id=:account_id and s.messageid=:messageid and s.traceuser=:traceuser");
		query.setString("account_id", accountid);
		query.setString("messageid", messageid);
		query.setBoolean("traceuser", false);
		return (Subscribe) query.uniqueResult();
	}

	@Override
	public Subscribe getSubscribeByAccountIdAndUserId(String accountid,
			String userid) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Subscribe s where s.account_id=:account_id and s.userid=:userid and s.traceuser=:traceuser");
		query.setString("account_id", accountid);
		query.setString("userid", userid);
		query.setBoolean("traceuser", true);
		return (Subscribe) query.uniqueResult();
	}

	@Override
	public boolean saveSubscribe(Subscribe subscribe) {
		try {
			sessionFactory.getCurrentSession().save(subscribe);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void deleteSubscribesByMessageId(String messageid) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"delete from Subscribe s where s.messageid=:messageid");
		query.setString("messageid", messageid);
		query.executeUpdate();
	}

	@Override
	public boolean deleteSubscribe(Subscribe subscribe) {
		try {
			sessionFactory.getCurrentSession().delete(subscribe);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subscribe> getSubscribesByAccountIdAndType(String accountid,
			int type) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Subscribe s where s.account_id=:account_id and s.type=:type");
		query.setString("account_id", accountid);
		query.setInteger("type", type);
		return query.list();
	}

}
