package com.terry.costnote.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author xinghuo.yao yaoxinghuo at 126 dot com
 * @create Apr 17, 2009 3:52:43 PM
 * @description
 */
public interface CostServiceAsync {

	void saveCost(String cost, AsyncCallback<Boolean> callback);

	void deleteCost(String costIds, AsyncCallback<Boolean> callback);

	void saveSchedule(String schedule, AsyncCallback<Boolean> callback);

	void deleteSchedule(String scheduleId, AsyncCallback<Boolean> callback);

	void getAccountInfo(AsyncCallback<String> callback);

	void sendVerifyCode(String mobile, AsyncCallback<Boolean> callback);

	void verifyCode(String code, AsyncCallback<Boolean> callback);

	void updateAccountBasic(String nickname, AsyncCallback<Boolean> callback);

	void updateAccountSms(boolean sendAlert, double alertLimit,
			AsyncCallback<Boolean> callback);
}
