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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "head")
public class Head implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6335570469122541868L;
	
	private String id;
	private String account_id;
	private String pmessage;
	private String nickname;
	private String avatar;
	private Date inputdate;
	
	private Account account;

	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPmessage() {
		return pmessage;
	}

	public void setPmessage(String pmessage) {
		this.pmessage = pmessage;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	@Column(name = "account_id", insertable = false, updatable = false)
	public String getAccount_id() {
		return account_id;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getInputdate() {
		return inputdate;
	}

}
