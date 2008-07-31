package org.springside.extremeweb.util;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * Service基类.
 * 
 * 封装SimpleJdbcTemplate及一些简便操作.
 */
public abstract class SimpleJdbcSupport {

	protected final Log logger = LogFactory.getLog(getClass());

	protected SimpleJdbcTemplate jdbcTemplate;

	@Autowired
	public void init(DataSource dataSource) {
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * 简化将ResultSet反射到Bean的定义.
	 */
	public <T> ParameterizedBeanPropertyRowMapper<T> resultBeanMapper(Class<T> clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}

	/**
	 * 简化将Bean反射到SQL参数的定义.
	 */
	public BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		return new BeanPropertySqlParameterSource(object);
	}
}
