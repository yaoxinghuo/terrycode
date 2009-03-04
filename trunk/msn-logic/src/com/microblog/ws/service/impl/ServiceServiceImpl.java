package com.microblog.ws.service.impl;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.microblog.ws.model.ServiceStatusWrapper;
import com.microblog.ws.service.intf.IServiceService;

public class ServiceServiceImpl implements IServiceService {

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
		targetEPR = new EndpointReference(wsUrl + "/services/Service");
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changeDisplayName(String displayName) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode,
				displayName };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(
				"http://service.webservice.msn.microblog.com",
				"changeDisplayName");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changePersonalMessage(String personalMessage)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode,
				personalMessage };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(
				"http://service.webservice.msn.microblog.com",
				"changePersonalMessage");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceStatusWrapper currentStatus() throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode };
		Class[] classes = new Class[] { ServiceStatusWrapper.class };
		QName opAddEntry = new QName(
				"http://service.webservice.msn.microblog.com",
				"currentStatus");
		return (ServiceStatusWrapper) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

}
