package com.microblog.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "message")
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2548088136866021150L;

	private String id;
	private String avatar;

	private String nickname;
	private String pmessage;
	private String url;
	
	private String image;//這個發布有圖片的，圖片的位置
	
	private boolean readr;//是否阅读过是个message下的回复（包括愤和推的rely//1回复 2愤）
	
	public String getPmessage() {
		return pmessage;
	}

	public void setPmessage(String pmessage) {
		this.pmessage = pmessage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	private String content;
	private int im;
	private Date cdate;
	private Date udate;
	
	private String account_id;
	private  Limit limit;

	@Column(name = "account_id", insertable = false, updatable = false)
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	private Account account;

	private List<Reply> replys = new ArrayList<Reply>();

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Reply.class)
	@Cascade(value = { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "message_id")
	@OrderBy(value = "inputdate desc")
	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}

	public int getIm() {
		return im;
	}

	public void setIm(int im) {
		this.im = im;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false, mappedBy = "message")
	public Limit getLimit() {
		return limit;
	}

	public void setReadr(boolean readr) {
		this.readr = readr;
	}

	public boolean isReadr() {
		return readr;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

}
