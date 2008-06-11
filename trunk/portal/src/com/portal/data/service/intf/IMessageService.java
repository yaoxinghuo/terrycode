package com.portal.data.service.intf;

import java.util.ArrayList;

import com.portal.data.pojo.Message;

/**
 * Developer: Terry DateTime : 2008-1-24 下午01:11:26
 */

public interface IMessageService {
	public boolean saveMessage(Message message);

	public ArrayList<Message> getMessages(int type);
}
