package cn.edu.jiangnan.lab.dwr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.directwebremoting.WebContextFactory;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.comm.MD5;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;

public class Account {
	private IAccountService accountService;

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public String login(String validate, String no, String password,
			boolean saveCookie) {
		JSONObject jo = new JSONObject();
		HttpServletRequest request = WebContextFactory.get()
				.getHttpServletRequest();
		HttpSession session = WebContextFactory.get().getSession();
		if (validate == null
				|| !validate.equals((String) session
						.getAttribute(Constants.SESSION_VALIDATE))) {
			jo.put("type", 1);
			jo.put("message", "验证码 输入有误！");
			return jo.toString();
		}
		session.setAttribute(Constants.SESSION_VALIDATE, null);
		HttpServletResponse response = WebContextFactory.get()
				.getHttpServletResponse();
		cn.edu.jiangnan.lab.data.pojo.Account account = accountService.login(
				no, password, saveCookie, request, response);
		if (account != null) {
			jo.put("type", 0);
			jo.put("message", account.getUsername() + "(" + account.getNo()
					+ ")");
			if (saveCookie) {
				// MD5加密用户详细信息
				String cookieValueWithMd5 = MD5.compute(no + ":"
						+ MD5.compute(password) + ":" + Constants.webKey);
				// 将要被保存的完整的Cookie值
				String cookieValue = no + ":" + cookieValueWithMd5;
				// 再一次对Cookie的值进行BASE64编码
				String cookieValueBase64 = new String(Base64.encode(cookieValue
						.getBytes()));
				jo.put("cookievalue", cookieValueBase64);
			}
			return jo.toString();
		} else
			return "{type:2,message:'密码和用户名不匹配！'}";
	}

	public String cookieLogin(String value) {
		JSONObject jo = new JSONObject();
		HttpServletRequest request = WebContextFactory.get()
				.getHttpServletRequest();
		HttpServletResponse response = WebContextFactory.get()
				.getHttpServletResponse();
		cn.edu.jiangnan.lab.data.pojo.Account account = accountService
				.cookielogin(value, request, response);
		if (account != null) {
			jo.put("type", 0);
			jo.put("message", account.getUsername() + "(" + account.getNo()
					+ ")");
			return jo.toString();
		} else {
			return "{type:2,message:'密码和用户名不匹配！'}";
		}
	}

	public void logout() {
		accountService.clearCookie(WebContextFactory.get()
				.getHttpServletResponse());
		WebContextFactory.get().getSession().invalidate();
	}

	public String getAccounts(int start, int limit, int type) {
		if (!isAdmin())
			return null;
		else {
			if (type == 2)
				return accountService.getAdminAccounts(start, limit);
			else if (type == 1)
				return accountService.getUserAccounts(start, limit);
			else
				return null;
		}
	}

	public String getAccountsBySearch(int start, int limit, String keyword,
			String column) {
		if (!isAdmin())
			return null;
		return accountService
				.getAccountsBySearch(start, limit, keyword, column);
	}

	public String getAccountDetailInfoById(String id) {
		if (isAdmin())
			return accountService.getAccountDetailInfoById(id);
		else
			return null;
	}

	public String getAccountDetailInfoBySession(boolean admin) {
		HttpSession session = WebContextFactory.get().getSession();
		if (session.getAttribute(admin ? Constants.SESSION_ADMIN_ID
				: Constants.SESSION_ID) != null)
			return accountService.getAccountDetailInfoById((String) session
					.getAttribute(admin ? Constants.SESSION_ADMIN_ID
							: Constants.SESSION_ID));
		else
			return null;
	}

	public String saveAccount(String account) {
		if (isSuperAdmin() && accountService.saveAccount(account))
			return "{result:true,message:'您已成功保存账户记录！'}";
		else
			return "{result:false,message:'出现错误，请确认您是最高权限管理员及添加用户的学号/工号是唯一的！'}";

	}

	public String removeAccount(String account_id) {
		if (isSuperAdmin() && accountService.removeAccount(account_id))
			return "{result:true,message:'您已成功删除账户记录！'}";
		else
			return "{result:false,message:'对不起，出现错误，请确认您是最高权限管理员或重新登录或稍后再试！'}";
	}

	public String batchRemoveAccount(String[] account_ids) {
		return accountService.batchRemoveAccount(isSuperAdmin(), account_ids);
	}

	public String updateAccount(String account) {
		if (isSuperAdmin() && accountService.updateAccount(account, false))
			return "{result:true,message:'您已成功修改账户记录！'}";
		else
			return "{result:false,message:'出现错误，请确认您是最高权限管理员及添加用户的学号/工号是唯一的！'}";

	}

	public String updateAccountBySession(String account, boolean admin) {
		HttpSession session = WebContextFactory.get().getSession();
		if (session.getAttribute(admin ? Constants.SESSION_ADMIN_ID
				: Constants.SESSION_ID) != null
				&& accountService.updateAccount(account, true))
			return "{result:true,message:'您已成功修改账户记录！'}";
		else
			return "{result:false,message:'对不起，出现错误，请确认您当前密码是正确的，稍后再试！'}";

	}

	private boolean isAdmin() {
		return (WebContextFactory.get().getSession().getAttribute(
				Constants.SESSION_ADMIN_ID) != null) ? true : false;
	}

	private boolean isSuperAdmin() {
		if (WebContextFactory.get().getSession().getAttribute(
				Constants.SESSION_ADMIN_ID) == null)
			return false;
		String adminid = (String) WebContextFactory.get().getSession()
				.getAttribute(Constants.SESSION_ADMIN_ID);
		cn.edu.jiangnan.lab.data.pojo.Account account = accountService
				.getAccountById(adminid);
		return (account.getType() == 0 && (account.getAdmin() == 3 || account
				.getAdmin() == 0)) ? true : false;
	}

	public void invalidateSession() {
		WebContextFactory.get().getSession().invalidate();
		System.out.println("Session Invalidate");
	}

}
