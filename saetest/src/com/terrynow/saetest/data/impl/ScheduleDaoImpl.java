package com.terrynow.saetest.data.impl;

import java.util.Date;
import java.util.List;

import com.terrynow.saetes.model.City;
import com.terrynow.saetes.model.Schedule;
import com.terrynow.saetest.data.intf.IScheduleDao;
import com.terrynow.saetest.db.DBHelper;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-3 下午04:32:47
 */
public class ScheduleDaoImpl implements IScheduleDao {

	@Override
	public boolean saveSchedule(Schedule schedule) {
		return true;
	}

	@Override
	public List<Schedule> getReadyToDoSchedules() {
		return null;
	}

	@Override
	public Schedule getScheduleById(String id) {
		return null;
	}

	@Override
	public boolean deleteScheduleById(String id) {
		return true;
	}

	@Override
	public boolean deleteScheduleByIdAndUid(String id, String uid,
			String account) {
		return true;
	}

	@Override
	public int getScheduleCountByUid(String uid, String account) {
		return 0;
	}

	@Override
	public int getScheduleCountByEmail(String email) {
		return 0;
	}

	@Override
	public int getScheduleCount() {
		return 0;
	}

	@Override
	public List<Schedule> getSchedulesByUid(String uid, String account,
			int start, int limit) {
		return null;
	}

	@Override
	public boolean updateScheduleById(String id, String email, String city,
			Date sdate, int type, int days, String remark) {
		return true;
	}

	@Override
	public int updateScheduleCount(int change) {
		return 0;
	}

	@Override
	public String getCityData(String name) {
		City city = (City) DBHelper.get("select * from city where name=? ",
				City.class, name);
		if (city != null)
			return city.getData();
		return null;
	}

	@Override
	public synchronized boolean saveCityData(String name, String data) {
		return DBHelper.update("insert into city(name, data) values(?,?)",
				name, data);
	}

	@Override
	public int pauseScheduleByEmail(String email) {
		return 0;
	}
}
