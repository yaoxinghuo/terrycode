package com.microblog.data.service.intf;

import com.microblog.data.model.Group;
import com.microblog.data.model.Robot;

public interface IServiceService {
	public int imGetRobotTypeByAccount(String account);

	public Group imGetForumByAccount(String account);

	public Robot imGetRobotByAccount(String account);

}
