package com.terry.weatherlib;

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
		if (cache != null) {
			Object o = cache.get(Constants.DEFAULT_WEATHER_CACHE_NAME
					+ "-"
					+ Pinyin4j
							.cn2Spell(loc.replaceAll("\\s", "").toLowerCase()));
			if (o != null && o instanceof Weather) {
				Weather weather = (Weather) o;
				if (weather != null
						&& System.currentTimeMillis()
								- weather.getUdate().getTime() < Constants.DEFAULT_WEATHER_CACHE_TIME * 1000) {
					return weather;
				}
			}
		}
		Weather w = WeatherFetcher.fetchWeather(loc);
		if (w != null && cache != null) {
			cache.put(Constants.DEFAULT_WEATHER_CACHE_NAME + "-"
					+ Pinyin4j.cn2Spell(w.getCity()), w);
		}
		return w;
	}
}
