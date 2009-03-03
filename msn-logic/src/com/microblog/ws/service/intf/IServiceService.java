package com.microblog.ws.service.intf;

import com.microblog.ws.model.ServiceStatusWrapper;

public interface IServiceService {

	public void init(String wsUrl, String passport, String passcode)
			throws Exception;

	public boolean changeDisplayName(String displayName) throws Exception;

	public boolean changePersonalMessage(String personalMessage)
			throws Exception;

	public ServiceStatusWrapper currentStatus() throws Exception;

}
