package com.terry.costnote.data.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		if (schedule == null) {
			schedule = new Schedule();
			schedule.setCdate(new Date());
			schedule.setEmail(email);
			try {
				schedule.setAdate(sdf2.parse(jo.getString("date")));
			} catch (ParseException e) {
				return false;
			}
		}
		schedule.setMessage(jo.getString("message"));
		String sid = fetchToSaveSchedule(account.getMobile(), schedule
				.getMessage(), jo.getString("date"));
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
	public boolean deleteSchedule(String scheduleId) {
		Schedule schedule = scheduleDao.getScheduleById(scheduleId);
		if (!fetchToDeleteSchedule(schedule.getSid()))
			return false;
		return scheduleDao.deleteSchedule(schedule);
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

	@SuppressWarnings("unchecked")
	@Override
	public String getAccountInfo(String email) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null) {
			account = new Account();
			account.setAlertLimit(0);
			account.setCdate(new Date());
			account.setEmail(email);
			account.setLastSendAlert(new Date());
			account.setMobile("");
			account.setMpassword("");
			account.setSendAlert(false);
			account.setActivate(false);
			account.setVerifyCode("");
			account.setNickname(email);
			if (!accountDao.saveAccount(account))
				return "";
		}

		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		if (cache != null) {
			Stack<String> stack = (Stack<String>) cache.get(email);
			if (stack != null) {
				while (!stack.isEmpty())
					ja.add(stack.pop());
			}
		}
		if (ja.size() == 0) {
			List<Cost> costs = costDao.getCostsByEmail(email, 0, 10);
			Stack<String> stack = new Stack<String>();
			for (Cost cost : costs) {
				String name = cost.getName();
				if (!stack.contains(name)) {
					stack.push(name);
				}
			}

			if (stack.size() > 0 && cache != null) {
				cache.put(email, stack);
			}

			Stack<String> stack2 = (Stack<String>) cache.get(email);
			if (stack2 != null) {
				while (!stack2.isEmpty())
					ja.add(stack2.pop());
			}

		}
		jo.put("suggest", ja);
		jo.put("nickname", account.getNickname());
		return jo.toString();
	}

	private String fetchToSaveSchedule(String mobile, String message,
			String date) {
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
			String content = "mobile=13916416465" + "&password=1qaz2wsx"
					+ "&friend=" + mobile + "&schedule="
					+ date.replace(" ", "%20") + "&message="
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
			}
			connection.disconnect();
		} catch (Exception e) {
			return null;
		}

		return null;
	}

	private boolean fetchToDeleteSchedule(String sid) {
		try {
			URL postUrl = new URL(
					"https://fetionlib.appspot.com/restlet/fetion/schedule/delete");
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
			String content = "mobile=13916416465"
					+ "&password=1qaz2wsx"
					+ "&friend="
					+ "&message="
					+ URLEncoder.encode("网络记帐本--短信验证码:"
							+ " 网址:http://costnote.appspot.com/", "utf-8");
			out.writeBytes(content);

			out.flush();
			out.close();

			int responseCode = connection.getResponseCode();
			if (responseCode == 202)
				return true;
			connection.disconnect();
		} catch (Exception e) {
			return false;
		}

		return false;
	}

}
