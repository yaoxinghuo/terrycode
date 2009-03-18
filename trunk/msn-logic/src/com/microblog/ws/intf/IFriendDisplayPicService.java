package com.microblog.ws.intf;

import com.microblog.ws.model.PicPack;

public interface IFriendDisplayPicService {

	public PicPack get(String passport, String passcode, String email);
}
