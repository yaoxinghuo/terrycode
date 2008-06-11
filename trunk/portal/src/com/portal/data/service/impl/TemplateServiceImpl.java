package com.portal.data.service.impl;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

import org.springframework.dao.DataAccessException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.portal.data.dao.intf.ILinkDao;
import com.portal.data.dao.intf.INewsDao;
import com.portal.data.dao.intf.IRowDao;
import com.portal.data.dao.intf.IRssDao;
import com.portal.data.dao.intf.ITabDao;
import com.portal.data.html.ModuleHtml;
import com.portal.data.html.TabHtml;
import com.portal.data.html.TipHtml;
import com.portal.data.mapping.Mapping;
import com.portal.data.pojo.Row;
import com.portal.data.pojo.Tab;
import com.portal.data.pojo.UserLink;
import com.portal.data.pojo.UserRssData;
import com.portal.data.service.comm.Util;
import com.portal.data.service.intf.ITemplateService;
import com.portal.data.util.StringUtil;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

public class TemplateServiceImpl implements ITemplateService {

	private ITabDao tabDao;
	private IRssDao rssDao;
	private IRowDao rowDao;
	private INewsDao newsDao;
	private ILinkDao linkDao;

	public ILinkDao getLinkDao() {
		return linkDao;
	}

	public void setLinkDao(ILinkDao linkDao) {
		this.linkDao = linkDao;
	}

