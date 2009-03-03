package com.microblog.process;

import java.util.EventObject;

public class Command extends EventObject {
	/*
	 * [指令][空格][機器人帳號][空格][朋友帳號][空格][附加資訊長度][換行字元][附加資訊]
	 */

	private static final long serialVersionUID = -2546916448053378948L;
	private Commands name;// 指令名字

	public Commands getName() {
		return name;
	}

	public void setName(Commands name) {
		this.name = name;
	}

	private String robotId;// 機器人帳號
	private String friendId;// 朋友帳號
	private int len;// 附加資訊長度

	public String getRobotId() {
		return robotId;
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
		if (len == 0)
			msg = null;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
		if (msg == null)
			len = 0;
		else
			len = msg.length();
	}

	private String msg;// 附加資訊

	public Command(Object source) {
		super(source);
	}

	public Command(String mix) throws Exception {
		super(mix);
		String[] parts = mix.split("\\s", 5);
		if (parts.length < 4)
			throw new Exception("Syntax Error:" + mix);
		try {
			name = Commands.valueOf(parts[0]);
		} catch (Exception e) {
			throw new Exception("Unrecognized command:" + parts[0]);
		}
		robotId = parts[1].toLowerCase();
		friendId = parts[2].toLowerCase();
		try {
			len = Integer.parseInt(parts[3]);
		} catch (Exception e) {
			throw new Exception("Unable to parser number:" + parts[3]);
		}
		if (len != 0) {
			if (parts.length < 5)
				msg = "";
			else
				msg = parts[4];

		} else
			msg = "";

	}

	public Command(Commands name, String robotId, String friendId, int len,
			String msg) {
		super(name);
		this.name = name;
		this.robotId = robotId;
		this.friendId = friendId;
		this.len = len;
		this.msg = msg;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name.toString()).append(" ");
		sb.append(robotId).append(" ");
		sb.append(friendId).append(" ");
		sb.append(len).append("\n");
		if (len != 0)
			sb.append(msg);
		return sb.toString();
	}

}
