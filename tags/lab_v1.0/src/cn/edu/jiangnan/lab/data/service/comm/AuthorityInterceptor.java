package cn.edu.jiangnan.lab.data.service.comm;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		//如果时间过期，不准访问，导向过期页面
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		long expired = sdfd.parse("2009-02-01").getTime();
		if (System.currentTimeMillis() > expired)
			return "expired";
		//如果时间过期，不准访问，导向过期页面
		
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		String admin_name = (String) session.get(Constants.SESSION_ADMIN_ID);
		if (admin_name != null)
			return invocation.invoke();
		ctx.put(Constants.REQUEST_TIP, "<font color=red>继续该操作需要验证身份</font>");
		return Action.INPUT;
	}
}
