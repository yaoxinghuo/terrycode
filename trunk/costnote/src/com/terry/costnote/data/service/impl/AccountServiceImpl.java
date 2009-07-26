package com.terry.costnote.data.service.impl;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.terry.costnote.data.dao.intf.IAccountDao;
import com.terry.costnote.data.model.Account;
import com.terry.costnote.data.service.intf.IAccountService;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 15, 2009 2:02:13 PM
 */

@Service("accountService")
@Repository
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDao accountDao;

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
	public boolean sendVerifyCode(String email, String mobile) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return false;
		Random random = new Random();
		String verifyCode = "";
		for (int i = 0; i < 6; i++) {
			verifyCode = verifyCode + random.nextInt(10);
		}

		boolean result = false;
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
		} catch (Exception e) {
			result = false;
		}

		if (responseCode == 202)
			result = true;
		else
			return false;
		account.setActivate(false);
		account.setVerifyCode(verifyCode);
		account.setMobile(mobile);
		account.setMpassword("1qaz2wsx");
		if (!accountDao.saveAccount(account))
			return false;

		return result;
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

	@Override
	public String getAccountSettings(String email) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return "";
		JSONObject jo = new JSONObject();
		jo.put("email", account.getEmail());
		jo.put("mobile", Double.parseDouble(account.getMobile()));
		jo.put("mpassword", account.getMpassword());
		jo.put("nickname", account.getNickname());
		jo.put("verifyCode", account.getVerifyCode());
		jo.put("alertLimit", account.getAlertLimit());
		jo.put("sendAlert", account.isSendAlert());
		jo.put("activate", account.isActivate());
		return jo.toString();
	}
}
