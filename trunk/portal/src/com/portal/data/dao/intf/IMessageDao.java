package com.portal.data.dao.intf;

import java.util.ArrayList;

import com.portal.data.pojo.Message;

/**
 * Developer: Terry DateTime : 2008-1-24 下午12:03:30
 */

public interface IMessageDao {
	public boolean saveMessage(Message message);

	public boolean deleteMessage(Message message);

	public ArrayList<Message> getMessagesByType(int type);
}
