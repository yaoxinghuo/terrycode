package cn.edu.jiangnan.lab.data.dao.intf;

import java.util.ArrayList;
import java.util.Date;

import cn.edu.jiangnan.lab.data.pojo.Log;

public interface ILogDao {
	public boolean saveLog(Log log);

	public boolean deleteLog(Log log);

	public ArrayList<Log> getLogs(int start, int limit, int action,
			Date startDate, Date endDate, String keyword, String column);

	public ArrayList<Log> getLogsByUserId(int start, int limit, String user_id);
	
	public ArrayList<Log> getRecentLogsByUserId(int days, String user_id);

	public long getCountLogsByUserId(String user_id);

	public Log getLogById(String id);

	public long getCountLogs(int action, Date startDate, Date endDate,
			String keyword, String column);

	public int logClean();
}
