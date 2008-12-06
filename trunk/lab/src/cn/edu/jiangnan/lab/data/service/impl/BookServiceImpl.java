package cn.edu.jiangnan.lab.data.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.util.HtmlUtils;

import cn.edu.jiangnan.lab.data.dao.intf.IAccountDao;
import cn.edu.jiangnan.lab.data.dao.intf.IBookDao;
import cn.edu.jiangnan.lab.data.dao.intf.IEquipDao;
import cn.edu.jiangnan.lab.data.dao.intf.ILogDao;
import cn.edu.jiangnan.lab.data.pojo.Account;
import cn.edu.jiangnan.lab.data.pojo.Book;
import cn.edu.jiangnan.lab.data.pojo.Equip;
import cn.edu.jiangnan.lab.data.pojo.Log;
import cn.edu.jiangnan.lab.data.service.comm.StringUtil;
import cn.edu.jiangnan.lab.data.service.intf.IBookService;

public class BookServiceImpl implements IBookService {

	private IBookDao bookDao;
	private IAccountDao accountDao;
	private IEquipDao equipDao;
	private ILogDao logDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private NumberFormat nf = new DecimalFormat("0.00");

	private static final int splitLen = 26;

	public IEquipDao getEquipDao() {
		return equipDao;
	}

	public void setEquipDao(IEquipDao equipDao) {
		this.equipDao = equipDao;
	}

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public IBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public String getBookDetailById(String id) {
		Book book = bookDao.getBookById(id);
		JSONObject b = new JSONObject();
		b.put("id", book.getEquip_id());
		b.put("bookid", book.getId());
		b.put("start", sdf.format(book.getStart()));
		b.put("end", sdf.format(book.getEnd()));
		b.put("teacher", book.getTeacher());
		b.put("sample_name", book.getSample_name());
		b.put("sample_mount", book.getSample_mount());
		b.put("compute_fee", nf.format(book.getCompute_fee()));
		b.put("exp_time", nf.format((float) book.getExp_time() / (float) 60)
				+ "H");
		b.put("content", book.getContent());
		b.put("charge", book.getEquip().isCharge());
		b.put("remark", book.getRemark());
		return b.toString();
	}

