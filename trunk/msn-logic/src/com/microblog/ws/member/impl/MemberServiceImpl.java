package com.microblog.ws.member.impl;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axiom.om.impl.llom.OMElementImpl;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.microblog.ws.member.intf.IMemberService;
import com.microblog.ws.model.MemberStatusWrapper;

public class MemberServiceImpl implements IMemberService {
	private EndpointReference targetEPR;
	private RPCServiceClient serviceClient;

	private String ns = "http://member.webservice.msn.microblog.com";

	private String passport;
	private String passcode;

	public void init(String wsUrl, String passport, String passcode)
			throws Exception {
		if (!wsUrl.startsWith("http://") && !wsUrl.startsWith("https://"))
			wsUrl = "http://" + wsUrl;
		this.passport = passport;
		this.passcode = passcode;
		targetEPR = new EndpointReference(wsUrl + "/services/Member");
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int acceptFriend(String account, String email) throws Exception {
		// 指定方法的参数值
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				email };
		// 指定方法返回值的数据类型的Class对象
		Class[] classes = new Class[] { Integer.class };
		// 指定要调用的getGreeting方法及WSDL文件的命名空间
		QName opAddEntry = new QName(ns, "acceptFriend");
		// 调用acceptFriend方法并输出该方法的返回值
		return (Integer) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int addFriend(String account, String email, int type)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				email };
		Class[] classes = new Class[] { Integer.class };
		QName opAddEntry = new QName(ns, "addFriend");
		return (Integer) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean allowFriend(String account, String email) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				email };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "allowFriend");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean blockFriend(String account, String email) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				email };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "blockFriend");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] friendList(String account) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account };
		Class[] classes = new Class[] { List.class };
		QName opAddEntry = new QName(ns, "friendList");
		List<OMElementImpl> olists = (List) (serviceClient.invokeBlocking(
				opAddEntry, opAddEntryArgs, classes)[0]);
		String[] lists = new String[olists.size()];
		for (int i = 0; i < olists.size(); i++) {
			lists[i] = olists.get(i).getText();
		}
		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MemberStatusWrapper friendStatus(String account, String email)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				email };
		Class[] classes = new Class[] { MemberStatusWrapper.class };
		QName opAddEntry = new QName(ns, "friendStatus");
		return (MemberStatusWrapper) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] pendingList(String account) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account };
		Class[] classes = new Class[] { List.class };
		QName opAddEntry = new QName(ns, "pendingList");
		List<OMElementImpl> olists = (List) (serviceClient.invokeBlocking(
				opAddEntry, opAddEntryArgs, classes)[0]);
		String[] lists = new String[olists.size()];
		for (int i = 0; i < olists.size(); i++) {
			lists[i] = olists.get(i).getText();
		}
		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int removeAndBlockFriend(String account, String email)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				email };
		Class[] classes = new Class[] { Integer.class };
		QName opAddEntry = new QName(ns, "removeAndBlockFriend");
		return (Integer) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int removeFriend(String account, String email) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				email };
		Class[] classes = new Class[] { Integer.class };
		QName opAddEntry = new QName(ns, "removeFriend");
		return (Integer) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

}
