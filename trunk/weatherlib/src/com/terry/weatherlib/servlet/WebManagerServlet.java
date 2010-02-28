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
		JSONObject jo = new JSONObject();
		if (!userService.isUserLoggedIn())
			return;
		List<Schedule> schedules = scheduleDao
				.getSchedulesByAccount(userService.getCurrentUser().getEmail());
		JSONArray rows = new JSONArray();
		for(Schedule s:schedules){
			JSONArray ja = new JSONArray();
			ja.put(s.getId());
			ja.put(sdf2.format(s.getCdate()));
			ja.put(s.getCity());
			ja.put(sdf.format(s.getSdate()));
			ja.put(s.getEmail());
			ja.put(s.getType());
			ja.put(s.getRemark());
			ja.put(sdf2.format(s.getAdate()));
			ja.put(s.getEmail());
			rows.put(ja);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
