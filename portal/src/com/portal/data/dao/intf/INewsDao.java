package com.portal.data.dao.intf;

import java.util.ArrayList;

import com.portal.data.pojo.RssData;
import com.portal.data.pojo.UserRssData;

/**
 * Developer: Terry DateTime : 2007-12-11 下午03:49:13
 */

public interface INewsDao {
	public ArrayList<RssData> getNewsByTypes(int type1, int type2,
			int[] ex_type3, int firstResult, int maxResult);

	public long getCountNewsByTypes(int type1, int type2, int[] ex_type3);
	
	public long getCountUserRssNews(String row_id);

	public void saveUserRssData(ArrayList<UserRssData> userRssDatas);
	
	public void deleteUserRssDataByRowId(String row_id);

	public ArrayList<UserRssData> getUserRssNews(String row_id,
			int firstResult, int maxResult);
}
