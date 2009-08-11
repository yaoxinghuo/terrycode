package com.terry.gfwout.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import com.terry.gfwout.client.GreetingService;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	private URLFetchService service = URLFetchServiceFactory
			.getURLFetchService();

	public String greetServer(String s) {
		try {
			if (s.startsWith("/"))
				s = s.substring(1);
			if (!s.startsWith("http://") && !s.startsWith("https://"))
				s = "http://" + s;
			URL url = new URL(s);

			HTTPRequest request = new HTTPRequest(url, HTTPMethod.GET);
			HTTPResponse response = service.fetch(request);
			if (response.getResponseCode() == 200) {
				PrintWriter pw = super.getThreadLocalResponse().getWriter();
				pw.write(new String(response.getContent()));
				pw.flush();
				pw.close();
			} else
				return "";

			return "OK";
		} catch (MalformedURLException e) {
			return "Format Error";
		} catch (IOException e) {
			e.printStackTrace();
			return "Exception";
		}
	}
}
