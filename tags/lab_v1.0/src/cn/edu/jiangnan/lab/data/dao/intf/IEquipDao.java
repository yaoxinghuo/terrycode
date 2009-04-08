package cn.edu.jiangnan.lab.data.dao.intf;

import java.util.ArrayList;

import cn.edu.jiangnan.lab.data.pojo.Equip;

public interface IEquipDao {
	public Equip getEquipById(String id);

	public boolean deleteEquip(Equip equip);

	public boolean saveEquip(Equip equip);

	public ArrayList<Equip> getEquipsByType(int start, int limit, int type,
			boolean checkpub);
	
	public ArrayList<Equip> getImageEquips();

	public long getCountEquipsByType(int type, boolean checkpub);

	public ArrayList<Equip> getEquipsBySearch(int start, int limit,
			String keyword, String column, boolean checkpub);

	public long getCountEquipsBySearch(String keyword, String column,
			boolean checkpub);

	public ArrayList<Equip> getEquips(int start, int limit, boolean checkpub);

	public long getCountEquips(boolean checkpub);

}
