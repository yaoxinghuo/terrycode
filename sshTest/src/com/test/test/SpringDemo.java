package com.test.test;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.test.data.model.Account;
import com.test.data.service.AccountServiceIntf;

public class SpringDemo {
	public static void main(String[] args) {
		Collection<String> files = new ArrayList<String>();
		files.add("WebContent/WEB-INF/beans.xml");
		files.add("WebContent/WEB-INF/dataAccessContext-local.xml");

		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
				files.toArray(new String[0]));
		AccountServiceIntf demo = (AccountServiceIntf) ctx.getBean("demo");
		// System.out.println(demo.getUsername("8a96823b194ae61a01194b02944b0002"));

		Account account1 = new Account();
		account1.setUsername("test1");
		Account account2 = new Account();
		account2.setUsername("test1");
		demo.saveAccounts(new Account[] { account1, account2 });
	}
}
