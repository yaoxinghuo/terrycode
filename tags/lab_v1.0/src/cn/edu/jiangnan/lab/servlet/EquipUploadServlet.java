package cn.edu.jiangnan.lab.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
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
import cn.edu.jiangnan.lab.data.pojo.Equip;
import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;
import cn.edu.jiangnan.lab.data.service.intf.IEquipService;

public class EquipUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 202386954875337495L;

	private IEquipService equipService;
	private IAccountService accountService;

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		equipService = (IEquipService) wac.getBean("equipService");
		accountService = (IAccountService) wac.getBean("accountService");
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
		String keyword = "";
		if (request.getParameter("remark") != null
				&& request.getParameter("remark").equals("true")) {
			keyword = new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.format(new Date())
					+ " "
					+ (String) request.getSession().getAttribute(
							Constants.SESSION_NAME) + " 导入";
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if (!isSuperAdmin(request)) {
			writer
					.print("{result:false,success:true,msg:'只有组别为‘管理所有设备’的系统管理员才能导入设备！'}");
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
		JSONObject result = importExcelToDB(tmpFile, keyword);
		tmpFile.delete();
		// System.out.println(tmpFile.delete());
		writer.print(result.toString());
		writer.flush();
	}

	private JSONObject importExcelToDB(File file, String keyword) {
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
			Equip equip = new Equip();
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
				case 0:// 设备名称
					if (value.equals(""))
						checkOK = false;
					equip.setName(value);
					break;
				case 1:// 编号
					if (value.equals(""))
						checkOK = false;
					equip.setNo(value);
					break;
				case 2:// 型号
					if (value.equals(""))
						checkOK = false;
					equip.setModel(value);
					break;
				case 3:// 性能参数
					equip.setSpecification(value);
					break;
				case 4:// 单价
					equip.setPrice(value);
					break;
				case 5:// 国别
					equip.setCountry(value);
					break;
				case 6:// 生产厂商
					equip.setCompany(value);
					break;
				case 7:// 出厂日期
					equip.setYear1(value);
					break;
				case 8:// 购置日期
					equip.setYear2(value);
					break;
				case 9:// 存放位置
					equip.setLocation(value);
					break;
				case 10:// 设备类别
					if (value.contains("工艺大厅设备"))
						equip.setType(1);
					else if (value.contains("物化设备"))
						equip.setType(2);
					else if (value.contains("方向托管设备"))
						equip.setType(3);
					else
						checkOK = false;
					break;
				case 11:// 负责人
					equip.setAdmin(value);
					break;
				case 12:// 负责人联系方式
					equip.setMobile(value);
					break;
				case 13:// 操作规程
					if (keyword.equals(""))
						equip.setCaution(value);
					else
						equip.setCaution(value + " [" + keyword + "]");
					break;
				case 14:// 收费方式
					equip.setRemark(value);
					break;
				case 15://是否公用
					if (value.equals("是"))
						equip.setPub(true);
					else if (value.trim().equals("否"))
						equip.setPub(false);
					else
						checkOK = false;
					break;
				case 16://是否收费
					if (value.equals("是"))
						equip.setCharge(true);
					else if (value.trim().equals("否"))
						equip.setCharge(false);
					else
						checkOK = false;
					break;
				case 17://是否可用
					if (value.equals("是"))
						equip.setStatus(true);
					else if (value.trim().equals("否"))
						equip.setStatus(false);
					else
						checkOK = false;
					break;
				case 18://检查预约冲突
					if (value.equals("是"))
						equip.setCheckd(true);
					else if (value.equals("否"))
						equip.setCheckd(false);
					else
						checkOK = false;
					break;
				case 19://禁止预约日期
					try {
						JSONArray ja = JSONArray.fromObject(value.trim());
						for (int k = 0; k < ja.size(); k++) {
							if (ja.get(k) instanceof Integer
									&& (Integer) ja.get(k) >= 0
									&& (Integer) ja.get(k) <= 6)
								// checkOK=true;
								;
							else {
								checkOK = false;
								break;
							}
						}
						if (ja.size() >= 7)
							checkOK = false;
						equip.setAppd(ja.toString());
					} catch (Exception e) {
						checkOK = false;
					}
					break;
				case 20://预约起始时间
					try {
						equip.setAppt1(sdf.format(sdf.parse(value.trim())));
					} catch (Exception e) {
						checkOK = false;
					}
					break;
				case 21://预约结束时间
					try {
						equip.setAppt2(sdf.format(sdf.parse(value.trim())));
					} catch (Exception e) {
						checkOK = false;
					}
					break;
				case 22://收费(元/分)
					try {
						equip.setFee(Float.parseFloat(value));
					} catch (Exception e) {
						checkOK = false;
					}
					break;
				case 23://处理时间(分/样)
					try {
						equip.setSampletime(Integer.parseInt(value));
					} catch (Exception e) {
						checkOK = false;
					}
					break;
				}

			}
			try {
				if (sdf.parse(equip.getAppt1()).getTime() >= sdf.parse(
						equip.getAppt2()).getTime())
					;
				else if (checkOK && equipService.saveEquip(equip))
					successCounter++;
			} catch (Exception e) {
			}
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
					failCounter).append("条）设备记录！");
		else
			sb.append("条设备记录！");
		if (!keyword.equals("")) {
			sb.append("输入关键字： ").append(keyword);
			sb.append(" 搜索‘操作规程’栏位以显示导入的设备。");
		}
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