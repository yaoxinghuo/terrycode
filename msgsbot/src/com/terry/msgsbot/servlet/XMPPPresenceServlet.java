package com.terry.msgsbot.servlet;

import java.io.IOException;
import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Presence;
import com.google.appengine.api.xmpp.PresenceType;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.terry.msgsbot.util.Constants;
import com.terry.msgsbot.util.XMPPSender;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2011-2-12 上午10:28:50
 */
public class XMPPPresenceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7028751881815193212L;

	private Cache cache;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}

	@Override
	public void init() throws ServletException {
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					Collections.emptyMap());
		} catch (CacheException e) {
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		if (cache == null || cache.containsKey(Constants.STATUS_CACHE_NAME)) {
			// log.debug("admin paused, so abort send to admin.");
			return;
		}

		XMPPService xmppService = XMPPServiceFactory.getXMPPService();
		Presence presence = xmppService.parsePresence(req);

		if (presence.getPresenceType() != PresenceType.AVAILABLE)
			return;
		JID jid = presence.getFromJid();
		String jids = jid.getId();
		if (jids.indexOf("/") != -1)
			jids = jids.substring(0, jids.indexOf("/"));
		if (!MessageOutServlet.isAdmin(jids))
			return;
		XMPPSender.sendStatus(jid, "Send '0000' to see menus");
		XMPPSender.sendXMPP(jid,
				"Welcome, now I am trying to send statistics query to robots.");
		XMPPSender.sendXMPP(MessageOutServlet
				.getJidByStatus(MessageOutServlet.WEATHERLIB), "c");
		XMPPSender.sendXMPP(MessageOutServlet
				.getJidByStatus(MessageOutServlet.WEATHERLIB2), "c");
		XMPPSender.sendXMPP(MessageOutServlet
				.getJidByStatus(MessageOutServlet.GCNMUSIC), "c");
		XMPPSender.sendXMPP(MessageOutServlet
				.getJidByStatus(MessageOutServlet.ETFETION), "c");
	}
}
