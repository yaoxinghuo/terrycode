package com.microblog.ws.model;

import java.io.Serializable;

public class MessengerStatusWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -534044835539341032L;
	private String account;
	private String displayName;
	private int members;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public String getPersonalMessage() {
		return personalMessage;
	}

	public void setPersonalMessage(String personalMessage) {
		this.personalMessage = personalMessage;
	}

	private String personalMessage;

}
