package com.portal.data.service.intf;

public interface IProfileService {
	public String getProfile(String username);

	public boolean changeTitle(String username, String title);

	public boolean saveTabSettings(String username, int position, String col0,
			String col1, String col2);

	public boolean saveModuleSettings(String username, String jsonString);

	public boolean updateTabProfile(String username, int position,
			String jsonSettings);

	public boolean updateDefaultTabPosition(String username,int position);

	public String getTabSettingsString(String username, int position);

	public boolean deleteModule(String username, int position, String colId,
			String moduleId);

}
