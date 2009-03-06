package com.microblog.process;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.microblog.data.model.Account;
import com.microblog.data.service.intf.IAccountService;
import com.microblog.data.service.intf.IMessageService;
import com.microblog.util.Logs;
import com.microblog.util.Settings;
import com.microblog.util.StringUtil;
import com.microblog.ws.action.intf.IActionService;
import com.microblog.ws.member.intf.IMemberService;
import com.microblog.ws.messenger.intf.IMessengerService;
import com.microblog.ws.model.MemberStatusWrapper;
import com.microblog.ws.service.intf.IServiceService;

public class ForumProcess extends ProcessBase {

	private String webBaseUrl;

	private String forumBaseUrl;

	private String forumid;

	private String[] adminAccounts;

	private String forumAdminAccount;

	private String[] managerMenu = { "取消", "查允許清單", "列出黑名單", "未確認清單", "查朋友資料",
			"改顯示名稱", "改個人訊息", "改線上狀態", "改個人頭像", "加入聯絡人", "移除聯絡人", "加允許清單",
			"加入黑名單", "送文字訊息" };
	private String managerMenuReply;
	private String[] msnUserStatusMenu = { "取消", "Online", "Busy", "Idle",
			"Be Right Back", "Away", "On the Phone", "Out to Lunch",
			"Appear Offline", "Offline" };
	private String msnUserStatusReply;
	private Hashtable<String, String> lastFriendEmail = new Hashtable<String, String>();
	private Hashtable<String, String> lastMessageId = new Hashtable<String, String>();
	private Hashtable<String, String> userAccountId = new Hashtable<String, String>();
	private Hashtable<String, Integer> managerMenuHash = new Hashtable<String, Integer>();
	private Hashtable<String, String> msnUserStatusHash = new Hashtable<String, String>();

	// 存储会话状态堆栈
	private Hashtable<String, LinkedList<Integer>> lastStatus = new Hashtable<String, LinkedList<Integer>>();
	// 记录机器人回覆堆栈，方便在“返回上一层”菜单的操作
	private Hashtable<String, LinkedList<String>> lastInput = new Hashtable<String, LinkedList<String>>();

	private Hashtable<String, Hashtable<String, String>> lastMessageHash = new Hashtable<String, Hashtable<String, String>>();

	private Hashtable<String, String[]> lastFriendsList = new Hashtable<String, String[]>();

	private Hashtable<String, Integer> lastPage = new Hashtable<String, Integer>();
	private Hashtable<String, String> lastChoise = new Hashtable<String, String>();

	// 记录上一次会话的时间，如果两次会话时间超过十分钟，重新启动新的会话
	private Hashtable<String, Date> lastSessionTime = new Hashtable<String, Date>();

	String defaultReply = "您可以利用這個界面來閱讀跟回覆同黨聚落的發佈！\r\n包括了下面這些功能（請输入数字）\r\n1.\t發佈\r\n2.\t閱讀目前十分鐘同黨聚落的發佈";
	String backReply = "【b】回上層";
	String cancelReply = "【0】取消";
	String inputFriendReply = "請輸入需要操作的好友（輸入前面的數字）";
	String managerReply = "\r\n9.\t管理";
	String operateDone = "您的操作已完成！";
	String inputReply = "請輸入要發佈的內容（70個中文字，140個英文字內）或上傳圖檔（目前僅支援 jpg gif png bmp 且檔案少於300K）";
	String resumeMenuReply = "\r\n繼續輸入文章編號閱讀內容,或按【n】列出下十篇;【m】前十篇;";
	String navReply = "\r\n輸入文章編號閱讀內容,或按【n】列出下十篇;【m】前十篇;";
	String errorOccured = "對不起，程式出錯，請稍候再試!";
	String operError = "請按照提示輸入正確的指令！";

	int defaultPageSize = 10;

	private IMessageService messageService;
	private IAccountService accountService;

	private IMemberService wsMemberService;
	private IServiceService wsServiceService;
	private IMessengerService wsMessengerService;
	private IActionService wsActionService;

