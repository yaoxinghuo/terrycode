package com.taobao.test;

import org.json.JSONObject;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoJsonRestClient;
import com.taobao.api.model.UserGetRequest;
import com.taobao.api.model.UserGetResponse;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Oct 23, 2009 2:41:05 PM
 */
public class Test {
	public static void main(String[] args) throws Exception {
		TaobaoJsonRestClient client = getClient("12012458",
				"6643e3298d5bb40863fd225824c5f0bf");

		String allFields = "nick,sex,buyer_credit,seller_credit,location.city,location.state,location.country,created,last_visit";
		UserGetRequest userGetRequest = new UserGetRequest();
		userGetRequest.setNick("alipublic18");

		userGetRequest.setFields(allFields);

		UserGetResponse userGetResponse = client.userGet(userGetRequest,
				"1e813efd884dcb4f0858cddba33479587");

		JSONObject jo = new JSONObject(userGetResponse.getBody());
		System.out.println(jo.toString());
		System.out.println(jo.getJSONObject("error_rsp").getString("msg"));
	}

	protected static TaobaoJsonRestClient getClient(String appKey, String secret)
			throws TaobaoApiException {
		return new TaobaoJsonRestClient(
				"http://gw.api.tbsandbox.com/router/rest", appKey, secret);
	}
}
