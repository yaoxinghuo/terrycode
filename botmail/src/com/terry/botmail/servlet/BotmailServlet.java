package com.terry.botmail.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.terry.botmail.util.MailSender;
import com.terry.botmail.util.StringUtil;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-2 下午04:03:50
 */
public class BotmailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4449085355159990305L;

	private XMPPService xmpp = XMPPServiceFactory.getXMPPService();

	private static final String HELP = "标准格式为：手机号[空格]标题[空格]内容";

	@Override
	public void init() throws ServletException {
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

		String msgBody = "Sorry, error occured, please try again later.";

		JID jid = message.getFromJid();

		msgBody = getResponse(message.getBody());

		Message msg = new MessageBuilder().withRecipientJids(jid).withBody(
				msgBody).build();

		if (xmpp.getPresence(jid).isAvailable()) {
			xmpp.sendMessage(msg);
		}
	}

	private String getResponse(String body) {
		if (StringUtil.isEmptyOrWhitespace(body))
			return HELP;
		String[] parts = body.split("\\s", 3);
		if (parts.length < 3)
			return HELP;
		if (!StringUtil.isChinaMobile(parts[0]))
			return "请输入有效的中国移动手机号码！";
		String email = parts[0] + "@139.com";
		try {
			MailSender.sendMail(email, parts[1], parts[2]);
			return "邮件已发送：" + parts[0] + "@139.com";
		} catch (Exception e) {
			return "对不起，邮件未发送：" + email + "，错误：" + e.getMessage();
		}
	}

}
