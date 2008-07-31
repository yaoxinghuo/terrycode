package org.springside.extremeweb.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.extremeweb.entity.User;
import org.springside.extremeweb.util.SimpleJdbcSupport;

@Service
@Transactional
public class UserService extends SimpleJdbcSupport {

	static String QUERY_ALL_USER = "select name,login_name,password from users";
	static String INSERT_USER = "insert into users (name,login_name,password) values(:name,:loginName,:password)";
	static String DELETE_USER = "delete from users where login_name=?";

	@Transactional(readOnly = true)
	public Collection<User> getAllUser() {
		return jdbcTemplate.query(QUERY_ALL_USER, resultBeanMapper(User.class));
	}

	public void createUser(User user) {
		jdbcTemplate.update(INSERT_USER, paramBeanMapper(user));
	}

	public void deleteUser(String loginName) {
		jdbcTemplate.update(DELETE_USER, loginName);
	}
}
