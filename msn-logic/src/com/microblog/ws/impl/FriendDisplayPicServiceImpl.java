package com.microblog.ws.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.activation.DataHandler;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import com.microblog.ws.intf.IFriendDisplayPicService;

public class FriendDisplayPicServiceImpl implements IFriendDisplayPicService {
	private EndpointReference targetEPR;
	private ServiceClient serviceClient;
	private OMFactory fac;
	private OMNamespace omNs;

	private String ns = "http://friendDisplayPic.webservice.msn.microblog.com";

	public FriendDisplayPicServiceImpl(String url) throws Exception {
		targetEPR = new EndpointReference(url);
		fac = OMAbstractFactory.getOMFactory();
		omNs = fac.createOMNamespace(ns, "get");
		serviceClient = new ServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		// enabling MTOM in the client side
		options.setProperty(Constants.Configuration.ENABLE_MTOM,
				Constants.VALUE_TRUE);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String get(String epassport, String epasscode, String eemail,
			String sDir) throws Exception {
		OMElement data = fac.createOMElement("get", omNs);
		OMElement passport = fac.createOMElement("passport", omNs);
		passport.setText(epassport);
		OMElement passcode = fac.createOMElement("passcode", omNs);
		passcode.setText(epasscode);
		OMElement email = fac.createOMElement("email", omNs);
		email.setText(eemail);
		data.addChild(passport);
		data.addChild(passcode);
		data.addChild(email);
		OMElement ome = serviceClient.sendReceive(data);
		Iterator<OMElement> ite = ome.getFirstElement().getChildElements();
		String fileName = null;
		int counter = 0;
		while (ite.hasNext()) {
			OMElement e = ite.next();
			if (counter++ == 0) {
				fileName = e.getText();
				if (fileName == null)
					return null;
			} else {
				OMText binaryNode = (OMText) e.getFirstOMChild();
				binaryNode.setOptimize(true);// 必须加此句，否则会出现ContentID is null的异常!
				DataHandler actualDH = (DataHandler) binaryNode
						.getDataHandler();
				File dir = new File(sDir);
				if (!dir.isDirectory()) {
					if (!dir.exists())
						dir.createNewFile();
					else
						return null;
				}
				FileOutputStream fos = new FileOutputStream(new File(dir,
						fileName));
				InputStream is = actualDH.getInputStream();
				byte[] b = new byte[1024];
				while (true) {
					int i = is.read(b);
					if (i == -1)
						break;
					fos.write(b, 0, i);
				}
				fos.flush();
				fos.close();
				is.close();
				return fileName;
			}
		}
		return null;
	}

}
