package com.terry.straincatalog.db;

import java.util.UUID;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import com.terry.straincatalog.model.Bacteria;
import com.terry.straincatalog.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-4-29 下午04:16:04
 */
public class DBUtil {
	private static final String DB_NAME = "./data/straincatalog.yap";
	private static ObjectContainer db = null;

	private DBUtil() {
		db = Db4o.openFile(DB_NAME);
	}

	private static DBUtil instance;

	public static synchronized DBUtil getInstance() {
		if (instance == null)
			instance = new DBUtil();
		return instance;
	}

	public synchronized boolean saveBacteria(String chineseName,
			String latinName, String strainSources, String accessionNumber,
			String remark) {
		String uuid;
		do {
			uuid = UUID.randomUUID().toString();
		} while (getBacteriaById(uuid) != null);
		Bacteria b = new Bacteria();
		b.setId(uuid);
		b.setChineseName(chineseName);
		b.setLatinName(latinName);
		b.setStrainSources(strainSources);
		b.setAccessionNumber(accessionNumber);
		b.setRemark(remark);
		try {
			db.store(b);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateBacteria(String id, String chineseName,
			String latinName, String strainSources, String accessionNumber,
			String remark) {
		Bacteria b = getBacteriaById(id);
		if (b == null)
			return false;
		b.setChineseName(chineseName);
		b.setLatinName(latinName);
		b.setStrainSources(strainSources);
		b.setAccessionNumber(accessionNumber);
		b.setRemark(remark);
		try {
			db.store(b);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Bacteria getBacteriaById(String id) {
		Bacteria b = new Bacteria();
		b.setId(id);
		ObjectSet<Bacteria> bs = db.queryByExample(b);
		if (bs == null || bs.size() == 0)
			return null;
		return bs.get(0);
	}

	public boolean deleteBacteriaById(String id) {
		Bacteria b = getBacteriaById(id);
		if (b == null)
			return false;
		try {
			db.delete(b);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void queryBacteria(String chineseName, String latinName,
			String strainSources, String accessionNumber, String remark) {
		Query query = db.query();
		query.constrain(Bacteria.class);
		if (!StringUtil.isNullOrEmpty(chineseName))
			query.descend("chineseName").constrain(chineseName).like()
					.contains();
		if (!StringUtil.isNullOrEmpty(chineseName))
			query.descend("latinName").constrain(latinName).like().contains();
		if (!StringUtil.isNullOrEmpty(chineseName))
			query.descend("strainSources").constrain(strainSources).like()
					.contains();
		if (!StringUtil.isNullOrEmpty(chineseName))
			query.descend("accessionNumber").constrain(accessionNumber).like()
					.contains();
		if (!StringUtil.isNullOrEmpty(chineseName))
			query.descend("remark").constrain(remark).like().contains();
	}

	public void closeDB() {
		db.close();
		instance = null;
	}

	public static void main(String[] args) {
		DBUtil db = DBUtil.getInstance();
		db.saveBacteria("chineseName", "latinName", "strainSources",
				"accessionNumber", "remark");
//		db.deleteBacteriaById("a3aa7583-58c8-4aa5-a751-3f2b0a0c7f6c");
		db.closeDB();
	}
}
