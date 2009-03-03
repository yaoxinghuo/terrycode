package com.microblog.ws.action.intf;

public interface IActionService {
	public void init(String wsUrl, String passport, String passcode)
			throws Exception;

	public boolean knockOn(String email) throws Exception;

	public boolean knockOnAll(String account) throws Exception;

	public boolean sendText(String email, String text) throws Exception;

	public boolean sendTextToAll(String account, String text) throws Exception;
}
