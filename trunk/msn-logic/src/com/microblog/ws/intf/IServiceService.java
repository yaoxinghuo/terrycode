package com.microblog.ws.intf;

import com.microblog.ws.model.ServiceStatusWrapper;

public interface IServiceService {
	public boolean changeDisplayName(String passport, String passcode,
			String displayName) throws Exception;

	public boolean changePersonalMessage(String passport, String passcode,
			String personalMessage) throws Exception;

	public ServiceStatusWrapper currentStatus(String passport, String passcode)
			throws Exception;

}
