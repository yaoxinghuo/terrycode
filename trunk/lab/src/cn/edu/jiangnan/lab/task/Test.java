package cn.edu.jiangnan.lab.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		DateFormat df=DateFormat.getInstance();
		try {
			Date d =df.parse("Sat Aug 2 21:00:00 UTC+0800 2008");
			System.out.println(d.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
