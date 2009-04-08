package cn.edu.jiangnan.lab.servlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edu.jiangnan.lab.data.pojo.Account;
import cn.edu.jiangnan.lab.data.pojo.Equip;
import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;
import cn.edu.jiangnan.lab.data.service.intf.IEquipService;

public class ImageUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 202386954875337495L;

	private IEquipService equipService;
	private IAccountService accountService;

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
		String equipid = request.getParameter("equipid");
		if (equipid == null)
			return;
		Equip equip = equipService.getEquip(equipid);
		if (equip == null)
			return;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if (!canupload(request, equip)) {
			writer
					.print("{result:false,success:true,msg:'对不起，您没有权限修改该设备的图片！'}");
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
			} else {
				if (fileFlag) {
					if (baos == null && bos == null) {
						// tmpFile = new File(TMP_DIR + fName);
						tmpFile = File.createTempFile("upload", "image");
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

		JSONObject result = new JSONObject();

		BufferedImage src = ImageIO.read(tmpFile); // 读入文件
		int width = src.getWidth(); // 得到源图宽
		int height = src.getHeight(); // 得到源图长

		if (width < 90 || height < 50) {
			result.put("success", true);
			result.put("result", false);
			result.put("msg", "你上传的图片文件太小，请上传大于90×50的图片文件！");
		} else {

			double scale = 1;
			if (width > 600 || height > 450) {
				scale = (double) width / (double) 600 > (double) height
						/ (double) 450 ? (double) width / (double) 600
						: (double) height / (double) 450;
				width = (int) (width / scale);
				height = (int) (height / scale);
			}

			String basepath = this.getServletContext().getRealPath(
					"/resources/equip/");
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(basepath + File.separator
					+ equipid + ".jpg"));

			image = src.getScaledInstance(90, 50, Image.SCALE_DEFAULT);
			tag = new BufferedImage(90, 50, BufferedImage.TYPE_INT_RGB);
			g = tag.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(basepath + File.separator
					+ "s_" + equipid + ".jpg"));
			equip.setImage(equipid + ".jpg");
			result.put("success", true);
			if (equipService.saveEquip(equip)) {
				result.put("result", true);
				result.put("name", equip.getName());
				result.put("msg", "您已成功上传设备图片！");
			} else {
				result.put("result", false);
				result.put("msg", "对不起，出现错误，请稍候再试！");
			}

		}
		tmpFile.delete();
		writer.print(result.toString());
		writer.flush();
	}

	private boolean canupload(HttpServletRequest request, Equip equip) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constants.SESSION_ADMIN_ID) == null)
			return false;
		String adminid = (String) session
				.getAttribute(Constants.SESSION_ADMIN_ID);
		Account account = accountService.getAccountById(adminid);
		if (account.getAdmin() != 0 && account.getAdmin() != 3)
			return false;
		return checkEquipCanOperate(account.getType(), equip.getType(), account
				.getUsername(), equip.getAdmin());
	}

	private boolean checkEquipCanOperate(int accountType, int equipType,
			String accountAdmin, String equipAdmin) {
		if (accountType == 0)
			return true;
		if (accountType == equipType) {
			if (equipAdmin.equals("") || equipAdmin.contains(accountAdmin))
				return true;
		}
		return false;

	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

}