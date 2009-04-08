package cn.edu.jiangnan.lab.data.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.jiangnan.lab.data.dao.intf.IAccountDao;
import cn.edu.jiangnan.lab.data.dao.intf.IBookDao;
import cn.edu.jiangnan.lab.data.dao.intf.IFeedbackDao;
import cn.edu.jiangnan.lab.data.dao.intf.ILogDao;
import cn.edu.jiangnan.lab.data.dao.intf.INoticeDao;
import cn.edu.jiangnan.lab.data.pojo.Account;
import cn.edu.jiangnan.lab.data.pojo.Feedback;
import cn.edu.jiangnan.lab.data.pojo.Log;
import cn.edu.jiangnan.lab.data.pojo.Notice;
import cn.edu.jiangnan.lab.data.service.comm.StringUtil;
import cn.edu.jiangnan.lab.data.service.intf.INoticeService;

public class NoticeServiceImpl implements INoticeService {

	private INoticeDao noticeDao;
	private IAccountDao accountDao;

	public IBookDao getBookDao() {
		return bookDao;
	}

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	private IBookDao bookDao;
	private IFeedbackDao feedbackDao;
	private ILogDao logDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");

	private static final int splitTitleLen = 30;
	private static final int splitTitleLen2 = 54;
	private static final int splitContentLen = 40;

	public INoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Override
	public boolean deleteNoticeById(String id) {
		Notice notice = noticeDao.getNoticeById(id);
		return noticeDao.deleteNotice(notice);
	}

	@Override
	public boolean deleteFeedbackById(String id) {
		Feedback feedback = feedbackDao.getFeedbackById(id);
		return feedbackDao.deleteFeedback(feedback);
	}

	@Override
	public String batchRemoveNotice(String[] ids) {
		JSONObject jo = new JSONObject();
		int pass = 0;
		for (int i = 0; i < ids.length; i++) {
			Notice notice = noticeDao.getNoticeById(ids[i]);
			if (noticeDao.deleteNotice(notice))
				pass++;
		}
		if (pass == ids.length) {
			jo.put("result", true);
			jo.put("message", "您已经成功批量删除 " + pass + " 条公告！");
		} else if (pass == 0) {
			jo.put("result", false);
			jo.put("message", "出现错误，您未批量删除公告！");
		} else {
			jo.put("result", true);
			jo.put("message", "您批量删除 " + pass + " 条公告，有" + (ids.length - pass)
					+ " 条公告删除时出现错误！");
		}
		return jo.toString();
	}

	@Override
	public String batchRemoveFeedback(String[] ids) {
		JSONObject jo = new JSONObject();
		int pass = 0;
		for (int i = 0; i < ids.length; i++) {
			Feedback feedback = feedbackDao.getFeedbackById(ids[i]);
			if (feedbackDao.deleteFeedback(feedback))
				pass++;
		}
		if (pass == ids.length) {
			jo.put("result", true);
			jo.put("message", "您已经成功批量删除 " + pass + " 条反馈！");
		} else if (pass == 0) {
			jo.put("result", false);
			jo.put("message", "出现错误，您未批量删除反馈！");
		} else {
			jo.put("result", true);
			jo.put("message", "您批量删除 " + pass + " 条反馈，有" + (ids.length - pass)
					+ " 条反馈删除时出现错误！");
		}
		return jo.toString();
	}

	@Override
	public String getNoticeById(String id, boolean checkpub) {
		Notice notice = noticeDao.getNoticeById(id);
		if (checkpub && !notice.isPub())
			return null;
		JSONObject jo = new JSONObject();
		jo.put("id", notice.getId());
		jo.put("title", notice.getTitle());
		jo.put("bold", notice.isBold());
		jo.put("red", notice.isRed());
		jo.put("date", sdfd.format(notice.getDate()));
		jo.put("type", notice.getType());
		jo.put("content", notice.getContent());
		jo.put("pub", notice.isPub());
		return jo.toString();
	}

	@Override
	public Notice getIntroduceNotice() {
		ArrayList<Notice> notices = noticeDao.getNotices(4, 1, true);
		if (notices.size() == 0)
			return null;
		else
			return notices.get(0);
	}

