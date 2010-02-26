package com.terry.botmail.util;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-3 下午04:38:15
 */
public class MailSender {
	public static void sendMail(String email, String sender, String replyTo,
			String subject, String body) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		javax.mail.Message msg = new MimeMessage(session);
		if (StringUtil.isEmptyOrWhitespace(sender))
			sender = replyTo.substring(0, replyTo.indexOf("@"));
		msg.setFrom(new InternetAddress("service@appmail.org.ru", MimeUtility
				.encodeText(sender, "UTF-8", "b")));
		msg.setReplyTo(new InternetAddress[] { new InternetAddress(replyTo,
				MimeUtility.encodeText(sender, "UTF-8", "b")) });
		msg.addRecipient(javax.mail.Message.RecipientType.TO,
				new InternetAddress(email));
		msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "b"));
		msg.setText(body);
		Transport.send(msg);
	}
}