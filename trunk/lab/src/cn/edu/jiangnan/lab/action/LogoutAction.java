package cn.edu.jiangnan.lab.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.edu.jiangnan.lab.data.service.comm.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4645122444472035660L;

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().invalidate();
		request
				.setAttribute(Constants.REQUEST_TIP,
						"<font color=green>您已安全退出，请重新登录或</font>&nbsp;<a href=index.action>进入首页</a>");

		if (type == 1)
			return "admin";
		else if (type == 2)
			return "equip";
		else
			return "index";
	}
}
