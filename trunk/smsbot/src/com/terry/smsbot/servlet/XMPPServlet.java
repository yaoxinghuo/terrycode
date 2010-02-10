package com.terry.smsbot.servlet;

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
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.terry.smsbot.AuthenticationExeption;
import com.terry.smsbot.GoogleVoice;
import com.terry.smsbot.util.Constants;
import com.terry.smsbot.util.StringUtil;
import com.terry.smsbot.util.XMPPSender;

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

	private Cache cache;

	private static final String WRONG_MOBILE = "请输入正确的手机号码";

	@Override
	public void init() throws ServletException {
		Map<Integer, Integer> props = new HashMap<Integer, Integer>();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
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

		if (checkWhiteList(jids))
			XMPPSender.sendXMPP(jid, getResponse(message.getBody()));
	}

	private String getResponse(String body) {
		String[] parts = body.split("\\s", 2);
		if (parts.length < 2) {
			return "格式为中国手机号[空格]+消息";
		}
		String mobile = parts[0];
		if (mobile.length() < 11)
			return WRONG_MOBILE;
		if (mobile.startsWith("+"))
			mobile = mobile.substring(1);
		if (mobile.startsWith("86"))
			mobile = mobile.substring(2);
		if (!StringUtil.isMobile(mobile))
			return WRONG_MOBILE;
		String message = parts[1];
		if (message.length() > 160)
			return "消息内容太长，不要超过160字";
		try {
			GoogleVoice gv = getGoogleVoiceInstance(Constants.GV_EMAIL,
					Constants.GV_PASSWORD);
			if (gv.sendSms("86" + mobile, message))
				return "短信已发送至：" + parts[0];
			else
				return "短信未发送";
		} catch (AuthenticationExeption e) {
			return "对不起，出现错误：" + e.getMessage();
		}
	}

	@SuppressWarnings("unchecked")
	private GoogleVoice getGoogleVoiceInstance(String email, String password)
			throws AuthenticationExeption {
		Object o = cache.get("xmpp-cache-gv");
		GoogleVoice gv = null;
		if (o != null || o instanceof GoogleVoice) {
			gv = (GoogleVoice) o;
			if (!password.equals(gv.getPassword()))
				throw new AuthenticationExeption("Forbidden.(Password wrong)");
			gv.setLastSessionTime(System.currentTimeMillis());
		} else
			gv = new GoogleVoice(email, password);
		cache.put("xmpp-cache-gv", gv);
		return gv;
	}

	private boolean checkWhiteList(String account) {
		if (Constants.WHITE_LIST == null || Constants.WHITE_LIST.length == 0)
			return true;
		for (String s : Constants.WHITE_LIST) {
			if (s.equals(account))
				return true;
		}
		return false;
	}
}
