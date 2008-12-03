package cn.edu.jiangnan.lab.data.service.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.util.HtmlUtils;

import cn.edu.jiangnan.lab.data.dao.intf.IAccountDao;
import cn.edu.jiangnan.lab.data.dao.intf.IEquipDao;
import cn.edu.jiangnan.lab.data.pojo.Account;
import cn.edu.jiangnan.lab.data.pojo.Equip;
import cn.edu.jiangnan.lab.data.service.intf.IEquipService;

public class EquipServiceImpl implements IEquipService {

	private IEquipDao equipDao;
	private IAccountDao accountDao;
	private NumberFormat nf = new DecimalFormat("0.00");

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public IEquipDao getEquipDao() {
		return equipDao;
	}

	public void setEquipDao(IEquipDao equipDao) {
		this.equipDao = equipDao;
	}

	@Override
	public Equip getEquip(String id) {
		return equipDao.getEquipById(id);
	}

	@Override
	public String getAllAdmins() {
		JSONArray ja = new JSONArray();
		ArrayList<Account> accounts = accountDao.getAllAdmins();
		for (Account account : accounts) {
			JSONArray a = new JSONArray();
			a.add(account.getId());
			a.add(account.getUsername());
			ja.add(a);
		}
		return ja.toString();
	}

	@Override
	public String getEquipDetailInfoById(String id, boolean login) {
		Equip equip = equipDao.getEquipById(id);
		JSONObject jo = new JSONObject();
		jo.put("id", equip.getId());
		jo.put("no", equip.getNo());
		jo.put("name", equip.getName());
		jo.put("country", equip.getCountry());
		jo.put("company", equip.getCompany());
		jo.put("model", equip.getModel());
		jo.put("specification", equip.getSpecification());
		jo.put("year1", equip.getYear1());
		jo.put("year2", equip.getYear2());
		jo.put("price", equip.getPrice());
		jo.put("status", equip.isStatus());
		jo.put("type", equip.getType());
		jo.put("pub", equip.isPub());
		jo.put("charge", equip.isCharge());
		jo.put("caution", equip.getCaution());
		jo.put("remark", equip.getRemark());
		jo.put("location", equip.getLocation());
		jo.put("appt1", equip.getAppt1());
		jo.put("appt2", equip.getAppt2());
		jo.put("checkd", equip.isCheckd());
		jo.put("admin", equip.getAdmin());
		jo.put("appd", equip.getAppd());
		jo.put("sampletime", equip.getSampletime());
		jo.put("fee", nf.format(equip.getFee()));
		jo.put("mobile", login ? equip.getMobile() : "**尚未登录，无法查看联系方式**");
		jo.put("image", equip.getImage() == null ? "" : equip.getImage());
		return jo.toString();
	}

