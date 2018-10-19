package com.nokinobi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.nokinobi.items.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nokinobi.items.Cart;
import com.nokinobi.items.IPhone;
import com.nokinobi.items.User;

import javax.sql.DataSource;

@Repository
public class OrderRepository {

	private static final String InsertOrder="insert into orders(name,capacity,price,login,adress,email)values(?,?,?,?,?,?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int[]  addOrder(DataSource con, List<Order> items) throws SQLException {
		return jdbcTemplate.batchUpdate(InsertOrder, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
				init(preparedStatement,items.get(i));
			}
			@Override
			public int getBatchSize() {
				return items.size();
			}
		});
	}

	private void init(PreparedStatement st,Order item) throws SQLException {
            st.setString(1,item.getName());
            st.setInt(2,item.getCapacity());
            st.setInt(3,item.getPrice());
            st.setString(4,item.getLogin());
            st.setString(5,item.getAddress());
            st.setString(6,item.getEmail());
		}
	
}
