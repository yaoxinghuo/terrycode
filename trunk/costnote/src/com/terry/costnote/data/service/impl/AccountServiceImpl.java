package com.terry.costnote.data.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import org.springframework.transaction.annotation.Transactional;

import com.terry.costnote.data.dao.intf.IAccountDao;
import com.terry.costnote.data.dao.intf.ICostDao;
import com.terry.costnote.data.model.Account;
import com.terry.costnote.data.model.Cost;
import com.terry.costnote.data.service.intf.IAccountService;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 15, 2009 2:02:13 PM
 */

@Service("accountService")
@Repository
@Transactional
public class AccountServiceImpl implements IAccountService {

	private static Log log = LogFactory.getLog(AccountServiceImpl.class);

	private static final int tryTimes = 5;

	@Autowired
	private ICostDao costDao;

	@Autowired
	private IAccountDao accountDao;

	private Cache cache;

	public AccountServiceImpl() {
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					Collections.emptyMap());
		} catch (CacheException e) {
		}
	}

	@Override
	public boolean updateAccount(String email, String a) {
		JSONObject jo = JSONObject.fromObject(a);
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return false;
		account.setAlertLimit(jo.getDouble("alertLimit"));
		account.setMobile(jo.getString("mobile"));
		account.setMpassword(jo.getString("mpassword"));
		account.setNickname(jo.getString("nickname"));
		account.setSendAlert(jo.getBoolean("sendAlert"));
		return accountDao.saveAccount(account);
	}

	@Override
	/*
	 * -1 错误 0 已加过 1OK
	 */
	public int addFriend(String email, String mobile) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return -1;

		int action = fetchToAddFriend(mobile);
		if (action == -1)
			return -1;
		account.setActivate(false);
		account.setVerifyCode("");
		account.setMobile(mobile);
		account.setMpassword("1qaz2wsx");
		if (accountDao.saveAccount(account))
			return action;
		else
			return -1;
	}

	@Override
	public boolean sendVerifyCode(String email, String mobile) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return false;
		Random random = new Random();
		String verifyCode = "";
		for (int i = 0; i < 6; i++) {
			verifyCode = verifyCode + random.nextInt(10);
		}

		if (!fetchToSendSMS(mobile, verifyCode))
			return false;
		account.setActivate(false);
		account.setVerifyCode(verifyCode);
		account.setMobile(mobile);
		account.setMpassword("1qaz2wsx");
		return accountDao.saveAccount(account);
	}

	@Override
	public boolean verifyCode(String email, String code) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return false;
		if (!account.getVerifyCode().equals(code))
			return false;
		account.setActivate(true);
		return accountDao.saveAccount(account);
	}

	@Override
	public boolean updateAccountBasic(String email, String nickname) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return false;
		account.setNickname(nickname);
		return accountDao.saveAccount(account);
	}

	@Override
	public boolean updateAccountSms(String email, boolean sendAlert,
			double alertLimit) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return false;
		account.setSendAlert(sendAlert);
		account.setAlertLimit(alertLimit);
		return accountDao.saveAccount(account);
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
		jo.put("email", account.getEmail());
		jo.put("mobile", account.getMobile());
		jo.put("mpassword", account.getMpassword());
		jo.put("verifyCode", account.getVerifyCode());
		jo.put("alertLimit", account.getAlertLimit());
		jo.put("sendAlert", account.isSendAlert());
		jo.put("activate", account.isActivate());
		return jo.toString();
	}

	private boolean fetchToSendSMS(String mobile, String verifyCode) {
		String uuid = UUID.randomUUID().toString();
		for (int i = 0; i < tryTimes; i++) {
			int responseCode = 0;
			try {
				URL postUrl = new URL(
						"https://fetionlib.appspot.com/restlet/fetion");
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
						+ "&uuid="
						+ uuid
						+ "&password=1qaz2wsx"
						+ "&friend="
						+ mobile
						+ "&message="
						+ URLEncoder.encode("网络记帐本--短信验证码:" + verifyCode
								+ " 网址:http://costnote.appspot.com/", "utf-8");
				out.writeBytes(content);

				out.flush();
				out.close();

				responseCode = connection.getResponseCode();
				connection.disconnect();
				if (responseCode == 202)
					return true;
				else
					return false;
			} catch (Exception e) {
				log.warn("error fetchToSendSMS, exception:" + e.getMessage()
						+ ". tried " + i + " times");
				if (!e.getMessage().contains("Unknown"))
					return false;
			}
		}
		return false;
	}

	private int fetchToAddFriend(String mobile) {
		String uuid = UUID.randomUUID().toString();
		for (int i = 0; i < tryTimes; i++) {
			int responseCode = 0;
			try {
				URL postUrl = new URL(
						"https://fetionlib.appspot.com/restlet/fetion/friend");
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
						+ "&password=1qaz2wsx" + "&friend=" + mobile + "&desc="
						+ URLEncoder.encode("网络记帐本", "utf-8");
				out.writeBytes(content);

				out.flush();
				out.close();

				responseCode = connection.getResponseCode();
				connection.disconnect();
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
					return jo.getInt("action");
				} else
					return -1;
			} catch (Exception e) {
				log.warn("error fetchToAddFriend, exception:" + e.getMessage()
						+ ". tried " + i + " times");
				if (!e.getMessage().contains("Unknown"))
					return -1;
			}
		}
		return -1;
	}
}
