package com.terry.smsbot.listener;

import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terry.smsbot.GoogleVoice;
import com.terry.smsbot.servlet.XMPPServlet;
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

		try {
			Cache cache = CacheManager.getInstance().getCacheFactory()
					.createCache(Collections.emptyMap());
			Object o = cache.get(XMPPServlet.XMPP_GV_CACHE);
			if (o != null || o instanceof GoogleVoice) {
				GoogleVoice gv = (GoogleVoice) o;
				if (!gv.getEmail().equals(Constants.GV_EMAIL)
						|| !gv.getPassword().equals(Constants.GV_PASSWORD))
					cache.remove(XMPPServlet.XMPP_GV_CACHE);
			}
		} catch (CacheException e) {

		}
	}
}
