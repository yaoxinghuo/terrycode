package com.terry.fetionlib.data.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 22, 2009 11:05:21 PM
 */

public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2779538270298214696L;

	private String id;

	private Account account;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLocalname() {
		return localname;
	}

	public void setLocalname(String localname) {
		this.localname = localname;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAmobile(String amobile) {
		this.amobile = amobile;
	}

	public String getAmobile() {
		return amobile;
	}

	private String mobile;
	private String uri;
	private String nickname;
	private String localname;
	private String userid;
	private String amobile;
	private Date cdate;
}
