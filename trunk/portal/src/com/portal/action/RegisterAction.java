package com.portal.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.portal.data.service.intf.IAccountService;

public class RegisterAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IAccountService accountService;
	private String username;
	private String password;
	private String nickname;
	private String email;
	// private boolean sex;
	private String industry;
	private String activity;
	private String service;
	private String tag;
	private String province;
	private String city;
	// private Date birthday;
	private String remark;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// public boolean isSex() {
	// return sex;
	// }
	//
	// public void setSex(boolean sex) {
	// this.sex = sex;
	// }

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// public Date getBirthday() {
	// return birthday;
	// }
	//
	// public void setBirthday(Date birthday) {
	// this.birthday = birthday;
	// }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public String execute() {
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		if (accountService.register(username, nickname, password, email, true,
				industry, activity, service, tag, ip, new Date(), ip, 0,
				province, city, new Date(), remark))
			return SUCCESS;
		else
			return INPUT;

		// if (accountService.unregister("8a96823b167fcf0d01167fd03a1b0001")) {
		// message = "Login Success!";
		// return SUCCESS;
		// } else {
		// message = "Invalid username or password!";
		// return INPUT;
		// }
	}
}
