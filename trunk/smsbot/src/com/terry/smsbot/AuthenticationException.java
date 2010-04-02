package com.terry.smsbot;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-10 上午10:45:03
 */
public class AuthenticationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3711466252389933243L;

	public AuthenticationException(String message) {
		super(message);
	}

	public String tranlateError() {
		String message = this.getMessage();
		if (message != null) {
			if (message.equals("CaptchaRequired"))
				return "本程序登录您的GV账户时需要验证码，Google认为有安全性问题，请解决好此问题后再试";
			if (message.equals("BadAuthentication"))
				return "帐号或密码错误";
			if (message.equals("NotVerified"))
				return "您的账户尚未验证过，您需要先登录Google账户后才能使用等三方程序登录";
			if (message.equals("TermsNotAgreed"))
				return "您还没有同意此Google服务的使用条约，您需要先登录Google账户后才能使用等三方程序登录";
			if (message.equals("AccountDeleted"))
				return "该账户已被删除";
			if (message.equals("AccountDisabled"))
				return "该账户已被禁用";
			if (message.equals("ServiceDisabled"))
				return "此Google服务已被禁用";
			if (message.equals("ServiceUnavailable"))
				return "Google服务器错误，请稍候再试";
			return "错误代码" + message;
		}
		return "未知错误，请稍候再试";
	}
}
