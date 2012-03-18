package com.terrynow.saetes.model;

import java.io.Serializable;


/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-8-23 下午12:58:12
 */
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9145312023578600086L;

	private int id;

	private String name;

	private String data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
