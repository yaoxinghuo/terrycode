package com.terry.costnote.data.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.terry.costnote.data.dao.intf.IAccountDao;
import com.terry.costnote.data.dao.intf.ICostDao;
import com.terry.costnote.data.model.Account;
import com.terry.costnote.data.model.Cost;
import com.terry.costnote.data.service.intf.ICostService;
import com.terry.costnote.data.util.EMF;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:26:55 AM
 */

@Service("costService")
@Repository
public class CostServiceImpl implements ICostService {

	EntityManager em = EMF.get().createEntityManager();

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private ICostDao costDao;

	@Autowired
	private IAccountDao accountDao;

	@Override
	public boolean saveCost(String c) {
		JSONObject jo = JSONObject.fromObject(c);
		Cost cost = null;
		if (!jo.getString("id").equals(""))
			cost = costDao.getCostById(jo.getString("id"));
		if (cost == null) {
			cost = new Cost();
			cost.setCdate(new Date());
			cost.setEmail("itcontent@gmail.com");
		}
		Date adate = new Date();
		try {
			adate = sdf.parse(jo.getString("date"));
		} catch (ParseException e) {
		}
		cost.setAdate(adate);
		cost.setName(jo.getString("name"));
		cost.setAmount(jo.getDouble("amount"));
		cost.setRemark(jo.getString("remark"));
		cost.setType(jo.getBoolean("type"));
		if (costDao.saveCost(cost)) {
			return true;
		} else
			return false;
	}

	@Override
	public List<Cost> getCostsByEmail(String email, int start, int limit) {
		List<Cost> costs = costDao.getCosts(0, 0);
		for (Cost cost : costs) {
			System.out.println(cost.getEmail() + "-" + cost.getName());
		}
		System.out.println("Size:" + costs.size());
		return costDao.getCostsByEmail(email, start, limit);
	}

	@Override
	public long getCostsCountByEmail(String email) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return 0;
		return costDao.getCostsCountByEmail(email);
	}

}
