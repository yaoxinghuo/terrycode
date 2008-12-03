package cn.edu.jiangnan.lab.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edu.jiangnan.lab.data.pojo.Account;
import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;

public class UserUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 870800287498059985L;

	private IAccountService accountService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		setAccountService((IAccountService) wac.getBean("accountService"));
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		upLoad(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		upLoad(request, response);
	}

	@SuppressWarnings("unchecked")
	public void upLoad(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if (!isSuperAdmin(request)) {
			writer
					.print("{result:false,success:true,msg:'只有组别为‘管理所有设备’的系统管理员才能导入用户！'}");
			writer.flush();
			return;
		}
		String name = null;
		String value = null;
		boolean fileFlag = false;
		// 文件存放目录
		// String TMP_DIR = "D:\\";
		File tmpFile = null;
		// file name
		String fName = null;

		FileOutputStream baos = null;
		BufferedOutputStream bos = null;
		Hashtable<String, ArrayList<String>> paramHt = new Hashtable<String, ArrayList<String>>();
		int BUFSIZE = 1024 * 8;
		int rtnPos = 0;
		byte[] buffs = new byte[BUFSIZE * 8];
		// 得到请求类型
		String contentType = request.getContentType();
		int index = contentType.indexOf("boundary=");
		String boundary = "--" + contentType.substring(index + 9);
		String endBoundary = boundary + "--";
		ServletInputStream sis = request.getInputStream();

		// System.out.println();
		// 循环读取文件
		while ((rtnPos = sis.readLine(buffs, 0, buffs.length)) != -1) {
			String strBuff = new String(buffs, 0, rtnPos);
			if (strBuff.startsWith(boundary)) {

				if (name != null && name.trim().length() > 0) {

					if (fileFlag) {
						bos.flush();
						baos.close();
						bos.close();
						baos = null;
						bos = null;
					} else {
						Object obj = paramHt.get(name);
						ArrayList<String> al = null;
						if (obj == null) {
							al = new ArrayList<String>();
						} else {
							ArrayList arrayList = (ArrayList) obj;
							al = arrayList;
						}
						al.add(value);
						paramHt.put(name, al);
					}
				}
				name = new String();
				value = new String();
				fileFlag = false;
				rtnPos = sis.readLine(buffs, 0, buffs.length);
				if (rtnPos != -1) {

					strBuff = new String(buffs, 0, rtnPos);
					if (strBuff.toLowerCase().startsWith(
							"content-disposition: form-data; ")) {
						int nIndex = strBuff.toLowerCase().indexOf("name=\"");
						int nLastIndex = strBuff.toLowerCase().indexOf("\"",
								nIndex + 6);
						name = strBuff.substring(nIndex + 6, nLastIndex);
					}
					int fIndex = strBuff.toLowerCase().indexOf("filename=\"");
					if (fIndex != -1) {
						fileFlag = true;
						int fLastIndex = strBuff.toLowerCase().indexOf("\"",
								fIndex + 10);
						fName = strBuff.substring(fIndex + 10, fLastIndex);
						fIndex = fName.lastIndexOf("\\");
						if (fIndex == -1) {
							fIndex = fName.lastIndexOf("/");
							if (fIndex != -1) {
								fName = fName.substring(fIndex + 1);
							}
						} else {
							fName = fName.substring(fIndex + 1);
						}
						if (fName == null || fName.trim().length() == 0) {
							fileFlag = false;
							sis.readLine(buffs, 0, buffs.length);
							sis.readLine(buffs, 0, buffs.length);
							sis.readLine(buffs, 0, buffs.length);
							continue;
						}
					}
					sis.readLine(buffs, 0, buffs.length);
					sis.readLine(buffs, 0, buffs.length);
				}
			} else if (strBuff.startsWith(endBoundary)) {
				// System.out.println("2...");
				if (name != null && name.trim().length() > 0) {
					// System.out.println("(name!=null)...");
					if (fileFlag) {
						bos.flush();
						baos.close();
						bos.close();
						baos = null;
						bos = null;
					} else {
						Object obj = paramHt.get(name);
						ArrayList<String> al = null;
						if (obj == null) {
							al = new ArrayList<String>();
						} else {
							ArrayList arrayList = (ArrayList) obj;
							al = arrayList;
						}
						al.add(value);

						paramHt.put(name, al);
					}
				}
			} else {
				if (fileFlag) {
					if (baos == null && bos == null) {
						// tmpFile = new File(TMP_DIR + fName);
						tmpFile = File.createTempFile("upload", "excel");
						baos = new FileOutputStream(tmpFile);
						bos = new BufferedOutputStream(baos);
					}
					bos.write(buffs, 0, rtnPos);
					baos.flush();
				} else {
					value = value + strBuff;
				}
			}
		}

		/*
		 * 有个问题?请求发来的时候...由于是UTF-8的编码;导致上传后的文件名是乱码
		 */
		// System.out.println("上传文件名:" + tmpFile.getName() + " 文件大小:"
		// + tmpFile.length() + " 文件路径:" + tmpFile.getPath());
		JSONObject result = importExcelToDB(tmpFile);
		tmpFile.delete();
		// System.out.println(tmpFile.delete());
		writer.print(result.toString());
		writer.flush();
	}

	private JSONObject importExcelToDB(File file) {
		JSONObject result = new JSONObject();
		HSSFWorkbook wb = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			wb = new HSSFWorkbook(fs);
		} catch (Exception e) {
			result.put("success", true);
			result.put("result", false);
			result.put("msg", "对不起，出现错误，请稍后再试！");
			return result;
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowNumber = sheet.getLastRowNum();
		int successCounter = 0;
		for (int i = 1; i <= rowNumber; i++) {
			HSSFRow row = sheet.getRow(i);
			int cellNumber = row.getLastCellNum();
			Account account = new Account();
			boolean checkOK = true;
			for (int j = 0; j < cellNumber; j++) {
				HSSFCell cell = row.getCell((short) j);
				String value = "";
				if (cell != null) {
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							value = cell.getDateCellValue().toString().trim();
						} else {
							Integer num = new Integer((int) cell
									.getNumericCellValue());
							value = String.valueOf(num).trim();

						}
						break;
					case HSSFCell.CELL_TYPE_STRING:
						value = cell.getRichStringCellValue().toString().trim();
						break;
					default:
						value = "";
					}
				}
				// System.out.print(value + "\t");
				switch (j) {
				case 0:
					if (value.equals(""))
						checkOK = false;
					account.setNo(value);
					break;
				case 1:
					if (value.equals(""))
						checkOK = false;
					account.setUsername(value);
					break;
				case 2:
					account.setTeacher(value);
					break;
				case 3:
					account.setMobile(value);
					break;
				case 4:
					if (value.equals(""))
						checkOK = false;
					account.setPassword(value);
					break;
				case 5:
					if (value.trim().equals("是"))
						account.setChanged(true);
					else if (value.trim().equals("否"))
						account.setChanged(false);
					else
						checkOK = false;
					break;
				case 6:
					if (value.trim().equals("是"))
						account.setDisabled(true);
					else if (value.trim().equals("否"))
						account.setDisabled(false);
					else
						checkOK = false;
					break;
				case 7:
					if (value.contains("学生"))
						account.setAdmin(1);
					else if (value.contains("老师"))
						account.setAdmin(4);
					else if (value.contains("设备管理员"))
						account.setAdmin(2);
					else if (value.contains("系统管理员"))
						account.setAdmin(3);
					else
						checkOK = false;
					break;
				case 8:
					if (value.contains("工艺大厅设备"))
						account.setType(1);
					else if (value.contains("物化设备"))
						account.setType(2);
					else if (value.contains("方向托管设备"))
						account.setType(3);
					else if (value.contains("所有设备"))
						account.setType(0);
					else if (value.equals(""))
						account.setType(0);
					else
						checkOK = false;
					break;
				}

			}
			if (checkOK && accountService.saveAccountEntity(account))
				successCounter++;
			// System.out.println();
		}
		if (successCounter == 0) {
			result.put("success", true);
			result.put("result", false);
			result.put("msg", "对不起，未导入任何数据，请检查源文件格式是否正确且设备编号未冲突！");
			return result;
		}
		result.put("success", true);
		result.put("result", true);
		StringBuffer sb = new StringBuffer();
		sb.append("您已成功导入").append(successCounter);
		int failCounter = rowNumber - successCounter;
		if (failCounter > 0)
			sb.append("条（共").append(rowNumber).append("条，失败").append(
					failCounter).append("条）用户记录！");
		else
			sb.append("条用户记录！");
		result.put("msg", sb.toString());
		return result;
	}

	private boolean isSuperAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constants.SESSION_ADMIN_ID) == null)
			return false;
		String adminid = (String) session
				.getAttribute(Constants.SESSION_ADMIN_ID);
		Account account = accountService.getAccountById(adminid);
		return (account.getType() == 0 && account.getAdmin() == 3) ? true
				: false;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

}
