package com.portal.data.service.intf;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAccountService {
	public boolean register(String username, String nickname, String password,
			String email, boolean sex, String industry, String activity,
			String service, String tag, String registerIp, Date lastLoginTime,
			String lastLoginIp, int loginCounter, String province, String city,
			Date birthday, String remark);

	public boolean login(String username, String password, boolean saveCookie,
			HttpServletRequest request, HttpServletResponse response);

	public boolean unregister(String id);
	
	public void saveCookie(String oUsername, String oPassword,
			HttpServletResponse response);

	public void clearCookie(HttpServletResponse response);

	public boolean cookieLogin(HttpServletRequest request,
			HttpServletResponse response);
	
	public String test();
	
}