	public INewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(INewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public IRowDao getRowDao() {
		return rowDao;
	}

	public void setRowDao(IRowDao rowDao) {
		this.rowDao = rowDao;
	}

	public IRssDao getRssDao() {
		return rssDao;
	}

	public void setRssDao(IRssDao rssDao) {
		this.rssDao = rssDao;
	}

	public ITabDao getTabDao() {
		return tabDao;
	}

	public void setTabDao(ITabDao tabDao) {
		this.tabDao = tabDao;
	}

	@Override
	public String getTemplate(String username, int position) {
		return getTabContent(username, position);
	}

	private String getTabContent(String username, int position) {
		Tab tab = tabDao.getTabByUsernameAndPosition(username, position);
		HashMap<String, Row> rows = rowDao.getRowsMapByUsernameAndTab(username,
				tab.getId());
		if (tab == null)
			return null;
		return TabHtml.getTabHtml(position, tab, rows);
	}

	@Override
	public TreeMap<String, String> getSelectOptions(String lastValue) {
		if (lastValue == null || lastValue.equals(""))
			return Mapping.newsMappingAskSelect;
		else if (lastValue.equals("0"))
			return Mapping.newsMappingType1;
		else {
			TreeMap<String, String> map = Mapping.newsMappingType0
					.get(lastValue);
			if (map != null)
				return Mapping.newsMappingType0.get(lastValue);
			else
				return Mapping.newsMappingNoType;
		}
	}

	@Override
	public String getCheckNewsString(String tab, String type1, String type2) {
		int type1Int = Integer.parseInt(type1);
		int type2Int = 0;
		if (type2 != null && !type2.equals("0") && !type2.trim().endsWith("00"))
			type2Int = Integer.parseInt(type2);
		ArrayList<Object[]> list = rssDao.getAvailableType3AndCount(type1Int,
				type2Int);
		if (list.size() == 0)
			return "<font color='green'>暂无此类信息</font>";
		return TipHtml.getCheckNewsHtml(list, tab);
	}

	@Override
	public String getModuleCheckNewsString(String tab, String type1,
			String type2, String ex_type3) {
		int type1Int = Integer.parseInt(type1);
		int type2Int = 0;
		if (type2 != null && !type2.equals("0") && !type2.trim().endsWith("00"))
			type2Int = Integer.parseInt(type2);
		ArrayList<Object[]> list = rssDao.getAvailableType3AndCount(type1Int,
				type2Int);
		if (list.size() == 0)
			return "<font color='green'>暂无此类信息</font>";
		return TipHtml.getModuleCheckNewsHtml(list, tab, ex_type3);
	}

	@Override
	public String getNewRssModuleString(String username, int position, int col,
			String jsonSettings) {
		JSONObject settings = JSONObject.fromObject(jsonSettings);
		Row rowObject = newColSettings(username, position, col, settings);
		if (rowObject == null)
			return null;
		JSONObject jo = new JSONObject();
		String content = ModuleHtml.getSystemRssModuleHtml(rowObject, position);
		if (content == null)
			return null;
		jo.put("content", content);
		jo.put("row_id", rowObject.getId());
		return jo.toString();

	}

	@Override
	public String getcNewRssModuleString(String username, int position,
			int col, String jsonSettings) {
		JSONObject settings = JSONObject.fromObject(jsonSettings);
		Row rowObject = newColSettings(username, position, col, settings);
		if (rowObject == null)
			return null;
		JSONObject jo = new JSONObject();
		String content = ModuleHtml.getUserDefineRssModuleHtml(rowObject,
				position);
		if (content == null)
			return null;
		jo.put("content", content);
		jo.put("row_id", rowObject.getId());
		return jo.toString();
	}

	@Override
	public String getNewLinkModuleString(String username, int position,
			int col, String jsonSettings) {
		JSONObject settings = JSONObject.fromObject(jsonSettings);
		Row rowObject = newColSettings(username, position, col, settings);
		if (rowObject == null)
			return null;
		JSONObject jo = new JSONObject();
		String content = ModuleHtml.getLinkModuleHtml(rowObject, position);
		if (content == null)
			return null;
		jo.put("content", content);
		jo.put("row_id", rowObject.getId());
		return jo.toString();
	}

	public String getcNewLinkModuleString(String username, int position,
			int col, String jsonSettings) {
		JSONObject settings = JSONObject.fromObject(jsonSettings);
		Row rowObject = newColSettings(username, position, col, settings);
		if (rowObject == null)
			return null;
		JSONObject jo = new JSONObject();
		String content = ModuleHtml.getLinkModuleHtml(rowObject, position);
		if (content == null)
			return null;
		jo.put("content", content);
		jo.put("row_id", rowObject.getId());
		return jo.toString();
	}

	private Row newColSettings(String username, int position, int col,
			JSONObject rowSettings) {
		Tab tab = tabDao.getTabByUsernameAndPosition(username, position);
		if (tab == null)
			return null;
		switch (tab.getType()) {
		case 1:
			return newColRssSettings(tab, username, col, rowSettings);
		case 2:
			return newColLinkSettings(tab, username, col, rowSettings);
		default:
			return null;
		}
	}

	private Row newColRssSettings(Tab tab, String username, int col,
			JSONObject rowSettings) {
		String oColSettings = null;
		String colSettings = null;
		Row row = Util.changeRssJsonToRowObject(username, rowSettings, false);
		String id = rowDao.saveAndGetId(row);
		if (id == null)
			return null;
		row.setId(id);
		saveUserRssData(id, rowSettings);// 如果是自定义的Rss，当场解析保存数据
		switch (col) {
		case 0:
			oColSettings = tab.getCol0();
			colSettings = combineColOrder(oColSettings, id);
			tab.setCol0(colSettings);
			break;
		case 1:
			oColSettings = tab.getCol1();
			colSettings = combineColOrder(oColSettings, id);
			tab.setCol1(colSettings);
			break;
		case 2:
			oColSettings = tab.getCol2();
			colSettings = combineColOrder(oColSettings, id);
			tab.setCol2(colSettings);
			break;
		}
		try {
			tabDao.saveTab(tab);
		} catch (DataAccessException e) {
			return null;
		}
		return row;
	}

	private Row newColLinkSettings(Tab tab, String username, int col,
			JSONObject rowSettings) {
		String oColSettings = null;
		String colSettings = null;
		Row row = Util.changeLinkJsonToRowObject(username, rowSettings);
		String id = rowDao.saveAndGetId(row);
		if (id == null)
			return null;
		row.setId(id);
		switch (col) {
		case 0:
			oColSettings = tab.getCol0();
			colSettings = combineColOrder(oColSettings, id);
			tab.setCol0(colSettings);
			break;
		case 1:
			oColSettings = tab.getCol1();
			colSettings = combineColOrder(oColSettings, id);
			tab.setCol1(colSettings);
			break;
		case 2:
			oColSettings = tab.getCol2();
			colSettings = combineColOrder(oColSettings, id);
			tab.setCol2(colSettings);
			break;
		}
		try {
			tabDao.saveTab(tab);
		} catch (DataAccessException e) {
			return null;
		}
		return row;
	}

	private void saveUserRssData(String id, JSONObject rowSettings) {
		if (rowSettings.getInt("type") == 1)
			return;
		ArrayList<UserRssData> userRssDatas = getUserRssData(id, rowSettings
				.getString("rssAddress"));
		if (userRssDatas != null || userRssDatas.size() != 0)
			newsDao.saveUserRssData(userRssDatas);
	}

	private String combineColOrder(String oColSettings, String rowId) {
		JSONArray oColSettingsArray = JSONArray.fromObject(oColSettings);
		oColSettingsArray.add(0, rowId);
		return oColSettingsArray.toString();
	}

	@SuppressWarnings("unchecked")
	private ArrayList<UserRssData> getUserRssData(String row_id,
			String rssAddress) {
		try {
			URL url = new URL(rssAddress);
			URLConnection conn = url.openConnection();
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new InputStreamReader(conn
					.getInputStream(), "UTF-8"));
			List entries = feed.getEntries();
			ArrayList<UserRssData> userRssDatas = new ArrayList<UserRssData>();
			for (int i = 0; i < entries.size(); i++) {
				SyndEntry entry = (SyndEntry) entries.get(i);
				UserRssData userRssData = new UserRssData();
				userRssData.setRow_id(row_id);
				userRssData.setTitle(entry.getTitle());
				userRssData.setLink(entry.getLink());
				userRssData.setDescription(entry.getDescription().getValue());
				userRssData.setTime(entry.getPublishedDate());
				userRssData.setType(2);
				userRssDatas.add(userRssData);
			}
			return userRssDatas;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String validateRss(String rssAddress, int limit) {
		try {
			URL url = new URL(rssAddress);
			URLConnection conn = url.openConnection();
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new InputStreamReader(conn
					.getInputStream(), "UTF-8"));
			List entries = feed.getEntries();
			feed.getTitle();
			if (entries.size() < limit)
				limit = entries.size();
			if (limit == 0)
				return null;
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
					DateFormat.MEDIUM, Locale.CHINA);
			JSONArray rssNews = new JSONArray();
			for (int i = 0; i < limit; i++) {
				SyndEntry entry = (SyndEntry) entries.get(i);
				JSONObject newObject = new JSONObject();
				newObject.put("title", StringUtil
						.filterString(entry.getTitle()));
				newObject.put("link", entry.getLink());
				newObject.put("desc", StringUtil.filterString(entry
						.getDescription().getValue()));
				newObject.put("date", df.format(entry.getPublishedDate()));
				rssNews.add(newObject);
			}
			JSONObject newsObject = new JSONObject();
			newsObject.put("title", StringUtil.filterString(feed.getTitle()));
			newsObject.put("news", rssNews);
			return newsObject.toString();
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean removeLink(String id) {
		UserLink ul = linkDao.getLinkById(id);
		return linkDao.deleteLink(ul);
	}

	@Override
	public String updateLink(String id, String jsonSettings) {
		UserLink ul = linkDao.getLinkById(id);
		JSONObject o = JSONObject.fromObject(jsonSettings);
		ul.setDescription(StringUtil.filterHtml(o.getString("desc")));
		ul.setLink(StringUtil.filterHtml(o.getString("link")));
		ul.setTitle(StringUtil.filterHtml(o.getString("title")));
		ul.setUpdate_time(new Date());
		if (!linkDao.updateLink(ul))
			return null;
		return DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, Locale.CHINA).format(ul.getUpdate_time());
	}

	@Override
	public String addLink(String jsonSettings) {
		JSONObject o = JSONObject.fromObject(jsonSettings);
		UserLink ul = new UserLink();
		ul.setDescription(StringUtil.filterHtml(o.getString("desc")));
		ul.setLink(StringUtil.filterHtml(o.getString("link")));
		ul.setTitle(StringUtil.filterHtml(o.getString("title")));
		ul.setRow_id(o.getString("row_id"));
		ul.setPosition(o.getInt("i"));
		ul.setUpdate_time(new Date());
		JSONObject jo = new JSONObject();
		if (linkDao.saveLink(ul)) {
			jo.put("id", ul.getId());
			jo.put("time", DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
					DateFormat.MEDIUM, Locale.CHINA)
					.format(ul.getUpdate_time()));
			return jo.toString();
		} else
			return null;
	}

	@Override
	public String getLinkEditString(String compid, String id) {
		UserLink ul = linkDao.getLinkById(id);
		if (ul == null)
			return null;
		return TipHtml.getLinkEditHtml(compid, id, ul.getTitle(), ul.getLink(),
				ul.getDescription());
	}

	@Override
	public String getLinkAddString(String baseid, String row_id) {
		return TipHtml.getLinkAddHtml(baseid, row_id);
	}

}
