package com.terry.costnote.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.axis.XAxis;
import com.extjs.gxt.charts.client.model.axis.YAxis;
import com.extjs.gxt.charts.client.model.charts.BarChart;
import com.extjs.gxt.charts.client.model.charts.CylinderBarChart;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HiddenField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.TimeField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.terry.costnote.client.model.Folder;
import com.terry.costnote.client.model.Item;
import com.terry.costnote.client.model.Type;

public class Costnote implements EntryPoint {
	private static CostServiceAsync costService = GWT.create(CostService.class);
	private static final String operateFail = "对不起，您的操作未能完成，请稍候再试！";
	private static final String operateError = "对不起，数据库维护中，请稍候再试！";
	private static final String operatePass = "您的操作成功完成！";
	private static final String operateWait = "请稍候...";
	private static DateTimeFormat format = DateTimeFormat
			.getFormat("yyyy-MM-dd");
	private static DateTimeFormat timeFormat = DateTimeFormat
			.getFormat("HH:mm");
	private static DateTimeFormat dateTimeFormat = DateTimeFormat
			.getFormat("yyyy-MM-dd HH:mm");
	private Viewport viewport;
	private static TabPanel tp = new TabPanel();
	private static TreePanel<ModelData> tree;
	private static ListStore<ModelData> store;
	private static ListStore<ModelData> scheduleStore;
	private static BasePagingLoadConfig loadConfig = new BasePagingLoadConfig();
	private static BasePagingLoadConfig scheduleLoadConfig = new BasePagingLoadConfig();
	private static ArrayList<String> names = new ArrayList<String>();
	private static JSONObject accountSettings;

	public void onModuleLoad() {
		exportJavaMethod();
		Element ele = DOM.getElementById("loading_div");
		DOM.setStyleAttribute(ele, "visibility", "hidden");

		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());

		createNorth();
		createWest();
		createCenter();

		RootPanel.get().add(viewport);
		tree.expandAll();

