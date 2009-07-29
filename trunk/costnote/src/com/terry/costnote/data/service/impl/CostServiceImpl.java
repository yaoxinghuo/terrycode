package com.terry.costnote.data.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.terry.costnote.data.dao.intf.IAccountDao;
import com.terry.costnote.data.dao.intf.ICostDao;
import com.terry.costnote.data.dao.intf.IScheduleDao;
import com.terry.costnote.data.model.Account;
import com.terry.costnote.data.model.Cost;
import com.terry.costnote.data.model.Schedule;
import com.terry.costnote.data.service.intf.ICostService;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:26:55 AM
 */

@Service("costService")
@Repository
public class CostServiceImpl implements ICostService {

	private static Log log = LogFactory.getLog(CostServiceImpl.class);

	private static final int tryTimes = 5;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private Cache cache;

	public CostServiceImpl() {
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					Collections.emptyMap());
		} catch (CacheException e) {
		}
	}

	@Autowired
	private ICostDao costDao;

	@Autowired
	private IAccountDao accountDao;

	@Autowired
	private IScheduleDao scheduleDao;

	@Override
	public boolean saveCost(String email, String c) {
		JSONObject jo = JSONObject.fromObject(c);
		String name = jo.getString("name");
		Cost cost = null;
		if (!jo.getString("id").equals(""))
			cost = costDao.getCostById(jo.getString("id"));
		if (cost == null) {
			cost = new Cost();
			cost.setCdate(new Date());
			cost.setEmail(email);
		}
		Date adate = new Date();
		try {
			adate = sdf.parse(jo.getString("date"));
		} catch (ParseException e) {
		}
		cost.setAdate(adate);
		cost.setName(name);
		cost.setAmount(jo.getDouble("amount"));
		cost.setRemark(jo.getString("remark"));
		cost.setType(jo.getInt("type"));
		if (costDao.saveCost(cost)) {
			addNameToCache(email, name);
			return true;
		} else
			return false;
	}

	@Override
	public boolean saveSchedule(String email, String c) {
		JSONObject jo = JSONObject.fromObject(c);
		Account account = accountDao.getAccountByEmail(email);
		Schedule schedule = null;
		if (!jo.getString("id").equals("")) {
			schedule = scheduleDao.getScheduleById(jo.getString("id"));
			return false;
		}
		Calendar cal = Calendar.getInstance();
		if (schedule == null) {
			schedule = new Schedule();
			schedule.setCdate(new Date());
			schedule.setEmail(email);
			try {
				Date date = sdf2.parse(jo.getString("date"));
				cal.setTime(date);
				cal.add(Calendar.HOUR_OF_DAY, -8);// 时区的问题，-8
				schedule.setAdate(date);
			} catch (ParseException e) {
				return false;
			}
		}
		schedule.setMessage(jo.getString("message"));
		String sid = fetchToSaveSchedule(account.getMobile(), schedule
				.getMessage(), sdf2.format(cal.getTime()));
		if (sid == null) {
			return false;
		}
		schedule.setSid(sid);
		if (scheduleDao.saveSchedule(schedule)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Cost> getCostsByEmail(String email, Date sfrom, Date sto,
			int stype, int start, int limit) {
		return costDao.getCostsByEmail(email, sfrom, sto, stype, start, limit);
	}

	@Override
	public long getCostsCountByEmail(String email, Date sfrom, Date sto,
			int stype) {
		return costDao.getCostsCountByEmail(email, sfrom, sto, stype);
	}

	@Override
	public List<Schedule> getSchedulesByEmail(String email, Date sfrom,
			Date sto, int start, int limit) {
		return scheduleDao.getSchedulesByEmail(email, sfrom, sto, start, limit);
	}

	@Override
	public long getSchedulesCountByEmail(String email, Date sfrom, Date sto) {
		return scheduleDao.getSchedulesCountByEmail(email, sfrom, sto);
	}

	@Override
	public boolean deleteCost(String costIds) {
		JSONArray ja = JSONArray.fromObject(costIds);
		boolean result = false;
		for (int i = 0; i < ja.size(); i++) {
			Cost cost = costDao.getCostById(ja.getString(i));
			if (costDao.deleteCost(cost))
				result = true;
		}
		return result;
	}

	@Override
	public boolean deleteSchedule(String scheduleIds) {
		JSONArray ja = JSONArray.fromObject(scheduleIds);
		JSONArray sids = new JSONArray();
		for (int i = 0; i < ja.size(); i++) {
			Schedule schedule = scheduleDao.getScheduleById(ja.getString(i));
			if (schedule.getAdate().getTime() > new Date().getTime())
				sids.add(schedule.getSid());
		}

		if (!fetchToDeleteSchedule(sids.toString())) {
			return false;
		}
		int count = 0;
		for (int i = 0; i < ja.size(); i++) {
			Schedule schedule = scheduleDao.getScheduleById(ja.getString(i));
			if (scheduleDao.deleteSchedule(schedule))
				count++;
		}
		return count != 0;
	}

	@SuppressWarnings("unchecked")
	private void addNameToCache(String email, String name) {
		if (cache != null) {
			Stack<String> stack = (Stack<String>) cache.get(email);
			if (stack == null)
				stack = new Stack<String>();
			if (stack.contains(name))
				stack.remove(name);
			stack.push(name);
			if (stack.size() > 10)
				stack.remove(0);
			cache.put(email, stack);
		}
	}

	private String fetchToSaveSchedule(String mobile, String message,
			String date) {
		String uuid = UUID.randomUUID().toString();
		for (int i = 0; i < tryTimes; i++) {
			try {
				URL postUrl = new URL(
						"https://fetionlib.appspot.com/restlet/fetion/schedule");
				HttpURLConnection connection = (HttpURLConnection) postUrl
						.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				connection.connect();
				DataOutputStream out = new DataOutputStream(connection
						.getOutputStream());
				String content = "mobile=13916416465" + "&uuid=" + uuid
						+ "&password=1qaz2wsx" + "&friend=" + mobile
						+ "&schedule=" + date.replace(" ", "%20") + "&message="
						+ URLEncoder.encode(message, "utf-8");
				out.writeBytes(content);

				out.flush();
				out.close();
				int responseCode = connection.getResponseCode();
				if (responseCode == 202) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(connection.getInputStream())); // 读取结果
					StringBuffer sb = new StringBuffer();
					String line;
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}
					reader.close();
					connection.disconnect();
					JSONObject jo = JSONObject.fromObject(sb.toString());
					return jo.getString("sid");
				} else {
					connection.disconnect();
					return null;
				}
			} catch (Exception e) {
				log.warn("error fetchToSaveSchedule, exception:"
						+ e.getMessage() + ". tried " + i + " times");
				if (!e.getMessage().contains("Unknown"))
					return null;
			}
		}
		return null;
	}

	private boolean fetchToDeleteSchedule(String sids) {
		String uuid = UUID.randomUUID().toString();
		for (int i = 0; i < tryTimes; i++) {
			try {
				URL postUrl = new URL(
						"https://fetionlib.appspot.com/restlet/fetion/scheduleDelete");
				HttpURLConnection connection = (HttpURLConnection) postUrl
						.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				connection.connect();
				DataOutputStream out = new DataOutputStream(connection
						.getOutputStream());
				String content = "mobile=13916416465" + "&uuid=" + uuid
						+ "&password=1qaz2wsx" + "&sids=" + sids;
				out.writeBytes(content);

				out.flush();
				out.close();

				int responseCode = connection.getResponseCode();
				connection.disconnect();
				if (responseCode == 202)
					return true;
				else
					return false;
			} catch (Exception e) {
				log.warn("error fetchToDeleteSchedule, exception:"
						+ e.getMessage() + ". tried " + i + " times");
				if (e.getMessage().contains("Unknown"))
					return true;
			}
		}
		return false;
	}
}
