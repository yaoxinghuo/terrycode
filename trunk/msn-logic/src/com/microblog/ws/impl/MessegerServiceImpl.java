package com.microblog.ws.impl;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axiom.om.impl.llom.OMElementImpl;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.microblog.ws.intf.IMessengerService;
import com.microblog.ws.model.MessengerStatusWrapper;

public class MessegerServiceImpl implements IMessengerService {

	private EndpointReference targetEPR;
	private RPCServiceClient serviceClient;

	private String ns = "http://messenger.webservice.msn.microblog.com";

	public MessegerServiceImpl(String url) throws Exception {
		targetEPR = new EndpointReference(url);
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changeDisplayName(String passport, String passcode,
			String account, String displayName) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				displayName };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "changeDisplayName");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changePersonalMessage(String passport, String passcode,
			String account, String personalMessage) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account,
				personalMessage };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "changePersonalMessage");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public MessengerStatusWrapper currentStatus(String passport,
			String passcode, String account) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode, account };
		Class[] classes = new Class[] { MessengerStatusWrapper.class };
		QName opAddEntry = new QName(ns, "currentStatus");
		return (MessengerStatusWrapper) (serviceClient.invokeBlocking(
				opAddEntry, opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] list(String passport, String passcode) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode };
		Class[] classes = new Class[] { List.class };
		QName opAddEntry = new QName(ns, "list");
		List<OMElementImpl> olists = (List) (serviceClient.invokeBlocking(
				opAddEntry, opAddEntryArgs, classes)[0]);
		String[] lists = new String[olists.size()];
		for (int i = 0; i < olists.size(); i++) {
			lists[i] = olists.get(i).getText();
		}
		return lists;
	}

}
