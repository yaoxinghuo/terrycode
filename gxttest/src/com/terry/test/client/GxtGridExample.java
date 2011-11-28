package com.terry.test.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @date 2011-11-28 下午2:20:54
 */
public class GxtGridExample extends LayoutContainer {

	/**
	 * This is the entry point method.
	 */
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));

		GridCellRenderer<Employee> checkSalary = new GridCellRenderer<Employee>() {
			public String render(Employee model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<Employee> employeeList, Grid<Employee> grid) {
				double val = (Double) model.get(property);
				String style = val < 70000 ? "red" : "green";
				return "<span style='color:" + style + "'>" + val + "</span>";
			}
		};
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Employee Name");
		column.setWidth(200);
		configs.add(column);

		column = new ColumnConfig("department", "Department", 150);
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);

		column = new ColumnConfig("designation", "Designation", 150);
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);

		column = new ColumnConfig("salary", "Slary", 100);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setRenderer(checkSalary);
		configs.add(column);

		column = new ColumnConfig("joiningdate", "Joining Date", 100);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());
		configs.add(column);

		ListStore<Employee> employeeList = new ListStore<Employee>();
		employeeList.add(TestData.getEmployees());

		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setSize(700, 300);

		Grid<Employee> grid = new Grid<Employee>(employeeList, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.setStripeRows(true);
		cp.add(grid);

		add(cp);
	}
}
