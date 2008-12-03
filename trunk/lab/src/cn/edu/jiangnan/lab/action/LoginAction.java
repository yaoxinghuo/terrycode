package cn.edu.jiangnan.lab.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.edu.jiangnan.lab.data.pojo.Account;
import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120167114052216168L;

	private String no;
	private String password;
	private String validate;
	private String saveCookie;
	private IAccountService accountService;

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();

		if (validate == null
				|| !validate.equals((String) session
						.getAttribute(Constants.SESSION_VALIDATE))) {
			req.setAttribute(Constants.REQUEST_TIP,
					"<font color=red>验证码 输入有误！</font>");
			return INPUT;
		}
		session.setAttribute(Constants.SESSION_VALIDATE, null);
		Account account = accountService.login(no, password, false,
				ServletActionContext.getRequest(), ServletActionContext
						.getResponse());
		if (account == null) {
			req.setAttribute(Constants.REQUEST_TIP,
					"<font color=red>用户名或密码 错误！</font>");
			return INPUT;
		}
		if (account.getAdmin() == 1 || account.getAdmin() == 4) {
			req.setAttribute(Constants.REQUEST_TIP,
					"<font color=red>普通用户不能登录！</font>");
			return INPUT;
		}

		session.setAttribute(Constants.SESSION_ADMIN_ID, account.getId());
		session.setAttribute(Constants.SESSION_ID, account.getId());
		session.setAttribute(Constants.SESSION_NAME, account.getUsername());
		if (account.getAdmin() == 0 || account.getAdmin() == 3)
			session.setAttribute(Constants.SESSION_SUPER_ADMIN, true);
		session.setAttribute(Constants.SESSION_GROUP_TYPE, account.getType());

		if (saveCookie != null && saveCookie.equals("saveCookie")) {
			// 当浏览器关闭，再次启动后，由于Servlet用于保存Session ID的JSESSIONID
			// Cookie是临时的（也就是说不是持久Cookie，当浏览器关闭后，这个Cookie就会被删除），
			// 因此，需要将JSESSIONID进行持久化。
			// 下次打开浏览器不用重新登陆了
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			// 客户端的JSESSIONID保存，session的过期时间在web.xml单位分钟:
			// <session-config><session-timeout>180</session-timeout></session-
			// config>
			cookie.setMaxAge(session.getMaxInactiveInterval());
			cookie.setPath("/");
			ServletActionContext.getResponse().addCookie(cookie);
		}

		saveCookie = null;

		return SUCCESS;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getSaveCookie() {
		return saveCookie;
	}

	public void setSaveCookie(String saveCookie) {
		this.saveCookie = saveCookie;
	}

}
