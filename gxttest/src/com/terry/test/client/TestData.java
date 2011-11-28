package com.terry.test.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @date 2011-11-28 下午2:23:14
 */
public class TestData {
	public static List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		DateTimeFormat f = DateTimeFormat.getFormat("yyyy-mm-dd");
		employees.add(new Employee("Hollie Voss", "General Administration",
				"Executive Director", 150000, f.parse("2006-05-01")));
		employees.add(new Employee("Emerson Milton", "Information Technology",
				"CTO", 120000, f.parse("2007-03-01")));
		employees.add(new Employee("Christina Blake", "Information Technology",
				"Project Manager", 90000, f.parse("2008-08-01")));
		employees.add(new Employee("Heriberto Rush", "Information Technology",
				"Senior S/W Engineer", 70000, f.parse("2009-02-07")));
		employees.add(new Employee("Candice Carson", "Information Technology",
				"S/W Engineer", 60000, f.parse("2007-11-01")));
		employees.add(new Employee("Chad Andrews", "Information Technology",
				"Senior S/W Engineer", 70000, f.parse("2008-02-01")));
		employees.add(new Employee("Dirk Newman", "Information Technology",
				"S/W Engineer", 62000, f.parse("2009-03-01")));
		employees.add(new Employee("Bell Snedden", "Information Technology",
				"S/W Engineer", 73000, f.parse("2007-07-07")));
		employees.add(new Employee("Benito Meeks", "Marketing",
				"General Manager", 105000, f.parse("2008-02-01")));
		employees.add(new Employee("Gail Horton", "Marketing", "Executive",
				55000, f.parse("2009-05-01")));
		employees.add(new Employee("Claudio Engle", "Marketing", "Executive",
				58000, f.parse("2008-09-03")));
		employees.add(new Employee("Buster misjenou", "Accounts", "Executive",
				52000, f.parse("2008-02-07")));
		return employees;
	}

}
