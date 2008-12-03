package cn.edu.jiangnan.lab.action;

import net.sf.json.JSONArray;
import cn.edu.jiangnan.lab.data.pojo.Equip;
import cn.edu.jiangnan.lab.data.service.intf.IEquipService;

import com.opensymphony.xwork2.ActionSupport;

public class DetailAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6911595108714182813L;

	private String id;
	private String appd;
	private Equip equip;
	private IEquipService equipService;

	private String[] days = { "日", "一", "二", "三", "四", "五", "六" };

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		if (id != null && !id.equals("")) {
			equip = equipService.getEquip(id);
			JSONArray ja = JSONArray.fromObject(equip.getAppd());
			StringBuffer sb = new StringBuffer("");
			outer: for (int i = 1; i < 8; i++) {
				int day = i == 7 ? 0 : i;
				for (int j = 0; j < ja.size(); j++) {
					if (day == ja.getInt(j))
						continue outer;
				}
				sb.append(days[day]).append(",");
			}
			 if (sb.length() != 0)
			 sb.deleteCharAt(sb.length()-1);
			appd = sb.toString();
		}
		return SUCCESS;
	}

	public void setEquipService(IEquipService equipService) {
		this.equipService = equipService;
	}

	public IEquipService getEquipService() {
		return equipService;
	}

	public void setEquip(Equip equip) {
		this.equip = equip;
	}

	public Equip getEquip() {
		return equip;
	}

	public void setAppd(String appd) {
		this.appd = appd;
	}

	public String getAppd() {
		return appd;
	}

}
