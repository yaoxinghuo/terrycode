package com.portal.dwr;

import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;

import com.portal.data.pojo.Message;
import com.portal.data.service.intf.IMessageService;

/**
 * Developer: Terry DateTime : 2008-1-23 上午11:17:29
 */

public class ServerMessages {
	private IMessageService messageService;
	private HashMap<Integer, JSONArray> messages = new HashMap<Integer, JSONArray>();

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	public String getMessages(int type) {
		JSONArray message = messages.get(type);
		if (message == null)
			return null;
		else
			return message.toString();
	}

	public void addMessage() {
		Message message = new Message();
		message
				.setContent("<font color='#0000CC'>20:15 </font>type2模拟更新后的数据-测试1<br>");
		message.setInuse(true);
		message.setType(2);
		messageService.saveMessage(message);
	}

	public void retrieveMessages() {
		System.out.println("Retriving...");
		for (int i = 1; i <= 3; i++) {
			ArrayList<Message> am = messageService.getMessages(i);
			if (am.size() != 0) {
				JSONArray ja = new JSONArray();
				for (int j = 0; j < am.size(); j++) {
					ja.add(am.get(j).getContent());
				}
				messages.put(i, ja);
			}
		}
	}

}
