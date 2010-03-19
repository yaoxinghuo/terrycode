package com.terry.weddingphoto.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.terry.weddingphoto.data.impl.PhotoDaoImpl;
import com.terry.weddingphoto.data.intf.IPhotoDao;
import com.terry.weddingphoto.model.Comment;
import com.terry.weddingphoto.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-17 下午12:49:41
 */
public class PhotoManagerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4006164308610992222L;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
			Locale.CHINA);
	private UserService userService = UserServiceFactory.getUserService();
	private MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
	private static final String IP_COUNT_CACHE = "ip-count-cache";
	private static final int COMMENT_LIMIT = 5;
	private static final int CACHE_SESSION_TIME = 3600;// 1个小时
	private IPhotoDao photoDao = new PhotoDaoImpl();
	private static final String ERROR = "对不起，程序出现错误，请稍候再试";

	@Override
	public void init() throws ServletException {
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		JSONObject jo = null;

		String action = req.getParameter("action");
		if (action != null) {
			if (action.equals("saveComment")) {
				jo = saveComment(req);
			} else if (action.equals("deleteComment")) {
				jo = deleteComment(req);
			}
		}

		if (jo != null)
			resp.getWriter().println(jo.toString());
	}

	private JSONObject saveComment(HttpServletRequest req) {
		JSONObject jo = createDefaultJo();
		String pid = req.getParameter("pid");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String content = req.getParameter("content");
		if (StringUtil.isEmptyOrWhitespace(pid)
				|| StringUtil.isEmptyOrWhitespace(nickname)
				|| StringUtil.isEmptyOrWhitespace(content)
				|| (!StringUtil.isEmptyOrWhitespace(email) && !StringUtil
						.validateEmail(email)) || nickname.length() > 12
				|| content.length() > 500) {
			try {
				jo.put("message", "请检查您的输入");
			} catch (JSONException e) {
			}
			return jo;
		}

		String ip = getIpAddr(req);
		if (getIpCommentCount(pid, ip) >= COMMENT_LIMIT) {
			try {
				jo.put("message", "您的评论频率太高");
			} catch (JSONException e) {
			}
			return jo;
		}

		Comment comment = new Comment();
		comment.setCdate(new Date());
		comment.setIp(ip);
		comment.setEmail(email);
		comment.setName(nickname);
		comment.setPid(pid);
		comment.setContent(content);
		int count = photoDao.saveComment(comment);
		if (count != -1) {
			updateIpCommentCount(pid, ip);
			try {
				jo.put("result", true);
				jo.put("message", "已成功发布评论");
				jo.put("count", count);
				jo.put("cdate", sdf.format(comment.getCdate()));
				jo.put("nickname", comment.getName());
				jo.put("content", comment.getContent());
				jo.put("cid", comment.getId());
			} catch (JSONException e) {
			}
		}
		return jo;
	}

	private JSONObject deleteComment(HttpServletRequest req) {
		JSONObject jo = createDefaultJo();
		if (!userService.isUserLoggedIn() || !userService.isUserAdmin()) {
			try {
				jo.put("message", "您无权删除该评论");
			} catch (JSONException e) {
			}
			return jo;
		}
		String cid = req.getParameter("cid");
		if (StringUtil.isEmptyOrWhitespace(cid)) {
			try {
				jo.put("message", "请检查您的输入");
			} catch (JSONException e) {
			}
			return jo;
		}
		int count = photoDao.deleteCommentById(cid);
		if (count != -1) {
			try {
				jo.put("result", true);
				jo.put("message", "已成功删除评论");
				jo.put("count", count);
			} catch (JSONException e) {
			}
		}
		return jo;
	}

	private JSONObject createDefaultJo() {
		JSONObject jo = new JSONObject();
		try {
			jo.put("result", false);
			jo.put("message", ERROR);
		} catch (JSONException e) {
		}
		return jo;
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private int getIpCommentCount(String pid, String ip) {
		String key = IP_COUNT_CACHE + "-" + pid + "-" + ip;
		Object o = cache.get(key);
		if (o != null && o instanceof Integer) {
			return (Integer) o;
		}
		return 0;
	}

	private void updateIpCommentCount(String pid, String ip) {
		String key = IP_COUNT_CACHE + "-" + pid + "-" + ip;
		Object o = cache.get(key);
		if (o != null && o instanceof Integer) {
			cache.increment(key, 1);
		} else
			cache.put(key, 1, Expiration.byDeltaSeconds(CACHE_SESSION_TIME));
	}
}
