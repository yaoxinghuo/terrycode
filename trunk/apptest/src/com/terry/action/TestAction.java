package com.terry.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component("gwt-testAction")
public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1859767044000357740L;

	public String test(String name) {
		return "Struts2:Hello~" + name;
	}

}
