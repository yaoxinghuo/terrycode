package com.terry.straincatalog.model;

import java.io.Serializable;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-4-29 下午04:20:36
 */
public class Strain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 949644069378377756L;

	private String id;
	private String chineseName;
	private String latinName;
	private String strainSources;
	private String remark;
	private String strainDescribe;
	private String cultureMeduim;
	private String activationMethod;
	private String physicalNumber;
	private String accessionNumber;
	private String presentLocation;
	private String preservation;
	private String identificationMethod;
	private String identificationTime;

	public String getStrainDescribe() {
		return strainDescribe == null ? "" : strainDescribe;
	}

	public void setStrainDescribe(String strainDescribe) {
		this.strainDescribe = strainDescribe;
	}

	public String getCultureMeduim() {
		return cultureMeduim == null ? "" : cultureMeduim;
	}

	public void setCultureMeduim(String cultureMeduim) {
		this.cultureMeduim = cultureMeduim;
	}

	public String getActivationMethod() {
		return activationMethod == null ? "" : activationMethod;
	}

	public void setActivationMethod(String activationMethod) {
		this.activationMethod = activationMethod;
	}

	public String getPhysicalNumber() {
		return physicalNumber == null ? "" : physicalNumber;
	}

	public void setPhysicalNumber(String physicalNumber) {
		this.physicalNumber = physicalNumber;
	}

	public String getPresentLocation() {
		return presentLocation == null ? "" : presentLocation;
	}

	public void setPresentLocation(String presentLocation) {
		this.presentLocation = presentLocation;
	}

	public String getPreservation() {
		return preservation == null ? "" : preservation;
	}

	public void setPreservation(String preservation) {
		this.preservation = preservation;
	}

	public String getIdentificationMethod() {
		return identificationMethod == null ? "" : identificationMethod;
	}

	public void setIdentificationMethod(String identificationMethod) {
		this.identificationMethod = identificationMethod;
	}

	public String getIdentificationTime() {
		return identificationTime == null ? "" : identificationTime;
	}

	public void setIdentificationTime(String identificationTime) {
		this.identificationTime = identificationTime;
	}

	public String getChineseName() {
		return chineseName == null ? "" : chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getLatinName() {
		return latinName == null ? "" : latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public String getStrainSources() {
		return strainSources == null ? "" : strainSources;
	}

	public void setStrainSources(String strainSources) {
		this.strainSources = strainSources;
	}

	public String getAccessionNumber() {
		return accessionNumber == null ? "" : accessionNumber;
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
		return remark == null ? "" : remark;
	}
}
