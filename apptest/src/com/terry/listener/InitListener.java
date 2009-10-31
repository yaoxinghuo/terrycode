package com.terry.listener;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import ognl.OgnlRuntime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.terry.robot.logic.Fortune;
import com.terry.robot.logic.FortuneContent;

public class InitListener implements ServletContextListener {

	private static Log log = LogFactory.getLog(InitListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		OgnlRuntime.setSecurityManager(null);

		String basePath = contextEvent.getServletContext().getRealPath("/");
		try {
			ArrayList<Fortune> fs = new ArrayList<Fortune>();

			Workbook rwb = Workbook.getWorkbook(new FileInputStream(new File(
					basePath + File.separator + "WEB-INF" + File.separator
							+ "fortune.xls")));
			Sheet rs = rwb.getSheet(0);
			for (int i = 0; i < rs.getRows(); i++) {
				Fortune fortune = new Fortune();
				for (int j = 0; j < rs.getColumns(); j++) {
					Cell cell = rs.getCell(j, i);
					String value = cell.getContents();
					switch (j) {
					case 0:
						fortune.setCol0(value);
						fortune.setNo(i + 1);
						fortune.setId(String.valueOf(i + 1));
						break;
					case 1:
						fortune.setCol1(value);
						break;
					case 2:
						fortune.setCol2(value);
						break;
					case 3:
						fortune.setCol3(value);
						break;
					case 4:
						fortune.setCol4(value);
						break;
					case 5:
						fortune.setCol5(value);
						break;
					case 6:
						fortune.setCol6(value);
						break;
					case 7:
						fortune.setCol7(value);
						break;
					}
				}

				fs.add(fortune);
			}

			FortuneContent.fortunes = fs;

		} catch (Exception e) {
			log.error("can not load fortune.json. exception:" + e.getMessage());
		}
	}

}
