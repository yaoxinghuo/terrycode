package com.microblog.ws.intf;

public interface IChangeDisplayPicService {

	public boolean changeMessengerDisplayPic(String passport, String passcode,
			String account, String picPath) throws Exception;

	public boolean changeServiceDisplayPic(String passport, String passcode,
			String picPath) throws Exception;

}