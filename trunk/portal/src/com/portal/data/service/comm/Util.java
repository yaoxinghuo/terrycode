package com.portal.data.service.comm;

import net.sf.json.JSONObject;

import com.portal.data.pojo.Row;

/**
 * Developer: Terry DateTime : 2007-12-17 下午02:05:36
 */

public class Util {
	public static Row changeRssJsonToRowObject(String username,
			JSONObject json, boolean hasId) {
		int type = json.getInt("type");// 1:本站Rss 2:自定义Rss 3:网址收藏
		Row row = new Row();
		row.setUsername(username);
		row.setType(type);
		row.setTitle(json.getString("title"));
		row.setShow_tip(json.getBoolean("show_tip"));
		if (hasId) {
			row.setId(json.getString("id"));
		}
		switch (type) {
		case 1:
			row.setFirstResult(0);// 分页用
			row.setTotalResult(-1);// 分页用
			row.setShowNumber(json.getInt("showNumber"));
			row.setType1(json.getString("type1"));
			row.setType2(json.getString("type2"));
			row.setEx_type3(json.getString("ex_type3"));
			row.setShow_from(json.getBoolean("show_from"));
			row.setShow_time(json.getBoolean("show_time"));
			break;
		case 2:
			row.setFirstResult(0);
			row.setTotalResult(-1);
			row.setShowNumber(json.getInt("showNumber"));
			row.setRssAddress(json.getString("rssAddress"));
			row.setShare(json.getBoolean("share"));
			row.setShow_time(json.getBoolean("show_time"));
			break;
		case 3:
			row.setShare(json.getBoolean("share"));
			break;
		default:
		}
		return row;
	}

	public static Row changeLinkJsonToRowObject(String username, JSONObject json) {
		Row row = new Row();
		row.setUsername(username);
		row.setType(json.getInt("type"));
		row.setTitle(json.getString("title"));
		row.setShare(json.getBoolean("share"));
		row.setShow_tip(json.getBoolean("show_tip"));
		return row;
	}
}
