package com.microblog.process;

public class Robot {

	private ProcessBase process = null;;

	public Robot(String email, String passport, String passcode) {
		this.email = email;
		this.passport = passport;
		this.passcode = passcode;
	}

	public void setProcess(ProcessBase process) {
		this.process = process;
	}

	public ProcessBase getProcess() {
		return process;
	}

	private String email;
	private String passport;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	private String passcode;
}
