package com.taobao.test;

import org.json.JSONObject;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoJsonRestClient;
import com.taobao.api.model.ProductSearchRequest;
import com.taobao.api.model.ProductSearchResponse;
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

		productSearchTest(client);
//		userDetailTest(client);
	}

	public static void productSearchTest(TaobaoJsonRestClient client)
			throws Exception {
		ProductSearchRequest request = new ProductSearchRequest();
		request
				.setFields("product_id,name,pic_path,cid,props,price,modified,tsc"); // 返回的具体Prodcut字段信息,注意产品搜索时,返回的并不是全部的Product字段
		request.setQ("诺基亚"); // 搜索的关键字.也可以用cid，props来搜索
		request.setPageNo(1);
		request.setPageSize(5);
		ProductSearchResponse response = client.productSearch(request);
		System.out.println(response.getBody()); // 输出响应的信息
	}

	public static void userDetailTest(TaobaoJsonRestClient client)
			throws Exception {
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
