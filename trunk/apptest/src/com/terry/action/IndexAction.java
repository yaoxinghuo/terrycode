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

	@Override
	public String execute() {
		return SUCCESS;
	}

}
