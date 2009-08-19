package com.terry.gfwout.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.terry.gfwout.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 11, 2009 10:39:57 PM
 */
public class GfwServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3212155045844726975L;

	private Cache cache;

	@Override
	public void init() {
		Map<Integer, Integer> props = new HashMap<Integer, Integer>();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600 * 24);
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					props);
		} catch (CacheException e) {
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uuid = req.getParameter("go");
		if (uuid == null || uuid.equals("")) {
			req.setAttribute("message", "对不起，该操作暂时无法在GFWout代理上支持！"
					+ "&nbsp;<a href=\"javascript:history.go(-1);\">返回</a>");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			return;
		}
		String s = (String) cache.get(uuid);
		if (s == null || s.trim().equals("") || s.trim().equals("/index.jsp")) {
			resp.sendRedirect("/index.jsp");
			return;
		}

		HttpURLConnection con = null;
		try {
			if (s.startsWith("/"))
				s = s.substring(1);
			if (!s.startsWith("http://") && !s.startsWith("https://"))
				s = "http://" + s;
			URL url = new URL(s);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");

			int code = con.getResponseCode();
			if (code == 200) {
				String contentType = con.getContentType();
				if (contentType == null)
					contentType = "text/html; charset=GBK";
				resp.setContentType(contentType);

				if (contentType.contains("image") || StringUtil.isBinary(s)
						|| StringUtil.isImage(s)) {
					ServletOutputStream responseOutputStream = resp
							.getOutputStream();
					InputStream in = con.getInputStream();
					byte[] data = new byte[1024];
					while (true) {
						int pos = in.read(data);
						if (pos == -1)
							break;
						responseOutputStream.write(data, 0, pos);
					}
					responseOutputStream.flush();
					responseOutputStream.close();
					in.close();
				} else {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(con.getInputStream(),
									StringUtil.getContentType(contentType))); // 读取结果
					StringBuffer sb = new StringBuffer();
					String line;
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}
					reader.close();
					con.disconnect();

					String html = sb.toString();
					resp.setCharacterEncoding(StringUtil
							.getContentType(contentType));

					PrintWriter pw = resp.getWriter();
					pw.write(StringUtil.replace(html,
							"http://gfwout.appspot.com/", s));
					pw.flush();
					pw.close();
				}
			} else {
				req
						.setAttribute(
								"message",
								"对不起，无法连接至指定的网站，请稍候再试！Error Code:"
										+ code
										+ "&nbsp;<a href=\"javascript:history.go(-1);\">返回</a>");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("message", "对不起，无法连接至指定的网站，请稍候再试！"
					+ e.getMessage()
					+ "&nbsp;<a href=\"javascript:history.go(-1);\">返回</a>");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} finally {
			if (con != null)
				try {
					con.disconnect();
				} catch (Exception e) {
				}
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
