package com.microblog.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "robot")
public class Robot implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1549776101986444210L;
	private String id;
	private String account;
	private int type;
	private String adminAccounts;
	private String forumAdmin;
	private String forumId;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAdminAccounts() {
		return adminAccounts;
	}

	public void setAdminAccounts(String adminAccounts) {
		this.adminAccounts = adminAccounts;
	}

	public String getForumAdmin() {
		return forumAdmin;
	}

	public void setForumAdmin(String forumAdmin) {
		this.forumAdmin = forumAdmin;
	}

	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}
}
