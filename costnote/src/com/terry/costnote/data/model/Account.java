package com.terry.costnote.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.jpa.annotations.Extension;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 7:47:43 AM
 */

@Entity
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3885801975771982116L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;

	private String email;
	private String nickname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public double getAlertLimit() {
		return alertLimit;
	}

	public void setAlertLimit(double alertLimit) {
		this.alertLimit = alertLimit;
	}

	public boolean isSendAlert() {
		return sendAlert;
	}

	public void setSendAlert(boolean sendAlert) {
		this.sendAlert = sendAlert;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getLastSendAlert() {
		return lastSendAlert;
	}

	public void setLastSendAlert(Date lastSendAlert) {
		this.lastSendAlert = lastSendAlert;
	}
	
	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	private String mobile;
	private String mpassword;
	private double alertLimit;
	private boolean sendAlert;
	private boolean activate;
	private String verifyCode;
	private Date cdate;
	private Date lastSendAlert;
}
