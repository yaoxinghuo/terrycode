package com.microblog.data.dao.intf;

import java.util.List;

import com.microblog.data.model.Group;

public interface IGroupDao {
	public List<Group> getGroupsByAccountId(String accountid, boolean includeForum);
	
	public List<Group> getForumGroupsByAccountId(String accountid);
	
	public Group getForumGroupByMsn(String msn);
	
	public List<Group> getForumGroups(String exclude);

	public boolean saveGroup(Group group);

	public Group getGroupById(String groupid);

	public boolean deleteGroup(Group group);

	public Group getDefaultGroupByAccountId(String accountid);
}
