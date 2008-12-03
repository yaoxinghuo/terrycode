package cn.edu.jiangnan.lab.data.service.intf;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.jiangnan.lab.data.pojo.Account;

public interface IAccountService {

	public Account getAccountById(String id);

	public boolean saveAccount(String account);

	public boolean saveAccountEntity(Account account);

	public boolean removeAccount(String account_id);

	public String batchRemoveAccount(boolean superAdmin, String[] account_ids);

	public boolean updateAccount(String account, boolean checkpassowrd);

	public Account login(String no, String password, boolean saveCookie,
			HttpServletRequest request, HttpServletResponse response);

	public Account cookielogin(String value, HttpServletRequest request,
			HttpServletResponse response);

	public boolean unregister(String id);

	public void saveCookie(String oUsername, String oPassword,
			HttpServletResponse response);

	public void clearCookie(HttpServletResponse response);

	public boolean cookieLogin(HttpServletRequest request,
			HttpServletResponse response);

	public ArrayList<Account> getAllAccoutsByType(int type);

	public String getAdminAccounts(int start, int limit);

	public String getUserAccounts(int start, int limit);

	public String getAccountsBySearch(int start, int limit, String keyword,
			String column);

	public String getAccountDetailInfoById(String id);

}
