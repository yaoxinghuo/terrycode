package com.microblog.ws.action.impl;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.microblog.ws.action.intf.IActionService;

public class ActionServiceImpl implements IActionService {
	private EndpointReference targetEPR;
	private RPCServiceClient serviceClient;

	private String ns = "http://action.webservice.msn.microblog.com";

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
	public boolean knockOn(String email) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, email };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "knockOn");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean knockOnAll(String account) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "knockOnAll");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean sendText(String email, String text) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, email,
				text };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "sendText");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean sendTextToAll(String account, String text) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				text };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "sendTextToAll");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

}
