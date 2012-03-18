package com.terrynow.saetest.db;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2012-3-18 下午9:47:55
 */
public class DBHelper {
	
	private static Logger log = Logger.getLogger(DBHelper.class);
	
	
	public static BasicDataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_niubi");
		dataSource.setUsername("");
		dataSource.setPassword("");
		return dataSource;
	}

	/**
	 * 查找多个对象
	 * 
	 * @param sqlString
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List query(String sqlString, Class clazz, Object... params) {
		List beans = null;
		try {
			QueryRunner qRunner = new QueryRunner(getDataSource());
			beans = (List) qRunner.query(sqlString, new BeanListHandler(clazz),
					params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}

	public static void QueryArray(String[] sqlString) {

	}

	/**
	 * 查找对象
	 * 
	 * @param sqlString
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object get(String sqlString, Class clazz, Object... params) {
		List beans = null;
		Object obj = null;
		try {
			QueryRunner qRunner = new QueryRunner(getDataSource());
			beans = (List) qRunner.query(sqlString, new BeanListHandler(clazz),
					params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (beans != null && !beans.isEmpty()) { // 注意这里
			obj = beans.get(0);
		}
		return obj;
	}

	/**
	 * 执行更新的sql语句,插入,修改,删除
	 * 
	 * @param sqlString
	 * @return
	 */
	public static boolean update(String sqlString, Object... params) {
		boolean flag = false;
		try {
			QueryRunner qRunner = new QueryRunner(getDataSource());
			int i = qRunner.update(sqlString, params);
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			log.error("can not update: "+sqlString, e);
		}
		return flag;
	}

}
