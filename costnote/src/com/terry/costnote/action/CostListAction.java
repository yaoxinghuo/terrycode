package com.terry.costnote.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.googlecode.jsonplugin.annotations.JSON;
import com.terry.costnote.data.model.Cost;
import com.terry.costnote.data.service.intf.ICostService;

@Scope("prototype")
@Component("costListAction")
public class CostListAction extends GenericAction {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private int stype;
	private String sfrom;

	@JSON(serialize = false)
	public int getStype() {
		return stype;
	}

	public void setStype(int stype) {
		this.stype = stype;
	}

	@JSON(serialize = false)
	public String getSfrom() {
		return sfrom;
	}

	public void setSfrom(String sfrom) {
		this.sfrom = sfrom;
	}

	@JSON(serialize = false)
	public String getSto() {
		return sto;
	}

	public void setSto(String sto) {
		this.sto = sto;
	}

	private String sto;

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
		Date start = null;
		Date end = null;
		if (sfrom == null) {
			start = new Date(new Date().getTime() - 30l * 24l * 3600l * 1000l);
		} else
			try {
				start = sdf.parse(sfrom);
			} catch (ParseException e) {
				start = new Date(new Date().getTime() - 30l * 24l * 3600l
						* 1000l);
			}
		if (sto == null)
			end = new Date();
		else
			try {
				end = sdf.parse(sto);
			} catch (ParseException e) {
				end = new Date();
			}
		JSONArray ja = new JSONArray();
		String email = getCurrentUserEmail();
		List<Cost> costs = costService.getCostsByEmail(email, start, end,
				stype, offset, limit);
		for (Cost cost : costs) {
			JSONObject a = new JSONObject();
			a.put("id", cost.getId());
			a.put("name", cost.getName());
			a.put("type", cost.getType());
			a.put("remark", cost.getRemark());
			a.put("date", sdf.format(cost.getAdate()));
			a.put("amount", cost.getAmount());
			ja.add(a);
		}
		setRows(ja);
		setResults(costService.getCostsCountByEmail(email, start, end, stype));
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
