package com.terry.test.client;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @date 2011-11-28 下午2:22:20
 */
public class Employee extends BaseModel {

	  /**
	         * 
	         */
	        private static final long serialVersionUID = 1L;

	public Employee() {
	  }

	  public Employee(String name, String department, String designation,double salary, Date joiningdate) {
	    set("name", name);
	    set("department", department);
	    set("designation", designation);
	    set("salary", salary);
	    set("joiningdate", joiningdate);
	  }

	  public Date getJoiningdate() {
	    return (Date) get("joiningdate");
	  }

	  public String getName() 
	  {
	    return (String) get("name");
	  }

	  public String getDepartment() {
	    return (String) get("department");
	  }
	  public String getDesignation() {
	            return (String) get("designation");
	          }
	  public double getSalary() {
	    Double salary = (Double) get("salary");
	    return salary.doubleValue();
	  }

	  
	  public String toString() {
	    return getName();
	  }
}
