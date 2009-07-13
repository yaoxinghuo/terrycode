package com.terry.costnote.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author xinghuo.yao yaoxinghuo at 126 dot com
 * @create Apr 17, 2009 3:52:43 PM
 * @description
 */
public interface CostServiceAsync {

	void saveCost(String cost, AsyncCallback<Boolean> callback);

}
