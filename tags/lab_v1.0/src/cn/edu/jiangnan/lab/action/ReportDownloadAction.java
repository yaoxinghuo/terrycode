package cn.edu.jiangnan.lab.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import cn.edu.jiangnan.lab.data.pojo.Log;
import cn.edu.jiangnan.lab.data.service.intf.IBookService;

import com.opensymphony.xwork2.Action;

public class ReportDownloadAction implements Action {

	private String startDate;
	private String endDate;
	private int action;
	private int type;
	private IBookService bookService;
	private String fileName;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
	private String[] actions = { "未批准", "已批准", "已删除" };

	public String execute() throws Exception {
		return SUCCESS;
	}

	public InputStream getInputStream() throws Exception {
		ArrayList<Log> logs = bookService.getLogsArray(0, 0, action, sdfd
				.parse(startDate), sdfd.parse(endDate), "", "id");
		File file = File.createTempFile("report", "xls");
		file.deleteOnExit();
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		WritableSheet ws = wwb.createSheet("Sheet 1", 0);
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 8,
				WritableFont.BOLD, false);
		wf.setColour(jxl.format.Colour.RED);
		jxl.write.WritableCellFormat CwcfF = new jxl.write.WritableCellFormat(
				wf);
		ws.addCell(new Label(0, 0, "填写日期", CwcfF));
		ws.addCell(new Label(1, 0, "设备编号", CwcfF));
		ws.addCell(new Label(2, 0, "设备名称", CwcfF));
		ws.addCell(new Label(3, 0, "申请人", CwcfF));
		ws.addCell(new Label(4, 0, "负责人", CwcfF));
		ws.addCell(new Label(5, 0, "审批情况", CwcfF));
		ws.addCell(new Label(6, 0, "起始日期", CwcfF));
		ws.addCell(new Label(7, 0, "中止日期", CwcfF));
		ws.addCell(new Label(8, 0, "导师", CwcfF));
		ws.addCell(new Label(9, 0, "预约内容", CwcfF));
		ws.addCell(new Label(10, 0, "特殊说明", CwcfF));
		ws.addCell(new Label(11, 0, "负责人附言", CwcfF));
		for (int i = 0; i < logs.size(); i++) {
			Log log = logs.get(i);
			ws.addCell(new Label(0, i + 1, sdf.format(log.getInput())));
			ws.addCell(new Label(1, i + 1, log.getEquip_no()));
			ws.addCell(new Label(2, i + 1, log.getEquip_name()));
			ws.addCell(new Label(3, i + 1, log.getUser_name()));
			ws.addCell(new Label(4, i + 1, log.getAdmin_name()));
			ws.addCell(new Label(5, i + 1, actions[log.getAction()]));
			ws.addCell(new Label(6, i + 1, sdf.format(log.getStart())));
			ws.addCell(new Label(7, i + 1, sdf.format(log.getEnd())));
			ws.addCell(new Label(8, i + 1, log.getTeacher()));
			ws.addCell(new Label(9, i + 1, log.getContent()));
			ws.addCell(new Label(10, i + 1, log.getRemark()));
			ws.addCell(new Label(11, i + 1, log.getAdmin_remark()));
		}
		wwb.write();
		wwb.close();
		return new FileInputStream(file);
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public IBookService getBookService() {
		return bookService;
	}

	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFileName() {
		fileName = "Report" + type + "(" + startDate + "~" + endDate + ").xls";
		return fileName;
	}

	public void setFileName(String fileName) {
		fileName = "Report" + type + "(" + startDate + "~" + endDate + ").xls";
		this.fileName = fileName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
