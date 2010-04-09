package com.terry.weatherlib.util;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import com.terry.weatherlib.Weather;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Mar 5, 2010 8:24:39 PM
 */
public class WeatherMailSender {
	private static final String HELP = "\r\n管理订阅请登录http://www.tianqiyubao.org.ru/\r\n请勿直接回复";

	private static SimpleDateFormat sdf2 = new SimpleDateFormat("M月d日H:mm",
			Locale.CHINA);
	
	static {
		sdf2.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
	}

	public static boolean sendWeatherMail(Weather weather, String email,
			int type, String nickname, boolean fetch) {
		String subject = null;
		String content = null;
		if (type == 2) {
			subject = weather.getCity()
					+ weather.getContent().replace("\r\n", " ");
			content = "如题。" + HELP;
		} else {
			subject = weather.getCity() + "天气预报--" + "谷歌天气"
					+ sdf2.format(weather.getUdate()) + "更新";
			content = weather.getReport() + HELP;
		}
		if (fetch) {
			return MailSender
					.fetchToSendMail(email, nickname, subject, content);
		}
		try {
			MailSender.sendMail(email, nickname, subject, content);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean sendWeatherMail(Weather weather, String email,
			int type, String nickname) {
		return sendWeatherMail(weather, email, type, nickname, false);
	}
}
