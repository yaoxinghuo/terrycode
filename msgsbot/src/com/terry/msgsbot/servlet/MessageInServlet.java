package com.terry.msgsbot.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.terry.msgsbot.util.Constants;
import com.terry.msgsbot.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Jan 31, 2010 10:23:05 AM
 */
public class MessageInServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3904347537700790329L;

	private static Log log = LogFactory.getLog(MessageInServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String content = req.getParameter("content");
		String from = req.getParameter("from");
		if (StringUtil.isEmptyOrWhitespace(content)
				|| StringUtil.isEmptyOrWhitespace(from)) {
			resp
					.sendError(HttpServletResponse.SC_BAD_REQUEST,
							"possible cause: parameter 'content' and 'from' can not be null.");
			return;
		}
		boolean messageSent = false;
		try {
			XMPPService xmpp = XMPPServiceFactory.getXMPPService();
			String str = from.indexOf("@") == -1 ? ("(From: " + from + ")\r\n" + content)
					: (from + "\r\n" + content);
			Message message = new MessageBuilder().withRecipientJids(
					Constants.REC_JID1, Constants.REC_JID2).withBody(str)
					.build();
			if (xmpp.getPresence(Constants.REC_JID1).isAvailable()
					|| xmpp.getPresence(Constants.REC_JID2).isAvailable()) {
				SendResponse status = xmpp.sendMessage(message);
				messageSent = (status.getStatusMap().get(Constants.REC_JID1) == SendResponse.Status.SUCCESS)
						|| (status.getStatusMap().get(Constants.REC_JID2) == SendResponse.Status.SUCCESS);
			}
			PrintWriter pw = resp.getWriter();
			pw.write(String.valueOf(messageSent));
			pw.flush();
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e
					.getMessage());
			log.warn("can not send xmpp message. exception:" + e.getMessage());
		}
		log.debug("sendXMPP result:" + messageSent);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
