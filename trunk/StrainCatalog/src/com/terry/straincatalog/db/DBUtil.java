package com.terry.straincatalog.db;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import com.terry.straincatalog.model.Attachment;
import com.terry.straincatalog.model.Strain;
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

	public synchronized boolean saveStrain(Strain strain) {
		String uuid;
		do {
			uuid = UUID.randomUUID().toString();
		} while (getStrainById(uuid) != null);
		strain.setId(uuid);
		try {
			db.store(strain);
			db.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean saveAttachment(Attachment a) {
		String uuid;
		do {
			uuid = UUID.randomUUID().toString();
		} while (getAttachmentById(uuid) != null);
		a.setId(uuid);
		try {
			db.store(a);
			db.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateStrain(String id, Strain strain) {
		Strain s = getStrainById(id);
		if (s == null)
			return false;
		s.setAccessionNumber(strain.getAccessionNumber());
		s.setActivationMethod(strain.getActivationMethod());
		s.setChineseName(strain.getChineseName());
		s.setCultureMeduim(strain.getCultureMeduim());
		s.setIdentificationMethod(strain.getIdentificationMethod());
		s.setIdentificationTime(strain.getIdentificationTime());
		s.setLatinName(strain.getLatinName());
		s.setPhysicalNumber(strain.getPhysicalNumber());
		s.setPresentLocation(strain.getPresentLocation());
		s.setPreservation(strain.getPreservation());
		s.setRemark(strain.getRemark());
		s.setStrainDescribe(strain.getStrainDescribe());
		s.setStrainSources(strain.getStrainSources());
		try {
			db.store(s);
			db.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateAttachment(String id, Attachment attachment) {
		Attachment a = getAttachmentById(id);
		if (a == null)
			return false;
		a.setData(attachment.getData());
		a.setDescription(attachment.getDescription());
		a.setFileName(attachment.getFileName());
		a.setStrainId(attachment.getStrainId());
		try {
			db.store(a);
			db.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Strain getStrainById(String id) {
		Strain b = new Strain();
		b.setId(id);
		ObjectSet<Strain> ss = db.queryByExample(b);
		if (ss == null || ss.size() == 0)
			return null;
		return ss.get(0);
	}

	public Attachment getAttachmentById(String id) {
		Attachment a = new Attachment();
		a.setId(id);
		ObjectSet<Attachment> as = db.queryByExample(a);
		if (as == null || as.size() == 0)
			return null;
		return as.get(0);
	}

	public boolean deleteStrainById(String id) {
		Strain b = getStrainById(id);
		if (b == null)
			return false;
		boolean result = true;
		try {
			ObjectSet<Attachment> as = getAttachmentsByStrainId(id);
			for (Attachment a : as) {
				if (!deleteAttachment(a)) {
					result = false;
					break;
				}
			}
			if (!result)
				db.rollback();
			else {
				db.delete(b);
				db.commit();
			}
		} catch (Exception e) {
			db.rollback();
			return false;
		}
		return true;
	}

	public boolean deleteAttachmentById(String id) {
		Attachment a = getAttachmentById(id);
		if (a == null)
			return false;
		try {
			db.delete(a);
			db.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean deleteAttachment(Attachment a) {
		try {
			db.delete(a);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public ObjectSet<Strain> queryStrain(String chineseName, String latinName,
			String strainSources, String accessionNumber, String remark) {
		Query query = db.query();
		query.constrain(Strain.class);
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
		return query.execute();
	}

	@SuppressWarnings("unchecked")
	public ObjectSet<Attachment> getAttachmentsByStrainId(String id) {
		Query query = db.query();
		query.constrain(Attachment.class);
		query.descend("strainId").constrain(id).equal();
		return query.execute();
	}

	public void closeDB() {
		db.close();
		instance = null;
	}

	public static void main(String[] args) throws Exception {
		DBUtil db = DBUtil.getInstance();
		// Attachment a = new Attachment();
		// a.setId("1234");
		// a.setFileName("a.jpg");
		// a.setStrainId("111");
		// JFileChooser chooser = new JFileChooser();
		// int state = chooser.showOpenDialog(null);
		// File file = chooser.getSelectedFile();
		// byte[] b = IOUtils.toByteArray(new FileInputStream(file));
		// a.setData(b);
		// db.saveAttachment(a);

		Attachment a = db
				.getAttachmentById("99ca2999-7f83-4215-b926-0b936f2796a5");
		FileOutputStream fos = new FileOutputStream(new File("e:\\xxx.jpng"));
		fos.write(a.getData());
		fos.flush();
		fos.close();
		// db.deleteBacteriaById("a3aa7583-58c8-4aa5-a751-3f2b0a0c7f6c");
		db.closeDB();
	}
}
