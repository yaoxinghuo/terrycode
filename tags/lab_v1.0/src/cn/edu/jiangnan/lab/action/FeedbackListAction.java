package cn.edu.jiangnan.lab.action;

import net.sf.json.JSONObject;
import cn.edu.jiangnan.lab.data.service.intf.INoticeService;

import com.opensymphony.xwork2.ActionSupport;

public class FeedbackListAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 549452405110363876L;
	private String id;
	private String c;
	private String input;
	private String title;
	private String from;
	private String message;
	private INoticeService noticeService;

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@Override
	public String execute() throws Exception {
		c = noticeService.getIndexFeedbacks(0);
		if ("form".equals(from))
			message = "<font color=green>感谢您的批评和建议，我们的管理员会尽快处理！</font>";
		else
			message = "";
		from = "";
		return SUCCESS;
	}

	public String detail() throws Exception {
		String feedback = noticeService.getFeedbackById(id, true);
		JSONObject jo = JSONObject.fromObject(feedback);
		input = jo.getString("input");
		title = jo.getString("title");
		c = jo.getString("name") + " 的投诉、反馈信息：<br>" + jo.getString("content")
				+ "<br><br>管理员 回复：<br>" + jo.getString("comment");
		return "feedback";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
