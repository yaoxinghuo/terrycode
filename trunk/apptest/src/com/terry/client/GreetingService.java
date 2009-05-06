package com.terry.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface GreetingService extends RemoteService {
	String test(String name);
}
