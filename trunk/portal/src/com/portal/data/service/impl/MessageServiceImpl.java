package com.portal.data.service.impl;

import java.util.ArrayList;

import com.portal.data.dao.intf.IMessageDao;
import com.portal.data.pojo.Message;
import com.portal.data.service.intf.IMessageService;

/**
 * Developer: Terry DateTime : 2008-1-24 下午01:12:52
 */

public class MessageServiceImpl implements IMessageService {

	private IMessageDao messageDao;

	public IMessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public ArrayList<Message> getMessages(int type) {
		return messageDao.getMessagesByType(type);
	}

	@Override
	public boolean saveMessage(Message message) {
		return messageDao.saveMessage(message);
	}

}
