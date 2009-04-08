package cn.edu.jiangnan.lab.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edu.jiangnan.lab.data.pojo.Book;
import cn.edu.jiangnan.lab.data.pojo.Equip;
import cn.edu.jiangnan.lab.data.pojo.Log;
import cn.edu.jiangnan.lab.data.service.intf.IBookService;

public class ReportDownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8884527862879550075L;

	private IBookService bookService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
	private NumberFormat nf = new DecimalFormat("0.00");
	private String[] actions = { "未批准", "已批准", "已删除","改费用" };
	private String[] types = { "", "工艺大厅设备", "物化设备", "方向托管设备" };

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		bookService = (IBookService) wac.getBean("bookService");
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Date startDate = null;
		Date endDate = null;
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		try {
			startDate = sdfd.parse(start);
		} catch (ParseException e) {
			startDate = new Date();
		}
		try {
			endDate = sdfd.parse(end);
		} catch (ParseException e) {
			endDate = new Date();
		}
		int action = Integer.parseInt(request.getParameter("action"));
		int type = Integer.parseInt(request.getParameter("type"));
		String keyword = URLDecoder.decode(request.getParameter("keyword"),
				"UTF-8");
		String column = request.getParameter("column");
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=Reports"
				+ type + "_" + sdfd.format(new Date()) + ".xls");
		HSSFWorkbook wb = null;
		if (type == 1)
			wb = getBookWorkbook(action, startDate, endDate, keyword, column);
		else
			wb = getLogWorkbook(action, startDate, endDate, keyword, column);
		if (wb != null) {
			wb.write(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}

	}

	private HSSFWorkbook getBookWorkbook(int action, Date startDate,
			Date endDate, String keyword, String column) {
		ArrayList<Book> books = bookService.getBooksArray(0, 0, action,
				startDate, endDate, keyword, column);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Sheet1");
		HSSFRow row = sheet.createRow((short) 0);
		HSSFCell cell0 = row.createCell((short) 0, 1);
		HSSFCell cell1 = row.createCell((short) 1, 1);
		HSSFCell cell2 = row.createCell((short) 2, 1);
		HSSFCell cell3 = row.createCell((short) 3, 1);
		HSSFCell cell4 = row.createCell((short) 4, 1);
		HSSFCell cell5 = row.createCell((short) 5, 1);
		HSSFCell cell6 = row.createCell((short) 6, 1);
		HSSFCell cell7 = row.createCell((short) 7, 1);
		HSSFCell cell8 = row.createCell((short) 8, 1);
		HSSFCell cell9 = row.createCell((short) 9, 1);
		HSSFCell cell10 = row.createCell((short) 10, 1);
		HSSFCell cell11 = row.createCell((short) 11, 1);
		HSSFCell cell12 = row.createCell((short) 12, 1);
		HSSFCell cell13 = row.createCell((short) 13, 1);
		HSSFCell cell14 = row.createCell((short) 14, 1);
		HSSFCell cell15 = row.createCell((short) 15, 1);
		HSSFCell cell16 = row.createCell((short) 16, 1);

		sheet.setColumnWidth((short) 0, (short) 4200);
		sheet.setColumnWidth((short) 1, (short) 3000);
		sheet.setColumnWidth((short) 2, (short) 3000);
		sheet.setColumnWidth((short) 4, (short) 4200);
		sheet.setColumnWidth((short) 5, (short) 4200);
		sheet.setColumnWidth((short) 8, (short) 4200);
		sheet.setColumnWidth((short) 9, (short) 4200);
		sheet.setColumnWidth((short) 10, (short) 4200);
		sheet.setColumnWidth((short) 13, (short) 4200);
		sheet.setColumnWidth((short) 14, (short) 4200);
		sheet.setColumnWidth((short) 11, (short) 4200);
		sheet.setColumnWidth((short) 15, (short) 4200);
		sheet.setColumnWidth((short) 16, (short) 4500);

		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFont(font);
		cell0.setCellStyle(cellStyle);
		cell1.setCellStyle(cellStyle);
		cell2.setCellStyle(cellStyle);
		cell3.setCellStyle(cellStyle);
		cell4.setCellStyle(cellStyle);
		cell5.setCellStyle(cellStyle);
		cell6.setCellStyle(cellStyle);
		cell7.setCellStyle(cellStyle);
		cell8.setCellStyle(cellStyle);
		cell9.setCellStyle(cellStyle);
		cell10.setCellStyle(cellStyle);
		cell11.setCellStyle(cellStyle);
		cell12.setCellStyle(cellStyle);
		cell13.setCellStyle(cellStyle);
		cell14.setCellStyle(cellStyle);
		cell15.setCellStyle(cellStyle);
		cell16.setCellStyle(cellStyle);

		cell0.setCellValue(new HSSFRichTextString("填写日期"));
		cell1.setCellValue(new HSSFRichTextString("设备编号"));
		cell2.setCellValue(new HSSFRichTextString("设备名称"));
		cell3.setCellValue(new HSSFRichTextString("是否收费"));
		cell4.setCellValue(new HSSFRichTextString("申请人"));
		cell5.setCellValue(new HSSFRichTextString("导师"));
		cell6.setCellValue(new HSSFRichTextString("设备分类"));
		cell7.setCellValue(new HSSFRichTextString("审批情况"));
		cell8.setCellValue(new HSSFRichTextString("起始日期"));
		cell9.setCellValue(new HSSFRichTextString("中止日期"));
		cell10.setCellValue(new HSSFRichTextString("实验时长(H)"));
		cell11.setCellValue(new HSSFRichTextString("样品"));
		cell12.setCellValue(new HSSFRichTextString("样品总数"));
		cell13.setCellValue(new HSSFRichTextString("实验费用(元)"));
		cell14.setCellValue(new HSSFRichTextString("应收费用(元)"));
		cell15.setCellValue(new HSSFRichTextString("预约内容"));
		cell16.setCellValue(new HSSFRichTextString("费用备注"));

		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			Equip equip = book.getEquip();
			row = sheet.createRow(i + 1);

			cell0 = row.createCell((short) 0, 1);
			cell1 = row.createCell((short) 1, 1);
			cell2 = row.createCell((short) 2, 1);
			cell3 = row.createCell((short) 3, 1);
			cell4 = row.createCell((short) 4, 1);
			cell5 = row.createCell((short) 5, 1);
			cell6 = row.createCell((short) 6, 1);
			cell7 = row.createCell((short) 7, 1);
			cell8 = row.createCell((short) 8, 1);
			cell9 = row.createCell((short) 9, 1);
			cell10 = row.createCell((short) 10, 1);
			cell11 = row.createCell((short) 11, 1);
			cell12 = row.createCell((short) 12, 1);
			cell13 = row.createCell((short) 13, 1);
			cell14 = row.createCell((short) 14, 1);
			cell15 = row.createCell((short) 15, 1);
			cell16 = row.createCell((short) 16, 1);

			cell0.setCellValue(new HSSFRichTextString(sdf.format(book
					.getInput())));
			cell1.setCellValue(new HSSFRichTextString(equip.getNo()));
			cell2.setCellValue(new HSSFRichTextString(equip.getName()));
			cell3.setCellValue(new HSSFRichTextString(equip.isCharge() ? "是"
					: "否"));
			cell4.setCellValue(new HSSFRichTextString(book.getUser_name()));
			cell5.setCellValue(new HSSFRichTextString(book.getTeacher()));
			cell6.setCellValue(new HSSFRichTextString(types[book.getType()]));
			cell7
					.setCellValue(new HSSFRichTextString(actions[book
							.getAction()]));
			cell8.setCellValue(new HSSFRichTextString(sdf.format(book
					.getStart())));
			cell9
					.setCellValue(new HSSFRichTextString(sdf.format(book
							.getEnd())));
			cell10.setCellValue(new HSSFRichTextString(nf.format((float) book
					.getExp_time()
					/ (float) 60)));
			cell11.setCellValue(new HSSFRichTextString(book.getSample_name()));
			cell12.setCellValue(new HSSFRichTextString(String.valueOf(book
					.getSample_mount())));
			cell13.setCellValue(new HSSFRichTextString(nf.format(book
					.getCompute_fee())));
			cell14.setCellValue(new HSSFRichTextString(nf.format(book
					.getActual_fee())));
			cell15.setCellValue(new HSSFRichTextString(book.getContent()));
			cell16.setCellValue(new HSSFRichTextString(book.getRemark()));
		}
		return wb;
	}

	private HSSFWorkbook getLogWorkbook(int action, Date startDate,
			Date endDate, String keyword, String column) {
		ArrayList<Log> logs = bookService.getLogsArray(0, 0, action, startDate,
				endDate, keyword, column);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Sheet1");
		HSSFRow row = sheet.createRow((short) 0);
		HSSFCell cell0 = row.createCell((short) 0, 1);
		HSSFCell cell1 = row.createCell((short) 1, 1);
		HSSFCell cell2 = row.createCell((short) 2, 1);
		HSSFCell cell3 = row.createCell((short) 3, 1);
		HSSFCell cell4 = row.createCell((short) 4, 1);
		HSSFCell cell5 = row.createCell((short) 5, 1);
		HSSFCell cell6 = row.createCell((short) 6, 1);
		HSSFCell cell7 = row.createCell((short) 7, 1);
		HSSFCell cell8 = row.createCell((short) 8, 1);
		HSSFCell cell9 = row.createCell((short) 9, 1);
		HSSFCell cell10 = row.createCell((short) 10, 1);
		HSSFCell cell11 = row.createCell((short) 11, 1);
		HSSFCell cell12 = row.createCell((short) 12, 1);
		HSSFCell cell13 = row.createCell((short) 13, 1);
		HSSFCell cell14 = row.createCell((short) 14, 1);
		HSSFCell cell15 = row.createCell((short) 15, 1);
		HSSFCell cell16 = row.createCell((short) 16, 1);
		HSSFCell cell17 = row.createCell((short) 17, 1);

		sheet.setColumnWidth((short) 0, (short) 4200);
		sheet.setColumnWidth((short) 1, (short) 3000);
		sheet.setColumnWidth((short) 2, (short) 3000);
		sheet.setColumnWidth((short) 8, (short) 4200);
		sheet.setColumnWidth((short) 9, (short) 4200);
		sheet.setColumnWidth((short) 10, (short) 4200);
		sheet.setColumnWidth((short) 13, (short) 4200);
		sheet.setColumnWidth((short) 14, (short) 4200);
		sheet.setColumnWidth((short) 11, (short) 4200);
		sheet.setColumnWidth((short) 15, (short) 4500);
		sheet.setColumnWidth((short) 16, (short) 4500);
		sheet.setColumnWidth((short) 17, (short) 4500);

		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFont(font);
		cell0.setCellStyle(cellStyle);
		cell1.setCellStyle(cellStyle);
		cell2.setCellStyle(cellStyle);
		cell3.setCellStyle(cellStyle);
		cell4.setCellStyle(cellStyle);
		cell5.setCellStyle(cellStyle);
		cell6.setCellStyle(cellStyle);
		cell7.setCellStyle(cellStyle);
		cell8.setCellStyle(cellStyle);
		cell9.setCellStyle(cellStyle);
		cell10.setCellStyle(cellStyle);
		cell11.setCellStyle(cellStyle);
		cell12.setCellStyle(cellStyle);
		cell13.setCellStyle(cellStyle);
		cell14.setCellStyle(cellStyle);
		cell15.setCellStyle(cellStyle);
		cell16.setCellStyle(cellStyle);
		cell17.setCellStyle(cellStyle);

		cell0.setCellValue(new HSSFRichTextString("审批日期"));
		cell1.setCellValue(new HSSFRichTextString("设备编号"));
		cell2.setCellValue(new HSSFRichTextString("设备名称"));
		cell3.setCellValue(new HSSFRichTextString("是否收费"));
		cell4.setCellValue(new HSSFRichTextString("申请人"));
		cell5.setCellValue(new HSSFRichTextString("导师"));
		cell6.setCellValue(new HSSFRichTextString("审批人"));
		cell7.setCellValue(new HSSFRichTextString("审批情况"));
		cell8.setCellValue(new HSSFRichTextString("起始日期"));
		cell9.setCellValue(new HSSFRichTextString("中止日期"));
		cell10.setCellValue(new HSSFRichTextString("实验时长(H)"));
		cell11.setCellValue(new HSSFRichTextString("样品"));
		cell12.setCellValue(new HSSFRichTextString("样品总数"));
		cell13.setCellValue(new HSSFRichTextString("实验费用(元)"));
		cell14.setCellValue(new HSSFRichTextString("应收费用(元)"));
		cell15.setCellValue(new HSSFRichTextString("预约内容"));
		cell16.setCellValue(new HSSFRichTextString("费用备注"));
		cell17.setCellValue(new HSSFRichTextString("负责人附言"));

		for (int i = 0; i < logs.size(); i++) {
			Log log = logs.get(i);
			row = sheet.createRow(i + 1);

			cell0 = row.createCell((short) 0, 1);
			cell1 = row.createCell((short) 1, 1);
			cell2 = row.createCell((short) 2, 1);
			cell3 = row.createCell((short) 3, 1);
			cell4 = row.createCell((short) 4, 1);
			cell5 = row.createCell((short) 5, 1);
			cell6 = row.createCell((short) 6, 1);
			cell7 = row.createCell((short) 7, 1);
			cell8 = row.createCell((short) 8, 1);
			cell9 = row.createCell((short) 9, 1);
			cell10 = row.createCell((short) 10, 1);
			cell11 = row.createCell((short) 11, 1);
			cell12 = row.createCell((short) 12, 1);
			cell13 = row.createCell((short) 13, 1);
			cell14 = row.createCell((short) 14, 1);
			cell15 = row.createCell((short) 15, 1);
			cell16 = row.createCell((short) 16, 1);
			cell17 = row.createCell((short) 17, 1);

			cell0.setCellValue(new HSSFRichTextString(sdf
					.format(log.getInput())));
			cell1.setCellValue(new HSSFRichTextString(log.getEquip_no()));
			cell2.setCellValue(new HSSFRichTextString(log.getEquip_name()));
			cell3.setCellValue(new HSSFRichTextString(log.isCharge() ? "是"
					: "否"));
			cell4.setCellValue(new HSSFRichTextString(log.getUser_name()));
			cell5.setCellValue(new HSSFRichTextString(log.getTeacher()));
			cell6.setCellValue(new HSSFRichTextString(log.getAdmin_name()));
			cell7
					.setCellValue(new HSSFRichTextString(actions[log
							.getAction()]));
			cell8.setCellValue(new HSSFRichTextString(sdf
					.format(log.getStart())));
			cell9
					.setCellValue(new HSSFRichTextString(sdf.format(log
							.getEnd())));
			cell10.setCellValue(new HSSFRichTextString(nf.format((float) log
					.getExp_time()
					/ (float) 60)));
			cell11.setCellValue(new HSSFRichTextString(log.getSample_name()));
			cell12.setCellValue(new HSSFRichTextString(String.valueOf(log
					.getSample_mount())));
			cell13.setCellValue(new HSSFRichTextString(nf.format(log
					.getCompute_fee())));
			cell14.setCellValue(new HSSFRichTextString(nf.format(log
					.getActual_fee())));
			cell15.setCellValue(new HSSFRichTextString(log.getContent()));
			cell16.setCellValue(new HSSFRichTextString(log.getRemark()));
			cell17.setCellValue(new HSSFRichTextString(log.getAdmin_remark()));
		}
		return wb;
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
