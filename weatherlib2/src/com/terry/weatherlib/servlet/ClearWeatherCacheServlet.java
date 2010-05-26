package com.terry.weatherlib.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.terry.weatherlib.Weather;
import com.terry.weatherlib.WeatherCache;
import com.terry.weatherlib.WeatherFetcher;
import com.terry.weatherlib.util.Constants;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Feb 27, 2010 8:50:27 AM
 */
public class ClearWeatherCacheServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1883179443719794954L;

	private static Log log = LogFactory.getLog(ClearWeatherCacheServlet.class);

	private static final String testCity = "北京";

	private Cache cache;

	@Override
	public void init() throws ServletException {
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					Collections.emptyMap());
		} catch (CacheException e) {
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		if (!checkShouldClearCache())
			return;

		Object o = cache.get(Constants.DEFAULT_CACHE_CACHE_NAME);
		if (o != null && o instanceof ArrayList<?>) {
			ArrayList<String> names = (ArrayList<String>) o;
			log.debug("remove " + names.size() + " weather cache names.");
			for (String name : names) {
				cache.remove(name);
			}
			cache.put(Constants.DEFAULT_CACHE_CACHE_NAME,
					new ArrayList<String>());
		} else
			cache.clear();
	}

	private boolean checkShouldClearCache() {
		if (cache == null)
			return false;
		long nowTime = System.currentTimeMillis();
		Weather cacheTextWeather = WeatherCache.queryWeather(testCity);
		if (cacheTextWeather == null
				|| cacheTextWeather.getUdate().getTime() >= nowTime)
			return false;// 如果是刚刚从网上取的，就不用检查了

		String hourS = getHour(cacheTextWeather.getDesc());
		if (hourS == null)
			return false;
		int cacheHour = Integer.parseInt(hourS);
		int nowHour = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"),
				Locale.CHINA).get(Calendar.HOUR_OF_DAY);
		if (nowHour >= 0 && nowHour < 8)
			cacheHour = 0;
		if (cacheHour == 0) {
			if (cacheHour < 8)
				return false;
		} else if (cacheHour == 8) {
			if (nowHour < 11)
				return false;
		} else if (cacheHour == 11) {
			if (nowHour < 18)
				return false;
		} else if (cacheHour == 18) {
			if (nowHour <= 23)
				return false;
		}

		Weather nowTestWeather = WeatherFetcher.fetchWeather(testCity);

		/*
		 * 如果从cache取出来的和直接从网上取到的desc（类似2010-05-01 11时发布）不一样，就要清空缓存
		 */
		if (nowTestWeather == null || nowTestWeather.getDesc() == null
				|| nowTestWeather.getDesc().equals(cacheTextWeather.getDesc()))
			return false;
		else
			return true;
	}

	private String getHour(String source) {
		if (source == null)
			return null;
		Pattern p = Pattern.compile("[^ ]* ([0-9]+)时发布");
		Matcher m = p.matcher(source);
		if (m.find())
			return m.group(1);
		return null;
	}

}
