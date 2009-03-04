package com.microblog.logic;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {

	private Properties properties;
	
	private String forumId;

	private String userImgBasePath;
	private String userFileBasePath;

	public String getUserFileBasePath() {
		return userFileBasePath;
	}

	public void setUserFileBasePath(String userFileBasePath) {
		this.userFileBasePath = userFileBasePath;
	}

	private String adminAccounts[];
	
	private String forumAdminAccount;

	private String webBaseUrl;

	private String socketHost;
	private int socketPort;
	private String socketPassport;
	private String socketPasscode;
	private String wsUrl;
	private String wsPassport;
	private String wsPasscode;

	public String getWsPasscode() {
		return wsPasscode;
	}

	public void setWsPasscode(String wsPasscode) {
		this.wsPasscode = wsPasscode;
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
		
		wsUrl = properties.getProperty("ws.url");
		wsPassport = properties.getProperty("ws.passport");
		wsPasscode = properties.getProperty("ws.passcode");
		
		adminAccounts = properties.getProperty("msn.adminAccounts").split(",");
		forumAdminAccount = properties.getProperty("msn.forumAdminAccount");
		forumId = properties.getProperty("msn.forumId");
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

	public String getWsUrl() {
		return wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	public void setUserImgBasePath(String userImgBasePath) {
		this.userImgBasePath = userImgBasePath;
	}

	public void setAdminAccounts(String adminAccounts[]) {
		this.adminAccounts = adminAccounts;
	}

	public String[] getAdminAccounts() {
		return adminAccounts;
	}

	public static void main(String[] args) {
		Settings s = Settings.getInstance();
		System.out.println(s.getSocketHost());
	}

	public void setForumAdminAccount(String forumAdminAccount) {
		this.forumAdminAccount = forumAdminAccount;
	}

	public String getForumAdminAccount() {
		return forumAdminAccount;
	}

	public void setWsPassport(String wsPassport) {
		this.wsPassport = wsPassport;
	}

	public String getWsPassport() {
		return wsPassport;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

	public String getForumId() {
		return forumId;
	}
}
