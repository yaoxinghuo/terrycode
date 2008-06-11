package com.bank.client;

import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.context.MessageContext;

public class BankClient {
	private static EndpointReference targetEPR = new EndpointReference(
			"http://localhost:8080/axis2/services/BankService");
	private static boolean finish = false;

	private static OMElement getWithdrawPayload(String name, double mount) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://www.bank.com/",
				"withdraw");
		OMElement method = fac.createOMElement("withdraw", omNs);

		OMElement nameEle = fac.createOMElement("name", omNs);
		nameEle.addChild(fac.createOMText(nameEle, name));
		method.addChild(nameEle);

		OMElement mountEle = fac.createOMElement("mount", omNs);
		mountEle.addChild(fac.createOMText(mountEle, Double.toString(mount)));
		method.addChild(mountEle);

		return method;
	}

	private static OMElement getQueryPayload(String name) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://www.bank.com/",
				"query");

		OMElement method = fac.createOMElement("query", omNs);
		OMElement value = fac.createOMElement("name", omNs);
		value.addChild(fac.createOMText(value, name));
		method.addChild(value);
		return method;
	}

	private static OMElement getSpringPayload(String name) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://www.bank.com/",
				"query");

		OMElement method = fac.createOMElement("spring", omNs);
		OMElement value = fac.createOMElement("name", omNs);
		value.addChild(fac.createOMText(value, name));
		method.addChild(value);
		return method;
	}

	public static void main(String[] args) throws Exception {
		//withdraw("terry", 1200);
		//query("terry");
		spring("terry");
	}

	private static void withdraw(String name, double mount) throws Exception {
		ConfigurationContext ctx = ConfigurationContextFactory
				.createConfigurationContextFromFileSystem(
						"D:\\program\\apache-tomcat-5.5.26\\webapps\\axis2\\WEB-INF",
						"D:\\program\\apache-tomcat-5.5.26\\webapps\\axis2\\WEB-INF\\conf\\axis2.xml");

		OMElement withdrawPayload = getWithdrawPayload(name, mount);
		Options options = new Options();
		options.setTo(targetEPR);
		options.setAction("urn:withdraw");
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

		ServiceClient sender = new ServiceClient(ctx, null);
		sender.setOptions(options);

		sender.fireAndForget(withdrawPayload);
		Thread.sleep(500);
		System.err.println("Withdraw executed...");
	}

	private static void query(String name) throws Exception {
		ConfigurationContext ctx = ConfigurationContextFactory
				.createConfigurationContextFromFileSystem(
						"D:\\program\\apache-tomcat-5.5.26\\webapps\\axis2\\WEB-INF",
						"D:\\program\\apache-tomcat-5.5.26\\webapps\\axis2\\WEB-INF\\conf\\axis2.xml");

		ServiceClient sender = new ServiceClient(ctx, null);
		Options options = new Options();
		try {
			AxisCallback callback = new AxisCallback() {
				public void onFault(MessageContext context) {
					System.out.println("Fault:\t"
							+ context.getEnvelope().getBody().getFault()
									.toString());
				}

				@SuppressWarnings("unchecked")
				public void onMessage(MessageContext context) {
					OMElement result = context.getEnvelope().getBody()
							.getFirstElement();
					System.out.println(result.toString());
					Iterator<OMElement> it = result.getChildren();
					while (it.hasNext()) {
						System.out.println(it.next().getText());
					}
					finish = true;
				}

				public void onComplete() {
					System.out.println("OK");
				}

				public void onError(Exception exception) {
					System.out.println("Exception:\t" + exception.getMessage());
				}
			};
			OMElement queryPayload = getQueryPayload(name);

			options.setTo(targetEPR);
			options.setAction("urn:query");
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			sender.setOptions(options);
			sender.sendReceiveNonBlocking(queryPayload, callback);

			synchronized (callback) {
				if (!finish) {
					callback.wait(10000);
					if (!finish) {
						throw new AxisFault(
								"Server was shutdown as the async response take too long to complete");
					}
				}
			}
		} finally {
			if (sender != null) {
				sender.disengageModule("addressing");
				sender.cleanup();
			}
		}
	}

	private static void spring(String name) throws Exception {
		ConfigurationContext ctx = ConfigurationContextFactory
				.createConfigurationContextFromFileSystem(
						"D:\\program\\apache-tomcat-5.5.26\\webapps\\axis2\\WEB-INF",
						"D:\\program\\apache-tomcat-5.5.26\\webapps\\axis2\\WEB-INF\\conf\\axis2.xml");

		ServiceClient sender = new ServiceClient(ctx, null);
		Options options = new Options();
		OMElement springPayload = getSpringPayload(name);

		options.setTo(targetEPR);
		options.setAction("urn:spring");
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		sender.setOptions(options);
		OMElement response=sender.sendReceive(springPayload);
		Thread.sleep(500);
		System.out.println(response.getText());

	}
}
