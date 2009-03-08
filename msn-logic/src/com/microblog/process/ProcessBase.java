package com.microblog.process;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.microblog.data.service.intf.IAccountService;
import com.microblog.data.service.intf.IMessageService;
import com.microblog.ws.intf.IActionService;
import com.microblog.ws.intf.IMemberService;
import com.microblog.ws.intf.IMessengerService;
import com.microblog.ws.intf.IServiceService;
import com.microblog.ws.model.MemberStatusWrapper;

public abstract class ProcessBase {

	public ProcessBase() throws Exception {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"applicationContext.xml");
		messageService = (IMessageService) ctx.getBean("messageService");
		accountService = (IAccountService) ctx.getBean("accountService");
//		serviceService = (com.microblog.data.service.intf.IServiceService) ctx
//				.getBean("serviceService");
		wsMemberService = (IMemberService) ctx.getBean("wsMemberService");
		wsServiceService = (IServiceService) ctx.getBean("wsServiceService");
		wsMessengerService = (IMessengerService) ctx
				.getBean("wsMessegerService");
		wsActionService = (IActionService) ctx.getBean("wsActionService");
	}

	protected abstract void init() throws Exception;

	protected abstract boolean isAdmin(String friend);

	protected abstract boolean isSuperAdmin(String friend);

	public abstract void textMessage(Command command) throws Exception;

	public abstract void process(Command command);

	protected String arrayToMessage(final String[] arr) {
		if (arr == null || arr.length == 0) {
			return "沒有資料.";
		}
		// Arrays.sort(arr);
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			sb.append(i + 1).append(".\t").append(str).append("\r\n");
		}
		return sb.toString();
	}

	protected String goMsnFriendDetail(String email, String friendEmail)
			throws Exception {
		MemberStatusWrapper friend = wsMemberService.friendStatus(email,
				friendEmail);
		if (friend == null) {
			return "找不到對象.";
		} else {
			final StringBuilder sb = new StringBuilder();
			sb.append("名稱:\t").append(friend.getDisplayName()).append("\r\n");
			sb.append("個人訊息:\t").append(friend.getPersonalMessage()).append(
					"\r\n");
			sb.append("狀態:\t").append(friend.getStatus()).append("\r\n");
			sb.append("朋友清單:\t").append(friend.isFollow() ? "是" : "否").append(
					"\r\n");
			sb.append("允許清單:\t").append(friend.isAllow() ? "是" : "否").append(
					"\r\n");
			sb.append("黑名單:\t").append(friend.isBlock() ? "是" : "否").append(
					"\r\n");
			sb.append("待確認:\t").append(friend.isPending() ? "是" : "否");
			return sb.toString();
		}
	}

	protected IMessageService messageService;
	protected IAccountService accountService;
//	protected com.microblog.data.service.intf.IServiceService serviceService;

	protected IMemberService wsMemberService;
	protected IServiceService wsServiceService;
	protected IMessengerService wsMessengerService;
	protected IActionService wsActionService;

	/*public com.microblog.data.service.intf.IServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(
			com.microblog.data.service.intf.IServiceService serviceService) {
		this.serviceService = serviceService;
	}*/

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public IMemberService getWsMemberService() {
		return wsMemberService;
	}

	public void setWsMemberService(IMemberService wsMemberService) {
		this.wsMemberService = wsMemberService;
	}

	public IServiceService getWsServiceService() {
		return wsServiceService;
	}

	public void setWsServiceService(IServiceService wsServiceService) {
		this.wsServiceService = wsServiceService;
	}

	public IMessengerService getWsMessengerService() {
		return wsMessengerService;
	}

	public void setWsMessengerService(IMessengerService wsMessengerService) {
		this.wsMessengerService = wsMessengerService;
	}

	public IActionService getWsActionService() {
		return wsActionService;
	}

	public void setWsActionService(IActionService wsActionService) {
		this.wsActionService = wsActionService;
	}
}
