package com.terry.weatherlib.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.terry.weatherlib.Weather;
import com.terry.weatherlib.WeatherCache;
import com.terry.weatherlib.data.impl.AccountDaoImpl;
import com.terry.weatherlib.data.impl.ScheduleDaoImpl;
import com.terry.weatherlib.data.intf.IAccountDao;
import com.terry.weatherlib.data.intf.IScheduleDao;
import com.terry.weatherlib.model.Account;
import com.terry.weatherlib.model.Schedule;
import com.terry.weatherlib.util.StringUtil;
import com.terry.weatherlib.util.XMPPSender;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-10 下午12:01:48
 */
public class XMPPServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5823820537706460688L;

	private XMPPService xmpp = XMPPServiceFactory.getXMPPService();

	private IScheduleDao scheduleDao = new ScheduleDaoImpl();
	private IAccountDao accountDao = new AccountDaoImpl();

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm",
			Locale.CHINA);

	private Cache cache;

	private static final String WEATHER_ERROR = "对不起，无法获取天气预报，请稍候再试！";
	private static final String ERROR = "对不起，程序出现错误，请稍候再试";
	private static final int DEFAULT_SCHEDULES_LIMIT = 10;

	private static final String HELP = "直接输入tq+城市名称或拼音将直接显示天气预报，"
			+ "\r\n若要定制每天定时邮件提醒，请输入如下标准格式："
			+ "\r\n每天发送时间(如20:00)[空格]手机号或Email[空格]城市名称"
			+ "\r\n输入list或00查看已设置定时列表" + "\r\n输入account或000查看或修改账户信息"
			+ "\r\n任何时候输入0取消当前操作";

	@Override
	public void init() throws ServletException {
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		sdf2.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		Map<Integer, Integer> props = new HashMap<Integer, Integer>();
		props.put(GCacheFactory.EXPIRATION_DELTA, 600);
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					props);
		} catch (CacheException e) {
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		Message message = xmpp.parseMessage(req);

		JID jid = message.getFromJid();
		String jids = jid.getId();
		if (jids.indexOf("/") != -1)
			jids = jids.substring(0, jids.indexOf("/"));

		XMPPSender.sendXMPP(jid, getResponse(jids, message.getBody()));
	}

	@SuppressWarnings("unchecked")
	private String getResponse(String account, String body) {
		Object o = cache.get(account);
		if (body.equals("0")) {
			if (o != null) {
				cache.remove(account);
				return "已取消。" + HELP;
			} else
				return HELP;
		}

		if (o != null && o instanceof HashMap<?, ?>) {
			HashMap<Integer, String> map = (HashMap<Integer, String>) o;
			if (!StringUtil.isDigital(body)) {
				return "请重新输入有效的序号以删除定时设置";
			}
			int index = Integer.parseInt(body);
			String id = map.get(index);
			if (id == null)
				return "对不起，找不到该序号的定时设置，请重新输入序号";
			else {
				if (scheduleDao.deleteScheduleById(id))
					return "您已成功删除定时设置，输入序号继续删除，按0返回";
				else
					return "对不起，找不到该序号的定时设置，可能已经删除";
			}
		}
		if (o != null && o instanceof Account) {
			if (body.length() > 20)
				return "请重新输入少于20个字的邮件发送人昵称，输入0返回";
			else {
				Account acc = (Account) o;
				acc.setNickname(body.trim());
				cache.remove(account);
				if (accountDao.updateAccountNickname(account, body))
					return "您已成功设置邮件发送人昵称为：" + body + "，输入0返回";
				else
					return ERROR;
			}
		}

		if (StringUtil.isEmptyOrWhitespace(body))
			return HELP;
		if (body.equalsIgnoreCase("list") || body.equalsIgnoreCase("00")) {
			return generateScheduleListResponse(account, scheduleDao
					.getSchedulesByAccount(account));
		}
		if (body.equalsIgnoreCase("account") || body.equalsIgnoreCase("000")) {
			Account acc = checkAndGetAccount(account);
			cache.put(account, acc);
			StringBuffer sb = new StringBuffer("");
			if (acc.getCdate().getTime() == acc.getUdate().getTime())
				sb.append("您从未设置过定时天气预报邮件");
			else {
				sb.append("您共有"
						+ scheduleDao.getScheduleCountByAccount(account)
						+ "条定时天气预报邮件设置，");
				sb.append("最近一次设置定时邮件时间为：").append(sdf2.format(acc.getUdate()));
			}
			sb.append("\r\n邮件发送人昵称为：").append(acc.getNickname()).append(
					"\r\n设置定时邮件的限额为：少于").append(acc.getSlimit()).append("条");
			sb.append("\r\n").append(
					"请直接输入昵称以修改现有的昵称（少于20个字）：" + acc.getNickname());
			sb.append("\r\n输入0返回");
			return sb.toString();
		}

		if (body.toLowerCase().startsWith("tq")) {
			if (body.trim().length() < 3) {
				return "请在tq后面输入城市名称或者拼音";
			} else {
				String city = body.substring(2).trim();
				if (city.length() > 12)
					return ERROR;
				Weather w = WeatherCache.queryWeather(city);
				if (w == null)
					return WEATHER_ERROR;
				return w.getReport();
			}
		}

		String[] parts = body.split("\\s", 3);
		if (parts.length < 3)
			return HELP;

		Account acc = checkAndGetAccount(account);
		if (scheduleDao.getScheduleCountByAccount(account) >= acc.getSlimit())
			return "对不起，您" + "设置的定时数目已经达到上限，请删除一些定时设置后再试";

		Date sdate = new Date();
		try {
			sdate = sdf.parse(parts[0].replace("：", ":"));
		} catch (Exception e) {
			return parts[0] + "不是有效的时间格式，正确的类似20:00";
		}
		String email = parts[1];
		if (StringUtil.isDigital(parts[1])) {
			if (!StringUtil.isChinaMobile(parts[1]))
				return parts[1] + "不是有效的中国移动手机号码";
			else
				email = parts[1] + "@139.com";
		} else {
			if (!StringUtil.validateEmail(parts[1]))
				return parts[1] + "不是有效的邮件地址";
		}
		Weather w = WeatherCache.queryWeather(parts[2]);
		if (w == null)
			return "无法获取“" + parts[2] + "”的天气，所以未能保存您的定时设置，请检查您的输入并稍候再试";
		else {
			Schedule schedule = new Schedule();
			Calendar c = Calendar.getInstance();
			c.setTime(sdate);
			Calendar now = Calendar.getInstance();
			c.set(Calendar.YEAR, now.get(Calendar.YEAR));
			c.set(Calendar.MONTH, now.get(Calendar.MONTH));
			c.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
			if (c.getTimeInMillis() < now.getTimeInMillis())
				c.add(Calendar.DAY_OF_YEAR, 1);// 如果小于现在时间，就再加一天，第二天再发
			sdate = c.getTime();
			schedule.setSdate(sdate);
			schedule.setAccount(account);
			schedule.setAdate(null);
			schedule.setCdate(new Date());
			schedule.setEmail(email);
			schedule.setCity(parts[2]);
			schedule.setType(0);
			schedule.setRemark("");
			schedule.setNickname(acc.getNickname());
			if (!scheduleDao.saveSchedule(schedule))
				return ERROR;
			else {
				accountDao.updateAccountUdate(account);
				return "天气预报定时设置已成功保存，程序会在每天" + parts[0] + "将“" + parts[2]
						+ "”的天气预报发往" + email
						+ "\r\n若您使用139邮箱可到邮箱设置“长短信”邮件通知，以便能在手机上看到正文的天气内容"
						+ "\r\n" + w.getReport();
			}
		}

	}

	@SuppressWarnings("unchecked")
	private String generateScheduleListResponse(String account,
			List<Schedule> schedules) {
		if (schedules == null || schedules.size() == 0)
			return "您尚未设置定时天气预报提醒邮件";
		StringBuffer sb = new StringBuffer("显示您现有的定时列表，直接输入序号数字将删除该定时设置，输入0返回");
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		int count = 0;
		for (Schedule schedule : schedules) {
			count++;
			map.put(count, schedule.getId());
			sb.append("\r\n").append(count).append(" ");
			sb.append("每天").append(sdf.format(schedule.getSdate())).append(
					"发送天气预报邮件至").append(schedule.getEmail()).append(
					"，定制城市为“" + schedule.getCity() + "”");
		}
		cache.put(account, map);
		return sb.toString();
	}

	private Account checkAndGetAccount(String a) {
		Account account = accountDao.getAccountByAccount(a);
		Date now = new Date();
		if (account == null) {
			account = new Account();
			account.setSlimit(DEFAULT_SCHEDULES_LIMIT);
			account.setAccount(a);
			account.setNickname("天气预报");
			account.setCdate(now);
			account.setUdate(now);
			accountDao.saveAccount(account);
			return account;
		} else {
			return account;
		}
	}
}
