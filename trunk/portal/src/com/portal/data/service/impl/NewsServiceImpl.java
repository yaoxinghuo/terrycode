package com.portal.data.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.portal.data.dao.intf.INewsDao;
import com.portal.data.mapping.Mapping;
import com.portal.data.pojo.RssData;
import com.portal.data.pojo.UserRssData;
import com.portal.data.service.intf.INewsService;
import com.portal.data.util.StringUtil;

/**
 * Developer: Terry DateTime : 2007-12-11 下午04:12:28
 */

public class NewsServiceImpl implements INewsService {

	private INewsDao newsDao;

	public INewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(INewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public String getNewsByTypes(String jsonSettings) {
		JSONObject jsonObject = JSONObject.fromObject(jsonSettings);
		int type = jsonObject.getInt("type");
		String news = "{\"news\":[],\"total\":0}";

		switch (type) {
		case 1:
			news = getSystemRssNews(jsonObject);
			break;
		case 2:
			news = getUserRssNews(jsonObject);
			break;
		}
		return news;
	}

	private String getSystemRssNews(JSONObject jsonObject) {
		int type1 = 0;
		if (!jsonObject.getString("type1").equals(""))
			type1 = Integer.parseInt(jsonObject.getString("type1"));
		int type2 = 0;
		if (!jsonObject.getString("type2").equals(""))
			type2 = Integer.parseInt(jsonObject.getString("type2"));
		String ex_type3String = jsonObject.getString("ex_type3");
		int[] ex_type3;
		if (ex_type3String == null || ex_type3String.trim().equals(""))
			ex_type3 = new int[0];
		else {
			StringTokenizer st = new StringTokenizer(ex_type3String, ",");
			ex_type3 = new int[st.countTokens()];
			int counter = 0;
			while (st.hasMoreTokens()) {
				ex_type3[counter] = Integer.parseInt(st.nextToken().trim());
				counter++;
			}
		}
		int showNumber = jsonObject.getInt("showNumber");
		long totalResult = jsonObject.getInt("totalResult");
		int firstResult = jsonObject.getInt("firstResult");
		if (totalResult == -1)
			totalResult = newsDao.getCountNewsByTypes(type1, type2, ex_type3);
		JSONObject newsObject = new JSONObject();
		newsObject.put("totalResult", totalResult);
		if (totalResult == 0)
			return newsObject.toString();

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, Locale.CHINA);
		ArrayList<RssData> rssData = newsDao.getNewsByTypes(type1, type2,
				ex_type3, firstResult, showNumber);
		JSONArray rssNews = new JSONArray();
		for (int i = 0; i < rssData.size(); i++) {
			JSONObject newObject = new JSONObject();
			RssData data = rssData.get(i);
			newObject.put("title", StringUtil.filterString(data.getTitle()));
			newObject.put("from", Mapping.newsMappingType3.get(String.valueOf(data
							.getType3())));
			newObject.put("link", StringUtil.filterString(data.getLink()));
			newObject.put("desc", StringUtil
					.filterString(data.getDescription()));
			newObject.put("date", df.format(data.getTime()));
			rssNews.add(newObject);
		}

		newsObject.put("news", rssNews);
		return newsObject.toString();
	}

	private String getUserRssNews(JSONObject jsonObject) {
		int showNumber = jsonObject.getInt("showNumber");
		long totalResult = jsonObject.getInt("totalResult");
		int firstResult = jsonObject.getInt("firstResult");
		String id = jsonObject.getString("id");
		if (totalResult == -1)
			totalResult = newsDao.getCountUserRssNews(id);
		JSONObject newsObject = new JSONObject();
		newsObject.put("totalResult", totalResult);
		if (totalResult == 0)
			return newsObject.toString();

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, Locale.CHINA);
		ArrayList<UserRssData> rssData = newsDao.getUserRssNews(id,
				firstResult, showNumber);
		JSONArray rssNews = new JSONArray();
		for (int i = 0; i < rssData.size(); i++) {
			JSONObject newObject = new JSONObject();
			UserRssData data = rssData.get(i);
			newObject.put("title", StringUtil.filterString(data.getTitle()));
			newObject.put("link", StringUtil.filterString(data.getLink()));
			newObject.put("desc", StringUtil
					.filterString(data.getDescription()));
			newObject.put("date", df.format(data.getTime()));
			rssNews.add(newObject);
		}

		newsObject.put("news", rssNews);
		return newsObject.toString();
	}
}
