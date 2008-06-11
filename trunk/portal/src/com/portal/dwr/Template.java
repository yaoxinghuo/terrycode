package com.portal.dwr;

import java.util.TreeMap;

import org.directwebremoting.WebContextFactory;

import com.portal.data.service.intf.ILinkService;
import com.portal.data.service.intf.INewsService;
import com.portal.data.service.intf.ITemplateService;

public class Template {
	private ITemplateService templateService;
	private INewsService newsService;
	private ILinkService linkService;

	public ILinkService getLinkService() {
		return linkService;
	}

	public void setLinkService(ILinkService linkService) {
		this.linkService = linkService;
	}

	public ITemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplateService templateService) {
		this.templateService = templateService;
	}

	private String getSessionUsername() {
		return (String) WebContextFactory.get().getSession().getAttribute(
				"_username");
	}

	public String getTemplate(int position) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return templateService.getTemplate(username, position);
	}

	public TreeMap<String, String> getSelectOptions(String lastValue) {
		return templateService.getSelectOptions(lastValue);
	}

	public String getCheckNewsString(String tab, String type1, String type2) {
		return templateService.getCheckNewsString(tab, type1, type2);
	}

	public String getModuleCheckNewsString(String tab, String type1,
			String type2, String ex_type3) {
		return templateService.getModuleCheckNewsString(tab, type1, type2,
				ex_type3);
	}

	public String getNewRssModuleString(int position, int col,
			String jsonSettings) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return templateService.getNewRssModuleString(username, position, col,
				jsonSettings);
	}

	public String getcNewRssModuleString(int position, int col,
			String jsonSettings) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return templateService.getcNewRssModuleString(username, position, col,
				jsonSettings);
	}

	public String getNewLinkModuleString(int position, int col,
			String jsonSettings) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return templateService.getNewLinkModuleString(username, position, col,
				jsonSettings);
	}

	public String getcNewLinkModuleString(int position, int col,
			String jsonSettings) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return templateService.getcNewLinkModuleString(username, position, col,
				jsonSettings);
	}

	public String getNews(String josnSettings) {
		return newsService.getNewsByTypes(josnSettings);
	}

	public String getLinks(String id) {
		return linkService.getLinksByRowId(id);
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public String validateRss(String rssAddress, String limit) {
		return templateService.validateRss(rssAddress, Integer.parseInt(limit));
	}

	public boolean removeLink(String id) {
		String username = getSessionUsername();
		if (username == null)
			return false;
		return templateService.removeLink(id);
	}

	public String updateLink(String id, String jsonSettings) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return templateService.updateLink(id, jsonSettings);
	}

	public String getLinkEditString(String compid, String id) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return templateService.getLinkEditString(compid, id);
	}

	public String addLink(String jsonSettings) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return templateService.addLink(jsonSettings);
	}

	public String getLinkAddString(String baseid, String row_id) {
		String username = getSessionUsername();
		if (username == null)
			return null;
		return templateService.getLinkAddString(baseid, row_id);
	}

}
