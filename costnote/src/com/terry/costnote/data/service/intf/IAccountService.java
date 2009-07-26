package com.terry.costnote.data.service.intf;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 15, 2009 1:59:58 PM
 */
public interface IAccountService {
	public boolean updateAccount(String email, String account);

	public boolean updateAccountBasic(String email, String nickname);

	public boolean updateAccountSms(String email, boolean sendAlert,
			double alertLimit);

	public boolean sendVerifyCode(String email, String mobile);

	public boolean verifyCode(String email, String code);

	public String getAccountSettings(String email);
}
