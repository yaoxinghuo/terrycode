package com.microblog.ws.messenger.impl;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.microblog.ws.messenger.intf.IMessengerService;
import com.microblog.ws.model.MessengerStatusWrapper;

public class MessegerServiceImpl implements IMessengerService {

	private EndpointReference targetEPR;
	private RPCServiceClient serviceClient;

	private String passport;
	private String passcode;

	public void init(String wsUrl, String passport, String passcode)
			throws Exception {
		if (!wsUrl.startsWith("http://") && !wsUrl.startsWith("https://"))
			wsUrl = "http://" + wsUrl;
		this.passport = passport;
		this.passcode = passcode;
		targetEPR = new EndpointReference(wsUrl + "/services/Action");
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changeDisplayName(String account, String displayName)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				displayName };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(
				"http://member.webservice.msn.microblog.com",
				"changeDisplayName");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changePersonalMessage(String account, String personalMessage)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				personalMessage };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(
				"http://member.webservice.msn.microblog.com",
				"changePersonalMessage");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public MessengerStatusWrapper currentStatus(String account)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account };
		Class[] classes = new Class[] { MessengerStatusWrapper.class };
		QName opAddEntry = new QName(
				"http://member.webservice.msn.microblog.com", "currentStatus");
		return (MessengerStatusWrapper) (serviceClient.invokeBlocking(
				opAddEntry, opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] list() throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode };
		Class[] classes = new Class[] { String[].class };
		QName opAddEntry = new QName(
				"http://member.webservice.msn.microblog.com", "list");
		return (String[]) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

}
