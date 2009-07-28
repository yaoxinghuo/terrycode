package com.terry.costnote.data.service.intf;

import java.util.Date;
import java.util.List;

import com.terry.costnote.data.model.Cost;
import com.terry.costnote.data.model.Schedule;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:25:53 AM
 */
public interface ICostService {
	public boolean saveCost(String email, String cost);

	public boolean deleteCost(String costIds);

	public boolean saveSchedule(String email, String schedule);

	public boolean deleteSchedule(String scheduleIds);

	public List<Cost> getCostsByEmail(String email, Date sfrom, Date sto,
			int stype, int start, int limit);

	public long getCostsCountByEmail(String email, Date sfrom, Date sto,
			int stype);

	public List<Schedule> getSchedulesByEmail(String email, Date sfrom,
			Date sto, int start, int limit);

	public long getSchedulesCountByEmail(String email, Date sfrom, Date sto);
}