	@Override
	public String getBooksInfoByEquipId(int start, int limit, String id) {
		ArrayList<Book> books = bookDao.getBooksByEquipId(start, limit, id);
		JSONObject jo = new JSONObject();
		jo.put("results", bookDao.getCountBooksByEquipId(id));
		JSONArray ja = new JSONArray();
		java.util.Date date = new java.util.Date();
		for (Book book : books) {
			Equip equip = book.getEquip();
			JSONObject b = new JSONObject();
			b.put("id", book.getId());
			b.put("input", sdf.format(book.getInput()));
			b.put("equip_id", book.getEquip_id());
			b.put("equip_no", equip.getNo());
			b.put("equip_name", equip.getName());
			b.put("type", book.getType());
			b.put("user_id", book.getUser_id());
			b.put("user_name", book.getUser_name());
			b.put("start", sdf.format(book.getStart()));
			b.put("end", sdf.format(book.getEnd()));
			b.put("expired", book.getEnd().getTime() <= date.getTime());
			b.put("teacher", HtmlUtils.htmlEscape(book.getTeacher()));
			b.put("sample", book.getSample_name() + "/"
					+ book.getSample_mount());
			b.put("fee", nf.format(book.getCompute_fee()) + "/"
					+ nf.format(book.getActual_fee()));
			b.put("exp_time", nf
					.format((float) book.getExp_time() / (float) 60)
					+ "H");
			b.put("content", StringUtil
					.splitString(book.getContent(), splitLen));
			b.put("remark", StringUtil.splitString(book.getRemark(), splitLen));
			b.put("action", book.getAction());
			b.put("charge", equip.isCharge());
			ja.add(b);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String getBooksInfoByUserId(int start, int limit, String id) {
		ArrayList<Book> books = bookDao.getBooksByUserId(start, limit, id);
		JSONObject jo = new JSONObject();
		jo.put("results", bookDao.getCountBooksByUserId(id));
		JSONArray ja = new JSONArray();
		java.util.Date date = new java.util.Date();
		for (Book book : books) {
			Equip equip = book.getEquip();
			JSONObject b = new JSONObject();
			b.put("id", book.getId());
			b.put("input", sdf.format(book.getInput()));
			b.put("equip_id", book.getEquip_id());
			b.put("equip_no", equip.getNo());
			b.put("type", book.getType());
			b.put("equip_name", equip.getName());
			b.put("user_id", book.getUser_id());
			b.put("user_name", book.getUser_name());
			b.put("start", sdf.format(book.getStart()));
			b.put("end", sdf.format(book.getEnd()));
			b.put("expired", book.getEnd().getTime() <= date.getTime());
			b.put("teacher", HtmlUtils.htmlEscape(book.getTeacher()));
			b.put("sample", book.getSample_name() + "/"
					+ book.getSample_mount());
			b.put("fee", nf.format(book.getCompute_fee()) + "/"
					+ nf.format(book.getActual_fee()));
			b.put("exp_time", nf
					.format((float) book.getExp_time() / (float) 60)
					+ "H");
			b.put("content", StringUtil
					.splitString(book.getContent(), splitLen));
			b.put("remark", StringUtil.splitString(book.getRemark(), splitLen));
			b.put("action", book.getAction());
			b.put("charge", equip.isCharge());
			b.put("appd", equip.getAppd());
			b.put("appt1", equip.getAppt1());
			b.put("appt2", equip.getAppt2());
			ja.add(b);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String getLogs(int start, int limit, int action, Date startDate,
			Date endDate, String keyword, String column) {
		ArrayList<Log> logs = logDao.getLogs(start, limit, action, startDate,
				endDate, keyword.trim(), column);
		JSONObject jo = new JSONObject();
		jo.put("results", logDao.getCountLogs(action, startDate, endDate,
				keyword.trim(), column));
		JSONArray ja = new JSONArray();
		for (Log log : logs) {
			JSONObject l = new JSONObject();
			l.put("id", log.getId());
			l.put("input", sdf.format(log.getInput()));
			l.put("equip_id", log.getEquip_id());
			l.put("equip_no", log.getEquip_no());
			l.put("equip_name", log.getEquip_name());
			l.put("user_id", log.getUser_id());
			l.put("user_name", log.getUser_name());
			l.put("admin_id", log.getAdmin_id());
			l.put("admin_name", log.getAdmin_name());
			l.put("start", sdf.format(log.getStart()));
			l.put("end", sdf.format(log.getEnd()));
			l.put("teacher", HtmlUtils.htmlEscape(log.getTeacher()));
			l
					.put("content", StringUtil.splitString(log.getContent(),
							splitLen));
			l.put("remark", StringUtil.splitString(log.getRemark(), splitLen));
			l.put("admin_remark", StringUtil.splitString(log.getAdmin_remark(),
					splitLen));
			l.put("sample", log.getSample_name() + "/" + log.getSample_mount());
			l.put("fee", nf.format(log.getCompute_fee()) + "/"
					+ nf.format(log.getActual_fee()));
			l.put("exp_time", nf.format((float) log.getExp_time() / (float) 60)
					+ "H");
			l.put("action", log.getAction());
			l.put("charge", log.isCharge());
			ja.add(l);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public ArrayList<Log> getLogsArray(int start, int limit, int action,
			Date startDate, Date endDate, String keyword, String column) {
		return logDao.getLogs(start, limit, action, startDate, endDate, keyword
				.trim(), column);
	}

	@Override
	public ArrayList<Book> getBooksArray(int start, int limit, int action,
			Date startDate, Date endDate, String keyword, String column) {
		return bookDao.getBooksLog(start, limit, action, startDate, endDate,
				keyword.trim(), column);
	}

	@Override
	public String getLogsByUserId(int start, int limit, String user_id) {
		ArrayList<Log> logs = logDao.getLogsByUserId(start, limit, user_id);
		JSONObject jo = new JSONObject();
		jo.put("results", logDao.getCountLogsByUserId(user_id));
		JSONArray ja = new JSONArray();
		for (Log log : logs) {
			JSONObject l = new JSONObject();
			l.put("id", log.getId());
			l.put("input", sdf.format(log.getInput()));
			l.put("equip_id", log.getEquip_id());
			l.put("equip_no", log.getEquip_no());
			l.put("equip_name", log.getEquip_name());
			l.put("user_id", log.getUser_id());
			l.put("user_name", log.getUser_name());
			l.put("admin_id", log.getAdmin_id());
			l.put("admin_name", log.getAdmin_name());
			l.put("start", sdf.format(log.getStart()));
			l.put("end", sdf.format(log.getEnd()));
			l.put("teacher", HtmlUtils.htmlEscape(log.getTeacher()));
			l
					.put("content", StringUtil.splitString(log.getContent(),
							splitLen));
			l.put("sample", log.getSample_name() + "/" + log.getSample_mount());
			l.put("fee", nf.format(log.getCompute_fee()) + "/"
					+ nf.format(log.getActual_fee()));
			l.put("exp_time", nf.format((float) log.getExp_time() / (float) 60)
					+ "H");
			l.put("remark", StringUtil.splitString(log.getRemark(), splitLen));
			l.put("admin_remark", StringUtil.splitString(log.getAdmin_remark(),
					splitLen));
			l.put("action", log.getAction());
			l.put("charge", log.isCharge());
			ja.add(l);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String saveBook(String equip_id, String user_id, Date start,
			Date end, String sample_name, int sample_mount, float compute_fee,
			String content) {
		JSONObject jo = new JSONObject();
		Account userAccount = accountDao.getAccountById(user_id);
		if (userAccount.isDisabled()) {
			jo.put("result", false);
			jo.put("message", "您暂时被禁止预约设备，请联系管理员！");
			return jo.toString();
		}
		Equip equip = equipDao.getEquipById(equip_id);
		if (!equip.isStatus()) {
			jo.put("result", false);
			jo.put("message", "该设备暂时不接受预约，请联系管理员！");
			return jo.toString();
		}
		if (userAccount.getMobile().equals("")
				|| userAccount.getTeacher().equals("")) {
			jo.put("result", false);
			jo
					.put(
							"message",
							"您的基本资料不完整，在预约设备前，请先更新您的<a href=# onclick='initUserAccount();return false;'>基本资料</a>！");
			return jo.toString();
		}
		Date nowDate = new Date();
		if (end.getTime() < nowDate.getTime()) {
			jo.put("result", false);
			jo.put("message", "预约的结束时间不能小于现在时间！");
			return jo.toString();
		}

		ArrayList<Book> books = null;
		String msg = null;
		if (equip.isCheckd()) {// 如果仪器需要检查是否同一时刻只能一个人预约，一般需要检查
			books = bookDao.getConfirmedBooksByEquipId(equip_id, nowDate);
			for (int i = 0; i < books.size(); i++) {
				Book b = books.get(i);
				if (!(start.getTime() >= b.getEnd().getTime() || end.getTime() <= b
						.getStart().getTime())) {
					Account account = accountDao.getAccountById(b.getUser_id());
					String name = (account == null) ? "" : (account
							.getUsername()
					// + "(" + account.getNo() + ")" //還是不要讓用戶看到其他用戶的學號比較安全
							);
					msg = "'" + equip.getName() + "'已经被" + name + "预约并审批通过("
							+ sdf.format(b.getStart()) + "--"
							+ sdf.format(b.getEnd()) + ")<br>您可查看'"
							+ equip.getName()
							+ "'的<a href=# onclick=\"showQueueEquip('"
							+ equip_id + "','" + equip.getName()
							+ "');return false;\">设备预约列表</a>，选择其他时间预约。";
					break;
				}
			}
			if (msg != null) {
				jo.put("result", false);
				jo.put("message", msg);
				return jo.toString();
			}
		}
		books = bookDao.getMyUnfonfirmedBooksByEquipId(user_id, equip_id,
				nowDate);
		for (int i = 0; i < books.size(); i++) {
			Book b = books.get(i);
			if (!(start.getTime() >= b.getEnd().getTime() || end.getTime() <= b
					.getStart().getTime())) {
				msg = "'" + equip.getName() + "'被您在以下时间段预约过("
						+ sdf.format(b.getStart()) + "--"
						+ sdf.format(b.getEnd()) + ")<br>您可查看'"
						+ equip.getName()
						+ "'的<a href=# onclick=\"showQueueEquip('" + equip_id
						+ "','" + equip.getName()
						+ "');return false;\">设备预约列表</a>，选择其他时间预约。";
				break;
			}
		}
		if (msg != null) {
			jo.put("result", false);
			jo.put("message", msg);
			return jo.toString();
		}

		Book book = new Book();
		book.setAction(0);
		book.setEquip_id(equip_id);
		book.setUser_id(user_id);
		book.setUser_name(userAccount.getUsername());
		book.setType(equip.getType());
		book.setStart(start);
		book.setEnd(end);
		book.setExp_time(sample_mount * equip.getSampletime());
		book.setCompute_fee(compute_fee);
		book.setActual_fee(0);// 还没有被批准时实际收费为零
		book.setSample_name(sample_name);
		book.setSample_mount(sample_mount);
		book.setTeacher(userAccount.getTeacher());
		book.setContent(content);
		book.setRemark("");
		book.setEquip(equip);
		if (bookDao.saveBook(book)) {
			// if (equip.isCharge()) {
			// jo.put("result", true);
			// jo
			// .put(
			// "message",
			// "您已登记预约'"
			// + equip.getName()
			// +
			// "'，请及时缴费后等待该设备负责人批准！<a href=# onclick='myBook();return false;'>预约历史</a>"
			// );
			// return jo.toString();
			// } else {
			jo.put("result", true);
			jo
					.put(
							"message",
							"您已登记预约'"
									+ equip.getName()
									+ "'，请等待该设备负责人批准！<a href=# onclick='myBook();return false;'>预约历史</a>");
			return jo.toString();
			// }
		} else {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍候再试！");
			return jo.toString();
		}
	}

	@Override
	public String updateBook(String bookid, String user_id, Date start,
			Date end, String sample_name, int sample_mount, float compute_fee,
			String content) {
		JSONObject jo = new JSONObject();
		Account userAccount = accountDao.getAccountById(user_id);
		if (userAccount.isDisabled()) {
			jo.put("result", false);
			jo.put("message", "您暂时被禁止预约设备，请联系管理员！");
			return jo.toString();
		}
		Book book = bookDao.getBookById(bookid);
		if (!book.getUser_id().equals(user_id)) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍候再试！");
			return jo.toString();
		}
		Equip equip = equipDao.getEquipById(book.getEquip_id());
		if (!equip.isStatus()) {
			jo.put("result", false);
			jo.put("message", "该设备暂时不接受预约，请联系管理员！");
			return jo.toString();
		}
		if (userAccount.getMobile().equals("")
				|| userAccount.getTeacher().equals("")) {
			jo.put("result", false);
			jo
					.put(
							"message",
							"您的基本资料不完整，在预约设备前，请先更新您的<a href=# onclick='initUserAccount();return false;'>基本资料</a>！");
			return jo.toString();
		}
		Date nowDate = new Date();
		if (end.getTime() < nowDate.getTime()) {
			jo.put("result", false);
			jo.put("message", "预约的结束时间不能小于现在时间！");
			return jo.toString();
		}
		ArrayList<Book> books = null;
		String msg = null;
		if (equip.isCheckd()) {// 如果仪器需要检查是否同一时刻只能一个人预约，一般需要检查
			books = bookDao.getConfirmedBooksByEquipId(book.getEquip_id(),
					nowDate);
			for (int i = 0; i < books.size(); i++) {
				Book b = books.get(i);
				if (!(start.getTime() >= b.getEnd().getTime() || end.getTime() <= b
						.getStart().getTime())) {
					Account account = accountDao.getAccountById(b.getUser_id());
					String name = (account == null) ? "" : (account
							.getUsername()
					// + "(" + account.getNo() + ")" //還是不要讓用戶看到其他用戶的學號比較安全
							);
					msg = "'" + equip.getName() + "'已经被" + name + "预约并审批通过("
							+ sdf.format(b.getStart()) + "--"
							+ sdf.format(b.getEnd()) + ")您可查看'"
							+ equip.getName()
							+ "'的<a href=# onclick=\"showQueueEquip('"
							+ equip.getId() + "','" + equip.getName()
							+ "');return false;\">设备预约列表</a>，选择其他时间预约。";
					break;
				}
			}
			if (msg != null) {
				jo.put("result", false);
				jo.put("message", msg);
				return jo.toString();
			}
		}
		books = bookDao.getMyUnfonfirmedBooksByEquipId(user_id, book
				.getEquip_id(), nowDate);
		for (Book b : books) {
			if (book.getId().equals(bookid))
				continue;
			if (!(start.getTime() >= b.getEnd().getTime() || end.getTime() <= b
					.getStart().getTime())) {
				msg = "'" + equip.getName() + "'被您在以下时间段预约过("
						+ sdf.format(b.getStart()) + "--"
						+ sdf.format(b.getEnd()) + ")您可查看'" + equip.getName()
						+ "'的<a href=# onclick=\"showQueueEquip('"
						+ equip.getId() + "','" + equip.getName()
						+ "');return false;\">设备预约列表</a>，选择其他时间预约。";
				break;
			}
		}
		if (msg != null) {
			jo.put("result", false);
			jo.put("message", msg);
			return jo.toString();
		}

		book.setStart(start);
		book.setEnd(end);
		book.setCompute_fee(compute_fee);
		book.setSample_name(sample_name);
		book.setSample_mount(sample_mount);
		book.setExp_time(equip.getSampletime() * sample_mount);
		book.setContent(content);
		if (bookDao.saveBook(book)) {
			jo.put("result", true);
			jo.put("message", "您的预约信息已成功更新！");
		} else {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍候再试！");
		}

		return jo.toString();
	}

	@Override
	public String getPrBooksInfo(int start, int limit, String admin_id) {
		Account account = accountDao.getAccountById(admin_id);
		ArrayList<Book> books = bookDao.getPrBooksByAdminType(start, limit,
				account.getType(), account.getUsername());
		JSONObject jo = new JSONObject();
		jo.put("results", bookDao.getCountPrBooksByAdminType(account.getType(),
				account.getUsername()));
		JSONArray ja = new JSONArray();
		java.util.Date date = new java.util.Date();
		for (int i = 0; i < books.size(); i++) {
			JSONObject b = new JSONObject();
			Book book = books.get(i);
			Equip equip = book.getEquip();
			b.put("id", book.getId());
			b.put("input", sdf.format(book.getInput()));
			b.put("equip_id", book.getEquip_id());
			b.put("equip_no", equip.getNo());
			b.put("type", book.getType());
			b.put("equip_name", equip.getName());
			b.put("user_id", book.getUser_id());
			b.put("user_name", book.getUser_name());
			b.put("admin_name", book.getEquip().getAdmin());
			b.put("start", sdf.format(book.getStart()));
			b.put("end", sdf.format(book.getEnd()));
			b.put("expired", book.getEnd().getTime() <= date.getTime());
			b.put("sample", book.getSample_name() + "/"
					+ book.getSample_mount());
			b.put("fee", nf.format(book.getCompute_fee()));
			b.put("exp_time", nf
					.format((float) book.getExp_time() / (float) 60)
					+ "H");
			b.put("teacher", HtmlUtils.htmlEscape(book.getTeacher()));
			b.put("content", StringUtil
					.splitString(book.getContent(), splitLen));
			b.put("remark", StringUtil.splitString(book.getRemark(), splitLen));
			b.put("charge", equip.isCharge());
			ja.add(b);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String getAfBooksInfo(int start, int limit, String admin_id) {
		Account account = accountDao.getAccountById(admin_id);
		ArrayList<Book> books = bookDao.getAfBooksByAdminType(start, limit,
				account.getType(), account.getUsername());
		JSONObject jo = new JSONObject();
		jo.put("results", bookDao.getCountAfBooksByAdminType(account.getType(),
				account.getUsername()));
		JSONArray ja = new JSONArray();
		java.util.Date date = new java.util.Date();
		for (int i = 0; i < books.size(); i++) {
			JSONObject b = new JSONObject();
			Book book = books.get(i);
			Equip equip = book.getEquip();
			b.put("id", book.getId());
			b.put("input", sdf.format(book.getInput()));
			b.put("equip_id", book.getEquip_id());
			b.put("equip_no", equip.getNo());
			b.put("type", book.getType());
			b.put("equip_name", equip.getName());
			b.put("user_id", book.getUser_id());
			b.put("user_name", book.getUser_name());
			b.put("admin_name", book.getEquip().getAdmin());
			b.put("start", sdf.format(book.getStart()));
			b.put("end", sdf.format(book.getEnd()));
			b.put("expired", book.getEnd().getTime() <= date.getTime());
			b.put("sample", book.getSample_name() + "/"
					+ book.getSample_mount());
			b.put("compute_fee", nf.format(book.getCompute_fee()));
			b.put("fee", nf.format(book.getCompute_fee()) + "/"
					+ nf.format(book.getActual_fee()));
			b.put("exp_time", nf
					.format((float) book.getExp_time() / (float) 60)
					+ "H");
			b.put("teacher", HtmlUtils.htmlEscape(book.getTeacher()));
			b.put("content", StringUtil
					.splitString(book.getContent(), splitLen));
			b.put("remark", StringUtil.splitString(book.getRemark(), splitLen));
			b.put("charge", equip.isCharge());
			ja.add(b);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String getBooksLogs(int start, int limit, int action,
			Date startDate, Date endDate, String keyword, String column) {
		ArrayList<Book> books = bookDao.getBooksLog(start, limit, action,
				startDate, endDate, keyword.trim(), column);
		JSONObject jo = new JSONObject();
		jo.put("results", bookDao.getCountBooksLog(action, startDate, endDate,
				keyword.trim(), column));
		JSONArray ja = new JSONArray();
		java.util.Date date = new java.util.Date();
		for (int i = 0; i < books.size(); i++) {
			JSONObject b = new JSONObject();
			Book book = books.get(i);
			Equip equip = book.getEquip();
			b.put("id", book.getId());
			b.put("input", sdf.format(book.getInput()));
			b.put("equip_id", book.getEquip_id());
			b.put("equip_no", equip.getNo());
			b.put("type", book.getType());
			b.put("equip_name", equip.getName());
			b.put("user_id", book.getUser_id());
			b.put("user_name", book.getUser_name());
			b.put("admin_name", book.getEquip().getAdmin());
			b.put("start", sdf.format(book.getStart()));
			b.put("end", sdf.format(book.getEnd()));
			b.put("teacher", HtmlUtils.htmlEscape(book.getTeacher()));
			b.put("content", StringUtil
					.splitString(book.getContent(), splitLen));
			b.put("remark", StringUtil.splitString(book.getRemark(), splitLen));
			b.put("expired", book.getEnd().getTime() <= date.getTime());
			b.put("sample", book.getSample_name() + "/"
					+ book.getSample_mount());
			b.put("compute_fee", nf.format(book.getCompute_fee()));
			b.put("fee", nf.format(book.getCompute_fee()) + "/"
					+ nf.format(book.getActual_fee()));
			b.put("exp_time", nf
					.format((float) book.getExp_time() / (float) 60)
					+ "H");
			b.put("action", book.getAction());
			b.put("charge", equip.isCharge());
			ja.add(b);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String messageBook(String book_id) {
		Book book = bookDao.getBookById(book_id);
		Equip equip = book.getEquip();
		StringBuffer sb = new StringBuffer();
		sb.append(book.getUser_name());
		sb.append(" 于").append(sdf.format(book.getInput()));
		sb.append("提交了'").append(equip.getName());
		sb.append("'(").append(sdf.format(book.getStart()));
		sb.append("-").append(sdf.format(book.getEnd()));
		sb.append(")的预约申请.\n申请人导师:\n  ");
		sb.append(book.getTeacher());
		sb.append("\n样品：\n  ");
		sb.append(book.getSample_name());
		sb.append("\n样品总数：\n  ");
		sb.append(book.getSample_mount());
		sb.append("\n预约内容:\n  ");
		sb.append(book.getContent());
		Account account = accountDao.getAccountById(book.getUser_id());
		String mobile = account.getMobile();
		if (mobile != null && !mobile.equals(""))
			sb.append("\n申请人联系方式:\n  ").append(mobile);
		sb.append("\n\n实验费用：\n  ").append(nf.format(book.getCompute_fee())+"元");
		sb.append("\n应收费用：\n  ").append(nf.format(book.getActual_fee())+"元");
		String remark = book.getRemark();
		if (remark != null && !remark.equals("")) {
			sb.append("\n费用备注:\n  ").append(remark);
		}
		return sb.toString();
	}

	@Override
	public String adminMessageBook(String log_id) {
		Log log = logDao.getLogById(log_id);
		StringBuffer sb = new StringBuffer();
		sb.append(log.getUser_name());
		sb.append(" 于").append(sdf.format(log.getUser_input()));
		sb.append("提交了'").append(log.getEquip_name());
		sb.append("'(").append(sdf.format(log.getStart()));
		sb.append("-").append(sdf.format(log.getEnd()));
		sb.append(")的预约申请.\n申请人导师:\n  ");
		sb.append(log.getTeacher());
		sb.append("\n样品：\n  ");
		sb.append(log.getSample_name());
		sb.append("\n样品总数：\n  ");
		sb.append(log.getSample_mount());
		sb.append("\n实验费用：\n  ");
		sb.append(nf.format(log.getCompute_fee()) + "元");
		sb.append("\n应收费用：\n  ");
		sb.append(nf.format(log.getActual_fee()) + "元");
		sb.append("\n预约内容:\n  ");
		sb.append(log.getContent());
		sb.append("\n\n").append(log.getAdmin_name());
		sb.append(" 于").append(sdf.format(log.getInput()));
		switch (log.getAction()) {
		case 1:
			sb.append("批准了");
			break;
		case 0:
			sb.append("未批准");
			break;
		case 2:
			sb.append("删除了");
			break;
		case 3:
			sb.append("改费用");
			break;
		default:
		}
		sb.append("该申请.\n负责人附言:\n  ");
		sb.append(log.getAdmin_remark());
		Account account = accountDao.getAccountById(log.getAdmin_id());
		String mobile = account.getMobile();
		if (mobile != null && !mobile.equals(""))
			sb.append("\n负责人联系方式:\n  ").append(mobile);
		return sb.toString();
	}

	@Override
	public String confirmBook(String book_id, String admin_id, String message) {
		JSONObject jo = new JSONObject();
		if (admin_id == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Account admin_account = accountDao.getAccountById(admin_id);
		if (admin_account == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Book book = bookDao.getBookById(book_id);
		Date start = book.getStart();
		Date end = book.getEnd();
		Date nowDate = new Date();
		if (end.getTime() < nowDate.getTime()) {
			jo.put("result", false);
			jo.put("message", "该预约的结束日期已经到期！");
			return jo.toString();
		}
		ArrayList<Book> books = bookDao.getConfirmedBooksByEquipId(book
				.getEquip_id(), nowDate);
		Equip equip = book.getEquip();
		String msg = equip.isCheckd() ? validateBook(books, start, end) : null;
		if (msg != null) {
			jo.put("result", false);
			jo.put("message", msg);
			return jo.toString();
		}
		book.setAction(1);
		book.setActual_fee(book.getCompute_fee());
		equip.setCounter(equip.getCounter() + 1);
		if (bookDao.saveBook(book) && equipDao.saveEquip(equip)) {
			jo.put("result", true);
			jo.put("message", "您已经成功批准'" + equip.getName() + "'预约给'"
					+ book.getUser_name() + "'!");
			logDao.saveLog(generateLog(book, 1, admin_id, admin_account
					.getUsername(), message));
		} else {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍候再试！");
		}
		return jo.toString();
	}

	@Override
	public String batchConfirmBook(String[] book_ids, String admin_id) {
		JSONObject jo = new JSONObject();
		if (admin_id == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Account admin_account = accountDao.getAccountById(admin_id);
		if (admin_account == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		int pass = 0;
		Date nowDate = new Date();
		for (int i = 0; i < book_ids.length; i++) {
			Book book = bookDao.getBookById(book_ids[i]);
			if (book.getEnd().getTime() < nowDate.getTime())
				continue;
			String msg = book.getEquip().isCheckd() ? validateBook(bookDao
					.getConfirmedBooksByEquipId(book.getEquip_id(), nowDate),
					book.getStart(), book.getEnd()) : null;
			if (msg == null) {
				pass++;
				book.setAction(1);
				book.setActual_fee(book.getCompute_fee());
				Equip equip = equipDao.getEquipById(book.getEquip_id());
				equip.setCounter(equip.getCounter() + 1);
				equipDao.saveEquip(equip);
				bookDao.saveBook(book);
				logDao.saveLog(generateLog(book, 1, admin_id, admin_account
						.getUsername(), null));
			}
		}
		if (pass == book_ids.length) {
			jo.put("result", true);
			jo.put("message", "您已经成功批量批准 " + pass + " 个预约！");
		} else if (pass == 0) {
			jo.put("result", false);
			jo.put("message", "您未批量批准，可能有冲突或预约已过期！");
		} else {
			jo.put("result", true);
			jo.put("message", "您批量批准 " + pass + " 个预约，"
					+ (book_ids.length - pass) + " 个预约存在冲突或预约已过期！");
		}
		return jo.toString();
	}

	@Override
	public String removeBook(String book_id, String admin_id, String message) {
		JSONObject jo = new JSONObject();
		if (admin_id == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Account admin_account = accountDao.getAccountById(admin_id);
		if (admin_account == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Book book = bookDao.getBookById(book_id);
		book.setAction(2);
		book.setActual_fee(0);
		book.setRemark("");
		if (bookDao.saveBook(book)) {
			jo.put("result", true);
			jo.put("message", "您已经成功删除了'" + book.getUser_name() + "'的预约！");
			Log log = generateLog(book, 2, admin_id, admin_account
					.getUsername(), message);
			logDao.saveLog(log);
		} else {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍候再试！");
		}
		return jo.toString();
	}

	public String deleteBook(String book_id) {
		JSONObject jo = new JSONObject();
		Book book = bookDao.getBookById(book_id);
		if (bookDao.deleteBook(book)) {
			jo.put("result", true);
			jo.put("message", "您已经成功撤销/删除了预约申请！");
		} else {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍候再试！");
		}
		return jo.toString();
	}

	@Override
	public String batchDeleteBook(String[] book_ids) {
		JSONObject jo = new JSONObject();
		int pass = 0;
		for (int i = 0; i < book_ids.length; i++) {
			Book book = bookDao.getBookById(book_ids[i]);
			if (bookDao.deleteBook(book))
				pass++;
		}
		if (pass == book_ids.length) {
			jo.put("result", true);
			jo.put("message", "您已经成功批量删除 " + pass + " 条预约记录！");
		} else if (pass == 0) {
			jo.put("result", false);
			jo.put("message", "出现错误，您未批量删除！");
		} else {
			jo.put("result", true);
			jo.put("message", "您批量删除 " + pass + " 个预约记录，有"
					+ (book_ids.length - pass) + " 个预约删除时出现错误！");
		}
		return jo.toString();
	}

	@Override
	public String batchRemoveBook(String[] book_ids, String admin_id) {
		JSONObject jo = new JSONObject();
		if (admin_id == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Account admin_account = accountDao.getAccountById(admin_id);
		if (admin_account == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		int pass = 0;
		for (int i = 0; i < book_ids.length; i++) {
			Book book = bookDao.getBookById(book_ids[i]);
			book.setAction(2);
			book.setActual_fee(0);
			book.setRemark("");
			// 预约结束的日期必须大于现在的日期
			// 现在想想好像不需要吧？所以我把日期的代码注释掉了
			if (/*
				 * book.getEnd().getTime() >= new Date().getTime() &&
				 */bookDao.saveBook(book)) {
				pass++;
				logDao.saveLog(generateLog(book, 2, admin_id, admin_account
						.getUsername(), null));
			}
		}
		if (pass == book_ids.length) {
			jo.put("result", true);
			jo.put("message", "您已经成功批量删除 " + pass + " 条预约！");
		} else if (pass == 0) {
			jo.put("result", false);
			jo.put("message", "出现错误，您未批量删除！");
		} else {
			jo.put("result", true);
			jo.put("message", "您批量删除 " + pass + " 个预约，有"
					+ (book_ids.length - pass) + " 个预约删除时出现错误！");
		}
		return jo.toString();
	}

	@Override
	public String cancelBook(String book_id, String admin_id, String message) {
		JSONObject jo = new JSONObject();
		if (admin_id == null) {
			jo.put("result", false);
			jo.put("message", "出现错误，只有以系统管理员帐号登录才能撤销预约或联系您的系统管理员！");
			return jo.toString();
		}
		Account admin_account = accountDao.getAccountById(admin_id);
		if (admin_account == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Book book = bookDao.getBookById(book_id);
		// if (book.getEnd().getTime() < new Date().getTime()) {//
		// 预约结束的日期必须大于现在的日期
		// jo.put("result", false);
		// jo.put("message", "对不起，该次预约已经结束！");
		// return jo.toString();
		// }
		book.setAction(0);
		book.setActual_fee(0);
		book.setRemark("");
		if (bookDao.saveBook(book)) {
			jo.put("result", true);
			jo.put("message", "您已经成功取消了'" + book.getUser_name() + "'的预约！");
			logDao.saveLog(generateLog(book, 0, admin_id, admin_account
					.getUsername(), message));
		} else {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍候再试！");
		}
		return jo.toString();
	}

	@Override
	public String remarkBook(String book_id, String admin_id, String message) {
		JSONObject jo = new JSONObject();
		if (admin_id == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Account admin_account = accountDao.getAccountById(admin_id);
		if (admin_account == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Book book = bookDao.getBookById(book_id);
		if (logDao.saveLog(generateLog(book, 0, admin_id, admin_account
				.getUsername(), message))) {
			jo.put("result", true);
			jo.put("message", "您已经成功给'" + book.getUser_name() + "'增加了留言！");
		} else {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍候再试！");
		}
		return jo.toString();
	}

	@Override
	public String batchCancelBook(String[] book_ids, String admin_id) {
		JSONObject jo = new JSONObject();
		if (admin_id == null) {
			jo.put("result", false);
			jo.put("message", "出现错误，只有以系统管理员帐号登录才能撤销预约或联系您的系统管理员！");
			return jo.toString();
		}
		Account admin_account = accountDao.getAccountById(admin_id);
		if (admin_account == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		int pass = 0;
		java.util.Date date = new java.util.Date();
		for (int i = 0; i < book_ids.length; i++) {
			Book book = bookDao.getBookById(book_ids[i]);
			book.setAction(0);
			book.setActual_fee(0);
			book.setRemark("");
			if (book.getEnd().getTime() >= date.getTime()
					&& bookDao.saveBook(book)) {
				pass++;
				logDao.saveLog(generateLog(book, 0, admin_id, admin_account
						.getUsername(), null));
			}
		}
		if (pass == book_ids.length) {
			jo.put("result", true);
			jo.put("message", "您已经成功批量撤销 " + pass + " 条预约！");
		} else if (pass == 0) {
			jo.put("result", false);
			jo.put("message", "出现错误，您未批量批准，可能预约已过期！");
		} else {
			jo.put("result", true);
			jo.put("message", "您批量批准 " + pass + " 个预约，有"
					+ (book_ids.length - pass) + " 个预约撤销时出现错误，可能预约已过期！");
		}
		return jo.toString();
	}

	@Override
	public String getLogDetailById(String id) {
		Log log = logDao.getLogById(id);
		StringBuffer sb = new StringBuffer();
		sb.append("[").append(sdf.format(log.getUser_input()));
		sb.append("]<br><b>").append(log.getUser_name()).append("</b> ");
		sb.append("提交了预约申请--<b>");
		sb.append(log.getEquip_name()).append("</b>");
		sb.append("<br>申请使用时间：<br>&nbsp;&nbsp;");
		sb.append(sdf.format(log.getStart())).append("&nbsp;至&nbsp;");
		sb.append("<br>实际实验时间：<br>&nbsp;&nbsp;");
		sb.append(nf.format(log.getExp_time() / 60)).append("H");
		sb.append("<br>样品：<br>&nbsp;&nbsp;");
		sb.append(log.getSample_name());
		sb.append("<br>样品数：<br>&nbsp;&nbsp;");
		sb.append(log.getSample_mount());
		sb.append(sdf.format(log.getEnd())).append("<br>");
		sb.append("申请人导师：<br>&nbsp;&nbsp;").append(log.getTeacher());
		sb.append("<br>预约内容：<br>&nbsp;&nbsp;").append(log.getContent());
		sb.append("<br>实验费用：<br>&nbsp;&nbsp;");
		sb.append(nf.format(log.getCompute_fee())).append("元");
		sb.append("<br>实际费用：<br>&nbsp;&nbsp;");
		sb.append(nf.format(log.getActual_fee())).append("元");
		sb.append("<br>负责人费用备注:<br>&nbsp;&nbsp;").append(log.getRemark());
		sb.append("<br><br>");
		sb.append("[").append(sdf.format(log.getInput()));
		sb.append("]<br><b>").append(log.getAdmin_name()).append("</b> ");
		String color = "green";
		String action = "批准了";
		switch (log.getAction()) {
		case 0:
			color = "blue";
			action = "未批准";
			break;
		case 1:
			color = "green";
			action = "批准了";
			break;
		case 2:
			color = "red";
			action = "删除了";
			break;
		case 3:
			color = "black";
			action = "改费用";
		default:
		}
		sb.append("<font color=");
		sb.append(color).append(">");
		sb.append(action).append("</font>该申请<br>");
		sb.append("管理员附言：<br>&nbsp;&nbsp;").append(log.getAdmin_remark());
		return sb.toString();
	}

	@Override
	public boolean deleteLogById(String id) {
		Log log = logDao.getLogById(id);
		return logDao.deleteLog(log);
	}

	@Override
	public String batchDeleteLogs(boolean admin, String[] ids) {
		JSONObject jo = new JSONObject();
		if (!admin) {
			jo.put("result", false);
			jo.put("message", "请确认您是系统管理员并重新登录或稍后再试！");
			return jo.toString();
		}
		int pass = 0;
		for (int i = 0; i < ids.length; i++) {
			Log log = logDao.getLogById(ids[i]);
			if (logDao.deleteLog(log))
				pass++;
		}
		if (pass == ids.length) {
			jo.put("result", true);
			jo.put("message", "您已经成功批量删除 " + pass + " 条审批记录！");
		} else if (pass == 0) {
			jo.put("result", false);
			jo.put("message", "出现错误，您未批量删除审批记录！");
		} else {
			jo.put("result", true);
			jo.put("message", "您批量删除 " + pass + " 个审批记录，有"
					+ (ids.length - pass) + " 个记录删除时出现错误！");
		}
		return jo.toString();
	}

	@Override
	public int logClean() {
		return /* logDao.logClean() + */bookDao.bookClean();
	}

	public ILogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	private Log generateLog(Book book, int action, String admin_id,
			String admin_name, String message) {
		Log log = new Log();
		Equip equip = book.getEquip();
		log.setAdmin_remark(message);
		log.setAction(action);
		log.setAdmin_id(admin_id);
		log.setAdmin_name(admin_name);
		log.setEnd(book.getEnd());
		log.setEquip_id(book.getEquip_id());
		log.setEquip_name(equip.getName());
		log.setEquip_no(equip.getNo());
		log.setTeacher(book.getTeacher());
		log.setContent(book.getContent());
		log.setRemark(book.getRemark());
		log.setStart(book.getStart());
		log.setUser_id(book.getUser_id());
		log.setUser_name(book.getUser_name());
		log.setUser_input(book.getInput());
		log.setCharge(equip.isCharge());

		log.setActual_fee(book.getActual_fee());
		log.setCompute_fee(book.getCompute_fee());
		log.setSample_mount(book.getSample_mount());
		log.setSample_name(book.getSample_name());
		log.setExp_time(book.getExp_time());
		return log;
	}

	private String validateBook(ArrayList<Book> books, Date start, Date end) {
		String msg = null;
		for (int i = 0; i < books.size(); i++) {
			Book b = books.get(i);
			if (!(start.getTime() >= b.getEnd().getTime() || end.getTime() <= b
					.getStart().getTime())) {
				msg = "'" + b.getEquip().getName() + "'已经被'" + b.getUser_name()
						+ "'预约并审批通过(" + sdf.format(b.getStart()) + "--"
						+ sdf.format(b.getEnd()) + ")";
				break;
			}
		}
		return msg;
	}

	@Override
	public String computerEndDateAndFee(String equipid, String startDate,
			int sample_mount) {
		JSONObject jo = new JSONObject();
		Equip equip = equipDao.getEquipById(equipid);
		if (equip == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍后再试！");
			return jo.toString();
		}
		try {
			String endDate = getEndDate(startDate, sample_mount
					* equip.getSampletime(), equip.getAppt1(),
					equip.getAppt2(), equip.getAppd());
			jo.put("result", true);
			jo.put("compute_fee", equip.isCharge() ? nf.format(sample_mount
					* equip.getSampletime() * equip.getFee()) : 0);
			jo.put("exp_time", nf.format((float) sample_mount
					* equip.getSampletime() / (float) 60)
					+ "H");
			jo.put("endDate", endDate);

			ArrayList<Book> books = null;
			String msg = null;
			Date nowDate = new Date();
			Date end = sdf.parse(endDate);
			Date start = sdf.parse(startDate);
			if (end.getTime() < nowDate.getTime())
				msg = "预约的结束时间不能小于现在时间！";
			if (msg == null && equip.isCheckd()) {//如果仪器需要检查是否同一时刻只能一个人预约，一般需要检查
				books = bookDao.getConfirmedBooksByEquipId(equipid, nowDate);
				for (int i = 0; i < books.size(); i++) {
					Book b = books.get(i);
					if (!(start.getTime() >= b.getEnd().getTime() || end
							.getTime() <= b.getStart().getTime())) {
						Account account = accountDao.getAccountById(b
								.getUser_id());
						String name = (account == null) ? "" : (account
								.getUsername()
						// + "(" + account.getNo() + ")" //還是不要讓用戶看到其他用戶的學號比較安全
								);
						msg = "'" + equip.getName() + "'已经被" + name
								+ "预约并审批通过(" + sdf.format(b.getStart()) + "--"
								+ sdf.format(b.getEnd()) + ")<br>您可查看'"
								+ equip.getName() + "'的设备预约列表，选择其他时间预约。";
						break;
					}
				}
			}

			if (msg != null) {
				jo.put("invalid", true);
				jo.put("invalidmsg", msg);
			} else
				jo.put("invalid", false);

			return jo.toString();
		} catch (Exception e) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍后再试！");
			return jo.toString();
		}
	}

	private String getEndDate(String startDate, int minutes, String appt1,
			String appt2, String appd) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date appt1d = df.parse(appt1);
		Date appt2d = df.parse(appt2);
		int t = (int) (appt2d.getTime() - appt1d.getTime()) / 1000 / 60;
		int ft = 24 * 60 - t;

		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(startDate));
		Calendar cc = Calendar.getInstance();
		cc.setTime(appt2d);

		JSONArray ja = JSONArray.fromObject(appd);

		for (int i = 0; i < minutes / t; i++) {
			c.add(Calendar.MINUTE, t);
			cc.set(Calendar.YEAR, c.get(Calendar.YEAR));
			cc.set(Calendar.MONTH, c.get(Calendar.MONTH));
			cc.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
			c = increaseDate(c, cc, ja, ft);
		}
		c.add(Calendar.MINUTE, minutes % t);
		cc.set(Calendar.YEAR, c.get(Calendar.YEAR));
		cc.set(Calendar.MONTH, c.get(Calendar.MONTH));
		cc.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
		c = increaseDate(c, cc, ja, ft);

		return sdf.format(c.getTime());
	}

	public Calendar increaseDate(Calendar c, Calendar cc, JSONArray ja, int ft)
			throws Exception {
		int counter = 0;
		while (true) {
			if (counter++ > 7)
				throw new Exception("7 days all not avaliable!!!");
			if (checkFDays(ja, c.get(Calendar.DAY_OF_WEEK) - 1)) {
				c.add(Calendar.DAY_OF_MONTH, 1);
				cc.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
				continue;
			} else if (c.getTimeInMillis() > cc.getTimeInMillis()) {
				c.add(Calendar.MINUTE, ft);
				cc.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
				continue;
			} else
				break;
		}

		return c;
	}

	private boolean checkFDays(JSONArray fday, int iday) {
		for (int i = 0; i < fday.size(); i++) {
			int day = fday.getInt(i);
			if (day == iday)
				return true;
		}
		return false;
	}

	@Override
	public String changeFee(String book_id, float fee, String remark,
			String admin_id) {
		JSONObject jo = new JSONObject();
		if (admin_id == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Account admin_account = accountDao.getAccountById(admin_id);
		if (admin_account == null) {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请确定您以管理员帐号登录！");
			return jo.toString();
		}
		Book book = bookDao.getBookById(book_id);
		book.setActual_fee(fee);
		book.setRemark(remark + "[" + admin_account.getUsername() + "/"
				+ sdf.format(new Date()) + "]");
		if (bookDao.saveBook(book)) {
			logDao.saveLog(generateLog(book, 3, admin_id, admin_account
					.getUsername(), ""));
			jo.put("result", true);
			jo.put("message", "您已经成功更改了实验费用!");
		} else {
			jo.put("result", false);
			jo.put("message", "对不起，出现错误，请稍候再试！");
		}
		return jo.toString();
	}

	@Override
	public Log getLogById(String id) {
		return logDao.getLogById(id);
	}

}
