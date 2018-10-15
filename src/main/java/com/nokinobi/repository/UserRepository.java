package com.nokinobi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.nokinobi.items.User;



@Repository
public class UserRepository {

	private static final String InsertUser="insert into users(login,pass)values(?,?)";
	private static final String FindUser="select * from users where login=? and pass=?";
	
	public void add(Connection con,User user) throws SQLException {
		try(PreparedStatement st=con.prepareStatement(InsertUser)){
			init(st, user);
			st.executeUpdate();
		}
	}
	
	public User find(Connection con, User user) throws SQLException {
		try(PreparedStatement st=con.prepareStatement(FindUser)){
			init(st,user);
			ResultSet res=st.executeQuery();
			return new User(res.getString(1),res.getString(2));
		}
	}
	
	private void init(PreparedStatement st,User user) throws SQLException {
		st.setString(1, user.getLogin());
		st.setString(2, user.getPass());
	}
	
	
}