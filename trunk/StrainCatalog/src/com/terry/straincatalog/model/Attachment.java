package com.terry.straincatalog.model;

import java.io.Serializable;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Apr 30, 2010 3:17:26 PM
 */
public class Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1786180516954512394L;

	private String id;
	private String strainId;
	private String fileName;
	private byte[] data;
	private String description;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStrainId() {
		return strainId;
	}

	public void setStrainId(String strainId) {
		this.strainId = strainId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
