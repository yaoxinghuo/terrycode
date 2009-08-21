package com.terry.gfwout.listener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static com.terry.gfwout.util.Constants.BACK_HOME_HTML;
import static com.terry.gfwout.util.Constants.ANALYTICS_HTML;
import static com.terry.gfwout.util.Constants.SCRIPT_HTML;
import static com.terry.gfwout.util.Constants.BASE_URL;

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
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(arg0.getServletContext()
					.getRealPath("/analytics.html")));
			StringBuffer sb = new StringBuffer("");
			String line = null;
			while (true) {
				line = br.readLine();
				if (line == null)
					break;
				sb.append(line).append("\r\n");
			}

			if (sb.length() != 0)
				ANALYTICS_HTML = sb.toString();

			br = new BufferedReader(new FileReader(arg0.getServletContext()
					.getRealPath("/script.html")));
			sb = new StringBuffer("");
			while (true) {
				line = br.readLine();
				if (line == null)
					break;
				sb.append(line).append("\r\n");
			}

			if (sb.length() != 0)
				SCRIPT_HTML = sb.toString();
		} catch (Exception e) {
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
		}

		boolean onGoogleAppEngine = arg0.getServletContext().getServerInfo()
				.startsWith("Google App Engine/");
		if (onGoogleAppEngine)
			BASE_URL = "http://gfwout.appspot.com/";

		BACK_HOME_HTML = "<!-- Injected by http://gfwout.appspot.com/ -->"
				+ "<div id='gfwout-h' style='filter:alpha(opacity=80);-moz-opacity:0.8;"
				+ "font-family: arial, sans-serif; font-size: 13px;left:0px;"
				+ "padding: 4px; background-color: #caff70;z-index: 2000;"
				+ " position: absolute;visibility: visible;top:0px;'>"
				+ "<img style='border:none;' src='"
				+ BASE_URL
				+ "home.png'/><a href='"
				+ BASE_URL
				+ "'><i>Back To GFWout Home</i></a>"
				+ "&nbsp;<a href='#' "
				+ "onclick=\"document.getElementById('gfwout-h').style.visibility = 'hidden';return false;\">"
				+ "<img src='"
				+ BASE_URL
				+ "close.gif' alt='Close' title='Close' style='border:none;'/></a></div>";
	}
}
