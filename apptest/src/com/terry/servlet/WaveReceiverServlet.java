package com.terry.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;
import com.terry.robot.logic.FortuneContent;
import com.terry.robot.logic.FortunetellingLogic;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Oct 24, 2009 8:45:13 AM
 */
public class WaveReceiverServlet extends AbstractRobotServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1209131804208251939L;

	private static Log log = LogFactory.getLog(WaveReceiverServlet.class);

	private Cache cache;

	private FortunetellingLogic fortunetellingLogic = new FortunetellingLogic();

	@Override
	public void init() throws ServletException {
		super.init();
		Map<Integer, Integer> CACHE_PROPS = new HashMap<Integer, Integer>();
		CACHE_PROPS.put(GCacheFactory.EXPIRATION_DELTA, 60 * 10);
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					CACHE_PROPS);
		} catch (CacheException e) {
		}
		fortunetellingLogic.setFortunes(FortuneContent.fortunes);
	}

	@Override
	public void processEvents(RobotMessageBundle bundle) {
		log.debug("wave event received...");
		Wavelet wavelet = bundle.getWavelet();

		if (bundle.wasSelfAdded()) {
			Blip blip = wavelet.appendBlip();
			TextView textView = blip.getDocument();
			textView.append("歡迎加我奧，輸入“擲筊”");
		}

		for (Event e : bundle.getEvents()) {
			if (e.getType() == EventType.BLIP_SUBMITTED) {
				Blip blip = e.getBlip();
				String blipCreator = blip.getCreator();
				String text = blip.getDocument().getText();
				// Only process standard blips (i.e. not title blip)
				if (blip.getBlipId().equals(wavelet.getRootBlipId()) == false) {
					String response = fortunetellingProcess(blipCreator, text);
					if (response != null) {
						Blip responseBlip = wavelet.appendBlip();
						TextView textView = responseBlip.getDocument();
						textView.append(response);
					}
				}
			}
		}

	}

	private String fortunetellingProcess(String blipCreator, String body) {
		if (cache == null)
			return null;

		long lastSessionTime = getSessionTime(blipCreator);

		JSONObject jo = null;
		if (lastSessionTime == 0) {
			if (!body.equals(fortunetellingLogic.getAhit())) {
				return null;
			} else {
				jo = fortunetellingLogic.reply(body, 0);
			}
		} else {
			int step = getNextStep(blipCreator);
			jo = fortunetellingLogic.reply(body, step);
		}
		if (jo != null) {
			String reply = jo.getString("response");
			setSessionTime(blipCreator);
			setNextStep(blipCreator, jo.getInt("step"));
			return reply;
		}
		return null;
	}

	private int getNextStep(String blipCreator) {
		Object value = cache.get("_next_step");
		if (value != null && value instanceof Integer)
			return (Integer) value;
		return 0;
	}

	private long getSessionTime(String blipCreator) {
		Object value = cache.get("_session_time");
		if (value != null && value instanceof Long)
			return (Long) value;
		return 0;
	}

	@SuppressWarnings("unchecked")
	private void setNextStep(String blipCreator, int step) {
		cache.put("_next_step", new Integer(step));
	}

	@SuppressWarnings("unchecked")
	private void setSessionTime(String blipCreator) {
		cache.put("_session_time", new Long(System.currentTimeMillis()));
	}

}
