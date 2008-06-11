package com.portal.data.dao.intf;

import java.util.ArrayList;

import com.portal.data.pojo.UserLink;

/**
 * Developer: Terry DateTime : 2008-1-14 下午02:10:49
 */

public interface ILinkDao {
	public boolean saveLink(UserLink link);

	public boolean deleteLink(UserLink link);

	public UserLink getLinkById(String id);

	public boolean updateLink(UserLink link);

	public ArrayList<UserLink> getLinksByRowId(String id);

	public void deleteUserLinksByRowId(String rowId);
}
