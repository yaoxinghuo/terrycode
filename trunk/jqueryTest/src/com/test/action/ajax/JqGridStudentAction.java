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

@Component("jqGridRetrieveStudentsAction")
public class JqGridStudentAction extends ActionSupport {

	/**
	 * 示例返回的JSON
	 * {
	 * "page":"1",
	 * "records":5,
	 * "rows": [ 
     * {"cell":["Blue","This is blue"],"id":1}, 
     * {"cell":["Green","This is green"],"id":2}, 
     * {"cell":["Re","This is red"],"id":3}, 
     * {"cell":["Black","This is Black"],"id":4}, 
     * {"cell":["Purple","This is purple"],"id":5} 
     * ],
     * "total":"1"} 
     * 
	 */
	private static final long serialVersionUID = 4295679237847764272L;

	@Resource(name = "studentService")
	private IStudentService studentService;

	private long page;//第几页，传过来的参数
	private long rows;//每页显示多少条记录，传过来的参数
	private String sidx;//根据哪一行排序，传过来的参数
	private String sord;//排序，desc 还是asc，传过来的参数

	private long total;//总页数
	private JSONArray arrayRows;
	private long records;//一共多少条记录

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	@SuppressWarnings("null")
	@Override
	public String execute() {
		records = studentService.getStudentTotalCount(sidx, ""); // 记录总数
		List<Student> custList = studentService.findPagedAll(page, rows, sidx,
				"", sidx, sord);// 根据页数和每页显示数量
		total = (long) Math.ceil((float)records / (float)rows);
		if (custList != null || custList.size() > 0) {
			JSONArray rows = new JSONArray();
			for (int i = 0; i < custList.size(); i++) {
				Student student = custList.get(i);
				JSONObject stu = new JSONObject();
				stu.put("id", student.getId());
				JSONArray ja = new JSONArray();
				ja.add(student.getId());
				ja.add(student.getUsername());
				ja.add(student.getPassword());
				ja.add(student.getAge());
				ja.add(student.getAddress());
				stu.put("cell", ja);
				rows.add(stu);
			}
			setArrayRows(rows);
		}
		return SUCCESS;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	@JSON(serialize = false)
	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	@JSON(serialize = false)
	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	@JSON(serialize = false)
	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@JSON(name = "rows")
	public JSONArray getArrayRows() {
		return arrayRows;
	}

	public void setArrayRows(JSONArray arrayRows) {
		this.arrayRows = arrayRows;
	}

}
