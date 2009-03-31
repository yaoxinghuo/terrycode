package com.microblog.data.service.impl;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;

import com.microblog.data.dao.intf.IAccountDao;
import com.microblog.data.dao.intf.IFriendDao;
import com.microblog.data.dao.intf.IGroupDao;
import com.microblog.data.dao.intf.IGroupFriendDao;
import com.microblog.data.dao.intf.IHeadDao;
import com.microblog.data.dao.intf.IPassportDao;
import com.microblog.data.dao.intf.ISubscribeDao;
import com.microblog.data.model.Account;
import com.microblog.data.model.Friend;
import com.microblog.data.model.Group;
import com.microblog.data.model.GroupFriend;
import com.microblog.data.model.Head;
import com.microblog.data.service.intf.IAccountService;

public class AccountServiceImpl implements IAccountService {

	@Resource(name = "accountDao")
	private IAccountDao accountDao;

	@Resource(name = "passportDao")
	private IPassportDao passportDao;

	@Resource(name = "friendDao")
	private IFriendDao friendDao;

	@Resource(name = "groupDao")
	private IGroupDao groupDao;

	@Resource(name = "groupFriendDao")
	private IGroupFriendDao groupFriendDao;

	@Resource(name = "subscribeDao")
	private ISubscribeDao subscribeDao;

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public IPassportDao getPassportDao() {
		return passportDao;
	}

	public void setPassportDao(IPassportDao passportDao) {
		this.passportDao = passportDao;
	}

	public IFriendDao getFriendDao() {
		return friendDao;
	}

	public void setFriendDao(IFriendDao friendDao) {
		this.friendDao = friendDao;
	}

	public IGroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public IGroupFriendDao getGroupFriendDao() {
		return groupFriendDao;
	}

	public void setGroupFriendDao(IGroupFriendDao groupFriendDao) {
		this.groupFriendDao = groupFriendDao;
	}

	public ISubscribeDao getSubscribeDao() {
		return subscribeDao;
	}

	public void setSubscribeDao(ISubscribeDao subscribeDao) {
		this.subscribeDao = subscribeDao;
	}

	public IHeadDao getHeadDao() {
		return headDao;
	}

	public void setHeadDao(IHeadDao headDao) {
		this.headDao = headDao;
	}

	@Resource(name = "headDao")
	private IHeadDao headDao;

	@Override
	public Account imGetAccountByMsn(String msn) {
		return accountDao.getAccountByMsn(msn);
	}

	@Override
	public Friend imGetFriendByAccountIdAndFriendId(String accountid,
			String friendid) {
		return friendDao.getFriendByAccountIdAndFriendId(accountid, friendid,
				true);
	}

	@Override
	public boolean imUpdateAccount(Account account) {
		return accountDao.updateAccount(account);
	}

	@Override
	public boolean imUpdateUserStatus(String accountid, String nickname,
			String pmessage, String avatar) {
		Account account = accountDao.getAccountById(accountid);
		if (nickname == null)
			nickname = account.getNickname();
		if (pmessage == null)
			pmessage = account.getPmessage();
		if (avatar == null)
			avatar = account.getAvatar();
		if (!account.getNickname().equals(nickname))
			groupFriendDao.updateFriendsNickname(accountid, nickname + "("
					+ account.getEmail() + ")");
		boolean needUpdate = !account.getNickname().equals(nickname)
				|| !account.getPmessage().equals(pmessage)
				|| !account.getAvatar().equals(avatar);
		if (needUpdate) {
			account.setNickname(nickname);
			account.setPmessage(pmessage);
			account.setAvatar(avatar);
			account.setUdate(new Date());
			accountDao.updateAccount(account);

			Head head = new Head();
			head.setAccount(account);
			head.setAvatar(avatar);
			head.setInputdate(new Date());
			head.setNickname(nickname);
			head.setPmessage(pmessage);
			headDao.saveHead(head);
		}
		return true;
	}

