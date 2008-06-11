package com.portal.data.dao.intf;

import java.util.ArrayList;

import com.portal.data.pojo.Tab;

public interface ITabDao {
	public ArrayList<Tab> getTabsByUsername(String username);

	public Tab getTabByUsernameAndPosition(String username, int position);

	public void saveTab(Tab tab);

}