	@Override
	public String getEquipsInfoByType(int start, int limit, int type,
			boolean checkpub) {
		ArrayList<Equip> equips = equipDao.getEquipsByType(start, limit, type,
				checkpub);
		JSONObject jo = new JSONObject();
		jo.put("results", equipDao.getCountEquipsByType(type, checkpub));
		JSONArray ja = new JSONArray();
		for (int i = 0; i < equips.size(); i++) {
			JSONObject e = new JSONObject();
			Equip equip = equips.get(i);
			e.put("id", equip.getId());
			e.put("no", equip.getNo());
			e.put("name", HtmlUtils.htmlEscape(equip.getName()));
			e.put("company", HtmlUtils.htmlEscape(equip.getCompany()));
			e.put("model", HtmlUtils.htmlEscape(equip.getModel()));
			e.put("admin", HtmlUtils.htmlEscape(equip.getAdmin()));
			e.put("price", HtmlUtils.htmlEscape(equip.getPrice()));
			e.put("status", equip.isStatus());
			e.put("type", equip.getType());
			e.put("pub", equip.isPub());
			e.put("charge", equip.isCharge());
			e.put("counter", equip.getCounter());
			e.put("appt1", equip.getAppt1());
			e.put("appt2", equip.getAppt2());
			e.put("appd", equip.getAppd());
			ja.add(e);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String getEquipsInfoBySearch(int start, int limit, String keyword,
			String column, boolean checkpub) {
		ArrayList<Equip> equips = equipDao.getEquipsBySearch(start, limit,
				keyword.trim(), column, checkpub);
		JSONObject jo = new JSONObject();
		jo.put("results", equipDao.getCountEquipsBySearch(keyword.trim(),
				column, checkpub));
		JSONArray ja = new JSONArray();
		for (int i = 0; i < equips.size(); i++) {
			JSONObject e = new JSONObject();
			Equip equip = equips.get(i);
			e.put("id", equip.getId());
			e.put("no", equip.getNo());
			e.put("name", HtmlUtils.htmlEscape(equip.getName()));
			e.put("company", HtmlUtils.htmlEscape(equip.getCompany()));
			e.put("model", HtmlUtils.htmlEscape(equip.getModel()));
			e.put("admin", HtmlUtils.htmlEscape(equip.getAdmin()));
			e.put("price", HtmlUtils.htmlEscape(equip.getPrice()));
			e.put("status", equip.isStatus());
			e.put("type", equip.getType());
			e.put("pub", equip.isPub());
			e.put("charge", equip.isCharge());
			e.put("counter", equip.getCounter());
			e.put("appt1", equip.getAppt1());
			e.put("appt2", equip.getAppt2());
			e.put("appd", equip.getAppd());
			ja.add(e);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String getEquipsInfoByAdminId(int start, int limit, String admin_id) {
		JSONObject jo = new JSONObject();
		Account account = accountDao.getAccountById(admin_id);
		ArrayList<Equip> equips = null;
		if (account.getType() == 0) {
			equips = equipDao.getEquips(start, limit, true);
			jo.put("results", equipDao.getCountEquips(true));
		} else {
			equips = equipDao.getEquipsByType(start, limit, account.getType(),
					true);
			jo.put("results", equipDao.getCountEquipsByType(account.getType(),
					true));
		}
		JSONArray ja = new JSONArray();
		for (Equip equip : equips) {
			if (account.getType() != 0) {// A是管理工艺大厅设备的，
				// 管辖到组别为工艺大厅设备的设备并且这些设备的负责人是A或没有指定负责人
				if (!equip.getAdmin().equals("")
						&& !equip.getAdmin().contains(account.getUsername()))
					continue;
			}
			JSONObject e = new JSONObject();
			e.put("id", equip.getId());
			e.put("no", equip.getNo());
			e.put("name", HtmlUtils.htmlEscape(equip.getName()));
			e.put("company", HtmlUtils.htmlEscape(equip.getCompany()));
			e.put("model", HtmlUtils.htmlEscape(equip.getModel()));
			e.put("admin", HtmlUtils.htmlEscape(equip.getAdmin()));
			e.put("price", HtmlUtils.htmlEscape(equip.getPrice()));
			e.put("status", equip.isStatus());
			e.put("type", equip.getType());
			e.put("pub", equip.isPub());
			e.put("charge", equip.isCharge());
			e.put("counter", equip.getCounter());
			e.put("appt1", equip.getAppt1());
			e.put("appt2", equip.getAppt2());
			e.put("appd", equip.getAppd());
			ja.add(e);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String getEquipsInfo(int start, int limit, boolean checkpub) {
		ArrayList<Equip> equips = equipDao.getEquips(start, limit, checkpub);

		JSONObject jo = new JSONObject();
		jo.put("results", equipDao.getCountEquips(checkpub));
		JSONArray ja = new JSONArray();
		for (Equip equip : equips) {
			JSONObject e = new JSONObject();
			e.put("id", equip.getId());
			e.put("no", equip.getNo());
			e.put("name", HtmlUtils.htmlEscape(equip.getName()));
			e.put("company", HtmlUtils.htmlEscape(equip.getCompany()));
			e.put("model", HtmlUtils.htmlEscape(equip.getModel()));
			e.put("admin", HtmlUtils.htmlEscape(equip.getAdmin()));
			e.put("price", HtmlUtils.htmlEscape(equip.getPrice()));
			e.put("status", equip.isStatus());
			e.put("type", equip.getType());
			e.put("pub", equip.isPub());
			e.put("charge", equip.isCharge());
			e.put("counter", equip.getCounter());
			e.put("appt1", equip.getAppt1());
			e.put("appt2", equip.getAppt2());
			e.put("appd", equip.getAppd());
			ja.add(e);
		}
		jo.put("rows", ja.toString());
		return jo.toString();
	}

	@Override
	public String saveEquip(String adminid, String equip) {
		Account account = accountDao.getAccountById(adminid);
		JSONObject message = new JSONObject();
		JSONObject jo = JSONObject.fromObject(equip);
		if (account.getType() != 0 && jo.getInt("type") != account.getType()) {
			message.put("result", false);
			message.put("message", "出现错误，您没有权限新建该组别的设备！");
			return message.toString();
		}
		if (account.getType() != 0
				&& (!account.getUsername().equals(jo.getString("admin")) || jo
						.getString("admin").equals(""))) {
			message.put("result", false);
			message.put("message", "出现错误，您没有权限指定其他设备负责人或不指定负责人！");
			return message.toString();
		}
		Equip e = new Equip();
		e.setCaution(jo.getString("caution"));
		e.setCompany(jo.getString("company"));
		e.setSpecification(jo.getString("specification"));
		e.setPrice(jo.getString("price"));
		e.setCountry(jo.getString("country"));
		e.setYear1(jo.getString("year1"));
		e.setYear2(jo.getString("year2"));
		e.setLocation(jo.getString("location"));
		e.setAdmin(jo.getString("admin"));
		e.setMobile(jo.getString("mobile"));
		e.setModel(jo.getString("model"));
		e.setName(jo.getString("name"));
		e.setNo(jo.getString("no"));
		e.setType(jo.getInt("type"));
		e.setRemark(jo.getString("remark"));
		e.setPub(jo.getBoolean("pub"));
		e.setCharge(jo.getBoolean("charge"));
		e.setStatus(jo.getBoolean("status"));
		e.setCheckd(jo.getBoolean("checkd"));
		e.setSampletime(jo.getInt("sampletime"));
		e.setFee((float) jo.getDouble("fee"));
		e.setAppd(jo.getString("appd"));
		e.setAppt1(jo.getString("appt1"));
		e.setAppt2(jo.getString("appt2"));
		if (equipDao.saveEquip(e)) {
			message.put("result", true);
			message.put("message", "您已成功保存设备记录！");
		} else {
			message.put("result", false);
			message.put("message", "对不起，出现错误，请确认设备编号唯一且您是管理员并重新登录或稍后再试！");
		}
		return message.toString();
	}

	@Override
	public boolean saveEquip(Equip equip) {
		try {
			return equipDao.saveEquip(equip);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String updateEquip(String adminid, String equip) {
		Account account = accountDao.getAccountById(adminid);
		JSONObject message = new JSONObject();
		JSONObject jo = JSONObject.fromObject(equip);
		Equip e = equipDao.getEquipById(jo.getString("id"));
		if (!checkEquipCanOperate(account.getType(), e.getType(), account
				.getUsername(), e.getAdmin())) {
			message.put("result", false);
			message.put("message", "出现错误，您没有权限更改该设备，请确认您是所有设备或该设备负责人！");
			return message.toString();
		}
		if (account.getType() != 0
				&& (jo.getInt("type") != e.getType() || !jo.getString("admin")
						.equals(e.getAdmin()))) {
			message.put("result", false);
			message.put("message", "出现错误，您没有权限更改该设备的组别或负责人！");
			return message.toString();
		}
		e.setCaution(jo.getString("caution"));
		e.setCompany(jo.getString("company"));
		e.setSpecification(jo.getString("specification"));
		e.setPrice(jo.getString("price"));
		e.setCountry(jo.getString("country"));
		e.setYear1(jo.getString("year1"));
		e.setYear2(jo.getString("year2"));
		e.setLocation(jo.getString("location"));
		e.setAdmin(jo.getString("admin"));
		e.setMobile(jo.getString("mobile"));
		e.setModel(jo.getString("model"));
		e.setName(jo.getString("name"));
		e.setNo(jo.getString("no"));
		e.setType(jo.getInt("type"));
		e.setRemark(jo.getString("remark"));
		e.setPub(jo.getBoolean("pub"));
		e.setCharge(jo.getBoolean("charge"));
		e.setStatus(jo.getBoolean("status"));
		e.setCheckd(jo.getBoolean("checkd"));
		e.setSampletime(jo.getInt("sampletime"));
		e.setFee((float) jo.getDouble("fee"));
		e.setAppd(jo.getString("appd"));
		e.setAppt1(jo.getString("appt1"));
		e.setAppt2(jo.getString("appt2"));
		if (equipDao.saveEquip(e)) {
			message.put("result", true);
			message.put("message", "您已成功保存设备记录！");
		} else {
			message.put("result", false);
			message.put("message", "对不起，出现错误，请确认设备编号唯一且您是管理员并重新登录或稍后再试！");
		}
		return message.toString();
	}

	@Override
	public String updateEquipStatus(String adminid, String equip) {
		Account account = accountDao.getAccountById(adminid);
		JSONObject message = new JSONObject();
		JSONObject jo = JSONObject.fromObject(equip);
		Equip e = equipDao.getEquipById(jo.getString("id"));
		if (!checkEquipCanOperate(account.getType(), e.getType(), account
				.getUsername(), e.getAdmin())) {
			message.put("result", false);
			message.put("message", "出现错误，您没有权限更改该设备，请确认您是所有设备或该设备负责人！");
			return message.toString();
		}
		e.setStatus(jo.getBoolean("status"));
		e.setAppd(jo.getString("appd"));
		e.setAppt1(jo.getString("appt1"));
		e.setAppt2(jo.getString("appt2"));
		e.setCheckd(jo.getBoolean("checkd"));
		e.setCharge(jo.getBoolean("charge"));
		e.setFee((float) jo.getDouble("fee"));
		e.setSampletime(jo.getInt("sampletime"));
		if (equipDao.saveEquip(e)) {
			message.put("result", true);
			message.put("message", "您已成功保存设备记录！");
		} else {
			message.put("result", false);
			message.put("message", "对不起，出现错误，请确认您是管理员并重新登录或稍后再试！");
		}
		return message.toString();
	}

	@Override
	public String removeEquip(String adminid, String equip_id, String basepath) {
		Account account = accountDao.getAccountById(adminid);
		JSONObject message = new JSONObject();
		Equip equip = equipDao.getEquipById(equip_id);
		if (!checkEquipCanOperate(account.getType(), equip.getType(), account
				.getUsername(), equip.getAdmin())) {
			message.put("result", false);
			message.put("message", "出现错误，您没有权限删除该设备，请确认您是所有设备或该设备负责人！");
			return message.toString();
		}
		if (equipDao.deleteEquip(equip)) {
			new File(basepath + File.separator + equip.getImage()).delete();
			new File(basepath + File.separator + "s_" + equip.getImage())
					.delete();
			message.put("result", true);
			message.put("message", "您已成功删除设备记录！");
		} else {
			message.put("result", false);
			message.put("message", "对不起，出现错误，请确认您是系统管理员并重新登录或稍后再试！");
		}
		return message.toString();
	}

	@Override
	public String batchRemoveEquip(String adminid, String[] equip_ids,
			String basepath) {
		Account account = accountDao.getAccountById(adminid);
		JSONObject jo = new JSONObject();
		int pass = 0;
		for (int i = 0; i < equip_ids.length; i++) {
			Equip equip = equipDao.getEquipById(equip_ids[i]);
			if (checkEquipCanOperate(account.getType(), equip.getType(),
					account.getUsername(), equip.getAdmin())
					&& equipDao.deleteEquip(equip)) {
				new File(basepath + File.separator + equip.getImage()).delete();
				new File(basepath + File.separator + "s_" + equip.getImage())
						.delete();
				pass++;
			}

		}
		if (pass == equip_ids.length) {
			jo.put("result", true);
			jo.put("message", "您已经成功批量删除 " + pass + " 条设备记录！");
		} else if (pass == 0) {
			jo.put("result", false);
			jo.put("message", "出现错误，您未批量删除设备记录！");
		} else {
			jo.put("result", true);
			jo.put("message", "您批量删除 " + pass + " 个设备记录，有"
					+ (equip_ids.length - pass) + " 个记录删除时出现错误！");
		}
		return jo.toString();
	}

	@Override
	public ArrayList<Equip> getDownloadEquipsByType(int type, String... args) {
		try {
			switch (type) {
			case 1:
				return equipDao.getEquips(0, 0, false);// 导出全部
			case 2:
				return equipDao.getEquipsByType(0, 0,
						Integer.parseInt(args[1]), false);// 分类导出
			case 3:
				return equipDao.getEquipsBySearch(0, 0, args[0],// 搜索导出
						args[1], false);
			default:
				return null;
			}
		} catch (RuntimeException e) {
			return null;
		}
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

	@Override
	public String removeEquipImage(String adminid, String equip_id,
			String basepath) {
		Account account = accountDao.getAccountById(adminid);
		JSONObject message = new JSONObject();
		Equip equip = equipDao.getEquipById(equip_id);
		if (!checkEquipCanOperate(account.getType(), equip.getType(), account
				.getUsername(), equip.getAdmin())) {
			message.put("result", false);
			message.put("message", "出现错误，您没有权限删除该设备图片，请确认您是所有设备或该设备负责人！");
			return message.toString();
		}
		if (new File(basepath + File.separator + equip.getImage()).delete()
				| new File(basepath + File.separator + "s_" + equip.getImage())
						.delete()) {
			equip.setImage(null);
			if (equipDao.saveEquip(equip)) {
				message.put("result", true);
				message.put("message", "您已成功删除设备图片！");
			} else {
				message.put("result", false);
				message.put("message", "对不起，出现错误，请确认您是系统管理员并重新登录或稍后再试！");
			}
		} else {
			message.put("result", false);
			message.put("message", "对不起， 程序出现错误，请您稍后再试！");
		}

		return message.toString();
	}

	@Override
	public String getRadomEquipImages(int limit) {
		List<Equip> equips = equipDao.getImageEquips();
		JSONArray ja = new JSONArray();
		if (limit >= equips.size())
			for (Equip equip : equips) {
				ja.add(equip.getImage());
			}
		else {
			Random r = new Random();
			ArrayList<Integer> al = new ArrayList<Integer>();
			for (int i = 0; i < limit; i++) {
				if (i >= equips.size())
					break;
				int index = r.nextInt(equips.size());
				if (al.contains(index)) {
					i--;
					continue;
				}
				al.add(index);
				ja.add(equips.get(index).getImage());
			}
		}
		return ja.toString();
	}
}
