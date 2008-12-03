package cn.edu.jiangnan.lab.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.edu.jiangnan.lab.data.pojo.Notice;
import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.comm.StringUtil;
import cn.edu.jiangnan.lab.data.service.intf.INoticeService;

import com.opensymphony.xwork2.ActionSupport;

public class NoticeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6911595108714182813L;

	private String id;
	private String content;
	private String title;
	private String date;
	private ArrayList<Notice> notices = new ArrayList<Notice>();
	private int type;
	private int start;
	private int limit;
	private boolean pub;
	private INoticeService noticeService;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public String detail() throws Exception {
		if (id == null || id.equals("")) {
			Notice notice = noticeService.getIntroduceNotice();
			if (notice == null) {
				title = "";
				date = "";
				content = "<table align='center'><tr><td><i>暂无实验室概况信息</i></td></tr></table>";
				type = 4;
			} else {
				content = notice.getContent();
				type = 4;
				date = new SimpleDateFormat("yyyy-MM-dd").format(notice
						.getDate());
				title = StringUtil.renderTitle(notice.getTitle(), notice
						.isBold(), notice.isRed());
			}
		} else {
			String notice = noticeService.getNoticeById(id, true);
			JSONObject jo = JSONObject.fromObject(notice);
			content = jo.getString("content");
			type = jo.getInt("type");
			date = jo.getString("date");
			title = StringUtil.renderTitle(jo.getString("title"), jo
					.getBoolean("bold"), jo.getBoolean("red"));
		}
		return "notice";
	}

	public String list() {
		content = noticeService.getIndexNotices(type, 0);
		return "index_notice";
	}

	public String adminlist() throws Exception {
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		if (ServletActionContext.getRequest().getSession().getAttribute(
				Constants.SESSION_SUPER_ADMIN) != null)
			writer.write(noticeService.getNotices(start, limit, pub));
		writer.flush();
		writer.close();
		return null;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<Notice> getNotices() {
		return notices;
	}

	public void setNotices(ArrayList<Notice> notices) {
		this.notices = notices;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}
}
