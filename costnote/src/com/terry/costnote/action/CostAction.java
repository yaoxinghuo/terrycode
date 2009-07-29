package com.terry.costnote.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.terry.costnote.data.service.intf.IAccountService;
import com.terry.costnote.data.service.intf.ICostService;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:57:42 AM
 */

@Scope("prototype")
@Component("gwt-costAction")
public class CostAction extends GenericAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1503567222310181255L;
	@Autowired
	private ICostService costService;
	@Autowired
	private IAccountService accountService;

	public boolean saveCost(String cost) {
		return costService.saveCost(getCurrentUserEmail(), cost);
	}

	public boolean deleteCost(String costIds) {
		return costService.deleteCost(costIds);
	}

	public boolean saveSchedule(String schedule) {
		return costService.saveSchedule(getCurrentUserEmail(), schedule);
	}

	public boolean deleteSchedule(String scheduleId) {
		return costService.deleteSchedule(scheduleId);
	}

	public String getAccountInfo() {
		return accountService.getAccountInfo(getCurrentUserEmail());
	}

	public int addFriend(String mobile) {
		return accountService.addFriend(getCurrentUserEmail(), mobile);
	}

	public boolean sendVerifyCode(String mobile) {
		return accountService.sendVerifyCode(getCurrentUserEmail(), mobile);
	}

	public boolean verifyCode(String code) {
		return accountService.verifyCode(getCurrentUserEmail(), code);
	}

	public boolean updateAccountBasic(String nickname) {
		return accountService.updateAccountBasic(getCurrentUserEmail(),
				nickname);
	}

	public boolean updateAccountSms(Boolean sendAlert, Double alertLimit) {
		return accountService.updateAccountSms(getCurrentUserEmail(),
				sendAlert, alertLimit);
	}

}
