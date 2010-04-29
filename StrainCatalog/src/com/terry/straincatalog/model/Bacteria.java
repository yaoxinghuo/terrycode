package com.terry.straincatalog.model;

import java.io.Serializable;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-4-29 下午04:20:36
 */
public class Bacteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 949644069378377756L;

	private String id;
	private String chineseName;
	private String latinName;
	private String strainSources;
	private String accessionNumber;
	private String remark;

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public String getStrainSources() {
		return strainSources;
	}

	public void setStrainSources(String strainSources) {
		this.strainSources = strainSources;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
}
