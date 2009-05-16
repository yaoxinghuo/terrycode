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
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
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
		Menu menu = new Menu();
		MenuItem mi = new MenuItem("Test", "feed", new SelectionListener<MenuEvent>(){

			@Override
			public void componentSelected(MenuEvent ce) {
				showPopMessage("You have clicked.");
			}
			
		});
		menu.add(mi);
		menu.add(new MenuItem("Test2"));
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

		TabPanel tp = new TabPanel();
		TabItem item = new TabItem();
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
