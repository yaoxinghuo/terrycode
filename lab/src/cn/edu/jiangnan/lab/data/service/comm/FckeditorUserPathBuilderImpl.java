package cn.edu.jiangnan.lab.data.service.comm;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.UserPathBuilder;

public class FckeditorUserPathBuilderImpl implements UserPathBuilder {

	@Override
	public String getUserFilesPath(HttpServletRequest arg0) {
		return "/userfiles/";
	}

}
