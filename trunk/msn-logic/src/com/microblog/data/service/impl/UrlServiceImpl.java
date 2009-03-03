package com.microblog.data.service.impl;

import java.util.Date;

import com.microblog.data.dao.intf.IUrlDao;
import com.microblog.data.model.Url;
import com.microblog.data.service.intf.IUrlService;

public class UrlServiceImpl implements IUrlService {

	public IUrlDao getUrlDao() {
		return urlDao;
	}

	public void setUrlDao(IUrlDao urlDao) {
		this.urlDao = urlDao;
	}

	private IUrlDao urlDao;

	@Override
	public String imGetUrlByHash(String hash) {
		Url url = urlDao.getUrlByHash(hash);
		if (url == null)
			return null;
		else
			return url.getUrl();
	}

	@Override
	public boolean imSaveUrl(String url, String hash) {
		Url u = new Url();
		u.setUrl(url);
		u.setHash(hash);
		u.setLastvisit(new Date());
		return urlDao.saveUrl(u);
	}

	@Override
	public boolean imUpdateUrlVisitDate(String hash) {
		Url u = urlDao.getUrlByHash(hash);
		if (u == null)
			return false;
		return urlDao.saveUrl(u);
	}

	@Override
	public String imGetHashByUrl(String url) {
		Url u = urlDao.getUrlByUrl(url);
		if (u == null)
			return null;
		else
			return u.getHash();
	}

}
