package com.microblog.ws.model;

import java.io.Serializable;

public class MemberStatusWrapper implements Serializable {

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public boolean isAllow() {
		return allow;
	}

	public void setAllow(boolean allow) {
		this.allow = allow;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isFollow() {
		return follow;
	}

	public void setFollow(boolean follow) {
		this.follow = follow;
	}

	public long getNetworkId() {
		return networkId;
	}

	public void setNetworkId(long networkId) {
		this.networkId = networkId;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public String getPersonalMessage() {
		return personalMessage;
	}

	public void setPersonalMessage(String personalMessage) {
		this.personalMessage = personalMessage;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -490819185600586688L;
	private String account;
	private boolean allow;
	private boolean block;
	private long clientId;
	private String displayName;
	private String email;
	private boolean follow;
	private long networkId;
	private boolean pending;
	private String personalMessage;
	private boolean reverse;
	private String status;
	private long type;
}
