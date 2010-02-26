package com.terry.weatherlib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-23 下午05:20:54
 */
public class WeatherFetcher {

	private static Log log = LogFactory.getLog(WeatherFetcher.class);

	public static Weather fetchWeather(String loc) {
		String unicodeLoc = null;
		try {
			unicodeLoc = URLEncoder.encode(loc, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		String data = fetchData("http://www.google.cn/m?loc=" + unicodeLoc
				+ "&site=weather");
		// log.warn("fetch data:"+data);
		if (data == null)
			return null;
		return parserWeather(data, loc);
	}

	private static String fetchData(String url) {
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url)
					.openConnection();
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.1 (KHTML, like Gecko) Chrome/5.0.322.2 Safari/533.1,gzip(gfe),gzip(gfe)");
			con.setRequestMethod("GET");

			int code = con.getResponseCode();
			if (code >= 200 && code < 300) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(con.getInputStream(), "UTF-8")); // 读取结果
				StringBuffer sb = new StringBuffer("");
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\r\n");
				}
				reader.close();
				con.disconnect();
				return sb.toString();
			}
			con.disconnect();
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public static Weather parserWeather(String html, String loc) {
		log.debug(html);
		Parser parser = Parser.createParser(html, "utf8");

		NodeFilter contentFilter = new NodeClassFilter(Div.class);
		NodeList nodelist = null;
		try {
			nodelist = parser.extractAllNodesThatMatch(contentFilter);
		} catch (ParserException e) {
			return null;
		}

		Node[] nodes = nodelist.toNodeArray();

		Weather weather = new Weather();

		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof Div) {
				Div div = (Div) node;
				String className = div.getAttribute("class");
				if (className != null) {
					if (className.equals("padbottom")) {
						if (div.getFirstChild() instanceof Div) {
							weather.setContent(div.toPlainTextString().replace(
									"   ", "\r\n").trim());
							log.debug("content:" + weather.getContent());
						}
					} else if (className.equals("section")
							|| className.equals("module_open")) {
						weather.setCity(div.toPlainTextString().trim().replace(
								"市天气", "").replace("县天气", "")
								.replace("省天气", ""));
						log.debug("city:" + weather.getCity());
					} else if (className.equals("center small padtop")
							|| className.equals("small padtop")) {
						weather.setDesc(div.toPlainTextString().replace(
								"&#0169;2010 -", "").replace("隐私权", "").trim());
					}
				}

			}
		}
		if (weather.getCity() == null)
			weather.setCity(loc);
		if (weather.checkComplete()) {
			weather.setUdate(new Date());
			return weather;
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String data = fetchData("http://www.google.cn/m?loc=wuxi&site=weather");
		Weather weather = parserWeather(data, "无锡");
		if (weather == null)
			System.out.println("null");
		else {
			System.out.println("city:" + weather.getCity());
			System.out.println("content:" + weather.getContent());
			System.out.println("desc:" + weather.getDesc());
		}
	}
}
