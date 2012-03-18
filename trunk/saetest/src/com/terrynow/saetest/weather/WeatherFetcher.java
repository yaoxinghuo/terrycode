package com.terrynow.saetest.weather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sina.sae.fetchurl.SaeFetchurl;
import com.terrynow.saetest.data.impl.ScheduleDaoImpl;
import com.terrynow.saetest.data.intf.IScheduleDao;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-23 下午05:20:54
 */
public class WeatherFetcher {
	private static Logger log = Logger.getLogger(WeatherFetcher.class);

	private static final String SEARCH_URL = "http://search.weather.com.cn/wap/search.php";
	private static final String WEATHER_URL = "http://m.weather.com.cn/data/{cityId}.html";
	private static final String WEATHER_SK_URL = "http://www.weather.com.cn/data/sk/{cityId}.html";

	private static SimpleDateFormat sdf = new SimpleDateFormat("d日",
			Locale.CHINA);
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年M月d日",
			Locale.CHINA);

	private static IScheduleDao scheduleDao = new ScheduleDaoImpl();

	static {
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		sdf2.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
	}

	public static Weather fetchWeather(String loc) {
		if (loc.endsWith("市") && loc.length() > 1) {
			loc = loc.substring(0, loc.length() - 1);
		}
		Weather weather = null;
		String redirectURL = scheduleDao.getCityData(loc);
		if (redirectURL == null) {
			SaeFetchurl fetchUrl = new SaeFetchurl();
			fetchUrl.setMethod("post");
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("city", loc);
			fetchUrl.setPostData(maps);
			String result = fetchUrl.fetch(SEARCH_URL);
			try {
				Document doc = Jsoup.parse(result);
				String data = doc.head().html();
				log.debug("fetch data:" + loc + ": " + data);
				Pattern p = Pattern.compile("\"([^\"]*)\"",
						Pattern.CASE_INSENSITIVE);
				Matcher matcher = p.matcher(data);
				if (matcher.find()) {
					redirectURL = matcher.group(1);
					if (redirectURL.endsWith("101010100.shtml")) {// 默认找不到都是写是北京
						if (!loc.contains("北京") && !loc.contains("beijing")) {
							return null;
						}
					}
					weather = parserWeather(redirectURL, loc);
					if (weather != null)
						scheduleDao.saveCityData(loc, redirectURL);
				} else
					return null;
			} catch (Exception e) {
				return null;
			}
		} else {
			weather = parserWeather(redirectURL, loc);
		}
		return weather;
	}

	public static Weather parserWeather(String data, String defaultCity) {
		try {
			String cityId = parserCityId(data);
			String html = new SaeFetchurl().fetch(WEATHER_URL.replace(
					"{cityId}", cityId));
			if (html == null)
				return null;

			String sd = null;
			try {
				String html2 = new SaeFetchurl().fetch(WEATHER_SK_URL.replace(
						"{cityId}", cityId));
				if (html2 != null) {
					JSONObject sk = new JSONObject(html2)
							.getJSONObject("weatherinfo");
					sd = sk.optString("SD", null);
					if (sd != null && !sd.contains("%"))
						sd = null;
					if (sd != null) {
						sd = sk.optString("time", "") + "湿度" + sd;
					}
				}
			} catch (Exception e) {
			}

			JSONObject wi = new JSONObject(html).getJSONObject("weatherinfo");
			Weather weather = new Weather();
			weather.setCity(wi.optString("city", defaultCity));
			int h = 8;
			try {
				h = Integer.parseInt(wi.optString("fchh", "08"));
			} catch (NumberFormatException e) {
			}
			Calendar calendar = Calendar.getInstance(
					TimeZone.getTimeZone("GMT+08:00"), Locale.CHINA);
			int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
			String dateY = wi.optString("date_y", "");
			String s = null;
			String desc = dateY + " " + wi.optString("fchh", "08") + "时发布";
			if (h >= 18) {
				if (nowHour < 12) {
					desc = sdf2.format(calendar.getTime()) + " 0时发布";
					s = parseWeatherContentMorning(calendar, wi, sd);
				} else
					s = parseWeatherContentEvening(calendar, wi, sd);
			} else
				s = parseWeatherContent(calendar, wi, sd);
			weather.setDesc(desc);
			if (s != null && s.length() != 0)
				weather.setContent(s);
			if (weather.checkComplete()) {
				weather.setUdate(new Date());
				return weather;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	private static String parseWeatherContent(Calendar calendar, JSONObject wi,
			String sd) {
		StringBuffer content = new StringBuffer();
		for (int i = 1; i <= 6; i++) {
			if (i > 1) {
				calendar.add(Calendar.DAY_OF_WEEK, 1);
				content.append("\r\n");
				if (i == 2)
					content.append("明天 ");
				else if (i == 3)
					content.append(
							getWeekName(calendar.get(Calendar.DAY_OF_WEEK)))
							.append(" ");
				else
					content.append(sdf.format(calendar.getTime())).append(" ");
			} else {
				content.append("今天 ");
			}
			content.append(wi.optString("weather" + i)).append(" ")
					.append(wi.optString("temp" + i)).append(" ")
					.append(wi.optString("wind" + i));
			if (i == 1 && sd != null)
				content.append(" ").append(sd);
		}
		return content.toString().trim();
	}

	private static String parseWeatherContentEvening(Calendar calendar,
			JSONObject wi, String sd) {
		StringBuffer content = new StringBuffer();
		String temp = null;
		Wind wind = null;
		String wea = null;
		for (int i = 1; i <= 6; i++) {
			if (i > 1) {
				calendar.add(Calendar.DAY_OF_WEEK, 1);
				content.append("\r\n");
				if (i == 2)
					content.append("明天 ");
				else if (i == 3)
					content.append(
							getWeekName(calendar.get(Calendar.DAY_OF_WEEK)))
							.append(" ");
				else
					content.append(sdf.format(calendar.getTime())).append(" ");
			} else {
				content.append("今晚 ");
			}
			String[] weaParts = wi.optString("weather" + i).split("转", 2);
			String[] tempParts = wi.optString("temp" + i, "").split("~", 2);
			Wind[] windParts = parseWind(wi.optString("wind" + i));

			if (wea == null)
				content.append(weaParts[0]);
			else {
				content.append(wea);
				if (!wea.equals(weaParts[0]))
					content.append("转").append(weaParts[0]);
			}
			wea = weaParts[weaParts.length - 1];
			content.append(" ");

			if (temp == null)
				content.append(tempParts[0]);
			else {
				content.append(temp);
				if (!temp.equals(tempParts[0]))
					content.append("~").append(tempParts[0]);
			}
			temp = tempParts[tempParts.length - 1];
			content.append(" ");

			if (wind == null)
				content.append(windParts[0].toWindStr());
			else {
				content.append(wind.toWindStr());
				if (windParts[0].toWindStr() != null
						&& windParts[0].toWindStr().length() > 0
						&& !wind.toWindStr().equals(windParts[0].toWindStr())) {
					content.append("转");
					if (windParts[0].toWind().equals(wind.toWind()))
						content.append(windParts[0].toPower());
					else
						content.append(windParts[0].toWindStr());
					if (content.lastIndexOf("转") == content.length() - 1)
						content.deleteCharAt(content.length() - 1);
				}
			}
			wind = windParts[windParts.length - 1];

			if (i == 1 && sd != null)
				content.append(" ").append(sd);
		}
		return content.toString().trim();
	}

	private static Wind[] parseWind(String str) {
		if (!str.contains("转"))
			str = str + "转" + str;
		String[] ss = str.split("转", 2);

		Wind wind1 = new Wind(ss[0]);
		Wind wind2 = new Wind(ss[1]);
		if (wind1.getWind() == null && wind2.getWind() != null)
			wind1.setWind(wind2.getWind());
		if (wind2.getWind() == null && wind1.getWind() != null)
			wind2.setWind(wind1.getWind());
		// if (wind1.getPower() == null && wind2.getPower() != null)
		// wind1.setPower(wind2.getPower());
		// if (wind2.getPower() == null && wind1.getPower() != null)
		// wind2.setPower(wind1.getPower());

		return new Wind[] { wind1, wind2 };
	}

	private static String parseWeatherContentMorning(Calendar calendar,
			JSONObject wi, String sd) {
		StringBuffer content = new StringBuffer();
		String temp = null;
		Wind wind = null;
		String wea = null;
		for (int i = 1; i <= 6; i++) {
			if (i > 1) {
				if (i > 2)
					calendar.add(Calendar.DAY_OF_WEEK, 1);
				content.append("\r\n");
				if (i == 2)
					content.append("今天 ");
				else if (i == 3)
					content.append("明天 ");
				else if (i == 4)
					content.append(
							getWeekName(calendar.get(Calendar.DAY_OF_WEEK)))
							.append(" ");
				else
					content.append(sdf.format(calendar.getTime())).append(" ");
			} else {
				// content.append("今晚 ");
			}
			String[] weaParts = wi.optString("weather" + i).split("转", 2);
			String[] tempParts = wi.optString("temp" + i, "").split("~", 2);
			Wind[] windParts = parseWind(wi.optString("wind" + i));

			if (wea == null) {
				// content.append(weaParts[0]);
			} else {
				content.append(wea);
				if (!wea.equals(weaParts[0]))
					content.append("转").append(weaParts[0]);
			}
			wea = weaParts[weaParts.length - 1];
			content.append(" ");

			if (temp == null) {
				// content.append(tempParts[0]);
			} else {
				content.append(temp);
				if (!temp.equals(tempParts[0]))
					content.append("~").append(tempParts[0]);
			}
			temp = tempParts[tempParts.length - 1];
			content.append(" ");

			if (wind == null) {
				// content.append(windParts[0].toWindStr());
			} else {
				content.append(wind.toWindStr());
				if (windParts[0].toWindStr() != null
						&& windParts[0].toWindStr().length() > 0
						&& !wind.toWindStr().equals(windParts[0].toWindStr())) {
					content.append("转");
					if (windParts[0].toWind().equals(wind.toWind()))
						content.append(windParts[0].toPower());
					else
						content.append(windParts[0].toWindStr());
					if (content.lastIndexOf("转") == content.length() - 1)
						content.deleteCharAt(content.length() - 1);
				}
			}
			wind = windParts[windParts.length - 1];

			if (i == 2 && sd != null)
				content.append(" ").append(sd);
		}
		return content.toString().trim();
	}

	/*
	 * date 格式要是类似5月1日
	 */
	private static String getWeekName(int dayOfWeek) {
		switch (dayOfWeek) {
		case 1:
			return "周日";
		case 2:
			return "周一";
		case 3:
			return "周二";
		case 4:
			return "周三";
		case 5:
			return "周四";
		case 6:
			return "周五";
		case 7:
			return "周六";
		default:
			return "";
		}
	}

	private static String parserCityId(String url) {
		// http://wap.weather.com.cn/wap/weather/101240401.shtml
		int last1 = url.lastIndexOf("/");
		int last2 = url.lastIndexOf(".");
		if (last1 != -1 && last2 > last1)
			return url.substring(last1 + 1, last2);
		return url;
	}

	public static void main(String[] args) throws Exception {
		Weather w = WeatherFetcher.fetchWeather("上海");
		if (w != null)
			System.out.println(w.getReport());
	}
}
