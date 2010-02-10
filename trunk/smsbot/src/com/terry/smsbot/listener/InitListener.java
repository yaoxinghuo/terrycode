package com.terry.smsbot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terry.smsbot.util.Constants;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 10, 2009 5:38:54 PM
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		Constants.GV_EMAIL = contextEvent.getServletContext().getInitParameter(
				"gvemail");
		Constants.GV_PASSWORD = contextEvent.getServletContext()
				.getInitParameter("gvpassword");

		Constants.WHITE_LIST = contextEvent.getServletContext()
				.getInitParameter("whitelist").split(",");
	}
}
