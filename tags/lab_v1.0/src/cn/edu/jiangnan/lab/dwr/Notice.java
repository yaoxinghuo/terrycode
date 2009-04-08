package cn.edu.jiangnan.lab.dwr;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;

import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;
import cn.edu.jiangnan.lab.data.service.intf.INoticeService;

public class Notice {
	private INoticeService noticeService;
	private IAccountService accountService;;
	
	private String purciewErrorMessage=Constants.NO_ADMIN_DO_ERROR_MESSAGE;

	private boolean isSuperAdmin() {
		if (WebContextFactory.get().getSession().getAttribute(
				Constants.SESSION_ADMIN_ID) == null)
			return false;
		String adminid = (String) WebContextFactory.get().getSession()
				.getAttribute(Constants.SESSION_ADMIN_ID);
		cn.edu.jiangnan.lab.data.pojo.Account account = accountService
				.getAccountById(adminid);
		return (account.getAdmin() == 3) ? true : false;
	}

	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public String getNotices(int start, int limit, boolean pub) {
		if (!isSuperAdmin())
			return null;
		return noticeService.getNotices(start, limit, pub);
	}

	public String getFeedbacks(int start, int limit, boolean pub) {
		if (!isSuperAdmin())
			return null;
		return noticeService.getFeedbacks(start, limit, pub);
	}

	public String saveNotice(String notice) {
		if (isSuperAdmin() && noticeService.saveNotice(notice))
			return "{result:true,message:'您已成功新增公告！'}";
		else
			return purciewErrorMessage;
	}

	public String updateNotice(String notice) {
		if (isSuperAdmin() && noticeService.updateNotice(notice))
			return "{result:true,message:'您已成功保存公告！'}";
		else
			return purciewErrorMessage;
	}

	public String updateFeedback(String feedback) {
		if (isSuperAdmin() && noticeService.updateFeedback(feedback))
			return "{result:true,message:'您已成功保存反馈信息！'}";
		else
			return purciewErrorMessage;
	}

	public String removeNotice(String id) {
		if (isSuperAdmin() && noticeService.deleteNoticeById(id))
			return "{result:true,message:'您已成功删除公告！'}";
		else
			return purciewErrorMessage;
	}

	public String removeFeedback(String id) {
		if (isSuperAdmin() && noticeService.deleteFeedbackById(id))
			return "{result:true,message:'您已成功删除反馈！'}";
		else
			return purciewErrorMessage;
	}

	public String batchRemoveNotice(String[] ids) {
		if (!isSuperAdmin())
			return purciewErrorMessage;
		else
			return noticeService.batchRemoveNotice(ids);
	}

	public String batchRemoveFeedback(String[] ids) {
		if (!isSuperAdmin())
			return purciewErrorMessage;
		else
			return noticeService.batchRemoveFeedback(ids);
	}

	public String getNoticeById(String id) {
		return noticeService.getNoticeById(id, false);
	}

	public String getFeedbackById(String id) {
		return noticeService.getFeedbackById(id, false);
	}

	public String getIndexNotices(int limit, int type) {
		return noticeService.getIndexNotices(type, limit);
	}

	public String getIndexFeedbacks(int limit) {
		return noticeService.getIndexFeedbacks(limit);
	}

	public String getIndexLogs(int limit) {
		return noticeService.getIndexLogs(limit);
	}

	public String getUndoTasks() {
		HttpSession session = WebContextFactory.get().getSession();
		if (session.getAttribute(Constants.SESSION_ADMIN_ID) == null)
			return "";
		return noticeService.getUndoTasks((String) session
				.getAttribute(Constants.SESSION_ADMIN_ID));
	}

	public String getRecentLogsNotice() {
		String id = getSessionId(false);
		if (id != null)
			return noticeService.getRecentLogsNoticeByUserId(id);
		return "";
	}

	public String deleteFile(String fileType, String fileName) {
		if (fileType == null || fileType.trim().equals("") || fileName == null
				|| fileName.trim().equals(""))
			return "{result:false,message:'您的输入有误，请检查后重试！'}";
		if (!isSuperAdmin())
			return "{result:false,message:'您不是管理员，没有权限删除文件！'}";
		return noticeService.deleteFileFromServer(WebContextFactory.get()
				.getSession().getServletContext().getRealPath("/userfiles/"),
				fileType + File.separator + fileName.trim());
	}

	private String getSessionId(boolean adminId) {
		if (adminId)
			return (String) WebContextFactory.get().getSession().getAttribute(
					Constants.SESSION_ADMIN_ID);
		else
			return (String) WebContextFactory.get().getSession().getAttribute(
					Constants.SESSION_ID);
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public IAccountService getAccountService() {
		return accountService;
	}
}
