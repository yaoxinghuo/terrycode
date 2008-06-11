package com.portal.data.service.impl;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.portal.data.dao.intf.ILinkDao;
import com.portal.data.dao.intf.INewsDao;
import com.portal.data.dao.intf.IPageDao;
import com.portal.data.dao.intf.IRowDao;
import com.portal.data.dao.intf.ITabDao;
import com.portal.data.html.TipHtml;
import com.portal.data.pojo.Page;
import com.portal.data.pojo.Row;
import com.portal.data.pojo.Tab;
import com.portal.data.pojo.UserLink;
import com.portal.data.pojo.UserRssData;
import com.portal.data.service.comm.Util;
import com.portal.data.service.intf.IProfileService;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

public class ProfileServiceImpl implements IProfileService {

	private IPageDao pageDao;
	private ITabDao tabDao;
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

	public ITabDao getTabDao() {
		return tabDao;
	}

	public void setTabDao(ITabDao tabDao) {
		this.tabDao = tabDao;
	}

	@Override
	public String getProfile(String username) {
		Page page = pageDao.getPageByUsername(username);
		int defaultTab = page.getDefaultTab();
		ArrayList<Tab> tabs = tabDao.getTabsByUsername(username);
		if (page == null || tabs == null)
			return null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", page.getTitle());
		map.put("nickname", page.getNickname());
		map.put("type", page.getType());
		map.put("tabs", tabs.size());
		map.put("defaultTab", defaultTab);
		for (int i = 0; i < tabs.size(); i++) {
			Tab tab = tabs.get(i);
			HashMap<String, Object> tabmap = new HashMap<String, Object>();
			tabmap.put("title", tab.getTitle());
			tabmap.put("closable", tab.isClosable());
			tabmap.put("type", tab.getType());
			map.put("tab" + tab.getPosition(), tabmap);
		}
		return JSONObject.fromObject(map).toString();
	}

	public IPageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	public boolean changeTitle(String username, String title) {
		Page page = pageDao.getPageByUsername(username);
		if (page == null)
			return false;
		page.setTitle(title);
		return pageDao.savePage(page);
	}

	@Override
	public boolean saveTabSettings(String username, int position, String col0,
			String col1, String col2) {
		Tab tab = tabDao.getTabByUsernameAndPosition(username, position);
		tab.setCol0(getIdsFromMixer(col0));
		tab.setCol1(getIdsFromMixer(col1));
		tab.setCol2(getIdsFromMixer(col2));
		switch (tab.getType()) {
		case 1:
			try {
				tabDao.saveTab(tab);
			} catch (DataAccessException e) {
				return false;
			}
			return true;
		case 2:
			tabDao.saveTab(tab);
			if (!saveLinkOrderAndRowIds(col0, col1, col2))
				return false;
			return true;
		default:
			return false;
		}
	}

	private String getIdsFromMixer(String mixer) {
		JSONArray array = JSONArray.fromObject(mixer);
		JSONArray ids = new JSONArray();
		for (int i = 0; i < array.size(); i++) {
			JSONObject o = JSONObject.fromObject(array.get(i));
			ids.add(o.getString("id"));
		}
		return ids.toString();
	}

	private boolean saveLinkOrderAndRowIds(String col1, String col2, String col3) {
		if (!saveLinkOrderAndRowId(col1))
			return false;
		if (!saveLinkOrderAndRowId(col2))
			return false;
		if (!saveLinkOrderAndRowId(col3))
			return false;
		return true;
	}

	private boolean saveLinkOrderAndRowId(String jsonSettings) {
		if (jsonSettings.equals("[]"))
			return true;
		JSONArray array = JSONArray.fromObject(jsonSettings);
		for (int i = 0; i < array.size(); i++) {
			JSONObject o = JSONObject.fromObject(array.get(i));
			String row_id = o.getString("id");
			String[] linkIds = o.getString("order").split(",");
			for (int j = 0; j < linkIds.length; j++) {
				if (linkIds[j].equals(""))
					continue;
				UserLink ul = linkDao.getLinkById(linkIds[j]);
				if (ul != null) {
					ul.setPosition(j);
					ul.setRow_id(row_id);
					if (!linkDao.updateLink(ul))
						return false;
				}
			}

		}
		return true;
	}

