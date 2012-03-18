package com.terrynow.saetest.data.intf;

import java.util.Date;
import java.util.List;

import com.terrynow.saetes.model.Schedule;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-3 下午04:32:36
 */
public interface IScheduleDao {
	public boolean saveSchedule(Schedule schedule);

	public Schedule getScheduleById(String id);

	public boolean deleteScheduleById(String id);

	public boolean deleteScheduleByIdAndUid(String id, String uid,
			String account);

	public int pauseScheduleByEmail(String email);

	public boolean updateScheduleById(String id, String email, String city,
			Date sdate, int type, int days, String remark);

	public int getScheduleCountByUid(String uid, String account);

	public int getScheduleCount();

	public int updateScheduleCount(int change);

	public List<Schedule> getReadyToDoSchedules();

	public List<Schedule> getSchedulesByUid(String uid, String account,
			int start, int limit);

	public int getScheduleCountByEmail(String email);

	public boolean saveCityData(String name, String data);

	public String getCityData(String name);
}
