package com.terry.action;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1859767044000357740L;

	public String test(String name) {
		return "Struts2:Hello" + name;
	}

}
