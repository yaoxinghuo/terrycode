package cn.edu.jiangnan.lab.data.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.jiangnan.lab.data.dao.intf.IAccountDao;
import cn.edu.jiangnan.lab.data.pojo.Account;
import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.comm.MD5;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class AccountServiceImpl implements IAccountService {
	private IAccountDao accountDao;

	@Override
	public Account login(String no, String password, boolean saveCookie,
			HttpServletRequest request, HttpServletResponse response) {
		Account account = accountDao.getAccountByNo(no);
		if (account == null)
			return null;
		if (MD5.compute(password).equals(account.getPassword())) {
			HttpSession session = request.getSession(true);
			session.setAttribute(Constants.SESSION_ID, account.getId());
			session.setAttribute(Constants.SESSION_NAME, account
					.getUsername()
					+ "(" + no + ")");
			if (saveCookie) {
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(session.getMaxInactiveInterval());
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			return account;
		} else
			return null;
	}
	
	@Override
	public void clearCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(Constants.cookieDomainName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	@Override
	public Account cookielogin(String cookieValue, HttpServletRequest request,
			HttpServletResponse response) {
		// 如果cookieValue不为空,才执行下面的代码
		// 先得到的CookieValue进行Base64解码
		String cookieValueAfterDecode;
		try {
			cookieValueAfterDecode = new String(Base64.decode(cookieValue),
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		// 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登录
		String cookieValues[] = cookieValueAfterDecode.split(":");
		if (cookieValues.length != 2) {
			return null;
		}

		// 取出cookie中的用户名,并到数据库中检查这个用户名,
		String no = cookieValues[0];
		// 根据用户名到数据库中检查用户是否存在
		Account account = accountDao.getAccountByNo(no);
		// 如果user返回不为空,就取出密码,使用用户名+密码+有效时间+ webSiteKey进行MD5加密
		if (account != null) {
			String md5ValueInCookie = cookieValues[1];
			String md5ValueFromUser = MD5.compute(account.getNo() + ":"
					+ account.getPassword() + ":" + Constants.webKey);

			// 将结果与Cookie中的MD5码 相比较,如果相同,写入Session,自动登录成功,并继续用户请求
			if (md5ValueFromUser.equals(md5ValueInCookie)) {
				HttpSession session = request.getSession(true);
				session.setAttribute(Constants.SESSION_ID, account.getId());
				session.setAttribute(Constants.SESSION_NAME, account
						.getUsername()
						+ "(" + no + ")");

				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(session.getMaxInactiveInterval());
				cookie.setPath("/");
				response.addCookie(cookie);
				return account;
			}
		}
		return null;
	}

	@Override
	public boolean removeAccount(String account_id) {
		Account account = accountDao.getAccountById(account_id);
		return accountDao.deleteAccount(account);
	}

	@Override
	public String batchRemoveAccount(boolean superAdmin, String[] account_ids) {
		JSONObject jo = new JSONObject();
		if (!superAdmin) {
			jo.put("result", false);
			jo.put("message", "出现错误，请确认您是最高权限管理员！");
			return jo.toString();
		}
		int pass = 0;
		for (int i = 0; i < account_ids.length; i++) {
			Account account = accountDao.getAccountById(account_ids[i]);
			if (accountDao.deleteAccount(account))
				pass++;
		}
		if (pass == account_ids.length) {
			jo.put("result", true);
			jo.put("message", "您已经成功批量删除 " + pass + " 条账户记录！");
		} else if (pass == 0) {
			jo.put("result", false);
			jo.put("message", "出现错误，您未批量删除！");
		} else {
			jo.put("result", true);
			jo.put("message", "您批量删除 " + pass + " 个账户记录，有"
					+ (account_ids.length - pass) + " 个记录删除时出现错误！");
		}
		return jo.toString();
	}

	@Override
	public String getAdminAccounts(int start, int limit) {
		ArrayList<Account> accounts = accountDao.getAdminAccounts(start, limit);
		JSONObject jo = new JSONObject();
		jo.put("results", accountDao.getAdminAccountsCount());
		JSONArray ja = new JSONArray();
		for (Account account : accounts) {
			JSONObject a = new JSONObject();
			a.put("id", account.getId());
			a.put("name", account.getUsername());
			a.put("no", account.getNo());
			a.put("mobile", account.getMobile());
			a.put("admin", account.getAdmin());
			a.put("teacher", account.getTeacher());
			a.put("changed", account.isChanged());
			a.put("disabled", account.isDisabled());
			a.put("type", account.getType());
			ja.add(a);
		}
		jo.put("rows", ja);
		return jo.toString();
	}

	@Override
	public String getUserAccounts(int start, int limit) {
		ArrayList<Account> accounts = accountDao.getUserAccounts(start, limit);
		JSONObject jo = new JSONObject();
		jo.put("results", accountDao.getUserAccountsCount());
		JSONArray ja = new JSONArray();
		for (Account account : accounts) {
			JSONObject a = new JSONObject();
			a.put("id", account.getId());
			a.put("name", account.getUsername());
			a.put("no", account.getNo());
			a.put("teacher", account.getTeacher());
			a.put("mobile", account.getMobile());
			a.put("changed", account.isChanged());
			a.put("disabled", account.isDisabled());
			a.put("admin", account.getAdmin());
			ja.add(a);
		}
		jo.put("rows", ja);
		return jo.toString();
	}

	@Override
	public String getAccountsBySearch(int start, int limit, String keyword,
			String column) {
		ArrayList<Account> accounts = accountDao.getAccountsBySearch(start,
				limit, keyword.trim(), column);
		JSONObject jo = new JSONObject();
		jo.put("results", accountDao.getAccountsCountBySearch(keyword.trim(),
				column));
		JSONArray ja = new JSONArray();
		for (Account account : accounts) {
			JSONObject a = new JSONObject();
			a.put("id", account.getId());
			a.put("name", account.getUsername());
			a.put("no", account.getNo());
			a.put("mobile", account.getMobile());
			a.put("teacher", account.getTeacher());
			a.put("changed", account.isChanged());
			a.put("disabled", account.isDisabled());
			a.put("admin", account.getAdmin());
			ja.add(a);
		}
		jo.put("rows", ja);
		return jo.toString();
	}

	@Override
	public String getAccountDetailInfoById(String id) {
		Account account = accountDao.getAccountById(id);
		JSONObject jo = new JSONObject();
		jo.put("id", account.getId());
		jo.put("name", account.getUsername());
		jo.put("teacher", account.getTeacher());
		// jo.put("password", account.getPassword());
		// jo.put("repassword", account.getPassword());
		jo.put("changed", account.isChanged());
		jo.put("mobile", account.getMobile());
		jo.put("type", account.getType());
		jo.put("disabled", account.isDisabled());
		jo.put("admin", account.getAdmin());
		jo.put("no", account.getNo());

		return jo.toString();
	}

	@Override
	public boolean saveAccount(String account) {
		Account a = new Account();
		JSONObject jo = JSONObject.fromObject(account);
		a.setUsername(jo.getString("name"));
		a.setPassword(MD5.compute(jo.getString("password")));
		a.setTeacher(jo.getString("teacher"));
		a.setNo(jo.getString("no"));
		a.setChanged(false);
		a.setDisabled(false);
		a.setMobile(jo.getString("mobile"));
		a.setAdmin(jo.getInt("admin"));
		a.setType(jo.getInt("type"));

		return accountDao.saveAccount(a);
	}

	@Override
	public boolean updateAccount(String account, boolean checkpassword) {
		JSONObject jo = JSONObject.fromObject(account);
		Account a = accountDao.getAccountById(jo.getString("id"));
		if (checkpassword
				&& !MD5.compute(jo.getString("cupassword")).equals(
						a.getPassword())) {
			return false;
		}
		a.setUsername(jo.getString("name"));
		if (!jo.getString("password").equals("")) {
			a.setPassword(MD5.compute(jo.getString("password")));
			if (checkpassword)// 如果需要密码验证，说明是用户自己在改密码，把用户更改过密码这个栏位设置成true
				a.setChanged(true);
		}
		if (!checkpassword) {
			a.setChanged(jo.getBoolean("changed"));
			a.setDisabled(jo.getBoolean("disabled"));
		}
		a.setType(jo.getInt("type"));
		a.setTeacher(jo.getString("teacher"));
		a.setNo(jo.getString("no"));
		a.setMobile(jo.getString("mobile"));
		a.setAdmin(jo.getInt("admin"));

		return accountDao.saveAccount(a);
	}

	@Override
	public boolean unregister(String id) {
		Account account = accountDao.getAccountById(id);
		if (account == null)
			return false;
		return accountDao.deleteAccount(account);
	}

	@Override
	public boolean cookieLogin(HttpServletRequest request,
			HttpServletResponse response) {
		// 根据cookieName取cookieValue
		Cookie cookies[] = request.getCookies();
		String cookieValue = null;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (Constants.cookieDomainName.equals(cookies[i].getName())) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}

		// 如果cookieValue为空,返回,
		if (cookieValue == null) {
			return false;
		}

		// 如果cookieValue不为空,才执行下面的代码
		// 先得到的CookieValue进行Base64解码
		String cookieValueAfterDecode;
		try {
			cookieValueAfterDecode = new String(Base64.decode(cookieValue),
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		// 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登录
		String cookieValues[] = cookieValueAfterDecode.split(":");
		if (cookieValues.length != 2) {
			return false;
		}

		// 取出cookie中的用户名,并到数据库中检查这个用户名,
		String no = cookieValues[0];
		// 根据用户名到数据库中检查用户是否存在
		Account account = accountDao.getAccountByNo(no);
		// 如果user返回不为空,就取出密码,使用用户名+密码+有效时间+ webSiteKey进行MD5加密
		if (account != null) {
			String md5ValueInCookie = cookieValues[1];
			String md5ValueFromUser = MD5.compute(account.getNo() + ":"
					+ account.getPassword() + ":" + Constants.webKey);

			// 将结果与Cookie中的MD5码 相比较,如果相同,写入Session,自动登录成功,并继续用户请求
			if (md5ValueFromUser.equals(md5ValueInCookie)) {
				HttpSession session = request.getSession(true);
				session.setAttribute(Constants.SESSION_ID, account.getId());
				session.setAttribute(Constants.SESSION_NAME, account
						.getUsername()
						+ "(" + no + ")");

				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(session.getMaxInactiveInterval());
				cookie.setPath("/");
				response.addCookie(cookie);
				return true;
			}
		}
		return false;
	}

	@Override
	public void saveCookie(String username, String password,
			HttpServletResponse response) {
		// MD5加密用户详细信息
		String cookieValueWithMd5 = MD5.compute(username + ":"
				+ MD5.compute(password) + ":" + Constants.webKey);
		// 将要被保存的完整的Cookie值
		String cookieValue = username + ":" + cookieValueWithMd5;
		// 再一次对Cookie的值进行BASE64编码
		String cookieValueBase64 = new String(Base64.encode(cookieValue
				.getBytes()));
		// 开始保存Cookie
		Cookie cookie = new Cookie(Constants.cookieDomainName,
				cookieValueBase64);
		// 存两年(这个值应该大于或等于validTime)
		cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
		cookie.setSecure(false);
		// cookie有效路径是网站根目录
		cookie.setPath("/");
		// 向客户端写入
		response.addCookie(cookie);
	}

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public ArrayList<Account> getAllAccoutsByType(int type) {
		if (type == 2)
			return accountDao.getAdminAccounts(0, 0);
		else
			return accountDao.getUserAccounts(0, 0);
	}

	@Override
	public boolean saveAccountEntity(Account account) {
		return accountDao.saveAccount(account);
	}

	@Override
	public Account getAccountById(String id) {
		return accountDao.getAccountById(id);
	}

}
