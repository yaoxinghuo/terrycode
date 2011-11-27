package com.terry.test.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt implements EntryPoint {

	private Viewport viewport;

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {

		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		createNorth();
		createWest();

		TabPanel panel = new TabPanel();
		panel.setResizeTabs(true);
		TabItem example = new TabItem("Example");
		example.setScrollMode(Scroll.AUTO);

		TabItem source = new TabItem("View Source");

		panel.add(example);
		panel.add(source);

		ToolBar toolBar = new ToolBar();
		Button item = new Button("View Source");

		toolBar.add(new FillToolItem());
		toolBar.add(item);
		viewport.add(panel, new BorderLayoutData(LayoutRegion.CENTER));

		RootPanel.get().add(viewport);

//		Window w = new Window();
//		w.setHeading("Product Information");
//		w.setModal(true);
//		w.setSize(600, 400);
//		w.setMaximizable(true);
//		w.setToolTip("The ExtGWT product page...");
//		w.setUrl("http://www.extjs.com/products/gxt");
//		w.show();
		
		RequestBuilder br = new RequestBuilder(RequestBuilder.GET, null);
		try {
			br.sendRequest(null, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					
				}
			});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createNorth() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='demo-header' class='x-small-editor'><div id='demo-theme'></div><div id=demo-title>Ext GWT Examples</div></div>");

		HtmlContainer northPanel = new HtmlContainer(sb.toString());
		northPanel.setStateful(false);

		BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 33);
		data.setMargins(new Margins());
		viewport.add(northPanel, data);
	}

	private void createWest() {
		ContentPanel panel = new ContentPanel();
		panel.setHeading("导航栏");
		panel.setBodyBorder(false);
		panel.setWidth(200);

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200);
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins(0, 5, 0, 0));

		viewport.add(panel, westData);

	}

}
