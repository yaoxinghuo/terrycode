package com.microblog.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mgroup")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8677996215444494748L;

	private String id;

	private String name;
	private String remark;
	
	private int counter;

	private int type;//0系统默认群组 1一般群组 2论坛
	private String msn;//如果是論壇的話，需要制定這個論壇的機器人msn,robot.account
	
	private String account_id;

	private List<GroupFriend> groupFriends = new ArrayList<GroupFriend>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = GroupFriend.class)
	@Cascade(value = { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "group_id")
	public List<GroupFriend> getGroupFriends() {
		return groupFriends;
	}

	public void setGroupFriends(List<GroupFriend> groupFriends) {
		this.groupFriends = groupFriends;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

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

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	@Column(name = "account_id", insertable = false, updatable = false)
	public String getAccount_id() {
		return account_id;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Transient
	public int getCounter() {
		return counter;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(unique = true)
	public String getMsn() {
		return msn;
	}

}
