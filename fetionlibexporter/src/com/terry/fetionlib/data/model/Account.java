package com.terry.fetionlib.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author terry Email: yaoxinghuo at 126 dot com
 * @version create: Sept 09, 2009 09:09:09 PM
 */

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7305590739271489598L;

	private String id;

	private String mobile;

	private String password;

	private Date cdate;

	private Date udate;

	private String uip;// 最后操作的IP
	
	private long ucversion = 0;// 最后contacts version
	
	private int utype = 0;// 最后操作的来源1 from web 2 http get 3 post or restlet web service

	private List<Friend> friends = new ArrayList<Friend>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUip(String uip) {
		this.uip = uip;
	}

	public String getUip() {
		return uip;
	}

	public void setUtype(int utype) {
		this.utype = utype;
	}

	public int getUtype() {
		return utype;
	}

	public void setUcversion(long ucversion) {
		this.ucversion = ucversion;
	}

	public long getUcversion() {
		return ucversion;
	}
}
