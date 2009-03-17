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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(Commands name) {
		this.name = name;
	}

	private String account;// 機器人帳號
	private String email;// 朋友帳號
	private int len;// 附加資訊長度

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
		if (len == 0)
			body = "";
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
		if (body == null)
			len = 0;
		else
			len = body.length();
	}

	private String body;// 附加資訊

	public Command(Object source) {
		super(source);
	}

	public Command(String mix) throws Exception {
		super(mix);
		String[] parts = mix.split("\\s", 5);
		if (parts.length < 4)
			throw new CommandParserException("Syntax Error:" + mix);
		try {
			name = Commands.valueOf(parts[0]);
		} catch (Exception e) {
			throw new CommandParserException("Unrecognized command:" + parts[0]);
		}
		account = parts[1].toLowerCase();
		email = parts[2].toLowerCase();
		try {
			len = Integer.parseInt(parts[3]);
		} catch (Exception e) {
			throw new CommandParserException("Unable to parser number:"
					+ parts[3]);
		}
		if (len != 0) {
			if (parts[4].length() < len)
				throw new CommandParserException("Extra msg length error:"
						+ parts[4]);
			body = parts[4].substring(0, len);
		} else
			body = "";

	}

	public Command(Commands name, String account, String email, int len,
			String body) {
		super(name.toString());
		this.name = name;
		this.account = account;
		this.email = email;
		this.len = len;
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name.toString()).append(" ");
		sb.append(account).append(" ");
		sb.append(email).append(" ");
		sb.append(len).append("\n");
		if (len != 0)
			sb.append(body);
		return sb.toString();
	}

}
