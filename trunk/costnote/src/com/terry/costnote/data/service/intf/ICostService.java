package com.terry.costnote.data.service.intf;

import java.util.Date;
import java.util.List;

import com.terry.costnote.data.model.Cost;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:25:53 AM
 */
public interface ICostService {
	public boolean saveCost(String email, String cost);

	public boolean deleteCost(String costIds);

	public List<Cost> getCostsByEmail(String email, Date sfrom, Date sto,
			int stype, int start, int limit);

	public long getCostsCountByEmail(String email, Date sfrom, Date sto,
			int stype);

	public String getAccountInfo(String email);
}
