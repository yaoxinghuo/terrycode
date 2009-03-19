package com.microblog.ws.intf;

public interface IFriendDisplayPicService {

	public String get(String passport, String passcode, String email, String dir)
			throws Exception;
}
