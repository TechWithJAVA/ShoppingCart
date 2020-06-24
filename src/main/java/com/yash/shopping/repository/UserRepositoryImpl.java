package com.yash.shopping.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.yash.shopping.constants.ResponseCode;
import com.yash.shopping.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSource dataSource;
	
	private final String SELECT_SQL = "select * from User where mobile=?";
	
	private final String INSERT_SQL = "INSERT INTO USER(mobile, username,name,lastname,email,password,usertype,createddate,updateddate,active) values(?,?,?,?,?,?,?,?,?,?)";

	@Override
	public User findById(Long mobile) {
		return jdbcTemplate.queryForObject(SELECT_SQL, new Object[] { mobile },
				new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public User save(User user) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, user.getMobile());
				ps.setString(2, user.getUsername());
				ps.setString(3, user.getName());
				ps.setString(4, user.getLastname());
				ps.setString(5, user.getEmail());
				ps.setString(6, user.getPassword());
				ps.setString(7,user.getUsertype());
				ps.setDate(8, getCurrentDate());
				ps.setDate(9, getCurrentDate());
				ps.setInt(10, user.getActive());
				return ps;
			}
		}, holder);
		logger.info(ResponseCode.SUCCESS_MESSAGE +getCurrentDate());
		long mobile = holder.getKey().intValue();
		user.setMobile(mobile);
		return user;
	}

	public static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
		
}
