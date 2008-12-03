package cn.edu.jiangnan.lab.data.dao.intf;

import java.util.ArrayList;

import cn.edu.jiangnan.lab.data.pojo.Notice;

public interface INoticeDao {
	public boolean saveNotice(Notice notice);

	public boolean deleteNotice(Notice notice);

	public Notice getNoticeById(String id);

	public ArrayList<Notice> getNotices(int type, int limit, boolean pub);
	
	public long getCountNotices(boolean pub);
}
