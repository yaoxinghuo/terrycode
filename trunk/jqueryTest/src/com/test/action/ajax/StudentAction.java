package com.test.action.ajax;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.test.data.model.Student;
import com.test.data.service.intf.IStudentService;

@Component("retrieveStudentsAction")
public class StudentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6007792478000829933L;

	@Resource(name = "studentService")
	private IStudentService studentService;

	private long rp;
	private long page;
	private String query;
	private String qtype;
	private String sortorder;
	private String sortname;
	private long total;

	private JSONArray rows;

	@JSON(name = "rows")
	public JSONArray getRows() {
		return rows;
	}

	public void setRows(JSONArray rows) {
		this.rows = rows;
	}

	@JSON(serialize = false)
	public long getRp() {
		return rp;
	}

	public void setRp(long rp) {
		this.rp = rp;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	@JSON(serialize = false)
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@JSON(serialize = false)
	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	@JSON(serialize = false)
	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	@JSON(serialize = false)
	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String execute() {
		total = studentService.getStudentTotalCount(qtype, query); // 记录总数
		List<Student> custList = studentService.findPagedAll(page, rp, qtype,
				query, sortname, sortorder);// 根据页数和每页显示数量
		if (custList != null || custList.size() > 0) {
			JSONArray rows = new JSONArray();
			for (int i = 0; i < custList.size(); i++) {
				Student student = custList.get(i);
				JSONObject stu = new JSONObject();
				stu.put("id", "ZW");
				JSONArray ja = new JSONArray();
				ja.add(student.getId());
				ja.add(student.getUsername());
				ja.add(student.getPassword());
				ja.add(student.getAge());
				ja.add(student.getAddress());
				stu.put("cell", ja);
				rows.add(stu);
			}
			setRows(rows);
		}
		return SUCCESS;
	}

}
