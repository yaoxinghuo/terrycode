package cn.edu.jiangnan.lab.dwr;

import cn.edu.jiangnan.lab.data.service.intf.IMessageService;

public class Message {
	private IMessageService messageService;

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	public String getMessages() {
		return messageService.getMessages();
	}

	public String getMessagesByType(int type) {
		return messageService.getMessagesByType(type);
	}

	public String getIntroduceMessagesByType(int type) {
		return messageService.getIntrodoceMessagesByType(type);
	}
}
