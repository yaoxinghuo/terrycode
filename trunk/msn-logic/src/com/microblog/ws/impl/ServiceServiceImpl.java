package com.microblog.ws.impl;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.microblog.ws.intf.IServiceService;
import com.microblog.ws.model.ServiceStatusWrapper;

public class ServiceServiceImpl implements IServiceService {

	private EndpointReference targetEPR;
	private RPCServiceClient serviceClient;

	private String ns = "http://service.webservice.msn.microblog.com";

	public ServiceServiceImpl(String url) throws Exception {
		targetEPR = new EndpointReference(url);
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changeDisplayName(String passport, String passcode,
			String displayName) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode,
				displayName };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "changeDisplayName");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changePersonalMessage(String passport, String passcode,
			String personalMessage) throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode,
				personalMessage };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "changePersonalMessage");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceStatusWrapper currentStatus(String passport, String passcode)
			throws Exception {
		String[] opAddEntryArgs = new String[] { passport, passcode };
		Class[] classes = new Class[] { ServiceStatusWrapper.class };
		QName opAddEntry = new QName(ns, "currentStatus");
		return (ServiceStatusWrapper) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

}
