package com.microblog.data.dao.intf;

import com.microblog.data.model.Url;

public interface IUrlDao {
	public Url getUrlById(String id);

	public Url getUrlByHash(String hash);
	
	public Url getUrlByUrl(String url);

	public boolean saveUrl(Url url);

	public boolean deleteUrl(Url url);
}
