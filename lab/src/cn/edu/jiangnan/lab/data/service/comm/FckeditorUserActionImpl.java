package cn.edu.jiangnan.lab.data.service.comm;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.UserAction;

public class FckeditorUserActionImpl implements UserAction {

	@Override
	public boolean isEnabledForFileBrowsing(HttpServletRequest request) {
		return isSuperAdmin(request);
	}

	@Override
	public boolean isEnabledForFileUpload(HttpServletRequest request) {
		return isSuperAdmin(request);
	}

	private boolean isSuperAdmin(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constants.SESSION_ADMIN_ID) == null)
			return false;
		else
			return true;
	}

}