	@Override
	public boolean saveModuleSettings(String username, String jsonString) {
		Row row = Util.changeRssJsonToRowObject(username, JSONObject
				.fromObject(jsonString), true);
		switch (row.getType()) {
		case 1:
		case 3:
			if (!rowDao.updateRow(row))
				return false;
			break;
		case 2:
			Row oRow = rowDao.getRowById(row.getId());
			if (!oRow.getRssAddress().equals(row.getRssAddress())) {
				updateUserRssData(row.getId(), row.getRssAddress());
			}
			oRow.setTitle(row.getTitle());
			oRow.setRssAddress(row.getRssAddress());
			oRow.setShare(row.isShare());
			oRow.setShow_time(row.isShow_time());
			oRow.setShow_tip(row.isShow_tip());
			oRow.setShowNumber(row.getShowNumber());
			if (!rowDao.updateRow(oRow))
				return false;
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public boolean updateTabProfile(String username, int position,
			String jsonSettings) {
		Tab tab = tabDao.getTabByUsernameAndPosition(username, position);
		JSONObject jsonObject = JSONObject.fromObject(jsonSettings);
		tab.setShare(jsonObject.getBoolean("share"));
		tab.setTitle(jsonObject.getString("title"));
		try {
			tabDao.saveTab(tab);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateDefaultTabPosition(String username, int position) {
		Page page = pageDao.getPageByUsername(username);
		page.setDefaultTab(position);
		return pageDao.savePage(page);
	}

	@Override
	public String getTabSettingsString(String username, int position) {
		Tab tab = tabDao.getTabByUsernameAndPosition(username, position);
		return TipHtml.getTabSettingsHtml(position, tab);
	}

	@Override
	public boolean deleteModule(String username, int position, String colId,
			String moduleId) {
		Tab tab = tabDao.getTabByUsernameAndPosition(username, position);
		String colIds = null;
		int col = Integer.parseInt(colId.substring(colId.length() - 1, colId
				.length()));
		switch (col) {
		case 0:
			colIds = tab.getCol0();
			break;
		case 1:
			colIds = tab.getCol1();
			break;
		case 2:
			colIds = tab.getCol2();
			break;
		default:
			return false;
		}
		JSONArray oIds = JSONArray.fromObject(colIds);
		for (int i = 0; i < oIds.size(); i++) {
			if (moduleId.equals((String) oIds.get(i))) {
				oIds.remove(i);
				Row row = rowDao.getRowById(moduleId);
				if (row == null)
					return false;
				switch (col) {
				case 0:
					tab.setCol0(oIds.toString());
					break;
				case 1:
					tab.setCol1(oIds.toString());
					break;
				case 2:
					tab.setCol2(oIds.toString());
					break;
				}
				tabDao.saveTab(tab);
				switch (row.getType()) {
				case 2:// 自定义RSS，删除UserRssData
					newsDao.deleteUserRssDataByRowId(row.getId());
					break;
				case 3:// 网址收藏，删除该模块下的网址
					linkDao.deleteUserLinksByRowId(row.getId());
					break;
				default:
				}
				return rowDao.deleteRow(row);
			}
		}
		return false;
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

	private void updateUserRssData(String id, String rssAddress) {
		// 如果是自定义的Rss，当场解析保存数据
		newsDao.deleteUserRssDataByRowId(id);
		ArrayList<UserRssData> userRssDatas = getUserRssData(id, rssAddress);
		if (userRssDatas != null || userRssDatas.size() != 0)
			newsDao.saveUserRssData(userRssDatas);
	}

}
