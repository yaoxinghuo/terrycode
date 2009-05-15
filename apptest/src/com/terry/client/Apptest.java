package com.terry.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Apptest implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private Viewport viewport;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		MyConstants constants = GWT.create(MyConstants.class);
		final Button sendButton = new Button(constants.btSend());
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML(constants.reply()));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				sendButton.setEnabled(false);
				String textToServer = nameField.getText();
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				ServiceDefTarget endpoint = (ServiceDefTarget) greetingService;
				endpoint.setServiceEntryPoint("gwt-test.action");
				greetingService.test(textToServer, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						MyMessages message = GWT.create(MyMessages.class);
						serverResponseLabel.setHTML(message.reply(result));
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);

		

		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());

		createNorth();
		createWest();
		createCenter();
		
		RootPanel.get().add(viewport);

	}

	private void createNorth() {
		StringBuffer sb = new StringBuffer();
		sb
				.append("<div id='demo-header' class='x-small-editor'><div id='demo-theme'></div><div id=demo-title>Ext GWT Examples</div></div>");

		HtmlContainer northPanel = new HtmlContainer(sb.toString());
		northPanel.setEnableState(false);

		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,
				33);
		northData.setMargins(new Margins(0,2,0,0));
		viewport.add(northPanel, northData);
	}

	private void createWest() {
		ContentPanel panel = new ContentPanel();
		panel.setHeading("AccordionLayout");
		panel.setBodyBorder(false);

		panel.setLayout(new AccordionLayout());
		panel.setIconStyle("icon-accordion");

		ContentPanel cp = new ContentPanel();
		cp.setHeading("Online Users");
		cp.setScrollMode(Scroll.AUTO);
		panel.add(cp);

		Tree tree = new Tree();
		TreeItem family = new TreeItem("Family");
		tree.getRootItem().add(family);
		family.add(newItem("Darrell", "user"));
		family.add(newItem("Maro", "user-girl"));
		family.add(newItem("Lia", "user-kid"));
		family.add(newItem("Alec", "user-kid"));
		family.add(newItem("Andrew", "user-kid"));
		family.setExpanded(true);

		TreeItem friends = new TreeItem("Friends");
		tree.getRootItem().add(friends);
		friends.add(newItem("Bob", "user"));
		friends.add(newItem("Mary", "user-girl"));
		friends.add(newItem("Sally", "user-girl"));
		friends.add(newItem("Jack", "user"));
		friends.setExpanded(true);

		cp.add(tree);

		cp = new ContentPanel();
		cp.setBodyStyleName("pad-text");
		cp.setHeading("Settings");
		cp.addText("Test3");
		panel.add(cp);

		cp = new ContentPanel();
		cp.setBodyStyleName("pad-text");
		cp.setHeading("Stuff");
		cp.addText("Test2");
		panel.add(cp);

		cp = new ContentPanel();
		cp.setBodyStyleName("pad-text");
		cp.setHeading("More Stuff");
		cp.addText("Test1");
		panel.add(cp);
		panel.setWidth(200);

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200);
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins(0,5,0,0));

		viewport.add(panel, westData);

	}

	private TreeItem newItem(String text, String iconStyle) {
		TreeItem item = new TreeItem(text);
		item.setIconStyle(iconStyle);
		return item;
	}
	
	@SuppressWarnings("unchecked")
	private void createCenter(){
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Company");
		column.setDataIndex("name");
		column.setWidth(200);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("symbol");
		column.setHeader("Symbol");
		column.setDataIndex("symbol");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("id");
		column.setHeader("No");
		column.setDataIndex("id");
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setWidth(75);

		// create Store //
		// data struction //
		ModelType mt = new ModelType();
		mt.setRoot("rows");
		mt.setTotalName("results");
		mt.addField("id");
		mt.addField("name");
		mt.addField("symbol");

		// --------------- //
		ListStore<ModelData> store = DataStruction.JsonStoreCreatePaginate(
				"ds01", mt, "/ajax/grid.action", "id"); // this will register
		// into ds01

		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeaderVisible(true);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		Grid<ModelData> grid = new Grid<ModelData>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setAutoExpandColumn("name");
		cp.add(grid);
		PagingToolBar toolBar = new PagingToolBar(20);
		toolBar.bind((BasePagingLoader<PagingLoadResult<ModelData>>) store
				.getLoader());
		cp.setBottomComponent(toolBar);
		
		TabPanel tp = new TabPanel();
		tp.setBodyBorder(false);
		TabItem item = new TabItem();  
	    item.setText("Grid Tab"); 
	    item.setScrollMode(Scroll.AUTO);
	    item.add(cp);
	    tp.add(item);
		
		viewport.add(tp, new BorderLayoutData(LayoutRegion.CENTER));
	}
}
