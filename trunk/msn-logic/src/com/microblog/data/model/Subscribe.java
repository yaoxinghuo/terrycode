package com.microblog.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "subscribe")
public class Subscribe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4753175306391324520L;

	private String id;
	private String account_id;
	private Account account;
	private String subscribe;// 对于type1追踪为作者的ID，2为文章ID 3 为RSS地址
	private String comment;//注解
	private int type;// 1追踪作者，2停止追蹤作者 3.RSS订阅 4.RSS订阅但暂停更新
	private Date udate;
	private Date sdate;//最近一條RSS的時間，如果更新的RSS的日期比這條舊，就放棄更新
	private String udateString;

	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "account_id", insertable = false, updatable = false)
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUdate() {
		return udate;
	}

	public void setUdateString(String udateString) {
		this.udateString = udateString;
	}

	@Transient
	public String getUdateString() {
		return udateString;
	}

	@Temporal(TemporalType.DATE)
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getSdate() {
		return sdate;
	}

}
