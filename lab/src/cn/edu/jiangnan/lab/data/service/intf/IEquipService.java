package cn.edu.jiangnan.lab.data.service.intf;

import java.util.ArrayList;

import cn.edu.jiangnan.lab.data.pojo.Equip;

public interface IEquipService {
	// checkpub是否显示状态为公用的设备
	public String getEquipsInfoByType(int start, int limit, int type,
			boolean checkpub);

	public String getEquipsInfoBySearch(int start, int limit, String keyword,
			String column, boolean checkpub);

	public String getEquipsInfoByAdminId(int start, int limit, String admin_id);

	public String getEquipsInfo(int start, int limit, boolean checkpub);

	public String getRadomEquipImages(int limit);

	public String getEquipDetailInfoById(String id, boolean login);

	public String saveEquip(String adminid, String equip);

	public boolean saveEquip(Equip equip);

	public String updateEquip(String adminid, String equip);

	public String updateEquipStatus(String adminid, String equip);

	public String removeEquip(String adminid, String equip_id, String bathpath);

	public String removeEquipImage(String adminid, String equip_id,
			String basepath);

	public String batchRemoveEquip(String adminid, String[] equip_ids, String basepath);

	public Equip getEquip(String id);

	public String getAllAdmins();

	public ArrayList<Equip> getDownloadEquipsByType(int type, String... args);

}
