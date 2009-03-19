package com.microblog.ws.impl;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.microblog.ws.intf.IChangeDisplayPicService;

public class ChangeDisplayPicServiceImpl implements IChangeDisplayPicService {

	private EndpointReference targetEPR;
	private RPCServiceClient serviceClient;

	private String ns = "http://changeDisplayPic.webservice.msn.microblog.com";

	public ChangeDisplayPicServiceImpl(String url) throws Exception {
		targetEPR = new EndpointReference(url);
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changeMessengerDisplayPic(String passport, String passcode,
			String account, String picPath) throws Exception {
		File file = new File(picPath);
		if (!file.exists())
			return false;
		long len = file.length();
		if (((float) len / 1024f) > 30)
			return false;
		FileInputStream fin = new FileInputStream(file);
		byte[] b = new byte[(int) len];
		fin.read(b, 0, b.length);
		fin.close();
		Object[] opAddEntryArgs = new Object[] { passport, passcode, account, b };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "changeMessengerDisplayPic");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changeServiceDisplayPic(String passport, String passcode,
			String picPath) throws Exception {
		File file = new File(picPath);
		if (!file.exists())
			return false;
		long len = file.length();
		if (((float) len / 1024f) > 30)
			return false;
		FileInputStream fin = new FileInputStream(file);
		byte[] b = new byte[(int) len];
		fin.read(b, 0, b.length);
		fin.close();
		Object[] opAddEntryArgs = new Object[] { passport, passcode, b };
		Class[] classes = new Class[] { Boolean.class };
		QName opAddEntry = new QName(ns, "changeServiceDisplayPic");
		return (Boolean) (serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0]);
	}

}
