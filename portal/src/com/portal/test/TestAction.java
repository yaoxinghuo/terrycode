package com.portal.test;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cb;
	private String radio;
	private String text;
	private String datefield;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDatefield() {
		return datefield;
	}

	public void setDatefield(String datefield) {
		this.datefield = datefield;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getCb() {
		return cb;
	}

	public void setCb(String cb) {
		this.cb = cb;
	}

	@Override
	public String execute() {
		try{
			Thread.sleep(3000);
		} catch(Exception e){
			
		}
		System.out.println("Executed...");
		System.out.println("Checkbox.value:"+cb);
		System.out.println("Radio.value:"+radio);
		System.out.println("text.value:"+text);
		System.out.println("DateField.value:"+datefield);
		return SUCCESS;
	}
}
