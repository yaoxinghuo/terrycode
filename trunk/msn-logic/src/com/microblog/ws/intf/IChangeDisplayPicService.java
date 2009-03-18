package com.microblog.ws.intf;

public interface IChangeDisplayPicService {

	public boolean changeMessengerDisplayPic(String passport, String passcode,
			String account, byte[] pic);

	public boolean changeServiceDisplayPic(String passport, String passcode,
			byte[] pic);

}
