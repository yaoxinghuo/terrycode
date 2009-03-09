package com.microblog.util;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {

	private Properties properties;
	
	private String userImgBasePath;
	private String userFileBasePath;

	public String getUserFileBasePath() {
		return userFileBasePath;
	}

	public void setUserFileBasePath(String userFileBasePath) {
		this.userFileBasePath = userFileBasePath;
	}

	private String webBaseUrl;

	private String socketHost;
	private int socketPort;
	private String socketPassport;
	private String socketPasscode;
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	private static Settings instance;

	private Settings() {
		try {
			parseSettings();
		} catch (Exception e) {
			// e.printStackTrace();
			instance = null;
		}
	}

	public synchronized static Settings getInstance() {
		if (instance == null)
			instance = new Settings();
		return instance;
	}

	public String getWebBaseUrl() {
		return webBaseUrl;
	}

	public void setWebBaseUrl(String webBaseUrl) {
		this.webBaseUrl = webBaseUrl;
	}

	public void parseSettings() throws Exception {
		properties = new Properties();
		properties.load(new FileInputStream("settings.properties"));

		userImgBasePath = properties.getProperty("user.imgBasePath");
		userFileBasePath = properties.getProperty("user.fileBasePath");

		socketHost = properties.getProperty("socket.host");
		socketPort = Integer.parseInt(properties.getProperty("socket.port"));
		socketPassport = properties.getProperty("socket.passport");
		socketPasscode = properties.getProperty("socket.passcode");
		
		webBaseUrl = properties.getProperty("web.webBaseUrl");
		
	}

	public String getUserImgBasePath() {
		return userImgBasePath;
	}

	public String getSocketHost() {
		return socketHost;
	}

	public void setSocketHost(String socketHost) {
		this.socketHost = socketHost;
	}

	public int getSocketPort() {
		return socketPort;
	}

	public void setSocketPort(int socketPort) {
		this.socketPort = socketPort;
	}

	public String getSocketPassport() {
		return socketPassport;
	}

	public void setSocketPassport(String socketPassport) {
		this.socketPassport = socketPassport;
	}

	public String getSocketPasscode() {
		return socketPasscode;
	}

	public void setSocketPasscode(String socketPasscode) {
		this.socketPasscode = socketPasscode;
	}

	public void setUserImgBasePath(String userImgBasePath) {
		this.userImgBasePath = userImgBasePath;
	}

	public static void main(String[] args) {
		Settings s = Settings.getInstance();
		System.out.println(s.getSocketHost());
	}

}
