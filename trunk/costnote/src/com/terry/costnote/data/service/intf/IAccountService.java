package com.terry.costnote.data.service.intf;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 15, 2009 1:59:58 PM
 */
public interface IAccountService {
	public boolean updateAccount(String email, String account);

	public boolean sendVerifyCode(String email, String mobile, String password);

	public boolean verifyCode(String email, String code);
}
