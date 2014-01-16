package com.terrynow.sparkmonitorserver;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @date 2012-3-27 下午2:09:59
 */
public class SparkResource extends ServerResource {

	private static Log log = LogFactory.getLog(ServerResource.class);

	@Override
	protected Representation post(Representation entity)
			throws ResourceException {
		return processForm(new Form(entity));
	}

	private StringRepresentation defaultSR() {
		return new StringRepresentation("OK");
	}

	private Representation processForm(Form form) {
		String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");
		String host = form.getFirstValue("host");
		String to = form.getFirstValue("to");
		String message = form.getFirstValue("message");

		if (StringUtil.isEmptyOrWhitespace(username)
				|| StringUtil.isEmptyOrWhitespace(password)
				|| StringUtil.isEmptyOrWhitespace(host)
				|| StringUtil.isEmptyOrWhitespace(to)
				|| StringUtil.isEmptyOrWhitespace(message)) {
			getResponse()
					.setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
							"Parameter: username, password, host, to, message can not be empty.");
			return defaultSR();
		}
		String error = "google".equals(host) ? sendMsgGoogle(username,
				password, to, message) : sendMsg(host, username, password, to,
				message);
		if (error == null) {
			getResponse().setStatus(Status.SUCCESS_OK);
			log.info("send message to: " + to + " ok");
		} else {
			log.info("send message to: " + to + " error: " + error);
		}

		return defaultSR();
	}

	@Override
	protected Representation get() throws ResourceException {
		return processForm(getReference().getQueryAsForm());

	}

	private String sendMsg(String host, String username, String password,
			String to, String message) {
		XMPPConnection conn = new XMPPConnection(host);
		try {
			conn.connect();
			conn.login(username, password); // 发送端的用户名密码
			Chat mychat = conn.getChatManager().createChat(to, // 接收端的JID，这个JID是要加域的，不然会报错
					new MessageListener() {
						@Override
						public void processMessage(Chat chat, Message message) {
							// String messageBody = message.getBody();
							// System.out.println("收到信息：" + messageBody + " "
							// + message.getFrom());
						}
					});
			// System.out.println("我的好友列表：=======================");
			// Collection<RosterEntry> rosters = conn.getRoster().getEntries();
			// for (RosterEntry rosterEntry : rosters) {
			// System.out.print("name: " + rosterEntry.getName() + ",jid: "
			// + rosterEntry.getUser()); // 此处可获取用户JID
			// System.out.println("");
			// }
			// System.out.println("我的好友列表：=======================");

			mychat.sendMessage(message); // 发送信息
			return null;
		} catch (Exception e) {
			return e.getMessage() == null ? "ERROR" : e.getMessage();
		} finally {
			conn.disconnect(); // 断开连接
		}
	}

	private String sendMsgGoogle(String username, String password, String to,
			String message) {
		String talkGoogle = "173.194.72.125";
		int port = 5222;
		String gmailServer = "173.194.72.17";
		try {
			ClassLoader classLoader = SparkResource.class.getClassLoader();
			Properties properties = new Properties();
			properties.load(new FileInputStream(classLoader.getResource(
					"google.properties").getFile()));
			talkGoogle = properties.getProperty("google.host", talkGoogle);
			port = Integer.parseInt(properties.getProperty("google.port",
					String.valueOf(port)));
			gmailServer = properties.getProperty("google.serviceName",
					gmailServer);
		} catch (Exception e) {
		}

		ConnectionConfiguration connConfig = new ConnectionConfiguration(
				talkGoogle, port, gmailServer);
		XMPPConnection connection = new XMPPConnection(connConfig);
		try {
			connection.connect();
			connection.login(username, password);

			Message msg = new Message(to, Message.Type.chat);
			msg.setBody(message);
			connection.sendPacket(msg);

			return null;
		} catch (XMPPException e) {
			return e.getMessage() == null ? "ERROR" : e.getMessage();
		} finally {
			connection.disconnect();
		}

	}
}
