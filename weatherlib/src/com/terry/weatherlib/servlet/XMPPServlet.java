package com.terry.weatherlib.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.terry.weatherlib.Weather;
import com.terry.weatherlib.WeatherCache;
import com.terry.weatherlib.util.XMPPSender;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-10 下午12:01:48
 */
public class XMPPServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5823820537706460688L;

	private XMPPService xmpp = XMPPServiceFactory.getXMPPService();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm",
			Locale.CHINA);
	
	private Cache cache;

	private static final String ERROR = "对不起，无法获取天气预报，请稍候再试！";
	private static final String HELP = "直接输入城市名称或拼音将直接显示天气预报，"
			+ "\r\n若要定制每天定时邮件提醒，请输入标准格式（机器人会提示您接下来的操作）："
			+ "\r\n每天发送时间(如20:00)[空格]手机号或Email[空格]城市名称"
			+ "\r\n输入list或00查看已设置定时列表" + "\r\n输入account或000查看或修改账户信息"
			+ "\r\n任何时候输入0取消当前操作";
	
	@Override
	public void init() throws ServletException {
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		sdf2.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		Map<Integer, Integer> props = new HashMap<Integer, Integer>();
		props.put(GCacheFactory.EXPIRATION_DELTA, 600);
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					props);
		} catch (CacheException e) {
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		Message message = xmpp.parseMessage(req);

		JID jid = message.getFromJid();
		String jids = jid.getId();
		if (jids.indexOf("/") != -1)
			jids = jids.substring(0, jids.indexOf("/"));

		XMPPSender.sendXMPP(jid, getResponse(message.getBody()));
	}

	private String getResponse(String body) {
		if (body.length() > 10)
			return ERROR;
		Weather w = WeatherCache.queryWeather(body);
		if (w == null)
			return ERROR;
		return w.getReport();
	}
}
