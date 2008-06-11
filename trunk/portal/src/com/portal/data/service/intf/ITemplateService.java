package com.portal.data.service.intf;

import java.util.TreeMap;

public interface ITemplateService {
	public String getTemplate(String username, int position);

	public TreeMap<String, String> getSelectOptions(String lastValue);

	public String getCheckNewsString(String tab, String type1, String type2);

	public String getModuleCheckNewsString(String tab, String type1,
			String type2, String ex_type3);

	public String getNewRssModuleString(String username, int position, int col,
			String jsonSettings);

	public String getcNewRssModuleString(String username, int position,
			int col, String jsonSettings);

	public String getNewLinkModuleString(String username, int position,
			int col, String jsonSettings);

	public String getcNewLinkModuleString(String username, int position,
			int col, String jsonSettings);

	public String validateRss(String rssAddress, int limit);

	public boolean removeLink(String id);

	public String updateLink(String id, String jsonSettings);

	public String addLink(String jsonSettings);

	public String getLinkEditString(String compid, String id);

	public String getLinkAddString(String baseid, String row_id);

}
