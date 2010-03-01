package com.terry.weatherlib.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.terry.weatherlib.data.impl.ScheduleDaoImpl;
import com.terry.weatherlib.data.intf.IScheduleDao;
import com.terry.weatherlib.model.Schedule;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Mar 1, 2010 12:36:37 AM
 */
public class WebManagerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -419330133682830958L;

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm",
			Locale.CHINA);

	private IScheduleDao scheduleDao = new ScheduleDaoImpl();
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
		resp.setContentType("text/html;charset=UTF-8");

		JSONObject jo = new JSONObject();
		if (!userService.isUserLoggedIn())
			return;
		List<Schedule> schedules = scheduleDao
				.getSchedulesByAccount(userService.getCurrentUser().getEmail());
		JSONArray rows = new JSONArray();
		for (Schedule s : schedules) {
			JSONObject jso = new JSONObject();
			JSONArray ja = new JSONArray();
			ja.put(sdf2.format(s.getCdate()));
			ja.put(s.getCity());
			ja.put(sdf.format(s.getSdate()));
			ja.put(s.getEmail());
			if (s.getType() == 0)
				ja.put("暂停");
			else if (s.getType() == 1)
				ja.put("邮件主题接收");
			else
				ja.put("邮件正文接收");
			ja.put(s.getRemark());
			ja.put(sdf2.format(s.getAdate()));
			try {
				jso.put("id", s.getId());
				jso.put("cell", ja);
			} catch (JSONException e) {
			}
			rows.put(jso);
		}
		try {
			jo.put("total", schedules == null ? 0 : schedules.size());
			jo.put("page", 1);
			jo.put("rows", rows);
		} catch (JSONException e) {
		}

		resp.setContentType("application/json");
		resp.getWriter().println(jo.toString());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
