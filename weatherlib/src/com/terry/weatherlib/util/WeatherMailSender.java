package com.terry.weatherlib.util;

import com.terry.weatherlib.Weather;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Mar 5, 2010 8:24:39 PM
 */
public class WeatherMailSender {
	private static final String HELP = "\r\n管理订阅请登录http://tianqiyubao.org.ru/\r\n请勿直接回复";

	public static boolean sendWeatherMail(Weather weather, String email,
			int type, String nickname, boolean fetch) {
		String subject = null;
		String content = null;
		if (type == 2) {
			subject = weather.getCity()
					+ weather.getContent().replace("\r\n", " ");
			content = "如题。" + HELP;
		} else {
			subject = weather.getCity() + "天气预报--" + weather.getDesc();
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
