package com.portal.dwr;

import org.directwebremoting.WebContextFactory;

import com.portal.data.service.intf.IProfileService;

public class Profile {
	private IProfileService profileService;

	public IProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}

	private String getSessionUsername() {
		return (String) WebContextFactory.get().getSession().getAttribute(
				"_username");
	}

	public String getProfile() {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return profileService.getProfile(username);
	}

	public boolean changeTitle(String title) {
		String username = getSessionUsername();
		if (username == null)
			return false;
		return profileService.changeTitle(username, title);
	}

	public boolean saveTabSettings(int position, String col0, String col1,
			String col2) {
		String username = getSessionUsername();
		if (username == null)
			return false;
		return profileService.saveTabSettings(username, position, col0, col1,
				col2);
	}
	
	public boolean saveLinkTabSettings(int position, String col0, String col1,
			String col2){
		System.out.println(col0);
		return true;
	}

	public boolean saveModuleSettings(int position, String jsonString) {
		String username = getSessionUsername();
		if (username == null)
			return false;
		return profileService.saveModuleSettings(username, jsonString);
	}

	public boolean deleteModule(int position, String colId, String moduleId) {
		String username = getSessionUsername();
		if (username == null)
			return false;
		return profileService.deleteModule(username, position, colId, moduleId);
	}

	public String getTabSettingsFrameString(int position) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return profileService.getTabSettingsString(username, position);
	}

	public boolean updateTabProfile(int position, String jsonSettings) {
		String username = getSessionUsername();
		if (username == null)
			return false;
		return profileService
				.updateTabProfile(username, position, jsonSettings);
	}

	public boolean updateDefaultTabPosition(int position) {
		String username = getSessionUsername();
		if (username == null)
			return false;
		return profileService.updateDefaultTabPosition(username, position);
	}

}
