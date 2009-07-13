package com.terry.costnote.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.terry.costnote.data.service.intf.ICostService;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:57:42 AM
 */

@Scope("prototype")
@Component("gwt-costAction")
public class CostAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1503567222310181255L;
	@Autowired
	private ICostService costService;

	public boolean saveCost(String cost) {
		return costService.saveCost(cost);
	}

}
