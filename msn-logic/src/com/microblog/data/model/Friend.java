package com.microblog.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4456609407504905064L;

	private String friendid;
	private String id;
	private boolean ingroup;
	private String groupid;
	private Date adate;// 申请日期
	private String nickname;//申请人的昵称,不进入数据库，需要的时候从Accouont查询出来
	private String forumname;//申請論壇名稱，需要的時候查出來
	private int type;//0群组中的朋友申请 2论坛的好友申请

	@Temporal(TemporalType.TIMESTAMP)
	public Date getAdate() {
		return adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	private boolean apply;// 是否主动发起好友申请，否：对方加自己为好友
	// apply在論壇的申請中，apply=true表示用戶已經申請了去加論壇機器人了，false表示只是在web申請，還沒有主動加機器人
	private String content;// 好友申请留言
	private String account_id;

	@Column(name = "account_id", insertable = false, updatable = false)
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public boolean isApply() {
		return apply;
	}

	public void setApply(boolean apply) {
		this.apply = apply;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private int status;// 对方是否接受自己的好友申请，0默认，1接受，2拒绝，3Hold

	public boolean isIngroup() {
		return ingroup;
	}

	public void setIngroup(boolean ingroup) {
		this.ingroup = ingroup;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private Account account;

	public String getFriendid() {
		return friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

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

	@ManyToOne
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Transient
	public String getNickname() {
		return nickname;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	@Transient
	public String getForumname() {
		return forumname;
	}
}
