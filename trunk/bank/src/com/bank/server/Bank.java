package com.bank.server;

import java.util.HashMap;

public class Bank {
	public static HashMap<String, Double> map = new HashMap<String, Double>();

	static {
		map.put("terry", 2000.00);
		map.put("xinghuo", 500.00);
	}
}
