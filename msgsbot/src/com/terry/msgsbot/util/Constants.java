package com.terry.msgsbot.util;

import com.google.appengine.api.xmpp.JID;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-3 上午09:33:28
 */
public class Constants {
	public static final String[] ADMINS = { "yaoxinghuo@gmail.com",
			"itcontent@gmail.com" };
	public static final JID REC_JID1 = new JID("yaoxinghuo@gmail.com");
	public static final JID REC_JID2 = new JID("itcontent@gmail.com");
	
	public static final String STATUS_CACHE_NAME = "statusCache";
}
