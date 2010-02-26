package com.terry.weatherlib;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.terry.weatherlib.util.Constants;
import com.terry.weatherlib.util.Pinyin4j;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-24 下午12:20:15
 */
public class WeatherCache {

	private static Cache cache;

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
	public static Weather queryWeather(String loc) {
		Weather w = null;
		if (cache != null) {
			Object o = cache.get(Constants.DEFAULT_WEATHER_CACHE_NAME
					+ "-"
					+ Pinyin4j
							.cn2Spell(loc.replaceAll("\\s", "").toLowerCase()));
			if (o != null && o instanceof Weather) {
				w = (Weather) o;
				if (w != null) {
					// 北京气象台Google每天8:00 17:00更新一次天气，太平洋时间0:00 09:00
					// 如果上次更新少于这两个时间点，就不要用缓存数据了
					Calendar uc = Calendar.getInstance();
					uc.setTime(w.getUdate());
					int ud = uc.get(Calendar.HOUR_OF_DAY);
					Calendar nc = Calendar.getInstance();
					int nd = nc.get(Calendar.HOUR_OF_DAY);
					if (nd >= 9 && ud < 9)
						w = null;
					if (nd >= 0 && nd < 9) {
						if (!(ud >= 0 && ud < 9))
							w = null;
					}
				}
			}
		}
		if (w == null) {// If weather do not from cache, then fetch it from
			// google
			w = WeatherFetcher.fetchWeather(loc);
			if (w != null && cache != null) {
				cache.put(Constants.DEFAULT_WEATHER_CACHE_NAME + "-"
						+ Pinyin4j.cn2Spell(w.getCity()), w);
			}
		}
		return w;
	}
}
