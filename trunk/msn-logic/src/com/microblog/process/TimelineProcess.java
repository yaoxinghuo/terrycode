package com.microblog.process;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import com.microblog.data.model.Account;
import com.microblog.util.Logs;
import com.microblog.util.Settings;
import com.microblog.util.StringUtil;

public class TimelineProcess extends ProcessBase {

	private String[] adminAccounts;

	private String webBaseUrl;

	/*
	 * 管理菜单
	 */
	private String[] managerMenu = { "取消", "查允許清單", "列出黑名單", "未確認清單", "查朋友資料",
			"改顯示名稱", "改個人訊息", "改線上狀態", "改個人頭像", "加入聯絡人", "移除聯絡人", "加允許清單",
			"加入黑名單", "送文字訊息" };
	private String managerMenuReply;
	private String[] msnUserStatusMenu = { "取消", "Online", "Busy", "Idle",
			"Be Right Back", "Away", "On the Phone", "Out to Lunch",
			"Appear Offline", "Offline" };
	private String msnUserStatusReply;
	private Hashtable<String, String> lastFriendEmail = new Hashtable<String, String>();
	private Hashtable<String, String[]> lastFriendsList = new Hashtable<String, String[]>();
	private Hashtable<String, Integer> managerMenuHash = new Hashtable<String, Integer>();
	private Hashtable<String, String> msnUserStatusHash = new Hashtable<String, String>();
	String managerReply = "\r\n9.\t管理";

	public String getManagerReply() {
		return managerReply;
	}

	public void setManagerReply(String managerReply) {
		this.managerReply = managerReply;
	}

	// 存储会话状态堆栈
	private Hashtable<String, LinkedList<Integer>> lastStatus = new Hashtable<String, LinkedList<Integer>>();
	private Hashtable<String, Hashtable<String, String>> lastGroupHash = new Hashtable<String, Hashtable<String, String>>();
	private Hashtable<String, Hashtable<String, String>> lastFriendHash = new Hashtable<String, Hashtable<String, String>>();
	private Hashtable<String, Hashtable<String, String>> lastMessageHash = new Hashtable<String, Hashtable<String, String>>();
	private Hashtable<String, Hashtable<String, String>> lastHash = new Hashtable<String, Hashtable<String, String>>();
	private Hashtable<String, Integer> lastPage = new Hashtable<String, Integer>();
	private Hashtable<String, String> lastChoise = new Hashtable<String, String>();
	private Hashtable<String, String> lastId = new Hashtable<String, String>();
	private Hashtable<String, String> userAccountId = new Hashtable<String, String>();

	// 记录机器人回覆堆栈，方便在“返回上一层”菜单的操作
	private Hashtable<String, LinkedList<String>> lastInput = new Hashtable<String, LinkedList<String>>();

	// 记录上一次会话的时间，如果两次会话时间超过十分钟，重新启动新的会话
	private Hashtable<String, Date> lastSessionTime = new Hashtable<String, Date>();

	String defaultReply = "您可以利用這個界面來閱讀跟回覆所有的發佈！\r\n包括了下面這些功能（請输入数字）\r\n1.\t發佈\r\n2.\t閱讀目前十分鐘社群的發佈\r\n3.\t閱讀當天我的發佈\r\n4.\t閱讀當天群組的發佈\r\n5.\t閱讀當天好友的發佈\r\n6.\t大家都說哪些東西";
	String backReply = "【b】回上層";
	String cancelReply = "【0】取消";
	String inputReply = "請輸入要發佈的內容（70個中文字，140個英文字內）或上傳圖檔（目前僅支援 jpg gif png bmp 且檔案少於300K）";
	String emptyFriendReply = "該群組下沒有好友！";
	String operateDone = "您的操作已完成！";
	String reply1 = "發佈給\r\n1.\t整個社群\r\n2.\t我自己\r\n3.\t群組\r\n4.\t好友";
	String ownCategoryReply = "选择分类\r\n1.\tRSS訂閱 \r\n2.\t追蹤訂閱\r\n3.\t我自己的發佈 ";
	String resumeMenuReply = "\r\n繼續輸入文章編號閱讀內容,或按【n】列出下十篇;【m】前十篇;";
	String navReply = "\r\n輸入文章編號閱讀內容,或按【n】列出下十篇;【m】前十篇;";
	String errorOccured = "對不起，程式出錯，請稍候再試!";
	String operError = "請按照提示輸入正確的指令！";
	int defaultPageSize = 10;

	private Settings settings;

	public TimelineProcess(String account) throws Exception {
		super();
		settings = Settings.getInstance();
		init();
//		com.microblog.data.model.Robot robot = serviceService
//				.imGetRobotByAccount(account);
//		if (robot == null)
//			throw new Exception("Cannot query record from database by account:"
//					+ account);
//		adminAccounts = robot.getAdminAccounts().split(",");
		this.adminAccounts = settings.getAdminAccounts();
		this.webBaseUrl = settings.getWebBaseUrl();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < managerMenu.length; i++) {
			String menu = managerMenu[i];
			sb.append(i).append(".\t").append(menu).append("\r\n");
		}
		managerMenuReply = sb.toString();

