package com.terrynow.saetest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.terrynow.saetest.util.StringUtil;
import com.terrynow.saetest.weather.Weather;
import com.terrynow.saetest.weather.WeatherFetcher;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-6-12 下午04:22:52
 */
public class WeatherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6073258583783342101L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain;charset=UTF-8");
		String city = req.getParameter("city");
		Weather w = null;
		if (!StringUtil.isEmptyOrWhitespace(city))
			w = WeatherFetcher.fetchWeather(city);

		JSONObject jo = new JSONObject();
		try {
			jo.put("result", w == null ? false : true);
			if (w != null) {
				jo.put("city", w.getCity());
				jo.put("content", w.getContent());
				jo.put("desc", w.getDesc());
			}
		} catch (JSONException e) {
		}
		PrintWriter out = resp.getWriter();
		out.print(jo.toString());
		out.flush();
	}

}
