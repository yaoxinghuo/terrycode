package com.terry.botmail.servlet;

import java.io.IOException;
import java.text.ParseException;
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
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.terry.botmail.data.impl.ScheduleDaoImpl;
import com.terry.botmail.data.intf.IScheduleDao;
import com.terry.botmail.model.Schedule;
import com.terry.botmail.util.MailSender;
import com.terry.botmail.util.StringUtil;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-2 下午04:03:50
 */
public class BotmailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4449085355159990305L;

	private XMPPService xmpp = XMPPServiceFactory.getXMPPService();

	private IScheduleDao scheduleDao = new ScheduleDaoImpl();

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm",
			Locale.CHINA);

	private Cache cache;

	private static final String HELP = "标准格式为：手机号[空格]标题[空格]内容";
	private static final String HELP_TYPE_CHOICE = "输入1每年发送";
	private static final String ERROR = "Sorry, error occured, please try again later.";

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

		Message msg = new MessageBuilder().withRecipientJids(jid).withBody(
				getResponse(jids, message.getBody())).build();

		if (xmpp.getPresence(jid).isAvailable()) {
			xmpp.sendMessage(msg);
		}
	}

	@SuppressWarnings("unchecked")
	private String getResponse(String account, String body) {
		if (cache == null)
			return ERROR;

		Object o = cache.get(account);
		if (o != null && o instanceof Schedule) {
			Schedule schedule = (Schedule) o;
			Calendar c = Calendar.getInstance();
			c.setTime(schedule.getSdate());
			Calendar now = Calendar.getInstance();
			c.set(Calendar.YEAR, now.get(Calendar.YEAR));
			c.set(Calendar.MONTH, now.get(Calendar.MONTH));
			c.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
			String t = null;
			if (body.equals("0")) {
				cache.remove(account);
				return "您已取消设置\r\n" + HELP;
			} else if (body.equals("1")) {
				if (c.getTimeInMillis() < now.getTimeInMillis())
					c.add(Calendar.YEAR, 1);
				t = "年";
				schedule.setType(1);
			} else if (body.equals("2")) {
				if (c.getTimeInMillis() < now.getTimeInMillis())
					c.add(Calendar.MONTH, 1);
				t = "月";
				schedule.setType(2);
			} else if (body.equals("3")) {
				if (c.getTimeInMillis() < now.getTimeInMillis())
					c.add(Calendar.WEEK_OF_YEAR, 1);
				t = "周";
				schedule.setType(3);
			} else if (body.equals("4")) {
				if (c.getTimeInMillis() < now.getTimeInMillis())
					c.add(Calendar.DAY_OF_YEAR, 1);
				t = "天";
				schedule.setType(4);
			} else if (body.equals("9")) {
				String email = schedule.getMobile() + "@139.com";
				cache.remove(account);
				try {
					MailSender.sendMail(email, schedule.getSubject(), schedule
							.getContent());
					return "邮件已即时发送：" + email;
				} catch (Exception e) {
					return "对不起，邮件未发送：" + email + "，错误：" + e.getMessage();
				}
			}
			if (t == null) {
				return HELP_TYPE_CHOICE;
			} else {
				cache.remove(account);
				schedule.setSdate(c.getTime());
				if (scheduleDao.saveSchedule(schedule)) {
					return "您成功设置定时每" + t + "发送，下次发送日期约为："
							+ sdf2.format(schedule.getSdate());
				} else
					return "对不起，出现错误，请稍候再试！";
			}
		}
		if (o != null && o instanceof HashMap<?, ?>) {
			HashMap<Integer, String> map = (HashMap<Integer, String>) o;
			if (body.equals("0")) {
				cache.remove(account);
				return "您已取消设置\r\n" + HELP;
			} else {
				if (!StringUtil.isDigital(body)) {
					return "请输入有效的数字来删除";
				}
				int index = Integer.parseInt(body);
				String id = map.get(index);
				if (id == null)
					return "对不起，找不到该序号的定时设置";
				else {
					if (scheduleDao.deleteScheduleById(id))
						return "您已成功删除定时设置，输入数字继续删除，按0返回";
					else
						return "对不起，出现错误，请稍候再试！";
				}
			}
		}

		if (StringUtil.isEmptyOrWhitespace(body))
			return HELP;
		if (body.equalsIgnoreCase("s")) {
			return generateScheduleListResponse(account, scheduleDao
					.getSchedulesByAccount(account));
		}
		String[] parts = body.split("\\s", 4);
		if (parts.length < 3)
			return HELP;
		if (parts.length == 4 && parts[0].replace("：", ":").contains(":")) {
			if (scheduleDao.getScheduleCount(account) >= 10) {
				return "对不起，您设置的定时数目已经达到上限，请删除一些再试！";
			}
			try {
				Schedule schedule = new Schedule();
				Date date = sdf.parse(parts[0]);
				if (!StringUtil.isChinaMobile(parts[1]))
					return parts[1] + " 不是有效的中国移动手机号码！";
				schedule.setSdate(date);
				schedule.setAccount(account);
				schedule.setAdate(null);
				schedule.setCdate(new Date());
				schedule.setMobile(parts[1]);
				schedule.setSubject(parts[2]);
				schedule.setContent(parts[3]);

				cache.put(account, schedule);

				return "您输入发送时间为 " + parts[0] + "\r\n" + HELP_TYPE_CHOICE;
			} catch (ParseException e) {
				return parts[0] + " 不是有效的时间格式！标准格式为XX:YY";
			}
		}

		if (parts.length == 4)
			parts = body.split("\\s", 3);

		if (!StringUtil.isChinaMobile(parts[0]))
			return parts[0] + " 不是有效的中国移动手机号码！";
		String email = parts[0] + "@139.com";
		try {
			MailSender.sendMail(email, parts[1], parts[2]);
			return "邮件已发送：" + email;
		} catch (Exception e) {
			return "对不起，邮件未发送：" + email + "，错误：" + e.getMessage();
		}
	}

	@SuppressWarnings("unchecked")
	private String generateScheduleListResponse(String account,
			List<Schedule> schedules) {
		if (schedules == null || schedules.size() == 0)
			return "您尚未设置定时";
		StringBuffer sb = new StringBuffer();
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		int count = 0;
		for (Schedule schedule : schedules) {
			count++;
			map.put(count, schedule.getId());
			sb.append("\r\n").append(count).append(" ").append(
					schedule.getSubject());
		}
		cache.put(account, map);
		return sb.toString();
	}

}
