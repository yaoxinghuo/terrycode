package com.microblog.ws.model;

import java.io.Serializable;

public class PicPack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1929467087422282301L;

	private String name;
	private byte[] pic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

}
