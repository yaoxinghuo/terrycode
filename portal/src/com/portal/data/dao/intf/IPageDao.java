package com.portal.data.dao.intf;

import com.portal.data.pojo.Page;

public interface IPageDao {
	public Page getPageByUsername(String username);

	public boolean savePage(Page page);
}
