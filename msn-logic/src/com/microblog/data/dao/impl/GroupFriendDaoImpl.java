package com.microblog.data.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.microblog.data.dao.intf.IGroupFriendDao;
import com.microblog.data.model.Group;
import com.microblog.data.model.GroupFriend;

public class GroupFriendDaoImpl implements IGroupFriendDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@Override
	public boolean saveGroupFriend(GroupFriend gf) {
		try {
			sessionFactory.getCurrentSession().save(gf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupFriend> getGroupFriendsByGroup(Group group) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from GroupFriend gf where gf.group=:group ");
		query.setEntity("group", group);
		return query.list();
	}

	@Override
	public GroupFriend getGroupFriendByFriendIdAndGroupId(String friendid,
			String groupid) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GroupFriend gf where gf.group_id=:group_id and gf.friendid=:friendid");
		query.setString("group_id", groupid);
		query.setString("friendid", friendid);
		return (GroupFriend) query.list().get(0);
	}

	@Override
	public boolean deleteGroupFriend(GroupFriend gf) {
		try {
			sessionFactory.getCurrentSession().delete(gf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupFriend> getGroupFriendsByGroupId(String groupid) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from GroupFriend gf where gf.group_id=:group_id ");
		query.setString("group_id", groupid);
		return query.list();
	}

	@Override
	public int updateFriendsNickname(String friendid, String nickname) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"update GroupFriend gf set gf.nickname=:nickname where gf.friendid=:friendid ");
		query.setString("friendid", friendid);
		query.setString("nickname", nickname);
		return query.executeUpdate();
	}

	@Override
	public void deleteGroupFriendByFriendIdAndGroupId(String friendid,
			String groupid) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"delete from GroupFriend gf where gf.group_id=:group_id and gf.friendid=:friendid");
		query.setString("group_id", groupid);
		query.setString("friendid", friendid);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGuestForumGroupsByFriendId(String friendid) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select distinct gf.id,gf.group from GroupFriend gf where gf.friendid=:friendid and gf.type=2 group by gf.id");
		query.setString("friendid", friendid);
		Iterator it = query.iterate();
		List<Group> groups = new ArrayList<Group>();
		while (it.hasNext()) {
			Object[] result = (Object[]) it.next();
			groups.add((Group) result[1]);
		}
		return groups;
	}

	@Override
	public GroupFriend getGroupFriendById(String groupfriendid) {
		return (GroupFriend) sessionFactory.getCurrentSession().get(
				GroupFriend.class, groupfriendid);
	}

}
