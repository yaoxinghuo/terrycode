package com.microblog.data.service.impl;

import javax.annotation.Resource;

import com.microblog.data.dao.intf.IRobotDao;
import com.microblog.data.model.Robot;
import com.microblog.data.service.intf.IServiceService;

public class ServiceServiceImpl implements IServiceService {

	@Resource(name = "robotDao")
	private IRobotDao robotDao;

	public IRobotDao getRobotDao() {
		return robotDao;
	}

	public void setRobotDao(IRobotDao robotDao) {
		this.robotDao = robotDao;
	}

	@Override
	public int imGetRobotTypeByAccount(String account) {
		Robot robot = robotDao.getRobotByAccount(account);
		if (robot == null)
			return 0;
		return robot.getType();
	}

	@Override
	public Robot imGetRobotByAccount(String account) {
		return robotDao.getRobotByAccount(account);
	}

}
