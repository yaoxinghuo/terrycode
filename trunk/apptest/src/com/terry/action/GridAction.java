package com.terry.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component("gridAction")
public class GridAction extends ActionSupport {

	private int start;
	private int limit;

	private int results;
	private JSONArray rows;

	@JSON(serialize = false)
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JSON(serialize = false)
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

	@Override
	public String execute() {
		JSONArray ja = new JSONArray();
		System.out.println("Start:" + start + "Limit:" + limit);
		for (int i = start; i < start + limit; i++) {
			JSONObject a = new JSONObject();
			a.put("id", i);
			a.put("name", "name" + i);
			a.put("symbol", "symbol" + i);
			ja.add(a);
		}
		setRows(ja);
		setResults(401);
		return SUCCESS;
	}

	public void setRows(JSONArray rows) {
		this.rows = rows;
	}

	@JSON(name = "rows")
	public JSONArray getRows() {
		return rows;
	}

	public void setResults(int results) {
		this.results = results;
	}

	@JSON(name = "results")
	public int getResults() {
		return results;
	}
}
