package com.terry.costnote.action;

import java.text.SimpleDateFormat;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.terry.costnote.data.model.Cost;
import com.terry.costnote.data.service.intf.ICostService;

@Scope("prototype")
@Component("costListAction")
public class CostListAction extends ActionSupport {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private int limit;
	private int offset;

	private long results;
	private JSONArray rows;

	@Autowired
	private ICostService costService;

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
	public String execute() throws JSONException {
		JSONArray ja = new JSONArray();
		List<Cost> costs = costService.getCostsByEmail("itcontent@gmail.com",
				offset, limit);
		for (Cost cost : costs) {
			JSONObject a = new JSONObject();
			a.put("id", cost.getId());
			a.put("name", cost.getName());
			a.put("type", cost.isType());
			a.put("remark", cost.getRemark());
			a.put("date", sdf.format(cost.getAdate()));
			a.put("amount", cost.getAmount());
			ja.add(a);
		}
		setRows(ja);
		setResults(costService.getCostsCountByEmail("itcontent@gmail.com"));
		return SUCCESS;
	}

	public void setRows(JSONArray rows) {
		this.rows = rows;
	}

	@JSON(name = "rows")
	public JSONArray getRows() {
		return rows;
	}

	public void setResults(long results) {
		this.results = results;
	}

	@JSON(name = "results")
	public long getResults() {
		return results;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@JSON(serialize = false)
	public int getOffset() {
		return offset;
	}
}
