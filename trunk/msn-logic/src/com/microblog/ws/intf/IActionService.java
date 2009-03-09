package com.microblog.ws.intf;

public interface IActionService {
	public boolean knockOn(String passport, String passcode, String email)
			throws Exception;

	public boolean knockOnAll(String passport, String passcode, String account)
			throws Exception;

	public boolean sendText(String passport, String passcode, String email,
			String text) throws Exception;

	public boolean sendTextToAll(String passport, String passcode,
			String account, String text) throws Exception;
}
