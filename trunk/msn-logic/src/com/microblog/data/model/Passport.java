package com.microblog.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "passport")
public class Passport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8426557696637263612L;

	private String id;
	private String email;
	private String password;

	private Account account;

	private String account_id;

	private int type;// 0 普通用户 1可以登录service forum管理后台

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

	@Column(unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	@Column(name = "account_id", insertable = false, updatable = false)
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@OneToOne(fetch = FetchType.LAZY)
	public Account getAccount() {
		return account;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}
