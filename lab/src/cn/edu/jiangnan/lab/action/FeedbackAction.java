package cn.edu.jiangnan.lab.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.edu.jiangnan.lab.data.pojo.Account;
import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;
import cn.edu.jiangnan.lab.data.service.intf.INoticeService;

import com.opensymphony.xwork2.ActionSupport;

public class FeedbackAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5510831042372959777L;
	private String id;
	private String input;
	private String no;
	private String password;
	private String title;
	private String validate;
	private String contact;
	private String content;
	private String message;
	private String from;
	private String c;
	private INoticeService noticeService;
	private IAccountService accountService;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public String save() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		if (validate == null
				|| !validate.equals((String) session
						.getAttribute(Constants.SESSION_VALIDATE))) {
			message = "<font color=red>验证码输入有误！</font>";
			return INPUT;
		}
		session.setAttribute(Constants.SESSION_VALIDATE, null);
		Account account = accountService.login(no, password, false,
				ServletActionContext.getRequest(), ServletActionContext
						.getResponse());
		if (account == null) {
			message = "<font color=red>您未能提交反馈，用户名或密码错误，请重试！</font>";
			return INPUT;
		}
		JSONObject jo = new JSONObject();
		jo.put("title", title);
		jo.put("content", content);
		jo.put("contact", contact);
		jo.put("name", account.getUsername() + "(" + no + ")");
		jo.put("user_id", account.getId());
		jo.put("comment", "");
		if (noticeService.saveFeedback(jo.toString())) {
			c = noticeService.getIndexFeedbacks(0);
			return SUCCESS;
		} else {
			message = "<font color=red>对不起，程序出现错误，请稍候再试！</font>";
			return INPUT;
		}
	}

	public String detail() throws Exception {
		message = "";
		String feedback = noticeService.getFeedbackById(id, true);
		JSONObject jo = JSONObject.fromObject(feedback);
		input = jo.getString("input");
		title = jo.getString("title");
		c = "<b>" + jo.getString("name") + "</b> 的投诉、反馈信息：<br>"
				+ jo.getString("content") + "<br><br>管理员 回复：<br>"
				+ jo.getString("comment");
		return "feedback";
	}

	public String list() throws Exception {
		message = "";
		if ("form".equals(from))
			message = "<font color=green>感谢您的意见和建议，我们的管理员会尽快处理，并显示在“投诉与反馈”栏！</font>";
		c = noticeService.getIndexFeedbacks(0);
		from = "";
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
