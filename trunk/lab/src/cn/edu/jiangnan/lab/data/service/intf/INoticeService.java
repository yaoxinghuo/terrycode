package cn.edu.jiangnan.lab.data.service.intf;

import cn.edu.jiangnan.lab.data.pojo.Notice;

public interface INoticeService {
	public boolean saveNotice(String notice);

	public boolean saveFeedback(String feedback);

	public boolean updateNotice(String notice);

	public boolean updateFeedback(String feedback);

	public boolean deleteNoticeById(String id);

	public boolean deleteFeedbackById(String id);

	public String batchRemoveNotice(String[] ids);

	public String batchRemoveFeedback(String[] ids);

	public String getNoticeById(String id, boolean checkpub);

	public Notice getIntroduceNotice();

	public String getFeedbackById(String id, boolean checkpub);

	public String getNotices(int start, int limit, boolean pub);

	public String getFeedbacks(int start, int limit, boolean pub);

	public String getIndexNotices(int type, int limit);

	public String getIndexFeedbacks(int limit);

	public String getIndexLogs(int limit);

	public String getUndoTasks(String adminid);

	public String getRecentLogsNoticeByUserId(String user_id);

	public String deleteFileFromServer(String basePath, String filePath);
};
