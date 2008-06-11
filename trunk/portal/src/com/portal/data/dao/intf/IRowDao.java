package com.portal.data.dao.intf;

import java.util.HashMap;

import com.portal.data.pojo.Row;

/**
 * Developer: Terry DateTime : 2007-12-17 下午01:54:39
 */

public interface IRowDao {
	public boolean saveRow(Row row);

	public Row getRowById(String id);
	
	public boolean deleteRow(Row row);

	public boolean updateRow(Row row);

	public String saveAndGetId(Row row);

	public HashMap<String, Row> getRowsMapByUsernameAndTab(String username,
			String tab_id);
}