	public ForumProcess(String account) throws Exception {
		super();
		settings = Settings.getInstance();
		init();
		com.microblog.data.model.Robot robot = serviceService
				.imGetRobotByAccount(account);
		if (robot == null || robot.getForumId() == null
				|| robot.getForumAdmin() == null)
			throw new Exception(
					"Cannot query record or record not complete from database by account:"
							+ account);
		this.forumid = robot.getForumId();
		this.forumAdminAccount = robot.getForumAdmin();
		this.adminAccounts = robot.getAdminAccounts().split(",");

		this.webBaseUrl = settings.getWebBaseUrl();
		this.forumBaseUrl = webBaseUrl + "forum.action?id=" + forumid;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < managerMenu.length; i++) {
			String menu = managerMenu[i];
			sb.append(i).append(".\t").append(menu).append("\r\n");
		}
		managerMenuReply = sb.toString();

		sb = new StringBuffer("請選擇狀態代码\r\n");
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

		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"applicationContext.xml");
		messageService = (IMessageService) ctx.getBean("messageService");
		accountService = (IAccountService) ctx.getBean("accountService");

		wsMemberService = (IMemberService) ctx.getBean("wsMemberService");
		wsMemberService.init(settings.getWsUrl(), settings.getSocketPassport(),
				settings.getSocketPasscode());
		wsServiceService = (IServiceService) ctx.getBean("wsServiceService");
		wsServiceService.init(settings.getWsUrl(),
				settings.getSocketPassport(), settings.getSocketPasscode());
		wsMessengerService = (IMessengerService) ctx
				.getBean("wsMessegerService");
		wsMessengerService.init(settings.getWsUrl(), settings
				.getSocketPassport(), settings.getSocketPasscode());
		wsActionService = (IActionService) ctx.getBean("wsActionService");
		wsActionService.init(settings.getWsUrl(), settings.getSocketPassport(),
				settings.getSocketPasscode());
	}

	private Settings settings;

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
				reply = inputReply;
				nextStatus = 11;
			} else if (choise.equals("2")) {
				Hashtable<String, String> messages = messageService
						.imGetForumMessageLists(new Date(), forumid,
								account_id, page * defaultPageSize,
								defaultPageSize);
				if (messages.size() == 0) {
					nextStatus = 1;
					reply = "没有發佈！\r\n" + sessionDefaultReply;
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
					nextStatus = 12;
					lastMessageHash.put(email, table);
					reply = sb.toString();
				}
			} else if (choise.equals("9") && isAdmin(email)) {
				reply = managerMenuReply;
				nextStatus = 19;
			} else {
				nextStatus = 1;
				reply = sessionDefaultReply;
			}
			break;
		case 11:// 用户选了1发布
			if (!StringUtil.validateContent(choise, 70)) {
				reply = "您的發佈太長，最大70個中文字，140個英文字，请重新输入：";
				nextStatus = status;
			} else {
				reply = saveMessage(email, account_id, choise, null,
						sessionDefaultReply);
				clearStack(email);
			}
			break;
		case 111:// 為圖檔增加說明
			if (lastMessageId.get(email) == null) {
				reply = errorOccured;
				clearStack(email);
			} else {
				if (!StringUtil.validateContent(choise, 70)) {
					reply = "您的發佈太長，最大70個中文字，140個英文字，请重新输入：";
					nextStatus = status;
				} else if (messageService.imUpdateMessageContent(account_id,
						lastMessageId.get(email), choise)) {
					reply = "為發佈添加文字說明成功！\r\n" + forumBaseUrl + "\r\n"
							+ sessionDefaultReply;
					clearStack(email);
				} else {
					reply = errorOccured;
					clearStack(email);
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
					nextStatus = status;
				}
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
					reply = arrayToMessage(wsMemberService.friendList(email))
							+ "\r\n\r\n" + managerMenuReply;
					nextStatus = 19;
					break;
				case 2:
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
					reply = "請輸入昵稱:";
					nextStatus = 195;
					break;
				case 6:// 改Personal Message
					reply = "請輸入個人訊息:";
					nextStatus = 196;
					break;
				case 7:// 改Status
					reply = msnUserStatusReply;
					nextStatus = 197;
					break;
				case 8:// 改头像
					reply = "請輸入頭像路徑:";
					nextStatus = 198;
				case 9:// 加入聯絡人
					reply = "論壇機器人暫不接受主動加好友！";
					nextStatus = 19;
					// reply = "請輸入聯絡人MSN\r\n0.\t取消" + backReply;
					// nextStatus = 199;
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
							&& isSuperAdmin(email)) {// 如果是管理员，而且发送指令是离线，那么登出msn
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

	private String saveMessage(String msn, String accountid, String content,
			String image, String sessionDefaultReply) {
		String text = null;
		String messageid = messageService.imSaveMessage(accountid, content,
				image, forumid, "", 1);
		if (messageid != null) {
			lastMessageId.put(msn, messageid);
			if (image == null)
				text = "發佈成功！\r\n" + forumBaseUrl + "\r\n"
						+ sessionDefaultReply;
			else {
				text = "發佈圖檔成功！\r\n" + forumBaseUrl
						+ "\r\n您也可以輸入一段說明文字（最大70個中文字，140個英文字）";
			}
		} else {
			text = "把發佈存入数据库时出现错误!";
		}
		return text;
	}

	private void clearStack(String email) {
		LinkedList<Integer> userStatus = lastStatus.get(email);
		if (userStatus == null) {
			userStatus = new LinkedList<Integer>();
			userStatus.add(1);
		}
		LinkedList<String> userInput = lastInput.get(email);
		if (userInput == null) {
			userInput = new LinkedList<String>();
			userInput.add(defaultReply);
		}
		userStatus.clear();
		userStatus.add(1);
		userInput.clear();
		userInput.add(defaultReply);

		lastStatus.put(email, userStatus);
		lastInput.put(email, userInput);
	}

	@Override
	protected String arrayToMessage(final String[] arr) {
		if (arr == null || arr.length == 0) {
			return "沒有資料.";
		}
		// Arrays.sort(arr);
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			sb.append(i + 1).append(".\t").append(str).append("\r\n");
		}
		return sb.toString();
	}

	@Override
	protected String goMsnFriendDetail(String email, String friendEmail)
			throws Exception {
		MemberStatusWrapper friend = wsMemberService.friendStatus(email,
				friendEmail);
		if (friend == null) {
			return "找不到對象.";
		} else {
			final StringBuilder sb = new StringBuilder();
			sb.append("名稱:\t").append(friend.getDisplayName()).append("\r\n");
			sb.append("個人訊息:\t").append(friend.getPersonalMessage()).append(
					"\r\n");
			sb.append("狀態:\t").append(friend.getStatus()).append("\r\n");
			sb.append("朋友清單:\t").append(friend.isFollow() ? "是" : "否").append(
					"\r\n");
			sb.append("允許清單:\t").append(friend.isAllow() ? "是" : "否").append(
					"\r\n");
			sb.append("黑名單:\t").append(friend.isBlock() ? "是" : "否").append(
					"\r\n");
			sb.append("待確認:\t").append(friend.isPending() ? "是" : "否");
			return sb.toString();
		}
	}

	@Override
	protected boolean isAdmin(String friend) {
		for (String adm : adminAccounts) {
			if (adm.equals(friend))
				return true;
		}
		if (forumAdminAccount.equals(friend))
			return true;
		return false;
	}

	@Override
	protected boolean isSuperAdmin(String friend) {
		for (String adm : adminAccounts) {
			if (adm.equals(friend))
				return true;
		}
		return false;
	}

	@Override
	protected void init() throws Exception {
		wsMemberService.init(settings.getWsUrl(), settings.getSocketPassport(),
				settings.getSocketPasscode());
		wsServiceService.init(settings.getWsUrl(),
				settings.getSocketPassport(), settings.getSocketPasscode());
		wsMessengerService.init(settings.getWsUrl(), settings
				.getSocketPassport(), settings.getSocketPasscode());
		wsActionService.init(settings.getWsUrl(), settings.getSocketPassport(),
				settings.getSocketPasscode());

	}

}