	@Override
	public Hashtable<String, String> imGetUserGroupFriends(String groupid) {
		Hashtable<String, String> friends = new Hashtable<String, String>();
		List<GroupFriend> gfs = groupFriendDao
				.getGroupFriendsByGroupId(groupid);
		for (GroupFriend gf : gfs) {
			friends.put(gf.getFriendid(), gf.getNickname());
		}
		return friends;
	}

	@Override
	public Hashtable<String, String> imGetUserGroups(String accountid) {
		List<Group> groups = groupDao.getGroupsByAccountId(accountid, false);
		Hashtable<String, String> gs = new Hashtable<String, String>();
		for (Group group : groups) {
			gs.put(group.getId(), group.getName());
		}
		return gs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.microblog.data.service.intf.IAccountService#isUserInFriend(java.lang
	 * .String, java.lang.String) 这个User是否在论坛好友中，不管是否已经被批准了还是在待普准中
	 */
	@Override
	public boolean imIsUserInFriend(String email, String forumid) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return false;
		Friend friend = friendDao.getFriendByFriendIdAndForumId(
				account.getId(), forumid);
		return friend == null ? false : true;
	}

	@Override
	public boolean imRemoveForumGroupFriend(String email, String forumid) {
		Account account = accountDao.getAccountByEmail(email);
		Friend friend = friendDao.getFriendByFriendIdAndForumId(
				account.getId(), forumid);
		groupFriendDao.deleteGroupFriendByFriendIdAndGroupId(account.getId(),
				forumid);
		friendDao.deleteFriend(friend);
		return false;
	}

	@Override
	public boolean imApplyForumWithAccountId(String friendid, String forumid,
			String content) {
		Group group = groupDao.getGroupById(forumid);
		Account account = group.getAccount();
		Friend friend = friendDao.getFriendByAccountIdAndFriendId(group
				.getAccount_id(), friendid, true);
		if (friend == null) {
			friend = new Friend();
			friend.setAccount(account);
			friend.setGroupid(forumid);
			friend.setAdate(new Date());
			friend.setFriendid(friendid);
			friend.setApply(true);// 用戶已經主動通過msn加論壇機器人為好友了
			friend.setContent(content);
			friend.setIngroup(false);
			friend.setStatus(0);
			friend.setType(2);
			friendDao.saveFriend(friend);
		} else {
			friend.setAdate(new Date());
			friend.setContent(content);
			friend.setApply(true);
			friendDao.saveFriend(friend);
		}
		return true;
	}

	@Override
	public boolean imAllowForumGroupFriend(String email, String forumid) {
		Account f_account = accountDao.getAccountByEmail(email);
		Group group = groupDao.getGroupById(forumid);
		Friend friend = friendDao.getFriendByAccountIdAndFriendId(group
				.getAccount_id(), f_account.getId(), true);

		friend.setStatus(1);
		friend.setIngroup(true);

		GroupFriend gf = new GroupFriend();
		gf.setMshow(true);
		gf
				.setNickname(f_account.getNickname() + "(" + f_account.getMsn()
						+ ")");
		gf.setFriendid(f_account.getId());
		gf.setType(2);
		gf.setGroup(group);
		groupFriendDao.saveGroupFriend(gf);
		friendDao.saveFriend(friend);
		return true;
	}

	@Override
	public boolean imIsAllowForumGroupFriend(String email, String forumid) {
		Account f_account = accountDao.getAccountByEmail(email);
		Group group = groupDao.getGroupById(forumid);
		Friend friend = friendDao.getFriendByAccountIdAndFriendId(group
				.getAccount_id(), f_account.getId(), true);
		if (friend == null)
			return false;
		return friend.getStatus() == 1 ? true : false;
	}

	@Override
	public Account imGetAccountById(String id) {
		return accountDao.getAccountById(id);
	}

}
