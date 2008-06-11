package com.portal.data.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portal.data.dao.intf.IAccountDao;
import com.portal.data.dao.intf.IPassportDao;
import com.portal.data.pojo.Account;
import com.portal.data.pojo.Page;
import com.portal.data.pojo.Passport;
import com.portal.data.pojo.Tab;
import com.portal.data.service.comm.Constants;
import com.portal.data.service.comm.MD5;
import com.portal.data.service.intf.IAccountService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class AccountServiceImpl implements IAccountService {
	private IAccountDao accountDao;
	private IPassportDao passportDao;

	@Override
	public boolean login(String username, String password, boolean saveCookie,
			HttpServletRequest request, HttpServletResponse response) {
		Passport passport = passportDao.getPassportByUsername(username);
		if (passport == null)
			return false;
		if (MD5.compute(password).equals(passport.getPassword())) {
			if (saveCookie)
				saveCookie(username, password, response);
			passport.setLoginCounter(passport.getLoginCounter() + 1);
			passport.setLastLoginIp(request.getRemoteAddr());
			passport.setLastLoginTime(new Date());
			passportDao.savePassport(passport);// 用户登录后记录下登录信息
			return true;
		} else
			return false;
	}

	@Override
	public boolean register(String username, String nickname, String password,
			String email, boolean sex, String industry, String activity,
			String service, String tag, String registerIp, Date lastLoginTime,
			String lastLoginIp, int loginCounter, String province, String city,
			Date birthday, String remark) {
		Account account = new Account();

		account.setUsername(username);
		account.setNickname(nickname);
		account.setActivity(activity);
		account.setBirthday(birthday);
		account.setCity(city);
		account.setEmail(email);
		account.setIndustry(industry);
		account.setProvince(province);
		account.setRegisterIp(registerIp);
		account.setRemark(remark);
		account.setService(service);
		account.setSex(sex);
		account.setTag(tag);

		Passport passport = new Passport();
		passport.setPassword(password);
		passport.setUsername(username);
		passport.setLastLoginIp(lastLoginIp);
		passport.setLastLoginTime(lastLoginTime);
		passport.setLoginCounter(loginCounter);

		Page page = new Page();
		page.setUsername(username);
		page.setNickname(nickname);
		page.setTitle(username);
		page.setType(1);
		page.setDefaultTab(0);
		passport.setPage(page);

		Tab tab1 = new Tab();
		tab1.setFold(false);
		tab1.setUsername(username);
		tab1.setPosition(0);
		tab1.setClosable(false);
		tab1.setShare(false);
		tab1.setTitle("RSS阅读");
		tab1.setType(1);

		Tab tab2 = new Tab();
		tab2.setFold(false);
		tab2.setUsername(username);
		tab2.setPosition(1);
		tab2.setClosable(false);
		tab2.setShare(false);
		tab2.setTitle("网址收藏");
		tab2.setType(2);

		Tab tab3 = new Tab();
		tab3.setFold(false);
		tab3.setUsername(username);
		tab3.setPosition(2);
		tab3.setClosable(false);
		tab3.setShare(false);
		tab3.setTitle("常用工具");
		tab3.setType(3);

		Set<Tab> tabs = new HashSet<Tab>();
		tabs.add(tab1);
		tabs.add(tab2);
		tabs.add(tab3);
		passport.setTabs(tabs);

		account.setPassport(passport);
		return accountDao.saveAccount(account);
	}

	@Override
	public boolean unregister(String id) {
		Account account = accountDao.getAccountById(id);
		if (account == null)
			return false;
		return accountDao.deleteAccount(account);
	}

	@Override
	public void clearCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(Constants.cookieDomainName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
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
		// 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆
		String cookieValues[] = cookieValueAfterDecode.split(":");
		if (cookieValues.length != 2) {
			return false;
		}

		// 取出cookie中的用户名,并到数据库中检查这个用户名,
		String username = cookieValues[0];
		// 根据用户名到数据库中检查用户是否存在
		Passport passport = passportDao.getPassportByUsername(username);
		// 如果user返回不为空,就取出密码,使用用户名+密码+有效时间+ webSiteKey进行MD5加密
		if (passport != null) {
			String md5ValueInCookie = cookieValues[1];
			String md5ValueFromUser = MD5.compute(passport.getUsername() + ":"
					+ passport.getPassword() + ":" + Constants.webKey);

			// 将结果与Cookie中的MD 5码 相比较,如果相同,写入Session,自动登陆成功,并继续用户请求
			if (md5ValueFromUser.equals(md5ValueInCookie)) {
				HttpSession session = request.getSession(true);
				session.setAttribute(Constants.SESSION_USERNAME, username);
				passport.setLastLoginIp(request.getRemoteAddr());
				passport.setLastLoginTime(new Date());
				passportDao.savePassport(passport);
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

	public IPassportDao getPassportDao() {
		return passportDao;
	}

	public void setPassportDao(IPassportDao passportDao) {
		this.passportDao = passportDao;
	}
	
	public String test(){
		Account account = accountDao.getAccountByUsername("terry");
		return account.getProvince().toString();
	}
}
