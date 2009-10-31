package com.terry.robot.logic;

import java.util.Random;

import net.sf.json.JSONObject;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jun 10, 2009 9:49:21 PM
 */
public abstract class ReplyLogicBase {

	public static final String RETURN = "\r\n";

	protected int mode;// 1 关键词模式2选单模式

	protected String randomResponse(String responses) {
		String[] responseArray = responses.split("\\|");
		int random = new Random().nextInt(responseArray.length);
		return responseArray[random];
	}

	public String getWelcomeResponse() {
		return randomResponse(welcome);
	}

	public String getHelpResponse() {
		return RETURN + randomResponse(help);
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public String getMresponse() {
		return mresponse;
	}

	public void setMresponse(String mresponse) {
		this.mresponse = mresponse;
	}

	public int getMlimit() {
		return mlimit;
	}

	public void setMlimit(int mlimit) {
		this.mlimit = mlimit;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected String welcome = "Hello, I am just a robot!";
	protected String mresponse = "Hello, I will contact you later!";
	protected String help = "";
	protected int mlimit = 0;
	protected String name;

	public String getName() {
		return name;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public abstract JSONObject reply(String message);

	public JSONObject reply(int no) {
		return null;
	}

	public JSONObject replyChooseMenu(int no) {
		return null;
	}

	public JSONObject replyBack(int no) {
		return null;
	}

	public JSONObject replyCancel(int no) {
		return null;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getHelp() {
		return help;
	}

}
