package com.microblog.process;

public class CommandParserException extends Exception {
	/**
	 * 命令解析异常 [指令][空格][機器人帳號][空格][朋友帳號][空格][附加資訊長度][換行字元][附加資訊]
	 * 
	 * 範例 MSG robot@hotmail.com friend@hotmail.com 10\n1234567890 
	 * ADC robot@hotmail.com friend@hotmail.com 0\n
	 */
	private static final long serialVersionUID = 758443439412754474L;

	public CommandParserException(String msg) {
		super(msg);
	}
}
