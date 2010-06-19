package com.terry.gfwout.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.terry.gfwout.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 11, 2009 10:39:57 PM
 */
public class RouterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3532764874610984413L;

	private Cache cache;

	@Override
	public void init() {
		Map<Integer, Integer> props = new HashMap<Integer, Integer>();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600 * 24);
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					props);
		} catch (CacheException e) {
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = req.getParameter("r");
		String search = req.getParameter("s");
		if (url == null || url.trim().equals(""))
			resp.sendRedirect("/index.jsp");
		String uuid = StringUtil.generateUUID();
		if (!StringUtil.validateUrl(url) || "true".equals(search))
			url = "http://www.google.com/search?hl=zh_CN&q="
					+ url.replace("\\s", "+") + "&aq=f&oq=&aqi=";
		cache.put(uuid, url);
		resp.sendRedirect("/gfw?g=" + uuid);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
