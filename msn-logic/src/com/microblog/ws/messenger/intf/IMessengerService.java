package com.microblog.ws.messenger.intf;

import com.microblog.ws.model.MessengerStatusWrapper;

public interface IMessengerService {

	public void init(String wsUrl, String passport, String passcode)
			throws Exception;

	public boolean changeDisplayName(String account, String displayName)
			throws Exception;

	public boolean changePersonalMessage(String account, String personalMessage)
			throws Exception;

	public MessengerStatusWrapper currentStatus(String account)
			throws Exception;

	public String[] list() throws Exception;

}
