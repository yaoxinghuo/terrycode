package com.terry.gfwout.listener;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terry.gfwout.util.Constants;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 20, 2009 9:58:58 AM
 */
public class InitListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(arg0
					.getServletContext().getRealPath("/analytics.html")));
			StringBuffer sb = new StringBuffer("");
			String line = null;
			while (true) {
				line = br.readLine();
				if (line == null)
					break;
				sb.append(line).append("\r\n");
			}
			if (sb.length() != 0)
				Constants.ANALYTICS_HTML = sb.toString();
		} catch (Exception e) {
		}

		Constants.BACK_HOME_HTML = "<div id='gfwout-h' style='filter:alpha(opacity=80);-moz-opacity:0.8;"
				+ "font-family: arial, sans-serif; font-size: 13px;left:0px;"
				+ "padding: 4px; background-color: #fad163;z-index: 2000;"
				+ " position: absolute;visibility: visible;top:0px;'><a href='"
				+ "http://gfwout.appspot.com/"
				+ "'><img style='border:none;' src='/home.png'/>Back To GFWout Home</a>"
				+ "&nbsp;<a href='#' "
				+ "onclick=\"document.getElementById('gfwout-h').style.visibility = 'hidden';return false;\">"
				+ "<img src='/close.gif' /></a></div>";
	}
}
