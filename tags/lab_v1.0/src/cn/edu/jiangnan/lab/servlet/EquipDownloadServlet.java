package cn.edu.jiangnan.lab.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

import cn.edu.jiangnan.lab.data.pojo.Equip;
import cn.edu.jiangnan.lab.data.service.intf.IEquipService;

public class EquipDownloadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2493890678634977956L;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private NumberFormat nf = new DecimalFormat("0.00");
	private IEquipService equipService;
	private String[] types = { "", "工艺大厅设备", "物化设备", "方向托管设备" };

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		equipService = (IEquipService) wac.getBean("equipService");
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("ISO-8859-1");
		// int type = Integer.parseInt(request.getParameter("type"));
		// String arg1 = new String(request.getParameter("arg1").getBytes(
		// "ISO-8859-1"), "UTF-8");
		/*
		 * 这里传过来的参数是中文，需要在server.xml中加上 <Connector port="8080"
		 * protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443"
		 * URIEncoding="utf-8"/> <Connector port="8009" protocol="AJP/1.3"
		 * redirectPort="8443" URIEncoding="utf-8"/> 如果不加需要用上面注释掉的代码，把下面相关的代码注释掉
		 */

		request.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		String arg1 = URLDecoder.decode(request.getParameter("arg1"), "UTF-8");

		String arg2 = request.getParameter("arg2");
		ArrayList<Equip> equips = equipService.getDownloadEquipsByType(type,
				arg1, arg2);
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-disposition",
				"attachment;filename=Equipments" + sdf.format(new Date())
						+ ".xls");
		if (equips == null || equips.size() == 0)
			return;
		// System.out.println("Fectch Size:" + equips.size());
		HSSFWorkbook wb = getWorkbook(equips);
		if (wb == null)
			return;
		wb.write(response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	private HSSFWorkbook getWorkbook(ArrayList<Equip> equips) {
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
		HSSFCell cell18 = row.createCell((short) 18, 1);
		HSSFCell cell19 = row.createCell((short) 19, 1);
		HSSFCell cell20 = row.createCell((short) 20, 1);
		HSSFCell cell21 = row.createCell((short) 21, 1);
		HSSFCell cell22 = row.createCell((short) 22, 1);
		HSSFCell cell23 = row.createCell((short) 23, 1);

		sheet.setColumnWidth((short) 0, (short) 4200);
		sheet.setColumnWidth((short) 1, (short) 3000);
		sheet.setColumnWidth((short) 2, (short) 4200);
		sheet.setColumnWidth((short) 3, (short) 4200);
		sheet.setColumnWidth((short) 6, (short) 4200);
		sheet.setColumnWidth((short) 7, (short) 3000);
		sheet.setColumnWidth((short) 8, (short) 3000);
		sheet.setColumnWidth((short) 10, (short) 4500);
		sheet.setColumnWidth((short) 12, (short) 4500);
		sheet.setColumnWidth((short) 13, (short) 4500);
		sheet.setColumnWidth((short) 14, (short) 4500);
		sheet.setColumnWidth((short) 18, (short) 4200);
		sheet.setColumnWidth((short) 19, (short) 4200);
		sheet.setColumnWidth((short) 20, (short) 4200);
		sheet.setColumnWidth((short) 21, (short) 4200);
		sheet.setColumnWidth((short) 22, (short) 3000);
		sheet.setColumnWidth((short) 23, (short) 3500);

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
		cell18.setCellStyle(cellStyle);
		cell19.setCellStyle(cellStyle);
		cell20.setCellStyle(cellStyle);
		cell21.setCellStyle(cellStyle);
		cell22.setCellStyle(cellStyle);
		cell23.setCellStyle(cellStyle);

		cell0.setCellValue(new HSSFRichTextString("设备名称*"));
		cell1.setCellValue(new HSSFRichTextString("编号*"));
		cell2.setCellValue(new HSSFRichTextString("型号*"));
		cell3.setCellValue(new HSSFRichTextString("性能参数"));
		cell4.setCellValue(new HSSFRichTextString("单价"));
		cell5.setCellValue(new HSSFRichTextString("国别"));
		cell6.setCellValue(new HSSFRichTextString("生产厂商"));
		cell7.setCellValue(new HSSFRichTextString("出厂日期"));
		cell8.setCellValue(new HSSFRichTextString("购置日期"));
		cell9.setCellValue(new HSSFRichTextString("存放位置"));
		cell10.setCellValue(new HSSFRichTextString("设备类别*"));
		cell11.setCellValue(new HSSFRichTextString("负责人"));
		cell12.setCellValue(new HSSFRichTextString("负责人联系方式"));
		cell13.setCellValue(new HSSFRichTextString("操作规程"));
		cell14.setCellValue(new HSSFRichTextString("收费方式"));
		cell15.setCellValue(new HSSFRichTextString("是否公用*"));
		cell16.setCellValue(new HSSFRichTextString("是否收费*"));
		cell17.setCellValue(new HSSFRichTextString("是否可用*"));
		cell18.setCellValue(new HSSFRichTextString("检查预约冲突*"));
		cell19.setCellValue(new HSSFRichTextString("禁止预约日期*"));
		cell20.setCellValue(new HSSFRichTextString("预约起始时间*"));
		cell21.setCellValue(new HSSFRichTextString("预约结束时间*"));
		cell22.setCellValue(new HSSFRichTextString("收费(元/分)"));
		cell23.setCellValue(new HSSFRichTextString("处理时间(分/样)"));

		for (int i = 0; i < equips.size(); i++) {
			Equip equip = equips.get(i);
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
			cell18 = row.createCell((short) 18, 1);
			cell19 = row.createCell((short) 19, 1);
			cell20 = row.createCell((short) 20, 1);
			cell21 = row.createCell((short) 21, 1);
			cell22 = row.createCell((short) 22, 1);
			cell23 = row.createCell((short) 23, 1);

			cell0.setCellValue(new HSSFRichTextString(equip.getName()));
			cell1.setCellValue(new HSSFRichTextString(equip.getNo()));
			cell2.setCellValue(new HSSFRichTextString(equip.getModel()));
			cell3
					.setCellValue(new HSSFRichTextString(equip
							.getSpecification()));
			cell4.setCellValue(new HSSFRichTextString(equip.getPrice()));
			cell5.setCellValue(new HSSFRichTextString(equip.getCountry()));
			cell6.setCellValue(new HSSFRichTextString(equip.getCompany()));
			cell7.setCellValue(new HSSFRichTextString(equip.getYear1()));
			cell8.setCellValue(new HSSFRichTextString(equip.getYear2()));
			cell9.setCellValue(new HSSFRichTextString(equip.getLocation()));
			cell10.setCellValue(new HSSFRichTextString(types[equip.getType()]));
			cell11.setCellValue(new HSSFRichTextString(equip.getAdmin()));
			cell12.setCellValue(new HSSFRichTextString(equip.getMobile()));
			cell13.setCellValue(new HSSFRichTextString(equip.getCaution()));
			cell14.setCellValue(new HSSFRichTextString(equip.getRemark()));
			cell15.setCellValue(new HSSFRichTextString(equip.isPub() ? "是"
					: "否"));
			cell16.setCellValue(new HSSFRichTextString(equip.isCharge() ? "是"
					: "否"));
			cell17.setCellValue(new HSSFRichTextString(equip.isStatus() ? "是"
					: "否"));
			cell18.setCellValue(new HSSFRichTextString(equip.isCheckd() ? "是"
					: "否"));
			cell19.setCellValue(new HSSFRichTextString(equip.getAppd()));
			cell20.setCellValue(new HSSFRichTextString(equip.getAppt1()));
			cell21.setCellValue(new HSSFRichTextString(equip.getAppt2()));
			cell22.setCellValue(new HSSFRichTextString(nf
					.format(equip.getFee())));
			cell23.setCellValue(new HSSFRichTextString(String.valueOf(equip
					.getSampletime())));

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
