package com.terry.fetionlib.data.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Sep 9, 2009 3:22:38 PM
 */
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2476223561469469353L;

	private String id;

	private String amobile;

	private String friends;

	private String ip;

	private int type = 0; // 1 from web 2 http get 3 post or restlet web service

	private boolean status = true;// TRUE:存在 FALSE:已删除

	private String content;

	private Date cdate;
	private Date adate;

	private String sid;// id return from fetion

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getAdate() {
		return adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setAmobile(String amobile) {
		this.amobile = amobile;
	}

	public String getAmobile() {
		return amobile;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return status;
	}
}
