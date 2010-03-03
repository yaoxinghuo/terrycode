package com.terry.weatherlib.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.terry.weatherlib.Weather;
import com.terry.weatherlib.WeatherCache;
import com.terry.weatherlib.data.impl.AccountDaoImpl;
import com.terry.weatherlib.data.impl.ScheduleDaoImpl;
import com.terry.weatherlib.data.intf.IAccountDao;
import com.terry.weatherlib.data.intf.IScheduleDao;
import com.terry.weatherlib.model.Account;
import com.terry.weatherlib.model.Schedule;
import com.terry.weatherlib.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Mar 1, 2010 12:36:37 AM
 */
public class WebManagerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -419330133682830958L;

	private static final String ERROR = "对不起，程序出现错误，请稍候再试";

	private static final int DEFAULT_SCHEDULES_LIMIT = 10;

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm",
			Locale.CHINA);

	private IScheduleDao scheduleDao = new ScheduleDaoImpl();
	private IAccountDao accountDao = new AccountDaoImpl();
	private UserService userService = UserServiceFactory.getUserService();

	@Override
	public void init() throws ServletException {
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		sdf2.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		JSONObject jo = null;
		if (userService.isUserLoggedIn()) {
			String action = req.getParameter("action");
			if (action != null) {
				if (action.equals("saveSchedule")) {
					jo = saveSchedule(req);
				} else if (action.equals("deleteSchedules")) {
					jo = deleteSchedules(req);
				} else if (action.equals("updateNickname")) {
					jo = updateNickanme(req);
				} else if (action.equals("getAccountInfo")) {
					jo = getAccountInfo();
				} else
					jo = schedulesList(req);
			}
		}

		resp.getWriter().println(jo.toString());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	private JSONObject schedulesList(HttpServletRequest req) {
		// 得到当前页数
		int page = Integer.parseInt(req.getParameter("page"));
		// 得到每页显示行数
		int limit = Integer.parseInt(req.getParameter("rp"));
		int start = (page - 1) * limit;

		String account = userService.getCurrentUser().getEmail();
		JSONObject jo = createDefaultJo();
		List<Schedule> schedules = scheduleDao.getSchedulesByAccount(account,
				start, limit);
		JSONArray rows = new JSONArray();
		for (Schedule s : schedules) {
			JSONObject jso = new JSONObject();
			JSONArray ja = new JSONArray();
			ja.put(sdf2.format(s.getCdate()));
			ja.put(s.getCity());
			ja.put(sdf.format(s.getSdate()));
			ja.put(s.getEmail());
			if (s.getType() == 0)
				ja.put("暂时停用");
			else if (s.getType() == 1)
				ja.put("天气内容放正文");
			else
				ja.put("天气内容放主题");
			if (s.getAdate() == null)
				ja.put("从未发送过");
			else
				ja.put(sdf2.format(s.getAdate()));
			ja.put(StringUtil.isEmptyOrWhitespace(s.getRemark()) ? "[无]" : s
					.getRemark());
			try {
				jso.put("id", s.getId());
				jso.put("cell", ja);
			} catch (JSONException e) {
			}
			rows.put(jso);
		}
		try {
			jo.put("total", scheduleDao.getScheduleCountByAccount(account));
			jo.put("page", page);
			jo.put("rows", rows);
			jo.put("result", true);
			jo.put("message", "ok");
		} catch (JSONException e) {
		}
		return jo;
	}

	private JSONObject saveSchedule(HttpServletRequest req) {
		JSONObject jo = createDefaultJo();

		String email = req.getParameter("email");
		String sdateS = req.getParameter("sdate");
		String city = req.getParameter("city");
		String remark = req.getParameter("remark");
		String typeS = req.getParameter("type");
		String sid = req.getParameter("sid");

		if (StringUtil.isEmptyOrWhitespace(email) || email.length() > 100
				|| StringUtil.isEmptyOrWhitespace(city) || city.length() > 12
				|| StringUtil.isEmptyOrWhitespace(sdateS)
				|| StringUtil.isEmptyOrWhitespace(typeS) || remark == null
				|| remark.length() > 100) {
			try {
				jo.put("message", "请检查必填栏位或是否符合长度规定");
			} catch (JSONException e) {
			}
			return jo;
		}

		if (!StringUtil.validateEmail(email)) {
			try {
				jo.put("message", "请检查邮件格式");
			} catch (JSONException e) {
			}
			return jo;
		}

		if (!StringUtil.isDigital(typeS)) {
			try {
				jo.put("message", "请检查状态");
			} catch (JSONException e) {
			}
			return jo;
		}
		int type = Integer.parseInt(typeS);
		if (type == 1 || type == 2)
			;
		else
			type = 0;

		String account = userService.getCurrentUser().getEmail();
		Account a = accountDao.getAccountByAccount(account);
		if (a == null) {
			try {
				jo.put("message", "非法操作");
			} catch (JSONException e) {
			}
			return jo;
		}

		if (scheduleDao.getScheduleCountByAccount(account) >= a.getSlimit()) {
			try {
				jo.put("message", "设置的定时数目已经达到上限:" + a.getSlimit()
						+ "，请删除一些定时设置后再试，或联系站长");
			} catch (JSONException e) {
			}
			return jo;
		}

		Date sdate = null;
		try {
			sdate = sdf.parse(sdateS);
			Calendar c = Calendar.getInstance();
			c.setTime(sdate);
			Calendar now = Calendar.getInstance();
			c.set(Calendar.YEAR, now.get(Calendar.YEAR));
			c.set(Calendar.MONTH, now.get(Calendar.MONTH));
			c.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
			if (c.getTimeInMillis() < now.getTimeInMillis())
				c.add(Calendar.DAY_OF_YEAR, 1);// 如果小于现在时间，就再加一天，第二天再发
			sdate = c.getTime();
		} catch (ParseException e1) {
			try {
				jo.put("message", "请检查发送时间");
			} catch (JSONException e) {
			}
			return jo;
		}

		Weather w = WeatherCache.queryWeather(city);
		if (w == null) {
			try {
				jo.put("message", "无法获取“" + city
						+ "”的天气，所以未能保存您的定时设置，请检查您的输入并稍候再试");
			} catch (JSONException e) {
			}
			return jo;
		}
		boolean result = false;
		if (!StringUtil.isEmptyOrWhitespace(sid)) {
			result = scheduleDao.updateScheduleById(sid, email, city, sdate,
					type, remark);
		} else {
			Schedule s = new Schedule();
			s.setAccount(account);
			s.setAdate(null);
			s.setCdate(new Date());
			s.setCity(city.trim());
			s.setEmail(email);
			s.setRemark(remark.trim());
			s.setSdate(sdate);
			s.setType(type);
			result = scheduleDao.saveSchedule(s);
		}
		try {
			jo.put("result", result);
			jo.put("message", result ? "已成功保存“" + city + "”的天气预报定时设置" : ERROR);
		} catch (JSONException e) {
		}
		return jo;
	}

	private JSONObject deleteSchedules(HttpServletRequest req) {
		JSONObject jo = createDefaultJo();
		String[] ids = req.getParameter("ids").split(",");
		String account = userService.getCurrentUser().getEmail();
		int total = 0;
		for (String id : ids) {
			if (!StringUtil.isEmptyOrWhitespace(id))
				if (scheduleDao.deleteScheduleByIdAndAccount(id, account))
					total++;
		}
		try {
			jo.put("result", total > 0);
			String message = total > 0 ? "您已成功删除" + total + "条天气预报设置" : ERROR;
			jo.put("message", message);
			jo.put("affected", total);
		} catch (JSONException e) {

		}
		return jo;
	}

	private JSONObject getAccountInfo() {
		JSONObject jo = createDefaultJo();
		String account = userService.getCurrentUser().getEmail();
		Account a = accountDao.getAccountByAccount(account);
		Date now = new Date();
		if (a == null) {
			a = new Account();
			a.setSlimit(DEFAULT_SCHEDULES_LIMIT);
			a.setAccount(account);
			a.setNickname("天气预报");
			a.setCdate(now);
			a.setUdate(now);
			if (!accountDao.saveAccount(a))
				return jo;
		}
		try {
			jo.put("result", true);
			jo.put("message", "ok");
			jo.put("nickname", a.getNickname());
			jo.put("slimit", a.getSlimit());
			jo.put("cdate", sdf2.format(a.getCdate()));
			jo.put("udate", sdf2.format(a.getUdate()));
			jo.put("count", scheduleDao.getScheduleCountByAccount(account));
		} catch (JSONException e) {
		}
		return jo;
	}

	private JSONObject updateNickanme(HttpServletRequest req) {
		JSONObject jo = createDefaultJo();
		String nickname = req.getParameter("nickname");
		if (StringUtil.isEmptyOrWhitespace(nickname) || nickname.length() > 12) {
			try {
				jo.put("message", "请检查您的输入");
			} catch (JSONException e) {
			}
			return jo;
		}
		if (accountDao.updateAccountNickname(userService.getCurrentUser()
				.getEmail(), nickname)) {
			try {
				jo.put("result", true);
				jo.put("message", "已成功更新邮件发送时昵称为：" + nickname);
			} catch (JSONException e) {
			}
		}
		return jo;
	}

	private JSONObject createDefaultJo() {
		JSONObject jo = new JSONObject();
		try {
			jo.put("result", false);
			jo.put("message", ERROR);
		} catch (JSONException e) {
		}
		return jo;
	}

}
