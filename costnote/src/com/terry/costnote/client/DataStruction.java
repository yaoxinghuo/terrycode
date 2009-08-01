package com.terry.costnote.client;

import java.util.Date;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.HttpProxy;
import com.extjs.gxt.ui.client.data.JsonPagingLoadResultReader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.http.client.RequestBuilder;

//import com.google.gwt.user.client.Window;

public class DataStruction {

	static public ListStore<ModelData> JsonStoreCreatePaginate(String symbol,
			ModelType mt, String url, final BasePagingLoadConfig config) {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		HttpProxy<Object> proxy = new HttpProxy<Object>(builder);

		// ------ json decoder -----------//
		// basic load without paginate //
		/*
		 * 如果你不要做分頁就用這個設定就行了。
		 * 
		 * JsonReader<ListLoadResult<ModelData>> reader = new
		 * JsonReader<ListLoadResult<ModelData>>(mt);
		 * 
		 * BaseListLoader<ListLoadResult<ModelData>> loader = new
		 * BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
		 */
		// ------ end json decoder -----------//
		JsonPagingLoadResultReader<ModelData> reader = new JsonPagingLoadResultReader<ModelData>(
				mt);
		BasePagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(
				proxy, reader);

		loader.addLoadListener(new LoadListener() {
			@Override
			public void loaderBeforeLoad(LoadEvent le) {
				config.set("timestamp", new Date().getTime());
			}

			// @Override
			// public void loaderLoad(LoadEvent le) {
			// Info.display("JData", "Loading complete!");
			// }
			//
			// @Override
			// public void loaderLoadException(LoadEvent le) {
			// Info.display("JData", "Loading Error!"
			// + le.exception.getMessage());
			// }
		});

		// loader.setRemoteSort(true);
		ListStore<ModelData> store = new ListStore<ModelData>(loader);
		Registry.register(symbol, store);
		loader.setReuseLoadConfig(true);
		loader.load(config);
		return store;
	}
}