	@Override
	public String getFeedbackById(String id, boolean checkpub) {
		Feedback feedback = feedbackDao.getFeedbackById(id);
		if (checkpub && !feedback.isPub())
			return null;
		JSONObject jo = new JSONObject();
		jo.put("id", feedback.getId());
		jo.put("name", feedback.getName());
		jo.put("title", feedback.getTitle());
		jo.put("input", sdfd.format(feedback.getInput()));
		jo.put("contact", feedback.getContact());
		jo.put("content", feedback.getContent());
		jo.put("comment", feedback.getComment());
		jo.put("pub", feedback.isPub());
		return jo.toString();
	}

	@Override
	public boolean saveNotice(String notice) {
		JSONObject jo = JSONObject.fromObject(notice);
		Notice n = new Notice();
		Date date = new Date();
		try {
			date = sdfd.parse(jo.getString("date"));
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		n.setDate(date);
		n.setContent(jo.getString("content"));
		n.setType(jo.getInt("type"));
		n.setTitle(jo.getString("title"));
		n.setBold(jo.getBoolean("bold"));
		n.setRed(jo.getBoolean("red"));
		n.setPub(jo.getBoolean("pub"));
		return noticeDao.saveNotice(n);
	}

	@Override
	public boolean saveFeedback(String feedback) {
		JSONObject jo = JSONObject.fromObject(feedback);
		Feedback f = new Feedback();
		f.setComment(jo.getString("comment"));
		f.setContact(jo.getString("contact"));
		f.setName(jo.getString("name"));
		f.setContent(jo.getString("content"));
		f.setTitle(jo.getString("title"));
		f.setPub(false);
		return feedbackDao.saveFeedback(f);
	}

	@Override
	public boolean updateNotice(String notice) {
		JSONObject jo = JSONObject.fromObject(notice);
		Notice n = noticeDao.getNoticeById(jo.getString("id"));
		Date date = new Date();
		try {
			date = sdfd.parse(jo.getString("date"));
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		n.setDate(date);
		n.setContent(jo.getString("content"));
		n.setType(jo.getInt("type"));
		n.setPub(jo.getBoolean("pub"));
		n.setTitle(jo.getString("title"));
		n.setBold(jo.getBoolean("bold"));
		n.setRed(jo.getBoolean("red"));
		return noticeDao.saveNotice(n);
	}

	@Override
	public boolean updateFeedback(String feedback) {
		JSONObject jo = JSONObject.fromObject(feedback);
		Feedback f = feedbackDao.getFeedbackById(jo.getString("id"));
		Date date = new Date();
		try {
			date = sdfd.parse(jo.getString("input"));
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		f.setInput(date);
		f.setComment(jo.getString("comment"));
		f.setContact(jo.getString("contact"));
		f.setName(jo.getString("name"));
		f.setContent(jo.getString("content"));
		f.setTitle(jo.getString("title"));
		f.setPub(jo.getBoolean("pub"));
		return feedbackDao.saveFeedback(f);
	}

	@Override
	public String getNotices(int start, int limit, boolean pub) {
		ArrayList<Notice> notices = noticeDao.getNotices(start, limit, pub);
		JSONObject jo = new JSONObject();
		jo.put("results", noticeDao.getCountNotices(pub));
		JSONArray ja = new JSONArray();
		for (Notice notice : notices) {
			JSONObject n = new JSONObject();
			n.put("id", notice.getId());
			n.put("input", sdf.format(notice.getInput()));
			n.put("title", StringUtil.renderTitle(StringUtil.splitString(notice
					.getTitle(), splitTitleLen2), notice.isBold(), notice
					.isRed()));
			n.put("type", notice.getType());
			n.put("pub", notice.isPub());
			n.put("date", sdfd.format(notice.getDate()));
			ja.add(n);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String getFeedbacks(int start, int limit, boolean pub) {
		ArrayList<Feedback> feedbacks = feedbackDao.getFeedbacks(start, limit,
				pub);
		JSONObject jo = new JSONObject();
		jo.put("results", feedbackDao.getCountFeedbacks(pub));
		JSONArray ja = new JSONArray();
		for (Feedback feedback : feedbacks) {
			JSONObject f = new JSONObject();
			f.put("id", feedback.getId());
			f.put("name", feedback.getName());
			f.put("contact", StringUtil.splitString(feedback.getContact(),
					splitTitleLen));
			f.put("input", sdfd.format(feedback.getInput()));
			f.put("title", StringUtil.splitString(feedback.getTitle(),
					splitTitleLen));
			f.put("content", StringUtil.splitString(feedback.getContent(),
					splitContentLen));
			f.put("pub", feedback.isPub());
			f.put("comment", StringUtil.splitString(feedback.getComment(),
					splitContentLen));
			ja.add(f);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String getIndexNotices(int type, int limit) {
		if (limit != 0)
			limit++;
		ArrayList<Notice> notices = noticeDao.getNotices(type, limit, true);
		if (notices == null || notices.size() == 0)
			return "<i>暂无公告信息！</i>";
		StringBuffer sb = new StringBuffer();
		sb.append("<table width='94%' border='0' ");
		sb.append("cellspacing='0' cellpadding='0'>");
		sb.append("<tr><td height='8'></td></tr>");
		for (int i = 0; i < notices.size(); i++) {
			if (i == limit - 1)
				continue;
			Notice notice = notices.get(i);
			sb.append("<tr><td height='26'>");
			sb.append("<img src='resources/images/list_icon.gif'");
			sb.append(" width='7' height='7'>");
			sb.append("&nbsp;<a href='notice!detail.action?id=");
			sb.append(notice.getId()).append("'");
			sb.append(" title='").append(notice.getTitle()).append("'>");
			sb.append(StringUtil.renderTitle(StringUtil.splitString(notice
					.getTitle(), splitTitleLen2), notice.isBold(), notice
					.isRed()));
			sb.append("</a></td><td width='80' align='right'>");
			sb.append("<font class='eng'>");
			sb.append(sdfd.format(notice.getDate()));
			sb.append("</font></td></tr>\n");

			sb.append("<tr><td height='1' ");
			sb.append("background='resources/images/index_news_line.gif' ");
			sb.append("colspan='2'></td></tr>\n");
		}
		if (limit != 0 && limit < 20 && notices.size() > (limit - 1)) {
			sb.append("<tr><td></td>");
			sb.append("<td align='right'><a href='notice!list.action?type=");
			sb.append(type).append("'>更多</a></td></tr>");
		}
		sb.append("</table><br>");
		return sb.toString();
	}

	@Override
	public String getIndexFeedbacks(int limit) {
		if (limit != 0)
			limit++;
		ArrayList<Feedback> feedbacks = feedbackDao
				.getFeedbacks(0, limit, true);
		if (feedbacks == null || feedbacks.size() == 0)
			return "<i>暂无投诉反馈信息！</i>";
		StringBuffer sb = new StringBuffer();
		sb.append("<table width='94%' border='0' ");
		sb.append("cellspacing='0' cellpadding='0'>");
		sb.append("<tr><td height='8'></td></tr>");
		for (int i = 0; i < feedbacks.size(); i++) {
			if (i == limit - 1)
				continue;
			Feedback feedback = feedbacks.get(i);
			sb.append("<tr><td height='26'>");
			sb.append("<img src='resources/images/list_icon.gif'");
			sb.append(" width='7' height='7'>");
			sb.append("&nbsp;<a href='feedback!detail.action?id=");
			sb.append(feedback.getId()).append("'");
			sb.append(" title='").append(feedback.getTitle()).append("'>");
			sb.append(StringUtil.splitString(feedback.getTitle(),
					splitTitleLen2));
			sb.append("</a></td><td width='80' align='right'>");
			sb.append("<font class='eng'>");
			sb.append(sdfd.format(feedback.getInput()));
			sb.append("</font></td></tr>\n");

			sb.append("<tr><td height='1' ");
			sb.append("background='resources/images/index_news_line.gif' ");
			sb.append("colspan='2'></td></tr>\n");
		}
		if (limit != 0 && limit < 20 && feedbacks.size() > (limit - 1)) {
			sb.append("<tr><td></td>");
			sb.append("<td align='right'><a href='feedback!list.action");
			sb.append("'>更多</a></td></tr>");
		}
		sb.append("</table><br>");
		return sb.toString();
	}

	@Override
	public String getIndexLogs(int limit) {
		if (limit != 0)
			limit++;
		Calendar c = Calendar.getInstance();
		Date endDate = c.getTime();
		c.add(Calendar.MONTH, -1);
		ArrayList<Log> logs = logDao.getLogs(0, limit, -1, c.getTime(),
				endDate, "", "id");
		if (logs == null || logs.size() == 0)
			return "<i>暂无预约审批信息！</i>";
		StringBuffer sb = new StringBuffer();
		sb.append("<table width='94%' border='0' ");
		sb.append("cellspacing='0' cellpadding='0'>");
		sb.append("<tr><td height='8'></td></tr>");
		for (int i = 0; i < logs.size(); i++) {
			if (i == limit - 1)
				continue;
			Log log = logs.get(i);
			String action = "批准了";
			switch (log.getAction()) {
			case 0:
				action = "未批准";
				break;
			case 1:
				action = "批准了";
				break;
			case 2:
				action = "删除了";
				break;
			case 3:
				action = "改费用";
				break;
			default:
			}
			StringBuffer message = new StringBuffer();
			message.append("[");
			message.append(log.getAdmin_name()).append("]");
			message.append(action);
			message.append(" ").append(log.getUser_name()).append(" ");
			message.append("的预约申请( ");
			message.append(log.getEquip_name()).append(" ");
			message.append(sdf.format(log.getStart())).append("-");
			message.append(sdf.format(log.getEnd())).append(")");

			sb.append("<tr><td height='26'>");
			sb.append("<img src='resources/images/list_icon.gif'");
			sb.append(" width='7' height='7'>");
			sb.append("&nbsp;<a href='log!detail.action?id=");
			sb.append(log.getId()).append("'");
			sb.append(" title='").append(message.toString()).append("'>");
			sb.append(StringUtil
					.splitString(message.toString(), splitTitleLen2));
			sb.append("</a></td><td width='80' align='right'>");
			sb.append("<font class='eng'>");
			sb.append(sdfd.format(log.getInput()));
			sb.append("</font></td></tr>\n");

			sb.append("<tr><td height='1' ");
			sb.append("background='resources/images/index_news_line.gif' ");
			sb.append("colspan='2'></td></tr>\n");
		}
		if (limit != 0 && limit < 20 && logs.size() > (limit - 1)) {
			sb.append("<tr><td></td>");
			sb.append("<td align='right'><a href='log!list.action");
			sb.append("'>更多</a></td></tr>");
		}
		sb.append("</table><br>");
		return sb.toString();
	}

	public String getUndoTasks(String adminid) {
		Account account = accountDao.getAccountById(adminid);
		StringBuffer sb = new StringBuffer("");
		long countPrBooks = bookDao.getCountPrBooksByAdminType(account
				.getType(), account.getUsername());
		if (countPrBooks != 0) {
			sb.append("您有&nbsp;").append(countPrBooks);
			sb.append("&nbsp;条待处理<a href='#' onclick='");
			sb.append("updateTab(\"pr_equipManager\",");
			sb.append("\"待批准预约\");return false;'>");
			sb.append("预约申请</a>");
		}
		if (account.getAdmin() == 3) {
			long countUndoFeedback = feedbackDao.getCountFeedbacks(false);
			if (countUndoFeedback != 0) {
				if (countPrBooks != 0) {
					sb.append(",&nbsp;").append(countUndoFeedback);
					sb.append("&nbsp;条待处理<a href='#' onclick='");
					sb.append("updateTab(\"feedbackManager\",");
					sb.append("\"用户反馈管理\");return false;'>");
					sb.append("用户反馈</a>！");
					sb.append("&nbsp;<a href=# onclick='clearMsg();");
					sb.append("return false;'>");
					sb.append("<img src='resources/images/close.gif'/></a>");
				} else {
					sb.append("您有&nbsp;").append(countUndoFeedback);
					sb.append("&nbsp;条待处理<a href='#' onclick='");
					sb.append("updateTab(\"feedbackManager\",");
					sb.append("\"用户反馈管理\");return false;'>");
					sb.append("用户反馈</a>！");
					sb.append("&nbsp;<a href=# onclick='clearMsg();");
					sb.append("return false;'>");
					sb.append("<img src='resources/images/close.gif'/></a>");
				}
			} else {
				if (countPrBooks != 0) {
					sb.append("！&nbsp");
					sb.append(" <a href=# onclick='clearMsg();return false;'>");
					sb.append("<img src='resources/images/close.gif'/></a>");
				}
			}
		} else if (countPrBooks != 0) {
			sb.append("！&nbsp");
			sb.append(" <a href=# onclick='clearMsg();return false;'>");
			sb.append("<img src='resources/images/close.gif'/></a>");
		}
		return sb.toString();
	}

	@Override
	public String getRecentLogsNoticeByUserId(String userid) {
		ArrayList<Log> logs = logDao.getRecentLogsByUserId(-7, userid);
		int[] a = new int[] { 0, 0, 0 };
		for (Log log : logs) {
			if (log.getAction() == 3)
				continue;
			a[log.getAction()]++;
		}
		if (a[0] == 0 && a[1] == 0 && a[2] == 0) {
			Account account = accountDao.getAccountById(userid);
			if (account.isDisabled())
				return "您被禁用预约，若要恢复预约功能，请联系管理员！&nbsp<a href=# onclick='clearMsg();return false;'><img src='resources/images/close.gif'/></a>";
			if (account.getMobile().equals("")
					|| account.getTeacher().equals(""))
				// return
				// "您的基本资料不完整，在预约设备前，请先更新您的<a href='javascript:void(0);' onclick='initUserAccount();return false;'>基本资料</a>！&nbsp<a href=# onclick='clearMsg();return false;'><img src='resources/images/close.gif'/></a>"
				// ;
				return "您的基本资料不完整，在预约设备前，请先更新您的<a href=# onclick='initUserAccount();return false;'>基本资料</a>！&nbsp<a href=# onclick='clearMsg();return false;'><img src='resources/images/close.gif'/></a>";
			else
				return "您好，"
						+ account.getUsername()
						+ "，您已成功登录设备预约系统！&nbsp<a href=# onclick='clearMsg();return false;'><img src='resources/images/close.gif'/></a>";
		}
		StringBuffer sb = new StringBuffer("近一周预约申请中,您有");
		for (int i = 0; i < 3; i++) {
			if (a[i] == 0)
				continue;
			switch (i) {
			case 0:
				sb.append("<font color=blue>&nbsp;");
				sb.append(a[i]).append("&nbsp;条未批准</font>,");
				break;
			case 1:
				sb.append("<font color=green>&nbsp;");
				sb.append(a[i]).append("&nbsp;条已批准</font>,");
				break;
			case 2:
				sb.append("<font color=red>&nbsp;");
				sb.append(a[i]).append("&nbsp;条被删除</font>,");
				break;
			}
		}
		sb
				.append("&nbsp<a href=# onclick='myAdminBook();return false;'>点击查看</a>");
		sb.append("&nbsp<a href=# onclick='clearMsg();return false;'>");
		sb.append("<img src='resources/images/close.gif'/></a>");
		return sb.toString();
	}

	public String deleteFileFromServer(String basePath, String name) {
		JSONObject jo = new JSONObject();
		File file = new File(basePath + File.separator + name);
		String canonicalPath = "";
		try {
			canonicalPath = file.getCanonicalPath().toLowerCase();// toLowerCase
			// windows下使用
			if (!canonicalPath.startsWith(basePath.toLowerCase())) {
				jo.put("result", false);
				jo.put("message", "您正在试图删除未授权访问的资源！");
				return jo.toString();
			}
		} catch (IOException e) {
			jo.put("result", false);
			jo.put("message", "对不起，程序错误，请稍候再试！");
			return jo.toString();
		}
		String fileName = file.getName();
		if (file.isDirectory() && checkSystemFolder(canonicalPath)) {
			jo.put("result", false);
			jo.put("message", "不能删除系统文件夹:'" + fileName + "'！");
			return jo.toString();
		}
		if (!file.exists()) {
			jo.put("result", false);
			jo.put("message", "您要删除文件(夹):'" + fileName + "'服务器上不存在！");
			return jo.toString();
		}

		if (file.isDirectory()) {
			if (file.listFiles().length > 0) {
				jo.put("result", false);
				jo.put("message", "您要删除的文件夹:'" + fileName + "'不为空！");
				return jo.toString();
			} else if (file.delete()) {
				jo.put("result", true);
				jo.put("message", "您已成功删除文件夹:'" + fileName + "'！");
				return jo.toString();
			} else {
				jo.put("result", false);
				jo.put("message", "对不起，程序错误，请稍候再试！");
				return jo.toString();
			}
		} else if (file.delete()) {
			jo.put("result", true);
			jo.put("message", "您已成功删除文件:'" + file.getName() + "'！");
			return jo.toString();
		} else {
			jo.put("result", false);
			jo.put("message", "对不起，程序错误，请稍候再试！");
			return jo.toString();
		}
	}

	private boolean checkSystemFolder(String path) {
		String[] systemFolders = { File.separator + "download",
				File.separator + "userfiles", File.separator + "file",
				File.separator + "image", File.separator + "flash" };
		for (String folder : systemFolders) {
			if (path.endsWith(folder)
					&& path.indexOf(folder) == path.lastIndexOf(folder))
				return true;
		}
		return false;
	}

	public IFeedbackDao getFeedbackDao() {
		return feedbackDao;
	}

	public void setFeedbackDao(IFeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}

	public ILogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

}
