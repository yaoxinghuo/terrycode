package com.terry.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Sep 4, 2009 4:31:59 PM
 */
@SuppressWarnings("serial")
public class XmppReceiverServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		req.setCharacterEncoding("UTF-8");
		XMPPService xmpp = XMPPServiceFactory.getXMPPService();
		Message message = xmpp.parseMessage(req);

		JID jid = message.getFromJid();
		String body = message.getBody();

		String msgBody = "您好，你刚才发送给我的是:" + body;
		Message msg = new MessageBuilder().withRecipientJids(jid).withBody(
				msgBody).build();

		@SuppressWarnings("unused")
		boolean messageSent = false;
		if (xmpp.getPresence(jid).isAvailable()) {
			SendResponse status = xmpp.sendMessage(msg);
			messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);
		}
	}
}
