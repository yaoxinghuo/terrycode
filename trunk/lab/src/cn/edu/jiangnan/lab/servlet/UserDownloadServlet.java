package cn.edu.jiangnan.lab.servlet;

import java.io.IOException;
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

import cn.edu.jiangnan.lab.data.pojo.Account;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;

public class UserDownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6262449036299094155L;

	private IAccountService accountService;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private String[] types = { "所有设备", "工艺大厅设备", "物化设备", "方向托管设备" };

	private String[] admins = { "", "学生", "设备管理员", "系统管理员", "老师" };

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		accountService = (IAccountService) wac.getBean("accountService");
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		ArrayList<Account> accounts = accountService.getAllAccoutsByType(type);
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-disposition",
				"attachment;filename=Accounts" + sdf.format(new Date())
						+ ".xls");
		if (accounts == null || accounts.size() == 0)
			return;
		HSSFWorkbook wb = getWorkbook(accounts);
		if (wb == null)
			return;
		wb.write(response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	private HSSFWorkbook getWorkbook(ArrayList<Account> accounts) {
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

		sheet.setColumnWidth((short) 0, (short) 4200);
		sheet.setColumnWidth((short) 1, (short) 4200);
		sheet.setColumnWidth((short) 2, (short) 4200);
		sheet.setColumnWidth((short) 3, (short) 6000);
		sheet.setColumnWidth((short) 4, (short) 10000);
		sheet.setColumnWidth((short) 7, (short) 3000);
		sheet.setColumnWidth((short) 8, (short) 3000);

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

		cell0.setCellValue(new HSSFRichTextString("登录号码*"));
		cell1.setCellValue(new HSSFRichTextString("姓名*"));
		cell2.setCellValue(new HSSFRichTextString("导师"));
		cell3.setCellValue(new HSSFRichTextString("联系方式"));
		cell4.setCellValue(new HSSFRichTextString("密码(已加密)*"));
		cell5.setCellValue(new HSSFRichTextString("密码已改*"));
		cell6.setCellValue(new HSSFRichTextString("禁用预约*"));
		cell7.setCellValue(new HSSFRichTextString("账户类别*"));
		cell8.setCellValue(new HSSFRichTextString("所属组别*"));

		for (int i = 0; i < accounts.size(); i++) {
			Account account = accounts.get(i);
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

			cell0.setCellValue(new HSSFRichTextString(account.getNo()));
			cell1.setCellValue(new HSSFRichTextString(account.getUsername()));
			cell2.setCellValue(new HSSFRichTextString(account.getTeacher()));
			cell3.setCellValue(new HSSFRichTextString(account.getMobile()));
			cell4.setCellValue(new HSSFRichTextString(account.getAdmin() == 1
					|| account.getAdmin() == 4 ? account.getPassword()
					: "******"));
			cell5.setCellValue(new HSSFRichTextString(account.isChanged() ? "是"
					: "否"));
			cell6.setCellValue(new HSSFRichTextString(
					account.isDisabled() ? "是" : "否"));
			cell7.setCellValue(new HSSFRichTextString(
					admins[account.getAdmin()]));
			if (account.getAdmin() == 1 || account.getAdmin() == 4)
				cell8.setCellValue(new HSSFRichTextString(""));
			else
				cell8.setCellValue(new HSSFRichTextString(types[account
						.getType()]));

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

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

}
