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
import com.terry.costnote.data.model.Schedule;
import com.terry.costnote.data.service.intf.ICostService;

@Scope("prototype")
@Component("scheduleListAction")
public class ScheduletListAction extends GenericAction {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private String sfrom;

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
		List<Schedule> schedules = costService.getSchedulesByEmail(email,
				start, end, offset, limit);
		for (Schedule schedule : schedules) {
			JSONObject a = new JSONObject();
			a.put("id", schedule.getId());
			a.put("type", schedule.isType());
			a.put("sid", schedule.getSid());
			a.put("message", schedule.getMessage());
			a.put("date", sdf.format(schedule.getAdate()));
			ja.add(a);
		}
		setRows(ja);
		setResults(costService.getSchedulesCountByEmail(email, start, end));
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
