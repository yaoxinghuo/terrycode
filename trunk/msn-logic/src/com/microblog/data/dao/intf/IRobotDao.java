package com.microblog.data.dao.intf;

import com.microblog.data.model.Robot;

public interface IRobotDao {
	public Robot getRobotById(String id);

	public Robot getRobotByAccount(String account);
}
