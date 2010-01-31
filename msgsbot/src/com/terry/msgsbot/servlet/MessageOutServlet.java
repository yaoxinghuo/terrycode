package com.terry.msgsbot.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.terry.msgsbot.util.Queue;
import com.terry.msgsbot.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Jan 31, 2010 11:29:01 AM
 */
public class MessageOutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7201336367329802309L;

	private static Log log = LogFactory.getLog(MessageOutServlet.class);

	private XMPPService xmpp = XMPPServiceFactory.getXMPPService();

	private static final String CALL_LOGS = "call-logs";

	private static final String STATUS = "status";

	private Cache cache;
	private Cache short_cache;

	@Override
	public void init() throws ServletException {
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					Collections.emptyMap());
			Map<Integer, Integer> CACHE_PROPS = new HashMap<Integer, Integer>();
			CACHE_PROPS.put(GCacheFactory.EXPIRATION_DELTA, 60 * 5);
			short_cache = CacheManager.getInstance().getCacheFactory()
					.createCache(CACHE_PROPS);
		} catch (CacheException e) {
			log.error("can not create cache instance. exception:"
					+ e.getMessage());
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		doGet(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		log.debug("xmpp received message...");
		Message message = xmpp.parseMessage(req);

		String msgBody = "Sorry, error occured, please try again later.";

		JID jid = message.getFromJid();
		String jids = jid.getId();
		if (jids.indexOf("/") != -1)
			jids = jids.substring(0, jids.indexOf("/"));
		if (jids.equals("yaoxinghuo@gmail.com")) {
			msgBody = getResponse(message.getBody());
		} else
			return;
		Message msg = new MessageBuilder().withRecipientJids(jid).withBody(
				msgBody).build();

		@SuppressWarnings("unused")
		boolean messageSent = false;
		if (xmpp.getPresence(jid).isAvailable()) {
			SendResponse status = xmpp.sendMessage(msg);
			messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);
		}
	}

	@SuppressWarnings("unchecked")
	private String getResponse(String body) {
		if (cache == null || short_cache == null) {
			return "CACHE为空，程序无法正常执行";
		}
		if (body == null || body.trim().equalsIgnoreCase("list")
				|| body.trim().equalsIgnoreCase("l")) {
			Object o = cache.get(CALL_LOGS);
			if (o == null || !(o instanceof Queue))
				return "历史记录为空";
			else {
				short_cache.put(STATUS, 1);
				return ((Queue) o).statics();
			}
		}
		Object o = short_cache.get(STATUS);
		if (o != null && ((Integer) o) == 1) {
			Queue queue = getQueueFromCache();
			if (body.startsWith("-")) {
				if (body.length() < 2) {
					queue.clear();
					cache.put(CALL_LOGS, queue);
					short_cache.put(STATUS, 0);
					return "历史记录已清空";
				} else {
					String other = body.substring(1);
					if (!StringUtil.isDigital(other))
						return "请输入-[数字]";
					int index = Integer.parseInt(other);
					if (index <= 0 || index > queue.getSize()) {
						return "数字范围为1<=x<=" + queue.getSize();
					}
					String result = "已从历史记录删除：" + queue.removeLink(index)
							+ "\r\n" + queue.statics();
					cache.put(CALL_LOGS, queue);
					return result;
				}
			} else {
				if (StringUtil.isDigital(body)) {
					int index = Integer.parseInt(body);
					if (index <= 0 || index > queue.getSize()) {
						return "数字范围为1<=x<=" + queue.getSize();
					}
					String link = queue.getLink(index);
					if (link == null)
						return "请重新选择序号";
					String[] p = link.split("\\s", 2);
					return link + "\r\n"
							+ fetchData(p[0], p.length > 1 ? p[1] : null);
				}
			}
		}
		String[] parts = body.trim().split("\\s", 2);
		if (!StringUtil.validateUrl(parts[0])) {
			return "请输入一个有效的URL地址，如果要POST数据，请加空格把数据写在URL后，"
					+ "输入list查看编辑或重新执行过往记录";
		}
		return fetchData(parts[0], parts.length > 1 ? parts[1] : null);
	}

	@SuppressWarnings("unchecked")
	private String fetchData(String url, String data) {
		try {
			if (StringUtil.isEmptyOrWhitespace(data))
				data = null;
			if (!url.startsWith("http://") && !url.startsWith("https://"))
				url = "http://" + url;
			HttpURLConnection con = (HttpURLConnection) new URL(url)
					.openConnection();
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("User-Agent", "MsgsBot");
			con.setDoOutput(true); // POST方式
			con.setRequestMethod(data != null ? "POST" : "GET");
			if (data != null) {
				con
						.setRequestProperty("Content-Type",
								"application/oct-stream");
				OutputStream os = con.getOutputStream(); // 输出流，写数据
				os.write(data.getBytes("UTF-8"));
				os.flush();
				os.close();
			}

			int code = con.getResponseCode();
			String message = con.getRequestMethod() + " " + code + " "
					+ con.getResponseMessage();
			if (code == HttpServletResponse.SC_OK) {
				message = message + "\r\n" + getContent(con.getInputStream());
				Queue queue = getQueueFromCache();
				if (data == null)
					queue.put(url);
				else
					queue.put(url + " " + data);
				cache.put(CALL_LOGS, queue);
			}
			con.disconnect();
			return message;
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}

	private Queue getQueueFromCache() {
		Queue queue = null;
		Object o = cache.get(CALL_LOGS);
		if (o == null || !(o instanceof Queue)) {
			queue = new Queue(20);
		} else
			queue = (Queue) o;
		return queue;
	}

	private String getContent(InputStream in) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,
				"UTF-8")); // 读取结果
		StringBuffer sb = new StringBuffer("");
		String line;
		while ((line = reader.readLine()) != null) {
			if (sb.length() > 3000) {
				sb.append("...");
				break;
			}
			sb.append(line).append("\r\n");
		}
		reader.close();
		return StringUtil.HTMLToTEXT(sb.toString().trim());

	}
}
