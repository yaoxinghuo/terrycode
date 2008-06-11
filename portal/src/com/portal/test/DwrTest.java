package com.portal.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DwrTest {
	public String getJSON(int start,int limit) {
		System.out.println("Start:"+start+"\tLimit:"+limit);
		JSONObject jo = new JSONObject();
		jo.put("results", limit);
		JSONArray ja = new JSONArray();
		for (int i = start; i < start+limit; i++) {
			JSONObject object = new JSONObject();
			object.put("id", i);
			object.put("name", "Name Test " + i);
			if (i % 2 == 0)
				object.put("sex", "male");
			else
				object.put("sex", "female");
			ja.add(object);
		}
		jo.put("rows", ja);
		return jo.toString();
	}
}
