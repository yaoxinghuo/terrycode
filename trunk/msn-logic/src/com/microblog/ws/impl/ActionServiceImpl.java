package com.microblog.ws.impl;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.microblog.ws.intf.IActionService;

public class ActionServiceImpl implements IActionService {
	private EndpointReference targetEPR;
	private RPCServiceClient serviceClient;

	private String ns = "http://action.webservice.msn.microblog.com";

	public ActionServiceImpl(String url) throws Exception {
		targetEPR = new EndpointReference(url);
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean knockOn(String passport, String passcode, String email)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, email };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "knockOn");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean knockOnAll(String passport, String passcode, String account)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "knockOnAll");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean sendText(String passport, String passcode, String email,
			String text) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, email,
				text };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "sendText");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean sendTextToAll(String passport, String passcode,
			String account, String text) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				text };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "sendTextToAll");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

}
