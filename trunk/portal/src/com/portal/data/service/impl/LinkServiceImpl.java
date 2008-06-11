package com.portal.data.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.portal.data.dao.intf.ILinkDao;
import com.portal.data.pojo.UserLink;
import com.portal.data.service.intf.ILinkService;

/**
 * Developer: Terry DateTime : 2008-1-14 下午02:20:28
 */

public class LinkServiceImpl implements ILinkService {

	private ILinkDao linkDao;

	public ILinkDao getLinkDao() {
		return linkDao;
	}

	public void setLinkDao(ILinkDao linkDao) {
		this.linkDao = linkDao;
	}

	@Override
	public void saveLinks(ArrayList<UserLink> userLinks) {
		for (int i = 0; i < userLinks.size(); i++) {
			linkDao.saveLink(userLinks.get(i));
		}
	}
	
	@Override
	public String getLinksByRowId(String id){
		JSONObject o = new JSONObject();
		JSONArray links = new JSONArray();
		ArrayList<UserLink> userLinks = linkDao.getLinksByRowId(id);
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, Locale.CHINA);
		for(int i=0;i<userLinks.size();i++){
			JSONObject link = new JSONObject();
			UserLink l = userLinks.get(i);
			link.put("title", l.getTitle());
			link.put("link", l.getLink());
			link.put("desc", l.getDescription());
			link.put("time", df.format(l.getUpdate_time()));
			link.put("id", l.getId());
			links.add(link);
		}
		o.put("links", links);
		o.put("ids", "");
		return o.toString();
	}
}
