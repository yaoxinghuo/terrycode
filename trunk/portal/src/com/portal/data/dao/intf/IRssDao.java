package com.portal.data.dao.intf;

import java.util.ArrayList;

/**
 * Developer: Terry DateTime : 2007-12-7 下午02:39:50
 */

public interface IRssDao {
	public ArrayList<Object[]> getAvailableType3AndCount(int type1, int type2);
}
