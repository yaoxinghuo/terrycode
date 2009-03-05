package com.microblog.data.dao.impl;

import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IRobotDao;
import com.microblog.data.model.Robot;

public class RobotDaoImpl implements IRobotDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Robot getRobotByAccount(String account) {
		return (Robot) sessionFactory.getCurrentSession().createQuery(
				"from Robot r where r.account='" + account + "'")
				.uniqueResult();
	}

	@Override
	public Robot getRobotById(String id) {
		return (Robot) sessionFactory.getCurrentSession().get(Robot.class, id);
	}

}
