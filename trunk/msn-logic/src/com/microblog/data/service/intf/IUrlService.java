package com.microblog.data.service.intf;

public interface IUrlService {
	public String imGetUrlByHash(String hash);
	
	public String imGetHashByUrl(String url);

	public boolean imUpdateUrlVisitDate(String hash);

	public boolean imSaveUrl(String url, String hash);
}
