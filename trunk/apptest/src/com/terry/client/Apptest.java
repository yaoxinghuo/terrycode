package com.terry.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreeEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

public class Apptest implements EntryPoint {
	private Viewport viewport;
	private TabPanel tp = new TabPanel();

	public void onModuleLoad() {
		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());

		createNorth();
		createWest();
		createCenter();

		RootPanel.get().add(viewport);

		showPopMessage("Welcome");

	}

	private void createNorth() {
		HtmlContainer northPanel = new HtmlContainer();
		Element ele = DOM.getElementById("north");
		DOM.setStyleAttribute(ele, "visibility", "visible");
		northPanel.setElement(ele);
		northPanel.setEnableState(false);

		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,
				53);
		northData.setMargins(new Margins(0, 2, 0, 0));
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
		tree.addListener(Events.SelectionChange, new Listener<TreeEvent>() {

			@Override
			public void handleEvent(TreeEvent te) {
				TreeItem treeItem = te.getTree().getSelectedItem();
				Object object = treeItem.getData("tab_id");
				if (object == null)
					return;
				String tab_id = object.toString();
				TabItem tabItem = tp.getItemByItemId(tab_id);
				if (tabItem == null) {
					showPopMessage("<font color='red'>TabItem is null</font>, create one, TabItem id is: "
							+ tab_id);
					tabItem = new TabItem(treeItem.getText());
					tabItem.setItemId(tab_id);
					tabItem.setClosable(true);
					tabItem
							.addText("Tab Test Content, <br/><font color='red'>TabItem id is: "
									+ tab_id + "</font>");
					tp.add(tabItem);
				} else {
					showPopMessage("TabItem exist, TabItem id is: " + tab_id);
					tabItem.show();
				}
				tp.setSelection(tabItem);
			}

		});
		TreeItem family = new TreeItem("Family");
		tree.getRootItem().add(family);
		family.add(newItem("Darrell", "feed"));
		family.add(newItem("Maro", "feed"));
		family.add(newItem("Lia", "feed"));
		family.add(newItem("Alec", "feed"));
		family.add(newItem("Andrew", "feed"));
		family.setExpanded(true);

		TreeItem friends = new TreeItem("Friends");
		tree.getRootItem().add(friends);
		friends.add(newItem("Bob", "feed"));
		friends.add(newItem("Mary", "feed"));
		friends.add(newItem("Sally", "feed"));
		friends.add(newItem("Jack", "feed"));
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
		westData.setMargins(new Margins(0, 5, 0, 0));

		viewport.add(panel, westData);

	}

	private TreeItem newItem(String text, String iconStyle) {
		TreeItem item = new TreeItem(text);
		item.setData("tab_id", text + "_id");
		item.setIconStyle(iconStyle);
		return item;
	}

	@SuppressWarnings("unchecked")
	private void createCenter() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		RowNumberer r = new RowNumberer();
		configs.add(r);

		CheckBoxSelectionModel<ModelData> sm = new CheckBoxSelectionModel<ModelData>();
		sm.setSelectionMode(SelectionMode.MULTI);
		configs.add(sm.getColumn());

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

		ColumnModel cm = new ColumnModel(configs);

		// --------------- //
		ListStore<ModelData> store = DataStruction.JsonStoreCreatePaginate(
				"ds01", mt, "/ajax/grid.action", "id"); // this will register
		// into ds01

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		Grid<ModelData> grid = new Grid<ModelData>(store, cm);
		grid.setSelectionModel(sm);
		grid.addPlugin(sm);
		grid.setAutoExpandColumn("name");
		grid.setHeight(480);
		grid.setLoadMask(true);
		Menu menu = new Menu();
		MenuItem mi = new MenuItem("Test", "feed",
				new SelectionListener<MenuEvent>() {

					@Override
					public void componentSelected(MenuEvent ce) {
						showPopMessage("You have clicked.");
					}

				});
		menu.add(mi);
		menu.add(new MenuItem("Test2", new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {
				MessageBox.alert("Test Title", "You have clicked!", null);
			}

		}));
		grid.setContextMenu(menu);
		cp.add(grid);
		ToolBar tb = new ToolBar();
		tb.add(new IconButton("feed"));
		tb.add(new ToolButton("x-tool-gear"));
		tb.add(new ToolButton("x-tool-close"));
		cp.setTopComponent(tb);
		PagingToolBar toolBar = new PagingToolBar(20);
		toolBar.bind((BasePagingLoader<PagingLoadResult<ModelData>>) store
				.getLoader());
		cp.setBottomComponent(toolBar);

		TabItem item = new TabItem();
		item.setId("tab_grid");
		item.setText("Grid Tab");
		item.setScrollMode(Scroll.AUTO);
		item.add(cp);
		tp.add(item);

		viewport.add(tp, new BorderLayoutData(LayoutRegion.CENTER));
	}

	public static void showPopMessage(String message) {
		DOM.getElementById("msg_content").setInnerHTML(message);
		DOM.setStyleAttribute(DOM.getElementById("msg"), "visibility",
				"visible");
	}

}
