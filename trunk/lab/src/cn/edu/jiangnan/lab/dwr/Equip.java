package cn.edu.jiangnan.lab.dwr;

import org.directwebremoting.WebContextFactory;

import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;
import cn.edu.jiangnan.lab.data.service.intf.IEquipService;

public class Equip {
	private IEquipService equipService;
	private IAccountService accountService;
	private String purviewErrorMessage = Constants.NO_ADMIN_DO_ERROR_MESSAGE;

	public String getEquipsInfoByType(int start, int limit, int type,
			boolean checkpub) {
		return equipService.getEquipsInfoByType(start, limit, type, checkpub);
	}

	public String getEquipsInfoBySearch(int start, int limit, String keyword,
			String column, boolean checkpub) {
		return equipService.getEquipsInfoBySearch(start, limit, keyword,
				column, checkpub);
	}

	public String getEquipsInfo(int start, int limit, boolean checkpub) {
		return equipService.getEquipsInfo(start, limit, checkpub);
	}

	private String getSessionId(boolean adminId) {
		if (adminId)
			return (String) WebContextFactory.get().getSession().getAttribute(
					Constants.SESSION_ADMIN_ID);
		else
			return (String) WebContextFactory.get().getSession().getAttribute(
					Constants.SESSION_ID);
	}

	public String getEquipsInfoByAdminType(int start, int limit) {
		String adminId = getSessionId(true);
		if (adminId != null)
			return equipService.getEquipsInfoByAdminId(start, limit, adminId);
		return null;
	}

	public String saveEquip(String equip) {
		String adminId = getSessionId(true);

		if (adminId == null || !isSuperAdmin())
			return purviewErrorMessage;
		else {
			try {
				return equipService.saveEquip(adminId, equip);
			} catch (RuntimeException e) {
				return "{result:false,message:'出现错误，可能您输入的设备编号已经和其他设备重复！'}";
			}
		}

	}

	public String updateEquip(String equip) {
		String adminId = getSessionId(true);

		if (adminId == null || !isSuperAdmin())
			return purviewErrorMessage;
		else {
			try {
				return equipService.updateEquip(adminId, equip);
			} catch (org.springframework.dao.DataIntegrityViolationException e) {
				return "{result:false,message:'出现错误，可能您输入的设备编号已经和其他设备重复！'}";
			}
		}
	}

	public String updateEquipStatus(String equip) {
		String adminId = getSessionId(true);

		if (adminId == null || !isSuperAdmin())
			return purviewErrorMessage;
		else
			return equipService.updateEquipStatus(adminId, equip);
	}

	public String removeEquip(String equip_id) {
		String adminId = getSessionId(true);

		if (adminId == null || !isSuperAdmin())
			return purviewErrorMessage;
		else {
			String basepath = WebContextFactory.get().getServletContext()
					.getRealPath("/resources/equip/");
			return equipService.removeEquip(adminId, equip_id, basepath);
		}
	}

	public String removeEquipImage(String equip_id) {
		String adminId = getSessionId(true);

		if (adminId == null || !isSuperAdmin())
			return purviewErrorMessage;
		else {
			String basepath = WebContextFactory.get().getServletContext()
					.getRealPath("/resources/equip/");
			return equipService.removeEquipImage(adminId, equip_id, basepath);
		}
	}

	public String getAllAdmins() {
		return equipService.getAllAdmins();
	}

	public String batchRemoveEquip(String[] equip_ids) {
		String adminId = getSessionId(true);

		if (adminId == null || !isSuperAdmin())
			return purviewErrorMessage;
		else {
			String basepath = WebContextFactory.get().getServletContext()
					.getRealPath("/resources/equip/");
			return equipService.batchRemoveEquip(adminId, equip_ids, basepath);
		}
	}

	public String getEquipDetailInfoById(String id) {
		boolean login = (getSessionId(false) != null) ? true : false;
		return equipService.getEquipDetailInfoById(id, login);
	}

	public String getRadomEquipImages(int limit) {
		return equipService.getRadomEquipImages(limit);
	}

	public IEquipService getEquipService() {
		return equipService;
	}

	public void setEquipService(IEquipService equipService) {
		this.equipService = equipService;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	private boolean isSuperAdmin() {
		if (WebContextFactory.get().getSession().getAttribute(
				Constants.SESSION_ADMIN_ID) == null)
			return false;
		cn.edu.jiangnan.lab.data.pojo.Account account = accountService
				.getAccountById((String) WebContextFactory.get().getSession()
						.getAttribute(Constants.SESSION_ADMIN_ID));
		return (account.getAdmin() == 0 || account.getAdmin() == 3) ? true
				: false;
	}
}
