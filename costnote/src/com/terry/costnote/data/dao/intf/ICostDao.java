package com.terry.costnote.data.dao.intf;

import java.util.Date;
import java.util.List;

import com.terry.costnote.data.model.Cost;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:16:24 AM
 */
public interface ICostDao {

	public Cost getCostById(String costId);

	public boolean saveCost(Cost cost);

	public boolean deleteCost(Cost cost);

	public List<Cost> getCostsByEmail(String email, Date sfrom, Date sto,
			int stype, int start, int limit);

	public List<Cost> getCosts(int start, int limit);

	public long getCostsCountByEmail(String email, Date sfrom, Date sto,
			int stype);
}
