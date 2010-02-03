package com.terry.botmail.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.datanucleus.jpa.annotations.Extension;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-3 下午03:35:27
 */
@Entity
public class Schedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2282192443072555696L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;

	@Enumerated
	private String account;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getAdate() {
		return adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	@Enumerated
	@Temporal(TemporalType.TIMESTAMP)
	private Date adate;
	@Enumerated
	@Temporal(TemporalType.TIMESTAMP)
	private Date cdate;
	@Enumerated
	@Temporal(TemporalType.TIMESTAMP)
	private Date sdate;
	@Enumerated
	private int type;// 0 定时发送一次 //1每年发送//2每月发送 //3每周发送 //4每天发送 //5立即发送
	@Enumerated
	private String subject;
	@Enumerated
	private String content;
	@Enumerated
	private String mobile;

}
