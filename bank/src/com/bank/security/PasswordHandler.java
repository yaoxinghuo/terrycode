package com.bank.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class PasswordHandler implements CallbackHandler {
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		/*for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[i];
			String id = pwcb.getIdentifer();
			if ("bob".equals(id)) {
				pwcb.setPassword("bobPW");
			}
		}*/
		
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof WSPasswordCallback) {
				WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[i];
				pwcb.setPassword("111111");
			}
		}
	}
}
