package com.microblog.data.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IGroupDao;
import com.microblog.data.model.Group;

public class GroupDaoImpl implements IGroupDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroupsByAccountId(String account_id,
			boolean includeForum) {
		StringBuffer sql = new StringBuffer(
				"from Group g where g.account_id=:account_id ");
		if (!includeForum)
			sql.append("and g.type!=2 ");
		sql.append("order by g.type");
		Query query = sessionFactory.getCurrentSession().createQuery(
				sql.toString());
		query.setString("account_id", account_id);
		return query.list();
	}

	@Override
	public boolean saveGroup(Group group) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(group);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Group getGroupById(String groupid) {
		return (Group) sessionFactory.getCurrentSession().get(Group.class,
				groupid);
	}

	@Override
	public boolean deleteGroup(Group group) {
		try {
			sessionFactory.getCurrentSession().delete(group);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Group getDefaultGroupByAccountId(String account_id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Group g where g.account_id=:account_id and g.type=0");
		query.setString("account_id", account_id);
		return (Group) query.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getForumGroupsByAccountId(String accountid) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Group g where g.account_id=:account_id and g.type=2");
		query.setString("account_id", accountid);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getForumGroups(String execute) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Group g where g.account_id!=:account_id and g.type=2");
		query.setString("account_id", execute);
		return query.list();
	}

	@Override
	public Group getForumGroupByMsn(String msn) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Group g where g.msn=:msn and g.type=2");
		query.setString("msn", msn);
		return (Group) query.uniqueResult();
	}

}
