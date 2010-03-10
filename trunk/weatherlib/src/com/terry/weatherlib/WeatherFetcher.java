package com.terry.weatherlib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-23 下午05:20:54
 */
public class WeatherFetcher {

	private static Log log = LogFactory.getLog(WeatherFetcher.class);

	public static Weather fetchWeather(String loc) {
		if (loc.lastIndexOf("市") == loc.length() - 1)
			loc = loc.substring(0, loc.length() - 1);
		String unicodeLoc = null;
		try {
			unicodeLoc = URLEncoder.encode(loc, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		String data = fetchData("http://www.google.cn/m?q=" + unicodeLoc
				+ "&site=weather");
		log.debug("fetch data:" + loc + data);
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
		Parser parser = Parser.createParser(html.replace("<br/>", "\r\n")
				.replace("&nbsp;", " "), "utf8");

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
				if (className == null
						&& div.getFirstChild() instanceof ImageTag) {
					weather.setContent(div.toPlainTextString().trim());
					log.debug("content:" + weather.getContent());
				}
				if (className != null) {
					if (className.equals("b")) {
						weather.setCity(div.toPlainTextString().trim().replace(
								"天气", ""));
						log.debug("city:" + weather.getCity());
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
//		Weather w = WeatherFetcher.fetchWeather("上海");
//		if (w != null)
//			System.out.println(w.getReport());
		
		 SimpleDateFormat sdf2 = new SimpleDateFormat(
				"M月d日H:mm", Locale.CHINA);
		System.out.println(sdf2.format(new Date()));
	}
}
