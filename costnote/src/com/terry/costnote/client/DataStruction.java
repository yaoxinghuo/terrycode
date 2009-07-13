package com.terry.costnote.client;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.HttpProxy;
import com.extjs.gxt.ui.client.data.JsonLoadResultReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
//import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
//import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
//import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.http.client.RequestBuilder;
//import com.google.gwt.user.client.Window;

public class DataStruction {
	static public ListStore<ModelData> JsonStoreCreatePaginate(String symbol,
			ModelType mt, String url, String sordField) {
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
		JsonLoadResultReader<PagingLoadResult<ModelData>> reader = new JsonLoadResultReader<PagingLoadResult<ModelData>>(
				mt) {
			@Override
			protected ListLoadResult<ModelData> newLoadResult(
					Object loadConfig, List<ModelData> models) {
				PagingLoadConfig pagingConfig = (PagingLoadConfig) loadConfig;
				PagingLoadResult<ModelData> result = new BasePagingLoadResult<ModelData>(
						models, pagingConfig.getOffset(), pagingConfig
								.getLimit());
				return result;
			}
		};
		BasePagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(
				proxy, reader);

//		loader.addLoadListener(new LoadListener() {
//			@Override
//			public void loaderBeforeLoad(LoadEvent le) {
//				Info.display("JData", "Staring Loading....");
//			}
//
//			@Override
//			public void loaderLoad(LoadEvent le) {
//				Info.display("JData", "Loading complete!");
//			}
//
//			@Override
//			public void loaderLoadException(LoadEvent le) {
//				Info.display("JData", "Loading Error!"
//						+ le.exception.getMessage());
//				Window.alert(le.exception.getMessage());
//			}
//		});

		loader.setSortField(sordField);
		loader.setRemoteSort(true);
		ListStore<ModelData> store = new ListStore<ModelData>(loader);
		Registry.register(symbol, store);
		loader.load(0, 20); // load Store
		return store;
	}
}
