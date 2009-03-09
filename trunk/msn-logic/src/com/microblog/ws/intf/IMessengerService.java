package com.microblog.ws.intf;

import com.microblog.ws.model.MessengerStatusWrapper;

public interface IMessengerService {
	public boolean changeDisplayName(String passport, String passcode,
			String account, String displayName) throws Exception;

	public boolean changePersonalMessage(String passport, String passcode,
			String account, String personalMessage) throws Exception;

	public MessengerStatusWrapper currentStatus(String passport,
			String passcode, String account) throws Exception;

	public String[] list(String passport, String passcode) throws Exception;

}
