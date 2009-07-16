package com.terry.costnote.data.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.terry.costnote.data.dao.intf.IAccountDao;
import com.terry.costnote.data.dao.intf.ICostDao;
import com.terry.costnote.data.model.Cost;
import com.terry.costnote.data.service.intf.ICostService;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:26:55 AM
 */

@Service("costService")
@Repository
public class CostServiceImpl implements ICostService {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private Cache cache;

	public CostServiceImpl() {
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					Collections.emptyMap());
		} catch (CacheException e) {
		}
	}

	@Autowired
	private ICostDao costDao;

	@SuppressWarnings("unused")
	@Autowired
	private IAccountDao accountDao;

	@Override
	public boolean saveCost(String email, String c) {
		JSONObject jo = JSONObject.fromObject(c);
		String name = jo.getString("name");
		Cost cost = null;
		if (!jo.getString("id").equals(""))
			cost = costDao.getCostById(jo.getString("id"));
		if (cost == null) {
			cost = new Cost();
			cost.setCdate(new Date());
			cost.setEmail(email);
		}
		Date adate = new Date();
		try {
			adate = sdf.parse(jo.getString("date"));
		} catch (ParseException e) {
		}
		cost.setAdate(adate);
		cost.setName(name);
		cost.setAmount(jo.getDouble("amount"));
		cost.setRemark(jo.getString("remark"));
		cost.setType(jo.getInt("type"));
		if (costDao.saveCost(cost)) {
			addNameToCache(email, name);
			return true;
		} else
			return false;
	}

	@Override
	public List<Cost> getCostsByEmail(String email, Date sfrom, Date sto,
			int stype, int start, int limit) {
		return costDao.getCostsByEmail(email, sfrom, sto, stype, start, limit);
	}

	@Override
	public long getCostsCountByEmail(String email, Date sfrom, Date sto,
			int stype) {
		return costDao.getCostsCountByEmail(email, sfrom, sto, stype);
	}

	@Override
	public boolean deleteCost(String costIds) {
		JSONArray ja = JSONArray.fromObject(costIds);
		boolean result = false;
		for (int i = 0; i < ja.size(); i++) {
			Cost cost = costDao.getCostById(ja.getString(i));
			if (costDao.deleteCost(cost))
				result = true;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private void addNameToCache(String email, String name) {
		if (cache != null) {
			Stack<String> stack = (Stack<String>) cache.get(email);
			if (stack == null)
				stack = new Stack<String>();
			if (stack.contains(name))
				stack.remove(name);
			stack.push(name);
			if (stack.size() > 10)
				stack.remove(0);
			cache.put(email, stack);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getSuggestNames(String email) {
		JSONArray ja = new JSONArray();
		if (cache != null) {
			Stack<String> stack = (Stack<String>) cache.get(email);
			if (stack != null) {
				while (!stack.isEmpty())
					ja.add(stack.pop());
			}
		}
		if (ja.size() == 0) {
			List<Cost> costs = costDao.getCostsByEmail(email, 0, 10);
			Stack<String> stack = new Stack<String>();
			for (Cost cost : costs) {
				String name = cost.getName();
				if (stack.contains(name)) {
					stack.push(name);
				}
			}
			
			if (stack.size() > 0 && cache != null) {
				cache.put(email, stack);
			}

			Stack<String> stack2 = (Stack<String>) cache.get(email);
			if (stack2 != null) {
				while (!stack2.isEmpty())
					ja.add(stack2.pop());
			}

			if (ja.size() == 0) {
				ja.add("用餐");
				ja.add("买衣服");
				ja.add("交通费");
			}
		}
		return ja.toString();
	}

}
