package com.microblog.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IReplyDao;
import com.microblog.data.model.Message;
import com.microblog.data.model.Reply;

public class ReplyDaoImpl implements IReplyDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@Override
	public boolean deleteReply(Reply reply) {
		try {
			sessionFactory.getCurrentSession().delete(reply);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Reply getReplyById(String id) {
		return (Reply) sessionFactory.getCurrentSession().get(Reply.class, id);
	}

	@Override
	public boolean saveReply(Reply reply) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(reply);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Reply> getReplysByMessage(Message message) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Reply r where r.message=:message order by r.inputdate desc");
		query.setEntity("message", message);
		return (ArrayList<Reply>) query.list();
	}

}
