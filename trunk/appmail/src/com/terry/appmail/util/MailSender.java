package com.terry.appmail.util;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.google.appengine.api.utils.SystemProperty;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-3 下午04:38:15
 */
public class MailSender {
	public static void sendMail(String[] emails, String sender, String subject,
			String body) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		javax.mail.Message msg = new MimeMessage(session);
		if (StringUtil.isEmptyOrWhitespace(sender))
			sender = "service";
		/**
		 * 下面的邮件地址请改成你的Gmail帐号，根据官方文档
		 * http://code.google.com/intl/zh-CN/appengine
		 * /docs/java/mail/overview.html#Email_Messages
		 * 
		 * 不能随意设置发件人
		 */
		String from = "service@" + SystemProperty.applicationId.get()
				+ ".appspotmail.com";
		msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(sender,
				"UTF-8", "b")));
		for (String email : emails)
			msg.addRecipient(javax.mail.Message.RecipientType.BCC,
					new InternetAddress(email));
		msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "b"));
		msg.setText(body);
		Transport.send(msg);
	}
}
