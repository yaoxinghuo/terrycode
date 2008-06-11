package com.portal.data.service.intf;

import java.util.ArrayList;

import com.portal.data.pojo.UserLink;

/**
 * Developer: Terry DateTime : 2008-1-14 下午02:15:08
 */

public interface ILinkService {
	public void saveLinks(ArrayList<UserLink> userLinks);
	
	public String getLinksByRowId(String id);
}
