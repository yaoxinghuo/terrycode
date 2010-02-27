package com.terry.weatherlib;

import java.util.ArrayList;
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
				return (Weather) o;
			}
		}

		Weather w = WeatherFetcher.fetchWeather(loc);
		if (w != null && cache != null) {
			String name = Constants.DEFAULT_WEATHER_CACHE_NAME + "-"
					+ Pinyin4j.cn2Spell(w.getCity());
			cache.put(name, w);

			// Add weather cache name to cache.
			ArrayList<String> names = null;
			Object o = cache.get(Constants.DEFAULT_CACHE_CACHE_NAME);
			if (o != null && o instanceof ArrayList<?>)
				names = (ArrayList<String>) o;
			else
				names = new ArrayList<String>();
			names.add(name);
			cache.put(Constants.DEFAULT_CACHE_CACHE_NAME, names);
		}
		return w;
	}
}
