package com.terry.fetionlib.exporter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.terry.fetionlib.data.model.Message;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Jan 9, 2010 10:02:57 PM
 */
public class MessageExporter {
	private static final String BASE_URL = "http://fetion.xinghuo.org.ru";
	private static final String L_DATE = "2010-01-18 00:00";
	private static final String DB_NAME = "E:\\Lab\\fetionlib_message.yap";
	private static final boolean DELETE = false;
	private static final int LIMIT = 500;

	private static final int TRY_TIMES = 3;
	private static final int TIME_OUT = 30000;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private long count = 0;

	public static void main(String[] args) throws Exception {
		MessageExporter exporter = new MessageExporter();
		exporter.run();
	}

	private void run() throws Exception {
		ObjectContainer db = Db4o.openFile(DB_NAME);
		try {
			long ldate = sdf.parse(L_DATE).getTime();

			outer: for (int j = 0; j < LIMIT; j++) {
				JSONArray ja = loop(j);
				if (ja != null) {
					long cdate = 0;
					for (int i = 0; i < ja.length(); i++) {
						JSONObject jo = ja.getJSONObject(i);
						Message message = new Message();
						message.setId(jo.getString("id"));
						cdate = sdf.parse(jo.getString("cdate")).getTime();
						message.setCdate(sdf.parse(jo.getString("cdate")));
						message.setAdate(sdf.parse(jo.getString("adate")));
						message.setAmobile(jo.getString("amobile"));
						message.setFriends(jo.getString("friends"));
						message.setContent(jo.getString("content"));
						message.setIp(jo.getString("ip"));
						if (jo.has("sid"))
							message.setSid(jo.getString("sid"));
						message.setStatus(jo.getBoolean("status"));
						message.setType(jo.getInt("type"));

						db.store(message);

						String content = jo.getString("content");
						if (content != null && content.length() > 15)
							content = content.substring(0, 14) + "...";
						System.out.println("Stored" + (++count) + " "
								+ jo.getString("cdate") + " "
								+ jo.getString("amobile") + " "
								+ jo.getString("friends") + "\t" + content);
					}

					db.commit();
					if (cdate >= ldate)
						break outer;
					Thread.sleep(8000);
				}

			}

		} finally {
			db.close();
		}
	}

	private JSONArray loop(int start) throws Exception {
		String result = fetchToExport("1qaz2wsx-export-password,*", 0,
				start * 10, 20, DELETE);
		if (result == null) {
			System.out.println("fetchToExport result null...");
			return null;
		} else {
			JSONArray ja = new JSONArray(result);
			// for (int i = 0; i < ja.length(); i++) {
			// JSONObject jo = ja.getJSONObject(i);
			// String content = jo.getString("content");
			// if (content != null && content.length() > 15)
			// content = content.substring(0, 14) + "...";
			// System.out.println((++count) + " " + jo.getString("cdate")
			// + " " + jo.getString("amobile") + " "
			// + jo.getString("friends") + "\t" + content);
			// }
			return ja;
		}
	}

	public String fetchToExport(String password, int no, int start, int limit,
			boolean delete) {
		String uuid = UUID.randomUUID().toString();
		for (int i = 0; i < TRY_TIMES; i++) {
			try {
				URL postUrl = new URL(BASE_URL + "/restlet/export");
				HttpURLConnection connection = (HttpURLConnection) postUrl
						.openConnection();
				connection.setConnectTimeout(TIME_OUT);
				connection.setReadTimeout(TIME_OUT);
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				connection.connect();
				DataOutputStream out = new DataOutputStream(connection
						.getOutputStream());
				String content = "no=" + no + "&start=" + start + "&uuid="
						+ uuid + "&limit=" + limit + "&password=" + password;
				if (delete) {
					content += "&delete=true";
				}
				out.writeBytes(content);

				out.flush();
				out.close();

				int responseCode = connection.getResponseCode();
				if (responseCode == 202) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(connection.getInputStream(),
									"UTF-8")); // 读取结果
					StringBuffer sb = new StringBuffer();
					String line;
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}
					reader.close();
					connection.disconnect();
					return sb.toString();
				} else {
					connection.disconnect();
					return null;
				}
			} catch (Exception e) {
				System.err.println("error fetchExport, exception:"
						+ e.getMessage() + ". tried " + i + " times");
			}
		}
		return null;
	}
}
