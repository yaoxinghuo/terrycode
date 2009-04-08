package cn.edu.jiangnan.lab.action;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import cn.edu.jiangnan.lab.data.pojo.Log;
import cn.edu.jiangnan.lab.data.service.intf.IBookService;
import cn.edu.jiangnan.lab.data.service.intf.INoticeService;

import com.opensymphony.xwork2.ActionSupport;

public class LogAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8215440631793965832L;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private NumberFormat nf = new DecimalFormat("0.00");

	private String id;
	private String content;
	private Log log;
	private IBookService bookService;
	private INoticeService noticeService;

	private String start;
	private String end;
	private String input;
	private String exp_time;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getExp_time() {
		return exp_time;
	}

	public void setExp_time(String exp_time) {
		this.exp_time = exp_time;
	}

	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public IBookService getBookService() {
		return bookService;
	}

	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}

	public String detail() throws Exception {
		// content = bookService.getLogDetailById(id);
		log = bookService.getLogById(id);
		start = sdf.format(log.getStart());
		end = sdf.format(log.getEnd());
		input = sdf.format(log.getInput());
		exp_time = nf.format(log.getExp_time() / 60);
		return "log";
	}

	public String list() throws Exception {
		content = noticeService.getIndexLogs(30);
		return "index_log";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public Log getLog() {
		return log;
	}
}
