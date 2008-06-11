package com.bank.server;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

public class BankService {
	private String inject;
	
	public String getInject() {
		return inject;
	}

	public void setInject(String inject) {
		this.inject = inject;
	}

	public void withdraw(OMElement in) {
		in.build();
		in.detach();
		OMElement nameEle = in.getFirstElement();
		String name = nameEle.getText();
		OMElement mountEle = (OMElement) nameEle.getNextOMSibling();
		Double mount = Double.parseDouble(mountEle.getText());

		String response = null;

		if (Bank.map.get(name) == null)
			response = "Sorry, Account does not exist!";
		else {
			Bank.map.put(name, Bank.map.get(name) + mount);
			if (mount.doubleValue() > 0)
				response = name + "\tWithdraw Success, Balance is:\t"
						+ Bank.map.get(name).toString();
			else if (mount.doubleValue() < 0)
				response = name + "\tDeposit Success, Balance is:\t"
						+ Bank.map.get(name).toString();
			else
				response = name + "\tNo Action, Balance is:\t"
						+ Bank.map.get(name).toString();
		}
		System.out.println(BankService.class.toString()+" withdraw() called...");
		System.out.println("\t"+response);
	}

	public OMElement query(OMElement in) {
		in.build();
		in.detach();

		OMElement nameEle = in.getFirstElement();
		String name = nameEle.getText();

		String response = null;

		if (Bank.map.get(name) == null)
			response = "Sorry, Account does not exist!";
		else {
			response = name + "'s Account Balance is:\t" + Bank.map.get(name).toString();
		}

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://www.bank.com/",
				"query");
		OMElement method = fac.createOMElement("queryResponse", omNs);
		OMElement value = fac.createOMElement("balance", omNs);
		value.addChild(fac.createOMText(value, response));
		method.addChild(value);
		System.out.println(BankService.class.toString()+" query() called...");
		System.out.println("\t"+response);
		return method;
	}
	
	public OMElement spring(OMElement in) {
		in.build();
		in.detach();

		OMElement nameEle = in.getFirstElement();
		String name = nameEle.getText();

		String response = "name:"+name+"\tSpring inject string:"+inject;

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://www.bank.com/",
				"spring");
		OMElement method = fac.createOMElement("springResponse", omNs);
		method.setText(response);
		
		System.out.println(BankService.class.toString()+" spring() called...");
		System.out.println("\t"+response);
		return method;
	}
}
