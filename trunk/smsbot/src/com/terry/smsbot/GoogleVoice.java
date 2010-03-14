package com.terry.smsbot;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.terry.smsbot.util.StringUtil;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-10 上午10:35:42
 */
public class GoogleVoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5889296580959689523L;
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String auth;
	private String rnr;

	private long lastSessionTime = 0;

	public long getLastSessionTime() {
		return lastSessionTime;
	}

	public void setLastSessionTime(long lastSessionTime) {
		this.lastSessionTime = lastSessionTime;
	}

	public GoogleVoice(String email, String password)
			throws AuthenticationExeption {
		this.email = email;
		this.password = password;
		signIn();
	}

	private void signIn() throws AuthenticationExeption {
		retrieveLoginAuth();
		retrieveRnr();
		lastSessionTime = System.currentTimeMillis();
	}

	public boolean sendSms(String mobile, String message) {
		if (!checkSession())
			return false;
		try {
			String payload = "id=&phoneNumber=" + mobile + "&text="
					+ URLEncoder.encode(message, "UTF-8") + "&_rnr_se="
					+ URLEncoder.encode(rnr, "UTF-8");
			String data = fetchData("https://www.google.com/voice/sms/send/",
					payload, auth);
			// Should return {"ok":true,"data":{"code":0}}
			if (data.contains(":true"))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean checkSession() {
		if (System.currentTimeMillis() - lastSessionTime >= 1800000l)
			return false;
		lastSessionTime = System.currentTimeMillis();
		return true;
	}

	private void retrieveLoginAuth() throws AuthenticationExeption {
		String payload = "accountType=GOOGLE&Email=" + email + "&Passwd="
				+ password + "&service=grandcentral&source=" + "GAE"
				+ "-GoogleVoiceTool";
		try {
			String data = fetchData(
					"https://www.google.com/accounts/ClientLogin", payload,
					null);
			int index = data.indexOf("Auth=");
			if (index != -1 && index + 5 < data.length())
				auth = data.substring(data.indexOf("Auth=") + 5);
			if (StringUtil.isEmptyOrWhitespace(auth))
				throw new AuthenticationExeption("Get rnrSEE failed");
		} catch (Exception e) {
			throw new AuthenticationExeption(e.getMessage());
		}
	}

	private void retrieveRnr() throws AuthenticationExeption {
		try {
			String data = fetchData("https://www.google.com/voice/m/", null,
					auth);
			Pattern p = Pattern.compile(
					"<input.*?name=\"_rnr_se\".*?value=\"(.*?)\"",
					Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(data);

			if (m.find())
				rnr = m.group(1);
			if (StringUtil.isEmptyOrWhitespace(rnr))
				throw new AuthenticationExeption("Get rnrSEE failed");
		} catch (Exception e) {
			throw new AuthenticationExeption(e.getMessage());
		}
	}

	private String fetchData(String url, String payload, String auth)
			throws Exception {
		if (auth != null)
			url = url + "?auth=" + auth;
		HttpURLConnection con = (HttpURLConnection) new URL(url)
				.openConnection();
		con
				.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (iPhone; U; CPU iPhone OS 2_2_1 like Mac OS X; en-us) AppleWebKit/525.18.1 (KHTML, like Gecko) Version/3.1.1 Mobile/5H11 Safari/525.20");
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		if (payload == null)
			con.setRequestMethod("GET");
		else {
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			OutputStream os = con.getOutputStream(); // 输出流，写数据
			os.write(payload.getBytes("UTF-8"));
			os.flush();
			os.close();
		}
		int code = con.getResponseCode();
		if (code == 200) {
			String response = getContent(con.getInputStream(), code);
			con.disconnect();
			return response;
		} else {
			String response = getContent(con.getErrorStream(), code);
			con.disconnect();
			throw new AuthenticationExeption(response);
		}
	}

	private String getContent(InputStream in, int code) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,
				"UTF-8")); // 读取结果
		StringBuffer sb = new StringBuffer("");
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
			if (code != 200 && line.startsWith("Error=")) {
				reader.close();
				return line.substring(6);
			}
		}
		reader.close();
		if (code != 200)
			return String.valueOf(code);
		return sb.toString();
	}

}
