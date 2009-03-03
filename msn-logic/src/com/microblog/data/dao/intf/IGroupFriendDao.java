package com.microblog.data.dao.intf;

import java.util.List;

import com.microblog.data.model.Group;
import com.microblog.data.model.GroupFriend;

public interface IGroupFriendDao {
	public boolean saveGroupFriend(GroupFriend gf);

	public List<GroupFriend> getGroupFriendsByGroup(Group group);

	public List<GroupFriend> getGroupFriendsByGroupId(String groupid);

	public GroupFriend getGroupFriendById(String groupfriendid);

	public GroupFriend getGroupFriendByFriendIdAndGroupId(String friendid,
			String groupid);

	public boolean deleteGroupFriend(GroupFriend gf);

	public void deleteGroupFriendByFriendIdAndGroupId(String friendid,
			String groupid);

	public int updateFriendsNickname(String friendid, String nickname);

	public List<Group> getGuestForumGroupsByFriendId(String friendid);
}
