package com.portal.test;

import java.io.Serializable;

public class LogBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8631915366913211656L;

	private String date;

	private String email;

	private String priority;

	private String message;

	private String dir;

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
}
