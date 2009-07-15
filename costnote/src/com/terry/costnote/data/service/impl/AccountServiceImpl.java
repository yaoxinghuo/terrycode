package com.terry.costnote.data.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.terry.costnote.data.dao.intf.IAccountDao;
import com.terry.costnote.data.model.Account;
import com.terry.costnote.data.service.intf.IAccountService;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 15, 2009 2:02:13 PM
 */

@Service("accountService")
@Repository
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDao accountDao;

	@Override
	public Account checkAccount(String email, String nickname) {
		Account account = accountDao.getAccountByEmail(email);
		if (account == null) {
			account = new Account();
			account.setAlertLimit(0);
			account.setCdate(new Date());
			account.setEmail(email);
			account.setLastSendAlert(new Date());
			account.setMobile("");
			account.setMpassword("");
			account.setSendAlert(false);
			account.setNickname(nickname);
			if (accountDao.saveAccount(account))
				return account;
			else
				return null;
		} else
			return account;
	}

	@Override
	public boolean updateAccount(String email, String a) {
		JSONObject jo = JSONObject.fromObject(a);
		Account account = accountDao.getAccountByEmail(email);
		if (account == null)
			return false;
		account.setAlertLimit(jo.getDouble("alertLimit"));
		account.setMobile(jo.getString("mobile"));
		account.setMpassword(jo.getString("mpassword"));
		account.setNickname(jo.getString("nickname"));
		account.setSendAlert(jo.getBoolean("sendAlert"));
		return accountDao.saveAccount(account);
	}

}
