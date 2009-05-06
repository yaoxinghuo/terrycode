package com.terry.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GreetingServiceAsync {
	void test(String input, AsyncCallback<String> callback);
}
