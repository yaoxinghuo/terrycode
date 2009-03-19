package com.microblog.data.service.intf;

import java.util.Hashtable;

import com.microblog.data.model.Account;
import com.microblog.data.model.Friend;

public interface IAccountService {
	public Account imGetAccountByMsn(String msn);

	public Friend imGetFriendByAccountIdAndFriendId(String accountid,
			String friendid);

	public boolean imUpdateAccount(Account account);

	public boolean imUpdateUserStatus(String accountid, String nickname,
			String pmessage, String avatar);

	public Hashtable<String, String> imGetUserGroups(String accountid);

	public Hashtable<String, String> imGetUserGroupFriends(String groupid);

	public boolean imIsUserInFriend(String email, String forumid);

	public boolean imRemoveForumGroupFriend(String email, String forumid);

	public boolean imApplyForumWithAccountId(String friendid, String forumid,
			String content);

	public boolean imAllowForumGroupFriend(String email, String forumid);

	public boolean imIsAllowForumGroupFriend(String email, String forumid);

}
