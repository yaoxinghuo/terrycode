package com.terry.msgsbot.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
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

	private String getResponse(String body) {
		if (body == null || body.trim().equals(""))
			return "Message body can not be empty.";
		String[] parts = body.trim().split("\\s");
		if (!StringUtil.validateUrl(parts[0])) {
			return "You must start with an valid URL seperated with post data if any.";
		}
		return fetchData(parts[0], parts.length > 1 ? parts[1] : null);
	}

	private String fetchData(String url, String data) {
		try {
			if (StringUtil.isEmptyOrWhitespace(data))
				data = null;
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
			if (code == HttpServletResponse.SC_OK) {
				String s = getContent(con.getInputStream());
				con.disconnect();
				log.debug("getResponse response:" + s);
				return code + "\r\n" + s;
			}
			con.disconnect();
			return String.valueOf(code);
		} catch (MalformedURLException e) {
			return "500 " + e.getMessage();
		} catch (Exception e) {
			return "500 " + e.getMessage();
		}
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
