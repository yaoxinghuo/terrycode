package com.terry.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component("indexAction")
public class IndexAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4575781393306576158L;

	private String message;

	@Override
	public String execute() {
		setMessage("struts2 action 传过来的中文！");
		return SUCCESS;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
