package com.terry.gtalk;

import java.util.Collection;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
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
				System.out.println("Presence changed: " + user + " "
						+ bestPresence.getStatus() + " Mode: "
						+ bestPresence.getMode());
			}

		});
		ChatManager chatmanager = con.getChatManager();
		Chat chat = chatmanager.createChat("yaoxinghuo@gmail.com",
				new MessageListener() {
					public void processMessage(Chat chat, Message message) {
						System.out.println("Received message: "
								+ message.getBody());
						try {
							chat.sendMessage(message.getBody());
						} catch (XMPPException e) {
							e.printStackTrace();
						}
					}
				});
		chat.sendMessage("Howdy!");
		while (true) {

		}
		// con.disconnect();
	}
}
