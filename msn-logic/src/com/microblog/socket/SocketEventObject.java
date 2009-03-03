package com.microblog.socket;

import java.util.EventObject;

public class SocketEventObject extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1358445569297545525L;

	private String msg;

	public SocketEventObject(Object source) {
		super(source);
		this.msg = (String) source;
	}

	@Override
	public String toString() {
		return msg;
	}

}