		sb = new StringBuffer("請選擇狀態代码:\r\n");
		for (int i = 0; i < msnUserStatusMenu.length; i++) {
			String menu = msnUserStatusMenu[i];
			sb.append(i).append(".\t").append(menu).append("\r\n");
		}
		msnUserStatusReply = sb.toString();

		managerMenuHash.put("0", 0);
		managerMenuHash.put("1", 1);
		managerMenuHash.put("2", 2);
		managerMenuHash.put("3", 3);
		managerMenuHash.put("4", 4);
		managerMenuHash.put("5", 5);
		managerMenuHash.put("6", 6);
		managerMenuHash.put("7", 7);
		managerMenuHash.put("8", 8);
		managerMenuHash.put("9", 9);
		managerMenuHash.put("10", 10);
		managerMenuHash.put("11", 11);
		managerMenuHash.put("12", 12);
		managerMenuHash.put("13", 13);

		msnUserStatusHash.put("0", "0");
		msnUserStatusHash.put("1", "NLN");
		msnUserStatusHash.put("2", "BSY");
		msnUserStatusHash.put("3", "IDL");
		msnUserStatusHash.put("4", "BRB");
		msnUserStatusHash.put("5", "AWY");
		msnUserStatusHash.put("6", "PHN");
		msnUserStatusHash.put("7", "LUN");
		msnUserStatusHash.put("8", "HDN");
		msnUserStatusHash.put("9", "FLN");
	}

	@Override
	public void process(Command command) {
		Commands commandEnum = command.getName();
		switch (commandEnum) {
		case MSG:
			try {
				textMessage(command);
			} catch (Exception e) {
				Logs.getLogger().error(
						"Error process command:" + command.toString()
								+ "\texception:" + e.getMessage());
			}
		default:
		}
	}

	@Override
	public void textMessage(Command command) throws Exception {
		String choise = command.getBody();
		String sessionDefaultReply = defaultReply;
		String email = command.getEmail();

		String account_id = userAccountId.get(email);
		if (account_id == null) {
			Account account = accountService.imGetAccountByMsn(email);
			if (account == null) {
				wsActionService.sendText(email, "数据库中找不到这个用户:" + email);
			} else {
				account_id = account.getId();
				userAccountId.put(email, account_id);
			}
		}

		if (isAdmin(email))
			sessionDefaultReply += managerReply;

		LinkedList<Integer> userStatus = lastStatus.get(email);
		if (userStatus == null || userStatus.size() == 0) {
			userStatus = new LinkedList<Integer>();
			userStatus.add(1);
		}
		LinkedList<String> userInput = lastInput.get(email);
		if (userInput == null) {
			userInput = new LinkedList<String>();
		}

		int status = userStatus.getLast();
		int nextStatus = 1;

		String reply;
		if (lastSessionTime.get(email) == null
				|| (new Date().getTime() - lastSessionTime.get(email).getTime()) > 10 * 60 * 1000
				|| choise.equals("0")) {
			userStatus.clear();
			userStatus.add(1);
			userInput.clear();
			lastStatus.put(email, userStatus);
			lastInput.put(email, userInput);
			if (lastChoise.get(email) != null)
				lastChoise.remove(email);
			if (lastId.get(email) != null)
				lastId.remove(email);

			lastSessionTime.put(email, new Date());
			reply = sessionDefaultReply;
			wsActionService.sendText(email, reply);
		}

		lastSessionTime.put(email, new Date());

		if (choise.equalsIgnoreCase("b")) {// 如果用户按“回上一层”
			if (userStatus.size() < 3) {
				userStatus.clear();
				userStatus.add(1);
				userInput.clear();
				lastStatus.put(email, userStatus);
				lastInput.put(email, userInput);
				lastSessionTime.put(email, new Date());

				reply = sessionDefaultReply;
				wsActionService.sendText(email, reply);
			}

			userStatus.removeLast();
			userStatus.removeLast();
			status = userStatus.getLast();
			userInput.removeLast();
			choise = userInput.getLast();
			userInput.removeLast();

			if (lastChoise.get(email) != null)
				lastChoise.remove(email);
			if (lastPage.get(email) != null)
				lastPage.put(email, 0);

		}

		int page = 0;
		String lChoise = lastChoise.get(email);
		if (lChoise != null) {
			if (lastPage.get(email) != null)
				page = lastPage.get(email);

			if (choise.equalsIgnoreCase("m") || choise.equalsIgnoreCase("n")) {
				if (choise.equals("m"))
					page = page - 1 < 0 ? 0 : page - 1;
				else
					page = page + 1;
				userStatus.removeLast();
				userInput.removeLast();
				status = userStatus.getLast();
				choise = lChoise;
				lastPage.put(email, page);
			}
		} else {
			if (lastPage.get(email) != null)
				lastPage.put(email, 0);
		}

		switch (status) {
		case 1:// 最初的菜单
			if (lastChoise.get(email) != null)
				lastChoise.remove(email);
			if (choise.equals("1")) {// 用户选1发布
				reply = reply1;
				nextStatus = 11;
			} else if (choise.equals("2")) {
				Hashtable<String, String> messages = messageService
						.imGetMessageLists(new Date(), 12, null, null, page
								* defaultPageSize, defaultPageSize);
				lastChoise.put(email, choise);
				if (messages.size() == 0) {
					nextStatus = status;
					reply = "没有發佈！";
				} else {
					StringBuffer sb = new StringBuffer("選擇要查看的發佈：");
					Enumeration<String> keys = messages.keys();
					int num = 0;
					Hashtable<String, String> table = new Hashtable<String, String>();
					while (keys.hasMoreElements()) {
						String key = keys.nextElement();
						sb.append("\r\n" + (++num) + ".\t" + messages.get(key));
						table.put(String.valueOf(num), key);
					}
					sb.append(navReply);
					nextStatus = 12;
					lastMessageHash.put(email, table);
					reply = sb.toString();
				}
			} else if (choise.equals("3")) {
				reply = ownCategoryReply;
				nextStatus = 13;
			} else if (choise.equals("4")) {
				reply = getUserGroups(account_id, email);
				nextStatus = 14;
			} else if (choise.equals("5")) {
				reply = getUserGroups(account_id, email);
				// 让用户选要的好友在那个群组里
				nextStatus = 15;
			} else if (choise.equals("9") && isAdmin(email)) {
				reply = managerMenuReply;
				nextStatus = 19;
			} else {
				nextStatus = 1;
				reply = sessionDefaultReply;
			}
			break;
		// case 19管理功能
		case 19:
			Integer c = managerMenuHash.get(choise);
			if (c == null) {
				reply = managerMenuReply;
				nextStatus = 19;
			} else {
				switch (c.intValue()) {
				case 1:
					// TODO allowList
					reply = arrayToMessage(wsMemberService.friendList(email))
							+ "\r\n\r\n" + managerMenuReply;
					nextStatus = 19;
					break;
				case 2:
					// TODO blockList
					reply = arrayToMessage(wsMemberService.pendingList(email))
							+ "\r\n\r\n" + managerMenuReply;
					nextStatus = 19;
					break;
				case 3:
					reply = arrayToMessage(wsMemberService.pendingList(email))
							+ "\r\n\r\n" + managerMenuReply;
					nextStatus = 19;
					break;
				case 4:// 查朋友資料
					lastFriendsList.put(email, wsMemberService
							.friendList(email));
					reply = "請選擇聯絡人：\r\n"
							+ arrayToMessage(wsMemberService.friendList(email));
					nextStatus = 194;
					break;
				case 5:// 改昵稱
					reply = "請輸入昵稱：";
					nextStatus = 195;
					break;
				case 6:// 改Personal Message
					reply = "請輸入個人訊息：";
					nextStatus = 196;
					break;
				case 7:// 改Status
					reply = msnUserStatusReply;
					nextStatus = 197;
					break;
				case 8:// 改头像
					reply = "請輸入頭像路徑：";
					nextStatus = 198;
				case 9:// 加入聯絡人
					reply = "請輸入聯絡人MSN：";
					nextStatus = 199;
					break;
				case 10:// 移除聯絡人
					lastFriendsList.put(email, wsMemberService
							.friendList(email));
					reply = "請選擇聯絡人：\r\n"
							+ arrayToMessage(wsMemberService.friendList(email));
					nextStatus = 1910;
					break;
				case 11:// 加允許清單
					String[] pendingList11 = wsMemberService.pendingList(email);

					lastFriendsList.put(email, pendingList11);
					reply = "請選擇聯絡人\r\n" + arrayToMessage(pendingList11);
					nextStatus = 1911;
					break;
				case 12:// 加入黑名單
					// TODO allowList
					lastFriendsList.put(email, wsMemberService
							.friendList(email));
					reply = "請選擇聯絡人\r\n"
							+ arrayToMessage(wsMemberService.friendList(email));
					nextStatus = 1912;
					break;
				case 13:// 送文字訊息
					lastFriendsList.put(email, wsMemberService
							.friendList(email));
					reply = "請選擇聯絡人(多個聯絡人請用【,】隔開，送給全部聯絡人，請輸入【a】)\r\n"
							+ arrayToMessage(wsMemberService.friendList(email));
					nextStatus = 1913;
					break;
				default:
					reply = managerMenuReply;
					nextStatus = 19;
				}

			}
			break;
		case 194:
			int f = 0;
			try {
				f = Integer.parseInt(choise);
				String[] friendsList = lastFriendsList.get(email);
				if (friendsList == null || f > friendsList.length || f < 0) {
					reply = "請選擇聯系人\r\n"
							+ arrayToMessage(wsMemberService.friendList(email));
					nextStatus = 194;
				} else {
					reply = goMsnFriendDetail(email, friendsList[f - 1])
							+ "\r\n\r\n" + managerMenuReply;
					nextStatus = 19;
				}

			} catch (NumberFormatException e) {
				reply = "請選擇聯系人\r\n"
						+ arrayToMessage(wsMemberService.friendList(email));
				nextStatus = 194;
			}
			break;
		case 195:
			if (wsMessengerService.changeDisplayName(email, choise))
				reply = operateDone;// + "\r\n\r\n" + managerMenuReply;
			else
				reply = errorOccured;
			nextStatus = 19;
			break;
		case 196:
			if (wsMessengerService.changePersonalMessage(email, choise))
				reply = operateDone;// + "\r\n\r\n" + managerMenuReply;
			else
				reply = errorOccured;
			nextStatus = 19;
			break;
		case 197:
			int f197 = 0;
			try {
				f197 = Integer.parseInt(choise);
				if (f197 > msnUserStatusMenu.length || f197 < 0) {
					reply = msnUserStatusReply;
					nextStatus = 197;
				} else {
					// msn.changeOnlineStatus(MsnUserStatus
					// .valueOf(msnUserStatusHash.get(choise)));
					nextStatus = 19;
					reply = operateDone;// + "\r\n\r\n" + managerMenuReply;
					if (msnUserStatusHash.get(choise).equals("FLN")
							&& isAdmin(email)) {// 如果是管理员，而且发送指令是离线，那么登出msn
						// msn.logout();
					}
				}

			} catch (NumberFormatException e) {
				reply = msnUserStatusReply;
				nextStatus = 197;
			}
			break;
		case 198:
			// msn.changeDisplayPic(choise);
			reply = operateDone;// + "\r\n\r\n" + managerMenuReply;
			nextStatus = 19;
			break;
		case 199:// 加联系人
			if (wsMemberService.addFriend(email, choise, 1) != -1)
				reply = operateDone;// + "\r\n\r\n" + managerMenuReply;
			else
				reply = errorOccured;
			nextStatus = 19;
			break;
		case 1910:
			int f1910 = 0;
			try {
				f1910 = Integer.parseInt(choise);
				String[] friendsList = lastFriendsList.get(email);
				if (friendsList == null || f1910 > friendsList.length
						|| f1910 < 0 || isAdmin(friendsList[f1910 - 1])) {
					reply = "不能執行操作，請重新選擇聯系人\r\n"
							+ arrayToMessage(lastFriendsList.get(email));
					nextStatus = 1910;
				} else {
					if (wsMemberService.removeFriend(email,
							friendsList[f1910 - 1]) != -1)
						reply = operateDone;// + "\r\n\r\n" + managerMenuReply;
					else
						reply = errorOccured;
					nextStatus = 19;
				}

			} catch (NumberFormatException e) {
				reply = "請重新選擇聯系人\r\n"
						+ arrayToMessage(lastFriendsList.get(email));
				nextStatus = 1910;
			}
			break;
		case 1911:
			int f1911 = 0;
			try {
				f1911 = Integer.parseInt(choise);
				String[] friendsList = lastFriendsList.get(email);
				if (friendsList == null || f1911 > friendsList.length
						|| f1911 < 0) {
					reply = "不能執行操作，請重新選擇聯系人\r\n"
							+ arrayToMessage(lastFriendsList.get(email));
					nextStatus = 1911;
				} else {
					if (wsMemberService.allowFriend(email,
							friendsList[f1911 - 1]))
						reply = operateDone;// + "\r\n\r\n" + managerMenuReply;
					else
						reply = errorOccured;
					nextStatus = 19;
				}

			} catch (NumberFormatException e) {
				reply = "請重新選擇聯系人\r\n"
						+ arrayToMessage(lastFriendsList.get(email));
				nextStatus = 1911;
			}
			break;
		case 1912:
			int f1912 = 0;
			try {
				f1912 = Integer.parseInt(choise);
				String[] friendsList = lastFriendsList.get(email);
				if (friendsList == null || f1912 > friendsList.length
						|| f1912 < 0 || isAdmin(friendsList[f1912 - 1])) {
					reply = "不能執行操作，請重新選擇聯系人\r\n"
							+ arrayToMessage(lastFriendsList.get(email));
					nextStatus = 1912;
				} else {
					if (wsMemberService.blockFriend(email,
							friendsList[f1912 - 1]))
						reply = operateDone;// + "\r\n\r\n" + managerMenuReply;
					else
						reply = errorOccured;
					nextStatus = 19;
				}

			} catch (NumberFormatException e) {
				reply = "請重新選擇聯系人\r\n"
						+ arrayToMessage(lastFriendsList.get(email));
				nextStatus = 1912;
			}
			break;

		case 1913:
			int f1913 = 0;
			String friendEmail = "";
			try {
				boolean needRechoise = false;
				String[] fs = choise.split(",");
				for (String ff : fs) {
					if (ff.trim().equalsIgnoreCase("a")) {
						friendEmail = "all";
						break;
					}
					f1913 = Integer.parseInt(ff.trim());
					String[] friendsList = lastFriendsList.get(email);
					if (friendsList == null || f1913 > friendsList.length
							|| f1913 < 0) {
						needRechoise = true;
						break;
					}
					friendEmail = friendEmail + friendsList[f1913 - 1] + ",";
				}
				if (needRechoise) {
					reply = "請重新選擇聯絡人(多個聯絡人請用【,】隔開，送給全部聯絡人，請輸入【a】):\r\n"
							+ arrayToMessage(lastFriendsList.get(email));
					nextStatus = 1913;
				} else {
					lastFriendEmail.put(email, friendEmail);
					reply = "请输入消息:";
					nextStatus = 19131;
				}

			} catch (NumberFormatException e) {
				reply = "請重新選擇聯絡人(多個聯絡人請用【,】隔開，送給全部聯絡人，請輸入【a】):\r\n"
						+ arrayToMessage(lastFriendsList.get(email));
				nextStatus = 1913;
			}
			break;
		case 19131:
			boolean ok = false;
			if (lastFriendEmail.get(email).equals("all"))
				ok = wsActionService.sendTextToAll(email, choise);
			else {
				String[] friendsList = lastFriendEmail.get(email).split(",");
				for (String friendList : friendsList) {
					if (friendList.trim().equals(""))
						continue;
					ok = ok | wsActionService.sendText(friendList, choise);
				}
			}
			if (ok)
				reply = operateDone;// + "\r\n\r\n" + managerMenuReply;
			else
				reply = errorOccured;
			nextStatus = 19;
			break;
		case 11:// 用户选了1发布以后再选。。。
			if (choise.equals("1")) {// 用户选发布给1社群
				reply = inputReply;
				nextStatus = 111;
			} else if (choise.equals("2")) {// 用户选发布给2我自己
				reply = inputReply;
				nextStatus = 112;
			} else if (choise.equals("3")) {// 用户选发布给3群组
				reply = getUserGroups(account_id, email);
				nextStatus = 113;
			} else if (choise.equals("4")) {// 用户选发布给4好友
				reply = getUserGroups(account_id, email);// 回复一个群组菜单，
				// 让用户选要发布的好友在那个群组里
				nextStatus = 114;
			} else {
				reply = reply1;
				nextStatus = 11;
			}
			break;
		case 114:
			Hashtable<String, String> table114 = lastGroupHash.get(email);
			if (table114 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String groupid = table114.get(choise);
				if (groupid == null) {
					reply = getUserGroups(account_id, email);
					nextStatus = 114;
				} else {
					Hashtable<String, String> friends = accountService
							.imGetUserGroupFriends(groupid);
					if (friends.size() == 0) {
						nextStatus = 114;
						reply = "該群組下沒有好友！";
					} else {
						StringBuffer sb = new StringBuffer("選擇好友:");
						Enumeration<String> keys = friends.keys();
						int num = 0;
						Hashtable<String, String> table = new Hashtable<String, String>();
						while (keys.hasMoreElements()) {
							String key = keys.nextElement();
							sb.append("\r\n" + (++num) + ".\t"
									+ friends.get(key));
							table.put(String.valueOf(num), key);
						}
						nextStatus = 1141;
						lastFriendHash.put(email, table);
						reply = sb.toString();
					}
				}
			}
			break;
		case 113:
			Hashtable<String, String> table113 = lastGroupHash.get(email);
			if (table113 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String groupid = table113.get(choise);
				if (groupid == null) {
					reply = getUserGroups(account_id, email);
					nextStatus = 113;
				} else if (accountService.imGetUserGroupFriends(groupid).size() == 0) {
					reply = emptyFriendReply;
					nextStatus = 113;
				} else {
					lastId.put(email, groupid);
					reply = inputReply;
					nextStatus = 1131;
				}
			}
			break;
		case 1111:// 為圖檔增加說明
		case 1121:// 為圖檔增加說明
		case 11311:// 為圖檔增加說明
		case 114111:// 為圖檔增加說明
			if (lastId.get(email) == null) {
				nextStatus = 1;
				reply = errorOccured;
			} else {
				if (!StringUtil.validateContent(choise, 70)) {
					reply = "您的發佈太長，最大70個中文字，140個英文字，请重新输入：";
					nextStatus = status;
				} else if (messageService.imUpdateMessageContent(account_id,
						lastId.get(email), choise)) {
					reply = "為發佈添加文字說明成功！\r\n" + webBaseUrl + "\r\n"
							+ sessionDefaultReply;
					nextStatus = 1;
				} else {
					reply = errorOccured;
					nextStatus = 1;
				}
			}
			break;
		case 111:
			if (!StringUtil.validateContent(choise, 70)) {
				reply = "您的發佈太長，最大70個中文字，140個英文字，请重新输入：";
				nextStatus = status;
			} else {
				reply = saveMessage(email, account_id, choise, null, "", "",
						12, sessionDefaultReply);
				nextStatus = 1;
			}
			break;
		case 112:
			if (!StringUtil.validateContent(choise, 70)) {
				reply = "您的發佈太長，最大70個中文字，140個英文字，请重新输入：";
				nextStatus = status;
			} else {
				reply = saveMessage(email, account_id, choise, null, "", "",
						-1, sessionDefaultReply);
				nextStatus = 1;
			}
			break;
		case 1141:
			Hashtable<String, String> table1141 = lastFriendHash.get(email);
			if (table1141 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String friendid = table1141.get(choise);
				if (friendid == null) {
					Hashtable<String, String> friends = accountService
							.imGetUserGroupFriends(account_id);
					if (friends.size() == 0) {
						nextStatus = 114;
						reply = "該群組下沒有好友！";
					} else {

						StringBuffer sb = new StringBuffer("選擇好友:");
						Enumeration<String> keys = friends.keys();
						int num = 0;
						Hashtable<String, String> table = new Hashtable<String, String>();
						while (keys.hasMoreElements()) {
							String key = keys.nextElement();
							sb.append("\r\n" + (++num) + ".\t"
									+ friends.get(key));
							table.put(String.valueOf(num), key);
						}
						nextStatus = 114;
						lastFriendHash.put(email, table);
						reply = sb.toString();
					}
				} else {
					reply = inputReply;
					lastId.put(email, friendid);
					nextStatus = 11411;
				}
			}
			break;
		case 11411:
			String friendid = lastId.get(email);
			if (friendid == null)
				reply = errorOccured;
			else {
				if (!StringUtil.validateContent(choise, 70)) {
					reply = "您的發佈太長，最大70個中文字，140個英文字，请重新输入：";
					nextStatus = status;
				} else {
					reply = saveMessage(email, account_id, choise, null, "",
							friendid, 3, sessionDefaultReply);
					nextStatus = 1;
				}
			}
			break;
		case 1131:
			String groupid = lastId.get(email);
			if (groupid == null)
				reply = errorOccured;
			else {
				if (!StringUtil.validateContent(choise, 70)) {
					reply = "您的發佈太長，最大70個中文字，140個英文字，请重新输入：";
					nextStatus = status;
				} else {
					reply = saveMessage(email, account_id, choise, null,
							groupid, "", 1, sessionDefaultReply);
					nextStatus = 1;
				}
			}

			break;
		case 15:
			Hashtable<String, String> table15 = lastGroupHash.get(email);
			if (table15 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String groupid15 = table15.get(choise);
				if (groupid15 == null) {
					reply = getUserGroups(account_id, email);
					nextStatus = 114;
				} else {
					Hashtable<String, String> friends = accountService
							.imGetUserGroupFriends(groupid15);
					if (friends.size() == 0) {
						nextStatus = 15;
						reply = "該群組下沒有好友！";
					} else {
						StringBuffer sb = new StringBuffer("選擇好友:");
						Enumeration<String> keys = friends.keys();
						int num = 0;
						Hashtable<String, String> table = new Hashtable<String, String>();
						while (keys.hasMoreElements()) {
							String key = keys.nextElement();
							sb.append("\r\n" + (++num) + ".\t"
									+ friends.get(key));
							table.put(String.valueOf(num), key);
						}
						nextStatus = 151;
						lastFriendHash.put(email, table);
						reply = sb.toString();
					}
				}
			}
			break;
		case 151:
			Hashtable<String, String> table151 = lastFriendHash.get(email);
			if (table151 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String friendid151 = table151.get(choise);
				if (friendid151 == null) {
					Hashtable<String, String> friends = accountService
							.imGetUserGroupFriends(account_id);
					if (friends.size() == 0) {
						nextStatus = 151;
						reply = "該群組下沒有好友！";
					} else {
						StringBuffer sb = new StringBuffer("選擇好友:");
						Enumeration<String> keys = friends.keys();
						int num = 0;
						Hashtable<String, String> table = new Hashtable<String, String>();
						while (keys.hasMoreElements()) {
							String key = keys.nextElement();
							sb.append("\r\n" + (++num) + ".\t"
									+ friends.get(key));
							table.put(String.valueOf(num), key);
						}
						nextStatus = 151;
						lastFriendHash.put(email, table);
						reply = sb.toString();
					}
				} else {
					Hashtable<String, String> messages = messageService
							.imGetUserPublicMessageLists(new Date(),
									account_id, friendid151, page
											* defaultPageSize, defaultPageSize);
					lastId.put(email, friendid151);
					lastChoise.put(email, choise);
					if (messages.size() == 0) {
						nextStatus = status;
						reply = "没有發佈！";
					} else {
						StringBuffer sb = new StringBuffer("選擇要查看的發佈:");
						Enumeration<String> keys = messages.keys();
						int num = 0;
						Hashtable<String, String> table = new Hashtable<String, String>();
						while (keys.hasMoreElements()) {
							String key = keys.nextElement();
							sb.append("\r\n" + (++num) + ".\t"
									+ messages.get(key));
							table.put(String.valueOf(num), key);
						}
						sb.append(navReply);
						nextStatus = 1511;
						lastMessageHash.put(email, table);
						reply = sb.toString();
					}
				}
			}
			break;
		case 1511:
			Hashtable<String, String> table15111 = lastMessageHash.get(email);
			if (table15111 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String messageid15111 = table15111.get(choise);
				if (messageid15111 == null) {
					reply = operError;
					nextStatus = status;
				} else {
					reply = messageService.imGetMessageDetailInfoById(
							messageid15111, account_id)
							+ resumeMenuReply;
					nextStatus = 0;
				}
			}
			break;
		case 12:
			Hashtable<String, String> table12 = lastMessageHash.get(email);
			if (table12 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String messageid12 = table12.get(choise);
				if (messageid12 == null) {
					reply = operError;
					nextStatus = status;
				} else {
					reply = messageService.imGetMessageDetailInfoById(
							messageid12, account_id)
							+ resumeMenuReply;
					nextStatus = 0;
				}
			}
			break;
		case 13:
			if (choise.equals("1") || choise.equals("2")) {// 阅读訂閱
				int type = choise.equals("1") ? 3 : 1;
				Hashtable<String, String> table13 = messageService
						.imGetOwnMessageCategory(account_id, type);

				if (table13.size() == 0) {
					nextStatus = 13;
					reply = "没有訂閱！\r\n" + ownCategoryReply;
				} else {
					StringBuffer sb = new StringBuffer("選擇訂閱:");
					Enumeration<String> keys = table13.keys();
					int num = 0;
					Hashtable<String, String> table = new Hashtable<String, String>();
					while (keys.hasMoreElements()) {
						String key = keys.nextElement();
						sb.append("\r\n" + (++num) + ".\t" + table13.get(key));
						table.put(String.valueOf(num), key);
					}
					nextStatus = choise.equals("1") ? 131 : 132;
					lastHash.put(email, table);
					reply = sb.toString();
				}
			} else if (choise.equals("3")) {// 我自己的發佈
				Hashtable<String, String> messages = messageService
						.imGetMessageLists(new Date(), -1, null, account_id,
								page * defaultPageSize, defaultPageSize);
				lastChoise.put(email, choise);
				if (messages.size() == 0) {
					nextStatus = status;
					reply = "没有發佈！";
				} else {
					StringBuffer sb = new StringBuffer("選擇要查看的發佈:");
					Enumeration<String> keys = messages.keys();
					int num = 0;
					Hashtable<String, String> table = new Hashtable<String, String>();
					while (keys.hasMoreElements()) {
						String key = keys.nextElement();
						sb.append("\r\n" + (++num) + ".\t" + messages.get(key));
						table.put(String.valueOf(num), key);
					}
					sb.append(navReply);
					nextStatus = 133;
					lastMessageHash.put(email, table);
					reply = sb.toString();
				}

			} else {
				reply = operError;
				nextStatus = status;
			}

			break;
		case 132:
		case 131:
			Hashtable<String, String> table131 = lastHash.get(email);
			if (table131 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String subscribeid;
				if (lastChoise.get(email) != null)
					subscribeid = lastId.get(email);
				else
					subscribeid = table131.get(choise);
				if (subscribeid == null) {
					reply = operError;
					nextStatus = status;
				} else {
					Hashtable<String, String> messages = status == 131 ? messageService
							.imGetOwnSubscribeMessageLists(new Date(),
									account_id, subscribeid, page
											* defaultPageSize, defaultPageSize)
							: messageService.imGetOwnTraceSubscribeMessageList(
									new Date(), account_id, page
											* defaultPageSize, defaultPageSize);
					lastId.put(email, subscribeid);
					lastChoise.put(email, choise);
					if (messages.size() == 0) {
						nextStatus = status;
						reply = "没有發佈！";
					} else {
						StringBuffer sb = new StringBuffer("選擇要查看的發佈：");
						Enumeration<String> keys = messages.keys();
						int num = 0;
						Hashtable<String, String> table = new Hashtable<String, String>();
						while (keys.hasMoreElements()) {
							String key = keys.nextElement();
							sb.append("\r\n" + (++num) + ".\t"
									+ messages.get(key));
							table.put(String.valueOf(num), key);
						}
						sb.append(navReply);
						nextStatus = status == 131 ? 1311 : 1321;
						lastMessageHash.put(email, table);
						reply = sb.toString();
					}

				}
			}
			break;
		case 1311:
		case 1321:
		case 133:
			Hashtable<String, String> table133 = lastMessageHash.get(email);
			if (table133 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String messageid133 = table133.get(choise);
				if (messageid133 == null) {
					reply = operError;
					nextStatus = status;
				} else {
					reply = messageService.imGetMessageDetailInfoById(
							messageid133, account_id)
							+ resumeMenuReply;
					nextStatus = 0;
				}
			}
			break;
		case 14:
			Hashtable<String, String> table14 = lastGroupHash.get(email);
			if (table14 == null) {
				reply = getUserGroups(account_id, email);
				nextStatus = 14;
			} else {
				String groupid14 = table14.get(choise);
				if (groupid14 == null) {
					reply = getUserGroups(account_id, email);
					nextStatus = 14;
				} else {
					Hashtable<String, String> messages14 = messageService
							.imGetMessageLists(new Date(), 1, groupid14,
									account_id, page * defaultPageSize,
									defaultPageSize);
					lastId.put(email, groupid14);
					lastChoise.put(email, choise);
					if (messages14.size() == 0) {
						nextStatus = status;
						reply = "没有發佈！";
					} else {
						StringBuffer sb = new StringBuffer("選擇要查看的發佈：");
						Enumeration<String> keys = messages14.keys();
						int num = 0;
						Hashtable<String, String> table = new Hashtable<String, String>();
						while (keys.hasMoreElements()) {
							String key = keys.nextElement();
							sb.append("\r\n" + (++num) + ".\t"
									+ messages14.get(key));
							table.put(String.valueOf(num), key);
						}
						sb.append(navReply);
						nextStatus = 141;
						lastMessageHash.put(email, table);
						reply = sb.toString();
					}
				}
			}
			break;
		case 141:
			Hashtable<String, String> table141 = lastMessageHash.get(email);
			if (table141 == null) {
				reply = sessionDefaultReply;
				nextStatus = 1;
			} else {
				String messageid141 = table141.get(choise);
				if (messageid141 == null) {
					reply = operError;
					nextStatus = status;
				} else {
					reply = messageService.imGetMessageDetailInfoById(
							messageid141, account_id)
							+ resumeMenuReply;
					nextStatus = 0;
				}
			}
			break;
		default:
			reply = sessionDefaultReply;
			nextStatus = 1;
		}

		if (nextStatus != 0) {
			if (userStatus.getLast() != nextStatus) {
				userInput.add(choise);
				userStatus.add(nextStatus);
			}

			if (nextStatus == 1) {
				userStatus.clear();
				userStatus.add(1);
				userInput.clear();

				lastInput.put(email, userInput);
				lastStatus.put(email, userStatus);
			}
		}

		if (userStatus.size() > 1)
			reply = reply + "\r\n" + backReply + "; " + cancelReply;

		String[] replys = new String[reply.length() / 400 + 1];
		for (int i = 0; i < replys.length; i++) {
			if (i != (replys.length - 1))
				replys[i] = reply.substring(i * 400, (i + 1) * 400);
			else
				replys[i] = reply.substring(i * 400);
		}
		for (String r : replys) {
			if (!r.equals("")) {
				Logs.getLogger().info(
						"Call webservice to send text(" + r + ") to " + email);
				if (!wsActionService.sendText(email, r))
					Logs.getLogger().error(
							"Unable to send text to " + email
									+ " via webservice");
			}
		}

	}

	private String getUserGroups(String account_id, String email) {
		Hashtable<String, String> groups = accountService
				.imGetUserGroups(account_id);
		StringBuffer sb = new StringBuffer("選擇群組:");
		Enumeration<String> keys = groups.keys();
		int num = 0;
		Hashtable<String, String> table = new Hashtable<String, String>();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			sb.append("\r\n" + (++num) + ".\t" + groups.get(key));
			table.put(String.valueOf(num), key);
		}
		lastGroupHash.put(email, table);
		return sb.toString();
	}

	private String saveMessage(String msn, String account_id, String content,
			String image, String groupid, String friendid, int purview,
			String sessionDefaultReply) {
		String text = null;
		String messageid = messageService.imSaveMessage(account_id, content,
				image, groupid, friendid, purview);
		if (messageid != null) {
			lastId.put(msn, messageid);
			if (image == null)
				text = "發佈成功！\r\n" + webBaseUrl + "\r\n" + sessionDefaultReply;
			else {
				text = "發佈圖檔成功！\r\n" + webBaseUrl
						+ "\r\n您也可以輸入一段說明文字（最大70個中文字，140個英文字）:";
			}
		} else {
			text = "把發佈存入数据库时出现错误!";
		}
		return text;
	}

	@Override
	protected boolean isAdmin(String friend) {
		for (String adm : adminAccounts) {
			if (adm.equals(friend))
				return true;
		}
		return false;
	}

	@Override
	protected boolean isSuperAdmin(String friend) {
		return isAdmin(friend);
	}

	@Override
	protected void init() throws Exception {
		wsMemberService.init(settings.getWsUrl(), settings.getWsPassport(),
				settings.getWsPasscode());
		wsServiceService.init(settings.getWsUrl(), settings.getWsPassport(),
				settings.getWsPasscode());
		wsMessengerService.init(settings.getWsUrl(), settings.getWsPassport(),
				settings.getWsPasscode());
		wsActionService.init(settings.getWsUrl(), settings.getWsPassport(),
				settings.getWsPasscode());

	}
}
