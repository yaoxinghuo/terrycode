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
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
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
	private static DateTimeFormat format = DateTimeFormat
			.getFormat("yyyy-MM-dd");
	private Viewport viewport;
	private static TabPanel tp = new TabPanel();
	private static TreePanel<ModelData> tree;
	private static ListStore<ModelData> store;
	private static BasePagingLoadConfig loadConfig = new BasePagingLoadConfig();
	private static ArrayList<String> names = new ArrayList<String>();

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

		names.add("用餐");
		names.add("买衣服");
		names.add("交通费");
		for (String s : names) {
			name.add(s);
			new_name.add(s);
		}
		ServiceDefTarget endpoint = (ServiceDefTarget) costService;
		endpoint.setServiceEntryPoint("gwt-cost!suggestNames.action");
		costService.suggestNames(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				JSONArray ja = (JSONArray) JSONParser.parse(result);
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
					showPopMessage("error", "请至少选择一条记录！");
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
		menu.add(new MenuItem("删除", getIcon("delete.gif"),
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
		PagingToolBar toolBar = createChinesePagingToolBar();
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

	public static void showPopMessage(String type, String message) {
		if (type.equals("info"))
			message = "<img src='icons/info.png'/>&nbsp;" + message;
		else if (type.equals("error"))
			message = "<img src='icons/fail.gif'/>&nbsp;" + message;
		else if (type.equals("pass"))
			message = "<img src='icons/pass.gif'/>&nbsp;" + message;
		DOM.getElementById("msg_content").setInnerHTML(message);
		DOM.setStyleAttribute(DOM.getElementById("msg"), "visibility",
				"visible");
	}

	private static PagingToolBar createChinesePagingToolBar() {
		PagingToolBar toolBar = new PagingToolBar(20);
		PagingToolBar.PagingToolBarMessages messages = toolBar.getMessages();
		// messages.setAfterPageText("页，共 {0} 页");
		// messages.setBeforePageText("第");
		messages.setDisplayMsg("显示 {0} - {1} 条，共 {2} 条");
		messages.setEmptyMsg("<font color=red>没有数据</font>");
		messages.setFirstText("第一页");
		messages.setLastText("最后页");
		messages.setNextText("下一页");
		messages.setPrevText("前一页");
		messages.setRefreshText("刷新");
		return toolBar;
	}

	public static Folder getTreeModel() {
		Folder[] folders = new Folder[] {
				new Folder("我的帐户", new Item[] {
						new Item("tree_note", "记账", "note.png"),
						new Item("tree_list", "查询", "list.png") }),
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
			window.setHeading("修改记录");
			window.setWidth(360);
			final FormPanel formPanel = new FormPanel();
			formPanel.setHeaderVisible(true);
			formPanel.setWidth(350);

			hidden = new HiddenField<String>();
			formPanel.add(hidden);

			date = new DateField();
			date.setPropertyEditor(new DateTimePropertyEditor(format));
			date.setFieldLabel("日期*");
			formPanel.add(date);

			name.setFieldLabel("名称*");
			name.setEmptyText("请从列表选择或自行输入");
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
			formPanel.add(amount);

			remark = new TextArea();
			remark.setPreventScrollbars(true);
			remark.setFieldLabel("备注");
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
					b.setText("请稍候");
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
			newWindow.setHeading("新增记录");
			newWindow.setWidth(360);
			final FormPanel formPanel = new FormPanel();
			formPanel.setHeaderVisible(true);
			formPanel.setWidth(350);

			final DateField date = new DateField();
			date.setPropertyEditor(new DateTimePropertyEditor(format));
			date.setValue(new Date());
			date.setFieldLabel("日期*");
			formPanel.add(date);

			new_name.setFieldLabel("名称*");
			new_name.setEmptyText("请从列表选择或自行输入");
			new_name.setAllowBlank(false);
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
			formPanel.add(amount);

			final TextArea remark = new TextArea();
			remark.setPreventScrollbars(true);
			remark.setFieldLabel("备注");
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
					b.setText("请稍候");
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

	public static void nav(final String tab_id, String name, String icon) {
		TabItem tabItem = tp.getItemByItemId(tab_id);
		if (tabItem == null) {
			if (tab_id.equals("tab_tree_note")) {
				showNewNoteWindow();
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
			} else
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

		$wnd.showPopMessage = 
		@com.terry.costnote.client.Costnote::showPopMessage(Ljava/lang/String;Ljava/lang/String;);

		$wnd.nav = 
		@com.terry.costnote.client.Costnote::nav(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;);
	}-*/;

}
