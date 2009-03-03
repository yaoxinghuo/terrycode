package com.microblog.data.dao.intf;

import java.util.Date;
import java.util.List;

import com.microblog.data.model.Head;

public interface IHeadDao {
	public boolean saveHead(Head head);

	public Head getHeadById(String headid);

	public List<Head> getHeadsByDateAndAccountId(Date date, int field,
			String accountid);

	public List<Head> getNewestHeads(int limit);
}
