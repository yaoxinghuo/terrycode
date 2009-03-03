package com.microblog.data.dao.intf;

import java.util.List;

import com.microblog.data.model.Account;
import com.microblog.data.model.Friend;

public interface IFriendDao {
	public Friend getFriendByAccountAndFiendId(Account account, String friend_id);

	public Friend getFriendByAccountIdAndFriendId(String account_id,
			String friend_id, boolean forum);
	
	public Friend getFriendByFriendIdAndForumId(String friendid, String forumid);

	public boolean saveFriend(Friend friend);

	public List<Friend> getApplyFriendsByAccountId(String accountid,
			boolean forum);

	public List<Friend> getFriendsByAccountId(String accountid);

	public boolean deleteFriend(Friend friend);

}
