package com.terry.gtalk;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jun 24, 2009 4:42:16 PM
 */
public class Test {
	/*
	 * <IFRAME height=36src=
	 * "http://www.google.com/talk/service/badge/Show?tk=z01q6amlqehq69dc4d4m9cj7befisgrjg7g3cp655fe8kssr5drln5tt1ukvlt48dsl6a9ik79jg6fptugikba6shpusar0il01qolqsi4kkabbefded588dm36vd2h41a7a4mchp4rgmhkk502gmt8iul1l798afult5nv9u9qh42d4cqpjrbpj6680gsed9gs&amp;w=159&amp;h=36"
	 * frameBorder=0 width=159 allowTransparency></IFRAME>
	 */
	public static void main(String[] args) throws Exception {
		ConnectionConfiguration config = new ConnectionConfiguration(
				"talk.google.com", 5222, "gmail.com");
		XMPPConnection con = new XMPPConnection(config);
		// config.setSASLAuthenticationEnabled(true);
		con.connect();
		// You have to put this code before you login
		SASLAuthentication.supportSASLMechanism("PLAIN", 0);
		con.login("javamemo@gmail.com", "1qaz2wsx");
		final Roster roster = con.getRoster();
		roster.addRosterListener(new RosterListener() {

			@Override
			public void entriesAdded(Collection<String> arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void entriesDeleted(Collection<String> arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void entriesUpdated(Collection<String> arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void presenceChanged(Presence presence) {
				String user = presence.getFrom();
				Presence bestPresence = roster.getPresence(user);
				System.out.println("Presence changed: " + user + " Status: "
						+ bestPresence.getStatus() + " Mode: "
						+ bestPresence.getMode());
			}

		});
		ChatManager chatmanager = con.getChatManager();
		chatmanager.addChatListener(new ChatManagerListener() {

			@Override
			public void chatCreated(Chat chat, boolean arg1) {
				chat.addMessageListener(new MessageListener() {

					@Override
					public void processMessage(Chat chat, Message message) {

						System.out.println("Received message: "
								+ message.getBody());
						try {
							if (message.getBody() != null) {
								Pattern p = Pattern
										.compile(
												"(http://|https://)?([\\w-]+\\.)+[\\w-]+(/[\\w-   ./?%&=]*)?",
												Pattern.CASE_INSENSITIVE);
								Matcher matcher = p.matcher(message.getBody());
								while (matcher.find()) {
									return;
								}
								chat.sendMessage(message.getBody());
							}
						} catch (XMPPException e) {
							e.printStackTrace();
						}
					}

				});

			}

		});

		while (true) {

		}
		// con.disconnect();
	}
}
