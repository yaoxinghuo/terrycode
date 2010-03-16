package com.terry.weddingphoto.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terry.weddingphoto.constants.Constants;
import com.terry.weddingphoto.util.StringUtil;

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
		String s = contextEvent.getServletContext().getInitParameter(
				"canUploadPhotos");
		if (!StringUtil.isEmptyOrWhitespace(s))
			Constants.CAN_UPLOAD = s.equals("true");

	}

}
