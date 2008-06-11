package com.portal.data.dao.intf;

import java.util.Set;

import com.portal.data.pojo.UserRssData;

/**
 * Developer: Terry DateTime : 2007-12-25 上午11:05:01
 */

public interface IRssDataDao {
	public boolean saveUserRssData(String row_id, Set<UserRssData> userRssDatas);
}
