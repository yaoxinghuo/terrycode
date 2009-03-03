package com.microblog.data.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IFriendDao;
import com.microblog.data.model.Account;
import com.microblog.data.model.Friend;

public class FriendDaoImpl implements IFriendDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@Override
	public Friend getFriendByAccountAndFiendId(Account account, String friend_id) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Friend f where f.friendid=:friendid and f.account=:account and f.type!=2");
		query.setString("friendid", friend_id);
		query.setEntity("account", account);
		return (Friend) query.uniqueResult();
	}

	@Override
	public boolean saveFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> getApplyFriendsByAccountId(String accountid,
			boolean forum) {
		String sql = "from Friend f where f.account_id=:account_id and f.status=0 and f.ingroup=false and apply=:apply and f.type";
		if (!forum)
			sql += "!";
		sql += "=2";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setString("account_id", accountid);
		query.setBoolean("apply", forum);//如果是論壇，那么apply=true,
		return query.list();
	}

	@Override
	public Friend getFriendByAccountIdAndFriendId(String account_id,
			String friend_id, boolean forum) {
		String sql = "from Friend f where f.friendid=:friendid and f.account_id=:account_id and f.type";
		if (!forum)
			sql += "!";
		sql += "=2";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setString("friendid", friend_id);
		query.setString("account_id", account_id);
		return (Friend) query.uniqueResult();
	}

	@Override
	public boolean deleteFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> getFriendsByAccountId(String accountid) {
		String sql = "from Friend f where f.account_id=:account_id and f.type!=2 and f.status=:status and f.ingroup=:ingroup";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setString("account_id", accountid);
		query.setInteger("status", 1);
		query.setBoolean("ingroup", true);
		return query.list();
	}
	
	@Override
	public Friend getFriendByFriendIdAndForumId(String friendid, String forumid) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Friend f where f.friendid=:friendid and f.type=2 and f.groupid=:forumid");
		query.setString("friendid", friendid);
		query.setString("forumid", forumid);
		return (Friend) query.uniqueResult();
	}

}
