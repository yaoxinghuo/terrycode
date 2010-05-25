package com.terry.weatherlib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.terry.weatherlib.util.Constants;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-24 下午12:20:15
 */
public class WeatherCache {

	private static Cache cache;

	private static Log log = LogFactory.getLog(WeatherCache.class);

	private static MemcacheService cacheService = MemcacheServiceFactory
			.getMemcacheService();

	static {
		Map<Integer, Integer> props = new HashMap<Integer, Integer>();
		props.put(GCacheFactory.EXPIRATION_DELTA,
				Constants.DEFAULT_WEATHER_CACHE_TIME);
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					props);
		} catch (CacheException e) {
		}
	}

	@SuppressWarnings("unchecked")
	public static Weather queryWeather(String loc, String suffix) {
		String key = Constants.DEFAULT_WEATHER_CACHE_NAME + "-"
				+ (suffix == null ? loc : suffix);
		if (cache != null) {
			Object o = cache.get(key);
			if (o != null && o instanceof Weather) {
				return (Weather) o;
			}
		}

		Weather w = WeatherFetcher.fetchWeather(loc);
		if (w != null && cache != null) {
			cacheService.put(key, w, getExpiration());

			// Add weather cache name to cache.
			ArrayList<String> names = null;
			Object o = cache.get(Constants.DEFAULT_CACHE_CACHE_NAME);
			if (o != null && o instanceof ArrayList<?>)
				names = (ArrayList<String>) o;
			else
				names = new ArrayList<String>();
			names.add(key);
			cache.put(Constants.DEFAULT_CACHE_CACHE_NAME, names);
		}
		return w;
	}

	public static Weather queryWeather(String loc) {
		return queryWeather(loc, null);
	}

	/*
	 * http://wap.weather.com.cn/wap的天气预报每天8:00 11:00 17:00更新一次，还有凌晨也要手动更新一次
	 * 
	 * 所以要得到Cache的保存时间
	 */
	public static Expiration getExpiration() {
		long now = System.currentTimeMillis();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(now);
		c.set(Calendar.HOUR_OF_DAY, 0);// 8:00左右
		c.set(Calendar.MINUTE, 10);
		boolean add = false;
		if (c.getTimeInMillis() <= now) {
			c.add(Calendar.DAY_OF_YEAR, 1);
			add = true;
		}
		long x = c.getTimeInMillis();

		if (add) {
			c.add(Calendar.DAY_OF_YEAR, -1);
			add = false;
		}
		c.set(Calendar.HOUR_OF_DAY, 3);// 11:00左右
		c.set(Calendar.MINUTE, 10);
		if (c.getTimeInMillis() <= now) {
			c.add(Calendar.DAY_OF_YEAR, 1);
			add = true;
		}
		long y = c.getTimeInMillis();

		if (add) {
			c.add(Calendar.DAY_OF_YEAR, -1);
			add = false;
		}
		c.set(Calendar.HOUR_OF_DAY, 9);// 17:00左右
		c.set(Calendar.MINUTE, 10);
		if (c.getTimeInMillis() <= now)
			c.add(Calendar.DAY_OF_YEAR, 1);
		long z = c.getTimeInMillis();

		if (add) {
			c.add(Calendar.DAY_OF_YEAR, -1);
			add = false;
		}
		c.set(Calendar.HOUR_OF_DAY, 16);// 24:00左右
		c.set(Calendar.MINUTE, 10);
		if (c.getTimeInMillis() <= now)
			c.add(Calendar.DAY_OF_YEAR, 1);
		long u = c.getTimeInMillis();

		Date minDate = new Date(min(x, y, z, u));
		log.debug("minDate:" + minDate.toString());
		return Expiration.onDate(minDate);
	}

	private static long min(long... data) {
		Long temp = null;
		for (long l : data) {
			if (temp == null)
				temp = l;
			else
				temp = Math.min(temp, l);
		}
		return temp;
	}
}
