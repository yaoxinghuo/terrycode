package com.terry.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component("gwt-testAction")
public class TestAction extends ActionSupport {

	private int start;
	private int limit;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1859767044000357740L;

	public String test(String name) {
		return "Struts2:Hello~" + name;
	}

	public String grid(int start, int limit) {
		JSONObject jo = new JSONObject();
		jo.put("results", 401);
		JSONArray ja = new JSONArray();
		for (int i = start; i < start + limit; i++) {
			JSONObject a = new JSONObject();
			a.put("id", i);
			a.put("name", "name" + i);
			a.put("symbol", "symbol" + i);
			ja.add(a);
		}
		jo.put("rows", ja);
		return jo.toString();
	}
}
