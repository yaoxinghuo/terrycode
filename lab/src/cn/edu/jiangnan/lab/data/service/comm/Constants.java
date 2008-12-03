package cn.edu.jiangnan.lab.data.service.comm;

/**
 * Developer: Terry DateTime : 2007-12-18 下午04:54:58
 */

public class Constants {
	// 保存cookie时的cookieName
	public final static String cookieDomainName = "lab";
	// 加密cookie时的网站自定码
	public final static String webKey = "lab";
	// 设置cookie有效期是两个星期，根据需要自定义
	public final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;

	public static final String SESSION_ID = "_userId";

	public static final String SESSION_NAME = "_userName";

	public static final String SESSION_ADMIN_ID = "_adminId";

	public static final String REQUEST_TIP = "_tip";

	public static final String SESSION_SUPER_ADMIN = "_superAdmin";

	public static final String SESSION_VALIDATE = "_validate";

	public static final String SESSION_GROUP_TYPE = "_group_type";

	public static final String NO_ADMIN_DO_ERROR_MESSAGE = "{result:false,message:'对不起，出现错误，请确认您是系统管理员并重新登录或稍后再试！'}";
}
