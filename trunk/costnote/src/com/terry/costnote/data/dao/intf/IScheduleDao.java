package com.terry.costnote.data.dao.intf;

import java.util.Date;
import java.util.List;

import com.terry.costnote.data.model.Schedule;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:16:24 AM
 */
public interface IScheduleDao {

	public Schedule getScheduleById(String scheduleId);

	public boolean saveSchedule(Schedule schedule);

	public boolean deleteSchedule(Schedule schedule);

	public List<Schedule> getSchedulesByEmail(String email, Date sfrom,
			Date sto, int start, int limit);

	public List<Schedule> getSchedulesByEmail(String email, int start, int limit);

	public long getSchedulesCountByEmail(String email, Date sfrom, Date sto);
}
