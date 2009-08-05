package com.terry.costnote.test;

import net.sf.json.JSONArray;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Aug 5, 2009 11:17:23 AM
 */
public class Test {
	public static void main(String[] args) {
		String oldSid="123545";
		JSONArray sids = new JSONArray();
		sids.add(oldSid);
		System.out.println("[\"" + oldSid + "\"]");
		System.out.println(sids.toString());
	}
}
