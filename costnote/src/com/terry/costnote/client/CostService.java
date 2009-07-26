package com.terry.costnote.client;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author xinghuo.yao yaoxinghuo at 126 dot com
 * @create Apr 17, 2009 3:51:11 PM
 * @description
 */
public interface CostService extends RemoteService {

	boolean saveCost(String cost);

	boolean deleteCost(String costIds);

	boolean saveSchedule(String schedule);

	boolean deleteSchedule(String scheduleId);

	String getAccountInfo();

	boolean sendVerifyCode(String mobile);

	boolean verifyCode(String code);

	boolean updateAccountBasic(String nickname);

	boolean updateAccountSms(boolean sendAlert, double alertLimit);

	String getAccountSettings();

}
