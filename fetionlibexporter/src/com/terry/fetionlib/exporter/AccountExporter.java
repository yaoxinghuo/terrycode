package com.terry.fetionlib.exporter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.terry.fetionlib.data.model.Account;
import com.terry.fetionlib.data.model.Friend;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Jan 9, 2010 10:02:57 PM
 */
public class AccountExporter {
	private static final String BASE_URL = "http://fetion.xinghuo.org.ru";
	private static final String L_DATE = "2010-01-01 00:00";
	private static final String DB_NAME = "E:\\Lab\\fetionlib.yap";
	private static final boolean DELETE = true;
	private static final int LIMIT = 500;

	private static final int TRY_TIMES = 3;
	private static final int TIME_OUT = 30000;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private long count = 0;

	public static void main(String[] args) throws Exception {
		AccountExporter exporter = new AccountExporter();
		exporter.run();
	}

	private void run() throws Exception {
		ObjectContainer db = Db4o.openFile(DB_NAME);

		try {
			long ldate = sdf.parse(L_DATE).getTime();

			for (int j = 0; j < LIMIT; j++) {
				JSONArray ja = loop(0);
				if (ja != null && ja.length() == 1) {
					long udate = 0;
					JSONObject jo = ja.getJSONObject(0);
					Account account = new Account();
					String mobile = jo.getString("mobile");
					account.setMobile(mobile);
					ObjectSet<Account> result = db.queryByExample(account);
					while (result.hasNext()) {
						Account a = result.next();
						List<Friend> fs = a.getFriends();
						if (fs != null) {
							for (Friend f : fs) {
								db.delete(f);
							}
						}
						System.out.println("find same account:" + mobile
								+ ". delete old account first.");
						db.delete(a);
						db.commit();
					}
					account.setCdate(sdf.parse(jo.getString("cdate")));
					account.setUdate(sdf.parse(jo.getString("udate")));
					udate = account.getUdate().getTime();
					account.setId(jo.getString("id"));
					account.setPassword(jo.getString("password"));
					account.setUcversion(jo.getInt("ucversion"));
					account.setUip(jo.getString("uip"));
					account.setUtype(jo.getInt("utype"));

					System.out.print((++count) + "\tStoring: "
							+ account.getMobile() + ". update date: "
							+ jo.getString("udate"));

					if (jo.has("friends")) {
						JSONArray fs = jo.getJSONArray("friends");
						System.out.print(" Friends size: " + fs.length());
						List<Friend> friends = new ArrayList<Friend>();
						for (int i = 0; i < fs.length(); i++) {
							JSONObject f = fs.getJSONObject(i);
							Friend friend = new Friend();
							friend.setAccount(account);
							friend.setAmobile(f.getString("amobile"));
							friend.setCdate(sdf.parse(f.getString("cdate")));
							friend.setId(f.getString("id"));
							if (f.has("localname"))
								friend.setLocalname(f.getString("localname"));
							if (f.has("nickname"))
								friend.setNickname(f.getString("nickname"));
							if (f.has("mobile"))
								friend.setMobile(f.getString("mobile"));
							friend.setUri(f.getString("uri"));
							friend.setUserid(f.getString("userid"));
							friends.add(friend);
						}
						account.setFriends(friends);
					}

					db.store(account);
					db.commit();
					System.out.println("\tStored.");
					if (udate >= ldate)
						break;
					Thread.sleep(8000);
				}
			}

		} finally {
			db.close();
		}
	}

	private JSONArray loop(int start) throws Exception {
		String result = fetchToExport("1qaz2wsx-export-password,*", 3, start,
				1, DELETE);
		if (result == null) {
			System.out.println("fetchToExport result null...");
			return null;
		} else {
			JSONArray ja = new JSONArray(result);
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
