package com.nokinobi.repository;

import com.nokinobi.items.IPhone;
import com.nokinobi.items.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;


@Repository
public class UserRepository {

	private static final String InsertUser="insert into users(login,pass)values(?,?)";
	private static final String FindUser="select * from users where login=? and pass=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public Integer add(DataSource ds, User user) throws SQLException {
		return jdbcTemplate.update(InsertUser,new Object[]{user.getLogin(),user.getPass()});
	}
	
	public User find(DataSource ds, User user) throws SQLException {
		return jdbcTemplate.queryForObject(FindUser,new Object[]{user.getLogin(),user.getPass()},
                (set,n)-> new User(set.getString(2),set.getString(3)));
	}
	

	
}
