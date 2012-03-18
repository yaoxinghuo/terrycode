package com.terrynow.saetest.weather;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2011-3-14 下午03:06:16
 */
public class Wind {
	private String wind;
	private String power;
	private String str;

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public Wind(String str) {
		this.str = str;
		try {
			int windIndex = str.indexOf("风");
			if (windIndex != -1)
				wind = str.substring(0, windIndex + 1);
			int powerIndex = str.indexOf("级");
			if (powerIndex != -1 && powerIndex > windIndex)
				power = str.substring(windIndex + 1, powerIndex + 1);
		} catch (Exception e) {
		}
	}

	public String toWindStr() {
		if (wind == null || power == null)
			return str;
		if (wind == null)
			return power;
		if (power == null)
			return wind;
		return wind + power;
	}
	
	public String toWind() {
		if (wind == null)
			return "";
		return wind;
	}

	public String toPower() {
		if (power == null)
			return "";
		return power;
	}
}
