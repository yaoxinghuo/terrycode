package com.microblog.logic;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class Test {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		// 使用RPC方式调用WebService
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		// 指定调用WebService的URL
		EndpointReference targetEPR = new EndpointReference(
				"http://imfarm.17fabu.net/services/Member");
		options.setTo(targetEPR);
		// 指定getGreeting方法的参数值
		Object[] opAddEntryArgs = new Object[] { "17fabu", "passcode",
				"nnii_001@hotmail.com" };
		// 指定getGreeting方法返回值的数据类型的Class对象
		Class[] classes = new Class[] { String[].class };
		// 指定要调用的getGreeting方法及WSDL文件的命名空间
		QName opAddEntry = new QName("http://111.com", "friendList");
		// 调用getGreeting方法并输出该方法的返回值
		String[] m = (String[]) serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes)[0];
		for (String s : m) {
			System.out.println(s);
		}

	}
}