		loadAccountInfo();
	}

	private void loadAccountInfo() {
		names.add("用餐");
		names.add("娱乐");
		names.add("交通费");
		for (String s : names) {
			name.add(s);
			new_name.add(s);
		}
		ServiceDefTarget endpoint = (ServiceDefTarget) costService;
		endpoint.setServiceEntryPoint("gwt-cost!getAccountInfo.action");
		costService.getAccountInfo(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				if (result.equals(""))
					return;
				accountSettings = (JSONObject) JSONParser.parse(result);
				JSONArray ja = (JSONArray) accountSettings.get("suggest");
				if (ja.size() != 0) {
					name.removeAll();
					new_name.removeAll();
					for (int i = 0; i < names.size(); i++)
						names.remove(i);
				}
				for (int i = 0; i < ja.size(); i++) {
					String s = ((JSONString) ja.get(i)).stringValue();
					names.add(s);
					name.add(s);
					new_name.add(s);
				}
				DOM
						.setInnerHTML(
								DOM.getElementById("nickname"),
								"欢迎&nbsp;<a href='#' onclick='nav(\"tab_tree_setting\",\"账户设置\",\"setting.png\");return false;'>"
										+ ((JSONString) accountSettings
												.get("nickname")).stringValue()
										+ "</a>");
			}

		});
	}

	private void createNorth() {
		HtmlContainer northPanel = new HtmlContainer();
		Element ele = DOM.getElementById("north");
		DOM.setStyleAttribute(ele, "visibility", "visible");
		northPanel.setElement(ele);

		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,
				96);
		northData.setMargins(new Margins(0, 2, 0, 0));
		viewport.add(northPanel, northData);
	}

	@SuppressWarnings("unchecked")
	private void createWest() {
		ContentPanel panel = new ContentPanel();
		panel.setHeading("导航栏");
		panel.setBodyBorder(false);
		panel.setWidth(200);

		final Folder model = getTreeModel();

		final TreeStore<ModelData> store = new TreeStore<ModelData>();
		store.add((List) model.getChildren(), true);

		tree = new TreePanel<ModelData>(store);
		tree.setDisplayProperty("name");
		tree.setWidth(250);
		tree.setIconProvider(new ModelIconProvider<ModelData>() {

			@Override
			public AbstractImagePrototype getIcon(ModelData model) {
				if (model instanceof Item) {
					return Costnote.getIcon((String) model.get("icon"));
				}
				return null;
			}
		});
		tree.getSelectionModel().addSelectionChangedListener(
				new SelectionChangedListener<ModelData>() {

					@Override
					public void selectionChanged(
							SelectionChangedEvent<ModelData> se) {
						if (se.getSelectedItem() == null)
							return;
						if (window != null)
							window.hide();
						final String tab_id = "tab_"
								+ se.getSelectedItem().get("id");
						if (!tab_id.contains("tree"))
							return;
						String name = se.getSelectedItem().get("name");
						String icon = se.getSelectedItem().get("icon");

						nav(tab_id, name, icon);
					}

				});

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200);
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins(0, 5, 0, 0));

		panel.add(tree);
		viewport.add(panel, westData);

	}

	@SuppressWarnings("unchecked")
	private static Widget createListPanel() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		RowNumberer r = new RowNumberer();
		configs.add(r);

		CheckBoxSelectionModel<ModelData> sm = new CheckBoxSelectionModel<ModelData>();
		sm.setSelectionMode(SelectionMode.MULTI);
		configs.add(sm.getColumn());

		ColumnConfig column = new ColumnConfig();
		column.setId("date");
		column.setHeader("日期");
		column.setDataIndex("date");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("name");
		column.setHeader("名称");
		column.setDataIndex("name");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setRenderer(new GridCellRenderer<ModelData>() {

			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<ModelData> store, Grid<ModelData> grid) {
				return (((Double) store.getAt(rowIndex).get("type")).intValue() == -1) ? "<font color='red'>支出</font>"
						: "<font color='green'>收入</font>";
			}

		});
		column.setId("type");
		column.setHeader("类型");
		column.setDataIndex("type");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("amount");
		column.setHeader("金额");
		column.setDataIndex("amount");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("remark");
		column.setHeader("备注");
		column.setDataIndex("remark");
		column.setWidth(200);
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
		mt.addField("date");
		mt.addField("type");
		mt.addField("name");
		mt.addField("remark");
		mt.addField("amount");

		ColumnModel cm = new ColumnModel(configs);

		loadConfig.setOffset(0);
		loadConfig.setLimit(20);
		loadConfig.set("timestamp", new Date().getTime());
		store = DataStruction.JsonStoreCreatePaginate("ds01", mt,
				"/ajax/costListAction.action", loadConfig);
		// this will register into ds01

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new BorderLayout());
		final Grid<ModelData> grid = new Grid<ModelData>(store, cm);
		grid.setSelectionModel(sm);
		grid.addPlugin(sm);
		grid.setAutoExpandColumn("name");
		grid.setAutoHeight(true);
		grid.setLoadMask(true);
		Menu menu = new Menu();
		MenuItem mi = new MenuItem("修改", new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {
				if (grid.getSelectionModel().getSelectedItems().size() == 0) {
					showPopMessage("error", "请选择一条记录！");
					return;
				}
				ModelData md = grid.getSelectionModel().getSelectedItem();
				showNoteWindow((String) md.get("id"), DateTimeFormat.getFormat(
						"yyyy-MM-dd").parse((String) md.get("date")),
						(String) md.get("name"), ((Double) md.get("type"))
								.intValue(), (Double) md.get("amount"),
						(String) md.get("remark"));
			}

		});
		mi.setIcon(getIcon("note.png"));
		menu.add(mi);
		menu.add(new MenuItem("删除", getIcon("delete.png"),
				new SelectionListener<MenuEvent>() {

					@Override
					public void componentSelected(MenuEvent ce) {
						if (grid.getSelectionModel().getSelectedItems().size() == 0) {
							showPopMessage("error", "请至少选择一条记录！");
							return;
						}
						MessageBox.confirm("确定删除", "您确定要删除选中的记录?",
								new Listener<MessageBoxEvent>() {

									@Override
									public void handleEvent(MessageBoxEvent be) {
										if (be.getButtonClicked().getItemId()
												.equals(Dialog.YES)) {
											List<ModelData> list = grid
													.getSelectionModel()
													.getSelectedItems();
											JSONArray ja = new JSONArray();
											for (int i = 0; i < list.size(); i++)
												ja.set(i, new JSONString(
														(String) list.get(i)
																.get("id")));
											ServiceDefTarget endpoint = (ServiceDefTarget) costService;
											endpoint
													.setServiceEntryPoint("gwt-cost!deleteCost.action");
											costService
													.deleteCost(
															ja.toString(),
															new AsyncCallback<Boolean>() {

																@Override
																public void onFailure(
																		Throwable caught) {
																	showPopMessage(
																			"error",
																			operateError);
																}

																@Override
																public void onSuccess(
																		Boolean result) {
																	if (result) {
																		reloadList();
																		showPopMessage(
																				"pass",
																				operatePass);
																	} else {
																		showPopMessage(
																				"error",
																				operateFail);
																	}
																}

															});
										}
									}
								});
					}

				}));
		grid.setContextMenu(menu);
		cp.add(grid);
		PagingToolBar toolBar = new PagingToolBar(20);
		toolBar.bind((BasePagingLoader<PagingLoadResult<ModelData>>) store
				.getLoader());
		cp.setBottomComponent(toolBar);

		ToolBar tb = new ToolBar();
		final DateField sfrom = new DateField();
		sfrom.setWidth("100");
		sfrom.setPropertyEditor(new DateTimePropertyEditor(format));
		sfrom.setValue(new Date(new Date().getTime() - 30l * 24l * 3600l
				* 1000l));
		final DateField sto = new DateField();
		sto.setWidth("100");
		sto.setValue(new Date());
		sto.setPropertyEditor(new DateTimePropertyEditor(format));
		tb.add(new LabelField("&nbsp;搜索日期从"));
		tb.add(sfrom);
		tb.add(new LabelField("&nbsp;到"));
		tb.add(sto);
		final ComboBox<Type> combo = new ComboBox<Type>();
		List<Type> types = new ArrayList<Type>();
		types.add(new Type(0, "所有类型"));
		types.add(new Type(-1, "支出"));
		types.add(new Type(1, "收入"));
		ListStore<Type> states = new ListStore<Type>();
		states.add(types);
		combo.setWidth("80");
		combo.setDisplayField("name");
		combo.setStore(states);
		combo.setTypeAhead(true);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setValue(types.get(0));
		tb.add(new LabelField("&nbsp;选择类型"));
		tb.add(combo);
		tb.add(new LabelField("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));
		Button sbutton = new Button("搜索");
		sbutton.setIcon(getIcon("search.png"));
		sbutton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				if (sfrom.getValue() == null || !sfrom.isValid()) {
					sfrom.markInvalid("请输入正确的日期!(yy-MM-dd)");
					return;
				}
				if (sto.getValue() == null || !sto.isValid()) {
					sto.markInvalid("请输入正确的日期!(yy-MM-dd)");
					return;
				}
				if (sto.getValue().getTime() < sfrom.getValue().getTime()) {
					sto.markInvalid("该日期应大于起始日期！");
					return;
				}
				if (combo.getValue() == null) {
					combo.markInvalid("请选择类型！");
					return;
				}
				loadConfig.setOffset(0);
				loadConfig.setLimit(20);
				loadConfig.set("sfrom", format.format(sfrom.getValue()));
				loadConfig.set("sto", format.format(sto.getValue()));
				loadConfig.set("stype", (Integer) combo.getValue().get("type"));
				loadConfig.set("timestamp", new Date().getTime());
				store.getLoader().load(loadConfig);
			}

		});
		tb.add(sbutton);
		cp.setTopComponent(tb);

		return cp;
	}

	@SuppressWarnings("unchecked")
	private static Widget createScheduleListPanel() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		RowNumberer r = new RowNumberer();
		configs.add(r);

		CheckBoxSelectionModel<ModelData> sm = new CheckBoxSelectionModel<ModelData>();
		sm.setSelectionMode(SelectionMode.MULTI);
		configs.add(sm.getColumn());

		ColumnConfig column = new ColumnConfig();
		column.setId("date");
		column.setHeader("提醒日期");
		column.setDataIndex("date");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setRenderer(new GridCellRenderer<ModelData>() {

			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<ModelData> store, Grid<ModelData> grid) {
				return (((Boolean) store.getAt(rowIndex).get("type"))
						.booleanValue()) ? "<font color='green'>已执行</font>"
						: "<font color='red'>未执行</font>";
			}

		});
		column.setId("type");
		column.setHeader("类型");
		column.setDataIndex("type");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("message");
		column.setHeader("给您发送的消息");
		column.setDataIndex("message");
		column.setWidth(200);
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
		mt.addField("date");
		mt.addField("type");
		mt.addField("sid");
		mt.addField("message");

		ColumnModel cm = new ColumnModel(configs);

		scheduleLoadConfig.setOffset(0);
		scheduleLoadConfig.setLimit(20);
		scheduleLoadConfig.set("timestamp", new Date().getTime());
		scheduleStore = DataStruction.JsonStoreCreatePaginate("ds02", mt,
				"/ajax/scheduleListAction.action", scheduleLoadConfig);
		// this will register into ds02

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new BorderLayout());
		final Grid<ModelData> grid = new Grid<ModelData>(scheduleStore, cm);
		grid.setSelectionModel(sm);
		grid.addPlugin(sm);
		grid.setAutoExpandColumn("message");
		grid.setAutoHeight(true);
		grid.setLoadMask(true);
		Menu menu = new Menu();

		MenuItem mi = new MenuItem("修改", new SelectionListener<MenuEvent>() {

			@Override
			public void componentSelected(MenuEvent ce) {
				if (grid.getSelectionModel().getSelectedItems().size() == 0) {
					showPopMessage("error", "请选择一条记录！");
					return;
				}
				ModelData md = grid.getSelectionModel().getSelectedItem();
				showScheduleWindow((String) md.get("id"), DateTimeFormat
						.getFormat("yyyy-MM-dd HH:mm").parse(
								(String) md.get("date")), (String) md
						.get("message"));
			}

		});
		mi.setIcon(getIcon("note.png"));
		menu.add(mi);

		menu.add(new MenuItem("删除", getIcon("delete.png"),
				new SelectionListener<MenuEvent>() {

					@Override
					public void componentSelected(MenuEvent ce) {
						if (grid.getSelectionModel().getSelectedItems().size() == 0) {
							showPopMessage("error", "请至少选择一条记录！");
							return;
						}
						MessageBox.confirm("确定删除", "您确定要删除选中的记录?",
								new Listener<MessageBoxEvent>() {

									@Override
									public void handleEvent(MessageBoxEvent be) {
										if (be.getButtonClicked().getItemId()
												.equals(Dialog.YES)) {
											List<ModelData> list = grid
													.getSelectionModel()
													.getSelectedItems();
											JSONArray ja = new JSONArray();
											for (int i = 0; i < list.size(); i++)
												ja.set(i, new JSONString(
														(String) list.get(i)
																.get("id")));
											ServiceDefTarget endpoint = (ServiceDefTarget) costService;
											endpoint
													.setServiceEntryPoint("gwt-cost!deleteSchedule.action");
											costService
													.deleteSchedule(
															ja.toString(),
															new AsyncCallback<Boolean>() {

																@Override
																public void onFailure(
																		Throwable caught) {
																	showPopMessage(
																			"error",
																			operateError);
																}

																@Override
																public void onSuccess(
																		Boolean result) {
																	if (result) {
																		reloadScheduleList();
																		showPopMessage(
																				"pass",
																				operatePass);
																	} else {
																		showPopMessage(
																				"error",
																				operateFail);
																	}
																}

															});
										}
									}
								});
					}

				}));
		grid.setContextMenu(menu);
		cp.add(grid);
		PagingToolBar toolBar = new PagingToolBar(20);
		toolBar
				.bind((BasePagingLoader<PagingLoadResult<ModelData>>) scheduleStore
						.getLoader());
		cp.setBottomComponent(toolBar);

		ToolBar tb = new ToolBar();
		final DateField sfrom = new DateField();
		sfrom.setWidth("100");
		sfrom.setPropertyEditor(new DateTimePropertyEditor(format));
		sfrom.setValue(new Date(new Date().getTime() - 30l * 24l * 3600l
				* 1000l));
		final DateField sto = new DateField();
		sto.setWidth("100");
		sto.setValue(new Date());
		sto.setPropertyEditor(new DateTimePropertyEditor(format));
		tb.add(new LabelField("&nbsp;搜索日期从"));
		tb.add(sfrom);
		tb.add(new LabelField("&nbsp;到"));
		tb.add(sto);
		tb.add(new LabelField("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));
		Button sbutton = new Button("搜索");
		sbutton.setIcon(getIcon("search.png"));
		sbutton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				if (sfrom.getValue() == null || !sfrom.isValid()) {
					sfrom.markInvalid("请输入正确的日期!(yy-MM-dd)");
					return;
				}
				if (sto.getValue() == null || !sto.isValid()) {
					sto.markInvalid("请输入正确的日期!(yy-MM-dd)");
					return;
				}
				if (sto.getValue().getTime() < sfrom.getValue().getTime()) {
					sto.markInvalid("该日期应大于起始日期！");
					return;
				}
				scheduleLoadConfig.setOffset(0);
				scheduleLoadConfig.setLimit(20);
				scheduleLoadConfig
						.set("sfrom", format.format(sfrom.getValue()));
				scheduleLoadConfig.set("sto", format.format(sto.getValue()));
				scheduleLoadConfig.set("timestamp", new Date().getTime());
				scheduleStore.getLoader().load(scheduleLoadConfig);
			}

		});
		tb.add(sbutton);
		cp.setTopComponent(tb);

		return cp;
	}

	private void createCenter() {
		TabItem item = new TabItem();
		item.addListener(Events.Select, new Listener<TabPanelEvent>() {

			@Override
			public void handleEvent(TabPanelEvent be) {
				tree.getSelectionModel().deselectAll();
			}

		});
		item.setId("tab_grid");
		item.setIcon(getIcon("home.png"));
		item.setText("欢迎");
		item.setScrollMode(Scroll.AUTO);

		Chart chart = new Chart("chart/open-flash-chart.swf");
		chart.setSize(400, 300);
		chart.setBorders(true);

		ChartModel cm = new ChartModel("前六个月开销情况表",
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cm.setBackgroundColour("#ffffff");
		XAxis xa = new XAxis();
		xa.setLabels("一月", "二月", "三月", "四月", "五月", "六月");
		xa.setZDepth3D(5);
		xa.setTickHeight(4);
		xa.setOffset(true);
		xa.setColour("#909090");
		cm.setXAxis(xa);
		YAxis ya = new YAxis();
		ya.setSteps(16);
		ya.setMax(160);
		cm.setYAxis(ya);
		CylinderBarChart bchart = new CylinderBarChart();
		bchart.setColour("#440088");
		bchart.setAlpha(.8f);
		bchart.setTooltip("￥#val#");
		for (int t = 0; t < 6; t++) {
			if ((t + 1) == (Math.ceil(5 / 3.0))) {
				bchart.addBars(new BarChart.Bar(Random.nextInt(50) + 50,
						"#aa88ff"));
			} else {
				bchart.addValues(Random.nextInt(50) + 50);
			}
		}
		cm.addChartConfig(bchart);

		chart.setChartModel(cm);
		item
				.add(new HTML(
						"<img src='images/stamp.jpg'/><a href='#' onclick='showNewNoteWindow();return false;'><img src='images/quick_addOne.gif'/>记账</a>"
								+ "&nbsp;<a href='#' onclick='nav(\"tab_tree_list\",\"查询\",\"list.png\");return false;'><img src='images/quick_blog.gif'/>列表</a>"));
		item.add(chart);
		tp.add(item);

		viewport.add(tp, new BorderLayoutData(LayoutRegion.CENTER));
	}

	public static native void showPopMessage(String type, String message) /*-{
		$wnd.showMsg(type, message);
	}-*/;

	public static Folder getTreeModel() {
		Folder[] folders = new Folder[] {
				new Folder("我的帐户", new Item[] {
						new Item("tree_note", "记账", "note.png"),
						new Item("tree_list", "帐务查询", "list.png"),
						new Item("tree_note_schedule", "新建提醒", "note.png"),
						new Item("tree_list_schedule", "提醒查询", "list.png") }),
				new Folder("设置", new Item[] { new Item("tree_setting", "帐户设置",
						"setting.png") }) };

		Folder root = new Folder("root");
		for (int i = 0; i < folders.length; i++) {
			root.add((Folder) folders[i]);
		}

		return root;
	}

	private static AbstractImagePrototype getIcon(String icon) {
		return IconHelper.create("icons/" + icon);
	}

	private static Window window;
	static HiddenField<String> hidden;
	static DateField date;
	static SimpleComboBox<String> name = new SimpleComboBox<String>();
	static Radio radio;
	static Radio radio2;
	static NumberField amount;
	static TextArea remark;

	private static void showNoteWindow(String id, Date date1, String name1,
			int type1, double amount1, String remark1) {
		if (window == null) {
			window = new Window();
			window.setIcon(getIcon("note.png"));
			window.setHeading("修改记录");
			window.setWidth(360);
			final FormPanel formPanel = new FormPanel();
			formPanel.setHeaderVisible(false);
			formPanel.setWidth(350);

			hidden = new HiddenField<String>();
			formPanel.add(hidden);

			date = new DateField();
			date.setPropertyEditor(new DateTimePropertyEditor(format));
			date.setFieldLabel("日期*");
			formPanel.add(date);

			name.setFieldLabel("名称*");
			name.setEmptyText("请从列表选择或自行输入");
			name.setMaxLength(50);
			name.setAllowBlank(false);
			formPanel.add(name);

			radio = new Radio();
			radio.setName("type");
			radio.setBoxLabel("支出");
			radio.setValue(true);

			radio2 = new Radio();
			radio2.setName("type2");
			radio2.setBoxLabel("收入");

			RadioGroup radioGroup = new RadioGroup("type");
			radioGroup.setFieldLabel("类型*");
			radioGroup.add(radio);
			radioGroup.add(radio2);
			formPanel.add(radioGroup);

			amount = new NumberField();
			amount.setAllowDecimals(true);
			amount.setAllowNegative(false);
			amount.setFormat(NumberFormat.getFormat("0.00"));
			amount.setFieldLabel("金额*");
			amount.setAllowBlank(false);
			amount.setMaxValue(1000000);
			amount.setMinValue(0);
			formPanel.add(amount);

			remark = new TextArea();
			remark.setPreventScrollbars(true);
			remark.setFieldLabel("备注");
			remark.setMaxLength(200);
			formPanel.add(remark);

			final Button b = new Button("保存");
			window.addButton(b);
			b.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					if (!formPanel.isValid())
						return;
					JSONObject jo = new JSONObject();
					jo.put("id", new JSONString(hidden.getValue()));
					jo.put("date", new JSONString(format
							.format(date.getValue())));
					jo.put("name", new JSONString(name.getSimpleValue()));
					jo
							.put("remark", new JSONString(
									remark.getValue() == null ? "" : remark
											.getValue()));
					jo
							.put("amount", new JSONNumber((Double) amount
									.getValue()));
					jo.put("type", radio.getValue() ? new JSONNumber(-1)
							: new JSONNumber(1));
					b.setEnabled(false);
					b.setText(operateWait);
					ServiceDefTarget endpoint = (ServiceDefTarget) costService;
					endpoint.setServiceEntryPoint("gwt-cost!saveCost.action");
					costService.saveCost(jo.toString(),
							new AsyncCallback<Boolean>() {

								@Override
								public void onFailure(Throwable caught) {
									b.setEnabled(true);
									b.setText("保存");
									showPopMessage("error", operateError);
								}

								@Override
								public void onSuccess(Boolean result) {
									b.setEnabled(true);
									b.setText("保存");
									if (result) {
										String s = name.getSimpleValue();
										if (!names.contains(s)) {
											name.add(s);
											new_name.add(s);
										}
										window.hide();
										reloadList();
										showPopMessage("pass", operatePass);
									} else {
										showPopMessage("error", operateFail);
									}
								}

							});
				}
			});
			Button reset = new Button("重置");
			reset.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					formPanel.reset();
				}
			});
			window.addButton(reset);
			window.add(formPanel);
		}
		hidden.setValue(id);
		name.setSimpleValue(name1);
		date.setValue(date1);
		if (type1 == -1)
			radio.setValue(true);
		else
			radio2.setValue(true);
		amount.setValue(amount1);
		remark.setValue(remark1);
		window.show();
	}

	private static Window newWindow;
	private static SimpleComboBox<String> new_name = new SimpleComboBox<String>();

	public static void showNewNoteWindow() {
		if (newWindow == null) {
			newWindow = new Window();
			newWindow.setIcon(getIcon("note.png"));
			newWindow.setHeading("新增记录");
			newWindow.setWidth(360);
			final FormPanel formPanel = new FormPanel();
			formPanel.setHeaderVisible(false);
			formPanel.setWidth(350);

			final DateField date = new DateField();
			date.setPropertyEditor(new DateTimePropertyEditor(format));
			date.setValue(new Date());
			date.setFieldLabel("日期*");
			formPanel.add(date);

			new_name.setFieldLabel("名称*");
			new_name.setEmptyText("请从列表选择或自行输入");
			new_name.setAllowBlank(false);
			new_name.setMaxLength(50);
			formPanel.add(new_name);

			final Radio radio = new Radio();
			radio.setName("type");
			radio.setBoxLabel("支出");
			radio.setValue(true);

			Radio radio2 = new Radio();
			radio2.setName("type2");
			radio2.setBoxLabel("收入");

			RadioGroup radioGroup = new RadioGroup("type");
			radioGroup.setFieldLabel("类型*");
			radioGroup.add(radio);
			radioGroup.add(radio2);
			formPanel.add(radioGroup);

			final NumberField amount = new NumberField();
			amount.setAllowDecimals(true);
			amount.setAllowNegative(false);
			amount.setFormat(NumberFormat.getFormat("0.00"));
			amount.setFieldLabel("金额*");
			amount.setAllowBlank(false);
			amount.setMaxValue(1000000);
			amount.setMinValue(0);
			formPanel.add(amount);

			final TextArea remark = new TextArea();
			remark.setPreventScrollbars(true);
			remark.setFieldLabel("备注");
			remark.setMaxLength(200);
			formPanel.add(remark);

			final Button b = new Button("保存");
			newWindow.addButton(b);
			b.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					if (!formPanel.isValid()) {
						return;
					}
					JSONObject jo = new JSONObject();
					jo.put("id", new JSONString(""));
					jo.put("date", new JSONString(format
							.format(date.getValue())));
					jo.put("name", new JSONString(new_name.getSimpleValue()));
					jo
							.put("remark", new JSONString(
									remark.getValue() == null ? "" : remark
											.getValue()));
					jo
							.put("amount", new JSONNumber((Double) amount
									.getValue()));
					jo.put("type", radio.getValue() ? new JSONNumber(-1)
							: new JSONNumber(1));
					b.setEnabled(false);
					b.setText(operateWait);
					ServiceDefTarget endpoint = (ServiceDefTarget) costService;
					endpoint.setServiceEntryPoint("gwt-cost!saveCost.action");
					costService.saveCost(jo.toString(),
							new AsyncCallback<Boolean>() {

								@Override
								public void onFailure(Throwable caught) {
									b.setEnabled(true);
									b.setText("保存");
									showPopMessage("error", operateError);
								}

								@Override
								public void onSuccess(Boolean result) {
									b.setEnabled(true);
									b.setText("保存");
									if (result) {
										String s = new_name.getSimpleValue();
										if (!names.contains(s)) {
											name.add(s);
											new_name.add(s);
										}
										formPanel.reset();
										newWindow.hide();
										reloadList();
										showPopMessage("pass", operatePass);
									} else
										showPopMessage("error", operateFail);
								}

							});
				}
			});
			Button reset = new Button("重置");
			reset.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					formPanel.reset();
				}
			});
			newWindow.addButton(reset);
			newWindow.add(formPanel);
		}
		newWindow.show();
	}

	private static Window scheduleWindow;
	private static DateField scheduleDate;
	private static TimeField scheduleTime;
	private static TextArea scheduleMessage;
	private static HiddenField<String> scheduleHidden;

	public static void showScheduleWindow(String id, Date date, String message) {
		if (!((JSONBoolean) accountSettings.get("activate")).booleanValue()) {
			showPopMessage(
					"error",
					"您还没有登记激活您的手机号，不能使用短信提醒功能！&nbsp;<a href='#' onclick='nav(\"tab_tree_setting\",\"账户设置\",\"setting.png\");return false;'>"
							+ "设置手机</a>");
			return;
		}
		if (scheduleWindow == null) {
			scheduleWindow = new Window();
			scheduleWindow.setIcon(getIcon("note.png"));
			scheduleWindow.setHeading("修改记录");
			scheduleWindow.setWidth(360);
			final FormPanel formPanel = new FormPanel();
			formPanel.setHeaderVisible(false);
			formPanel.setWidth(350);

			formPanel.add(new HTML("<b>保存提醒时需要和飞信服务器通讯，可能需要数十秒种。"
					+ "<br/>请您等待，给您造成的不便，敬请谅解！</b><br/>"));

			scheduleHidden = new HiddenField<String>();
			formPanel.add(scheduleHidden);

			scheduleDate = new DateField();
			scheduleDate.setMinValue(new Date());
			scheduleDate.setPropertyEditor(new DateTimePropertyEditor(format));
			scheduleDate.setFieldLabel("提醒日期*");
			formPanel.add(scheduleDate);

			scheduleTime = new TimeField();
			scheduleTime.setFieldLabel("时间*");
			scheduleTime.setFormat(timeFormat);
			scheduleTime.setIncrement(30);
			formPanel.add(scheduleTime);

			scheduleMessage = new TextArea();
			scheduleMessage.setPreventScrollbars(true);
			scheduleMessage.setFieldLabel("短信消息*");
			scheduleMessage.setMaxLength(200);
			formPanel.add(scheduleMessage);

			final Button b = new Button("保存");
			scheduleWindow.addButton(b);
			b.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					if (!formPanel.isValid()) {
						return;
					}
					String s = format.format(scheduleDate.getValue()) + " "
							+ scheduleTime.getValue().getText();
					final JSONObject jo = new JSONObject();
					jo.put("id", new JSONString(scheduleHidden.getValue()));
					jo.put("date", new JSONString(s));
					jo.put("message", new JSONString(
							scheduleMessage.getValue() == null ? ""
									: scheduleMessage.getValue()));
					if (dateTimeFormat.parse(s).getTime()
							- new Date().getTime() < 1200000) {
						MessageBox
								.confirm(
										"确定立即发送",
										"根据中国移动的规定，定时短信的发送日期要超过现在日期20分钟以上！<br/>是否立即发送短信到您的手机?",
										new Listener<MessageBoxEvent>() {

											@Override
											public void handleEvent(
													MessageBoxEvent be) {
												if (be.getButtonClicked()
														.getItemId().equals(
																Dialog.YES)) {
													jo
															.put(
																	"schedule",
																	JSONBoolean
																			.getInstance(false));
													b.setEnabled(false);
													b.setText(operateWait);
													ServiceDefTarget endpoint = (ServiceDefTarget) costService;
													endpoint
															.setServiceEntryPoint("gwt-cost!saveSchedule.action");
													costService
															.saveSchedule(
																	jo
																			.toString(),
																	new AsyncCallback<Boolean>() {

																		@Override
																		public void onFailure(
																				Throwable caught) {
																			b
																					.setEnabled(true);
																			b
																					.setText("保存");
																			showPopMessage(
																					"error",
																					operateError);
																		}

																		@Override
																		public void onSuccess(
																				Boolean result) {
																			b
																					.setEnabled(true);
																			b
																					.setText("保存");
																			if (result) {
																				scheduleWindow
																						.hide();
																				reloadScheduleList();
																				showPopMessage(
																						"pass",
																						operatePass);
																			} else
																				showPopMessage(
																						"error",
																						operateFail);
																		}

																	});
												} else
													return;
											}
										});
					} else {
						jo.put("schedule", JSONBoolean.getInstance(true));
						b.setEnabled(false);
						b.setText(operateWait);
						ServiceDefTarget endpoint = (ServiceDefTarget) costService;
						endpoint
								.setServiceEntryPoint("gwt-cost!saveSchedule.action");
						costService.saveSchedule(jo.toString(),
								new AsyncCallback<Boolean>() {

									@Override
									public void onFailure(Throwable caught) {
										b.setEnabled(true);
										b.setText("保存");
										showPopMessage("error", operateError);
									}

									@Override
									public void onSuccess(Boolean result) {
										b.setEnabled(true);
										b.setText("保存");
										if (result) {
											scheduleWindow.hide();
											reloadScheduleList();
											showPopMessage("pass", operatePass);
										} else
											showPopMessage("error", operateFail);
									}

								});
					}
				}
			});
			Button reset = new Button("重置");
			reset.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					formPanel.reset();
				}
			});
			scheduleWindow.addButton(reset);
			scheduleWindow.add(formPanel);
		}
		scheduleWindow.show();
		scheduleHidden.setValue(id);
		scheduleDate.setValue(date);
		scheduleTime.setDateValue(date);
		scheduleMessage.setValue(message);
	}

	private static Window newScheduleWindow;

	public static void showNewScheduleWindow() {
		if (!((JSONBoolean) accountSettings.get("activate")).booleanValue()) {
			showPopMessage(
					"error",
					"您还没有登记激活您的手机号，不能使用短信提醒功能！&nbsp;<a href='#' onclick='nav(\"tab_tree_setting\",\"账户设置\",\"setting.png\");return false;'>"
							+ "设置手机</a>");
			return;
		}
		if (newScheduleWindow == null) {
			newScheduleWindow = new Window();
			newScheduleWindow.setIcon(getIcon("note.png"));
			newScheduleWindow.setHeading("新增记录");
			newScheduleWindow.setWidth(360);
			final FormPanel formPanel = new FormPanel();
			formPanel.setHeaderVisible(false);
			formPanel.setWidth(350);

			formPanel.add(new HTML("<b>保存提醒时需要和飞信服务器通讯，可能需要数十秒种。"
					+ "<br/>请您等待，给您造成的不便，敬请谅解！</b><br/>"));

			final DateField date = new DateField();
			date.setMinValue(new Date());
			date.setPropertyEditor(new DateTimePropertyEditor(format));
			date.setValue(new Date());
			date.setFieldLabel("提醒日期*");
			formPanel.add(date);

			final TimeField time = new TimeField();
			time.setFieldLabel("时间*");
			time.setFormat(timeFormat);
			time.setIncrement(30);
			time.setDateValue(new Date(new Date().getTime() + 3600000l));
			formPanel.add(time);

			// date.addListener(Events.Blur, new Listener<FieldEvent>() {
			//
			// @Override
			// public void handleEvent(FieldEvent be) {
			// time.setMinValue(new Date());
			//
			// }
			// });
			final TextArea message = new TextArea();
			message.setPreventScrollbars(true);
			message.setFieldLabel("短信消息*");
			message.setMaxLength(200);
			formPanel.add(message);

			final Button b = new Button("保存");
			newScheduleWindow.addButton(b);
			b.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					if (!formPanel.isValid()) {
						return;
					}
					String s = format.format(date.getValue()) + " "
							+ time.getValue().getText();
					final JSONObject jo = new JSONObject();
					jo.put("id", new JSONString(""));
					jo.put("date", new JSONString(s));
					jo.put("message", new JSONString(
							message.getValue() == null ? "" : message
									.getValue()));
					if (dateTimeFormat.parse(s).getTime()
							- new Date().getTime() < 1200000) {
						MessageBox
								.confirm(
										"确定立即发送",
										"根据中国移动的规定，定时短信的发送日期要超过现在日期20分钟以上！<br/>是否立即发送短信到您的手机?",
										new Listener<MessageBoxEvent>() {

											@Override
											public void handleEvent(
													MessageBoxEvent be) {
												if (be.getButtonClicked()
														.getItemId().equals(
																Dialog.YES)) {
													jo
															.put(
																	"schedule",
																	JSONBoolean
																			.getInstance(false));
													b.setEnabled(false);
													b.setText(operateWait);
													ServiceDefTarget endpoint = (ServiceDefTarget) costService;
													endpoint
															.setServiceEntryPoint("gwt-cost!saveSchedule.action");
													costService
															.saveSchedule(
																	jo
																			.toString(),
																	new AsyncCallback<Boolean>() {

																		@Override
																		public void onFailure(
																				Throwable caught) {
																			b
																					.setEnabled(true);
																			b
																					.setText("保存");
																			showPopMessage(
																					"error",
																					operateError);
																		}

																		@Override
																		public void onSuccess(
																				Boolean result) {
																			b
																					.setEnabled(true);
																			b
																					.setText("保存");
																			if (result) {
																				// formPanel.reset();
																				message
																						.setValue("");
																				newScheduleWindow
																						.hide();
																				reloadScheduleList();
																				showPopMessage(
																						"pass",
																						operatePass);
																			} else
																				showPopMessage(
																						"error",
																						operateFail);
																		}

																	});
												} else
													return;
											}
										});
					} else {
						jo.put("schedule", JSONBoolean.getInstance(true));
						b.setEnabled(false);
						b.setText(operateWait);
						ServiceDefTarget endpoint = (ServiceDefTarget) costService;
						endpoint
								.setServiceEntryPoint("gwt-cost!saveSchedule.action");
						costService.saveSchedule(jo.toString(),
								new AsyncCallback<Boolean>() {

									@Override
									public void onFailure(Throwable caught) {
										b.setEnabled(true);
										b.setText("保存");
										showPopMessage("error", operateError);
									}

									@Override
									public void onSuccess(Boolean result) {
										b.setEnabled(true);
										b.setText("保存");
										if (result) {
											// formPanel.reset();
											message.setValue("");
											newScheduleWindow.hide();
											reloadScheduleList();
											showPopMessage("pass", operatePass);
										} else
											showPopMessage("error", operateFail);
									}

								});
					}
				}
			});
			Button reset = new Button("重置");
			reset.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					formPanel.reset();
				}
			});
			newScheduleWindow.addButton(reset);
			newScheduleWindow.add(formPanel);
		}
		newScheduleWindow.show();
	}

	private static Window settingWindow;

	private static void createSettingWindow() {
		if (settingWindow == null) {
			settingWindow = new Window();
			settingWindow.setIcon(getIcon("setting.png"));
			settingWindow.setHeading("账户设置");
			settingWindow.setWidth(380);

			TabPanel tp = new TabPanel();
			tp.setHeight(200);

			final FormPanel formPanel = new FormPanel();
			formPanel.setBodyBorder(false);
			formPanel.setHeaderVisible(false);
			formPanel.setWidth(350);

			final LabelField email = new LabelField();
			email.setFieldLabel("电子邮件:");
			email.setValue("yaoxinghuo@msn.com");
			formPanel.add(email);

			final TextField<String> nickname = new TextField<String>();
			nickname.setFieldLabel("昵称");
			nickname.setMaxLength(20);
			nickname.setAllowBlank(false);
			formPanel.add(nickname);

			final Button updateButton = new Button("更新");
			updateButton
					.addSelectionListener(new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							if (!formPanel.isValid())
								return;
							ServiceDefTarget endpoint = (ServiceDefTarget) costService;
							endpoint
									.setServiceEntryPoint("gwt-cost!updateAccountBasic.action");
							costService.updateAccountBasic(nickname.getValue(),
									booleanAsyncCallback);
						}

					});
			formPanel.addButton(updateButton);

			TabItem item1 = new TabItem("基本设置");
			item1.setIcon(getIcon("person.png"));
			item1.add(formPanel);

			tp.add(item1);

			TabItem item2 = new TabItem("手机验证");
			final FormPanel formPanel2 = new FormPanel();
			item2.setIcon(getIcon("phone.png"));
			formPanel2.setBodyBorder(false);
			formPanel2.setHeaderVisible(false);

			formPanel2
					.add(new HTML(
							"<font color='red'>发送验证码前，请先按“加为好友”，机器人将自动将您加为好友，本站仅使用您的手机号给您本人发送免费提醒短信。<br/>"
									+ "本站承诺不会将使用您的号码用于其他任何用途！若您对此有任何疑议，请不要使用该功能！"
									+ "<a href='http://sites.google.com/site/it/feedback' target='_blank'>给本站留言</a>"));

			final CheckBox isActivate = new CheckBox();
			isActivate.setFieldLabel("手机激活");
			isActivate.setBoxLabel("已激活");
			isActivate.setEnabled(false);
			formPanel2.add(isActivate);

			final TextField<String> mobile = new TextField<String>();
			mobile.setFieldLabel("手机号");
			mobile.setAllowBlank(false);
			mobile.setValidator(new VTypeValidator(VType.MOBILE));
			formPanel2.add(mobile);

			final Button addFriendButton = new Button("加为好友");
			formPanel2.add(addFriendButton);

			LayoutContainer container = new LayoutContainer();
			container.setLayout(new ColumnLayout());

			final Button validateButton = new Button("发送验证码");
			validateButton
					.addSelectionListener(new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							if (mobile.isValid()) {
								validateButton.setText(operateWait);
								validateButton.setEnabled(false);
								ServiceDefTarget endpoint = (ServiceDefTarget) costService;
								endpoint
										.setServiceEntryPoint("gwt-cost!sendVerifyCode.action");
								final String m = mobile.getValue();
								costService.sendVerifyCode(m,
										new AsyncCallback<Boolean>() {

											@Override
											public void onFailure(
													Throwable caught) {
												validateButton.setText("发送验证码");
												validateButton.setEnabled(true);
												showPopMessage("error",
														operateError);
											}

											@Override
											public void onSuccess(Boolean result) {
												validateButton.setText("发送验证码");
												validateButton.setEnabled(true);
												if (result) {
													isActivate.setValue(false);
													showPopMessage(
															"pass",
															"系统已将验证码发送到"
																	+ m
																	+ ",请输入后按'验证激活'");
												} else
													showPopMessage("error",
															"系统发送验证码时发生错误,请确认您的手机号码已开通飞信且已加13916416465为飞信好友!");
											}

										});
							}
						}

					});
			container.add(validateButton,
					new com.extjs.gxt.ui.client.widget.layout.ColumnData(75));
			container.add(new HTML("&nbsp;&nbsp;"));
			final TextField<String> verifyCode = new TextField<String>();
			verifyCode.setAllowBlank(false);
			verifyCode.setMaxLength(6);
			verifyCode.setEmptyText("输入手机收到的验证码");
			container.add(verifyCode);
			container.add(new HTML("&nbsp;&nbsp;"));
			final Button activeButton = new Button("验证激活");
			activeButton
					.addSelectionListener(new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							if (!verifyCode.isValid())
								return;
							ServiceDefTarget endpoint = (ServiceDefTarget) costService;
							endpoint
									.setServiceEntryPoint("gwt-cost!verifyCode.action");
							costService.verifyCode(verifyCode.getValue(),
									new AsyncCallback<Boolean>() {

										@Override
										public void onFailure(Throwable caught) {
											showPopMessage("error",
													operateError);
										}

										@Override
										public void onSuccess(Boolean result) {
											if (result) {
												validateButton
														.setEnabled(false);
												verifyCode.setEnabled(false);
												activeButton.setEnabled(false);
												isActivate.setValue(true);
												accountSettings
														.put(
																"activate",
																JSONBoolean
																		.getInstance(true));
												showPopMessage("pass",
														"您的手机号已成功验证!");
											} else {
												showPopMessage("error",
														"请输入正确的验证号码!");
											}

										}

									});

						}

					});

			container.add(activeButton,
					new com.extjs.gxt.ui.client.widget.layout.ColumnData(75));

			mobile.addListener(Events.Change, new Listener<FieldEvent>() {

				@Override
				public void handleEvent(FieldEvent be) {
					addFriendButton.setEnabled(true);
				}

			});

			addFriendButton
					.addSelectionListener(new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							if (mobile.isValid()) {
								addFriendButton.setEnabled(false);
								addFriendButton.setEnabled(false);
								ServiceDefTarget endpoint = (ServiceDefTarget) costService;
								endpoint
										.setServiceEntryPoint("gwt-cost!addFriend.action");
								final String m = mobile.getValue();
								costService.addFriend(m,
										new AsyncCallback<Integer>() {

											@Override
											public void onFailure(
													Throwable caught) {
												addFriendButton.setText("加为好友");
												addFriendButton
														.setEnabled(true);
												showPopMessage("error",
														operateError);
											}

											@Override
											public void onSuccess(Integer result) {
												addFriendButton.setText("加为好友");
												if (result != -1) {
													addFriendButton
															.setEnabled(false);
													if (result == 1) {

														showPopMessage(
																"pass",
																"系统已尝试加"
																		+ m
																		+ "为好友,请按提示同意加为好友！");
													} else if (result == 0) {
														accountSettings
																.put(
																		"activate",
																		JSONBoolean
																				.getInstance(false));
														isActivate
																.setValue(false);
														showPopMessage(
																"pass",
																"系统已加"
																		+ m
																		+ "为好友！");
													}
													validateButton
															.setEnabled(true);
													verifyCode.setEnabled(true);
													activeButton
															.setEnabled(true);
													accountSettings
															.put(
																	"activate",
																	JSONBoolean
																			.getInstance(false));
													isActivate.setValue(false);
												} else {
													addFriendButton
															.setEnabled(true);
													showPopMessage("error",
															"系统加您为好友时发生错误,请确认您的手机号码已开通飞信!");
												}

											}

										});
							}
						}

					});

			formPanel2.add(container);

			item2.add(formPanel2);
			tp.add(item2);

			TabItem item3 = new TabItem("短信服务");
			item3.setIcon(getIcon("phone.png"));

			final FormPanel formPanel3 = new FormPanel();
			formPanel3.setBodyBorder(false);
			formPanel3.setHeaderVisible(false);

			final CheckBox sendAlert = new CheckBox();
			sendAlert.setFieldLabel("短信提醒");
			sendAlert.setBoxLabel("启用短信提醒");
			formPanel3.add(sendAlert);

			final NumberField alertLimit = new NumberField();
			alertLimit.setFieldLabel("提醒金额");
			alertLimit.setMaxValue(1000000);
			alertLimit.setMinValue(0);
			formPanel3.add(alertLimit);

			final Button updateButton3 = new Button("更新");
			updateButton3
					.addSelectionListener(new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							if (!formPanel3.isValid())
								return;
							ServiceDefTarget endpoint = (ServiceDefTarget) costService;
							endpoint
									.setServiceEntryPoint("gwt-cost!updateAccountSms.action");
							costService.updateAccountSms(sendAlert.getValue(),
									alertLimit.getValue().doubleValue(),
									booleanAsyncCallback);
						}

					});
			formPanel3.addButton(updateButton3);

			item3.add(formPanel3);
			tp.add(item3);

			email.setValue(((JSONString) accountSettings.get("email"))
					.stringValue());
			nickname.setValue(((JSONString) accountSettings.get("nickname"))
					.stringValue());
			mobile.setValue(((JSONString) accountSettings.get("mobile"))
					.stringValue());
			verifyCode
					.setValue(((JSONString) accountSettings.get("verifyCode"))
							.stringValue());
			alertLimit
					.setValue(((JSONNumber) accountSettings.get("alertLimit"))
							.doubleValue());
			sendAlert.setValue(((JSONBoolean) accountSettings.get("sendAlert"))
					.booleanValue());
			isActivate.setValue(((JSONBoolean) accountSettings.get("activate"))
					.booleanValue());

			validateButton.setEnabled(false);
			verifyCode.setEnabled(false);
			activeButton.setEnabled(false);
			if (isActivate.getValue()) {
				addFriendButton.setEnabled(false);
			}

			settingWindow.add(tp);
		}
		settingWindow.show();
	}

	public static void nav(final String tab_id, String name, String icon) {
		TabItem tabItem = tp.getItemByItemId(tab_id);
		if (tabItem == null) {
			if (tab_id.equals("tab_tree_note")) {
				showNewNoteWindow();
				tree.getSelectionModel().deselectAll();
				return;
			} else if (tab_id.equals("tab_tree_note_schedule")) {
				showNewScheduleWindow();
				tree.getSelectionModel().deselectAll();
				return;
			} else if (tab_id.equals("tab_tree_setting")) {
				createSettingWindow();
				tree.getSelectionModel().deselectAll();
				return;
			}
			tabItem = new TabItem(name);
			tabItem.addListener(Events.Select, new Listener<TabPanelEvent>() {

				@Override
				public void handleEvent(TabPanelEvent be) {
					tree.getSelectionModel().deselectAll();
				}

			});
			tabItem.setItemId(tab_id);
			tabItem.setIcon(getIcon(icon));
			tabItem.setClosable(true);
			if (tab_id.equals("tab_tree_list")) {
				tabItem.add(createListPanel());
			} else if (tab_id.equals("tab_tree_list_schedule"))
				tabItem.add(createScheduleListPanel());
			else
				tabItem
						.addText("Tab Test Content, <br/><font color='red'>TabItem id is: "
								+ tab_id + "</font>");
			tp.add(tabItem);
		} else {
			tabItem.show();
		}
		tp.setSelection(tabItem);
	}

	private static void reloadList() {
		if (store != null) {
			store.getLoader().load(loadConfig);
		}
	}

	private static void reloadScheduleList() {
		if (scheduleStore != null)
			scheduleStore.getLoader().load(scheduleLoadConfig);
	}

	public static final AsyncCallback<Boolean> booleanAsyncCallback = new AsyncCallback<Boolean>() {

		@Override
		public void onFailure(Throwable caught) {
			showPopMessage("error", operateError);
		}

		@Override
		public void onSuccess(Boolean result) {
			if (result) {
				showPopMessage("pass", operatePass);
			} else {
				showPopMessage("error", operateFail);
			}

		}

	};

	/*
	 * http://java.sun.com/j2se/1.4.2/docs/guide/jni/spec/types.html#wp16432
	 * 
	 * Type Signature
	 * 
	 * Java Type
	 * 
	 * Z boolean
	 * 
	 * B byte
	 * 
	 * C char
	 * 
	 * S short
	 * 
	 * I int
	 * 
	 * J long
	 * 
	 * F float
	 * 
	 * D double
	 * 
	 * L fully-qualified-class ; fully-qualified-class
	 * 
	 * [ type type[]
	 * 
	 * ( arg-types ) ret-type method type
	 * 
	 * For example, the Java method:
	 * 
	 * long f (int n, String s, int[] arr); has the following type signature:
	 * 
	 * (ILjava/lang/String;[I)J
	 */
	public static native void exportJavaMethod() /*-{
		$wnd.showNewNoteWindow =
		@com.terry.costnote.client.Costnote::showNewNoteWindow();

		$wnd.nav = 
		@com.terry.costnote.client.Costnote::nav(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;);
	}-*/;

}
