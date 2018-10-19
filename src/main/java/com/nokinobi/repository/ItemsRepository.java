package com.nokinobi.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nokinobi.items.Filter;
import com.nokinobi.items.IPhone;

import javax.sql.DataSource;

@Repository
public class ItemsRepository {

	private static final String SelectAll = "select * from goods";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<IPhone> mapper=(set,n)->new IPhone(set.getString("name"),
			set.getString("imgStr"),
			set.getInt("price"),
			set.getInt("capacity"));

	public List<IPhone> getItems(DataSource ds) throws SQLException {
		return jdbcTemplate.query(SelectAll,mapper);
	}
	
	public List<IPhone> getItems(DataSource con, Filter filter) throws SQLException {
		String sql= SelectAll +" where";
		if (!filter.getName().equals("default"))
            sql += " name='" + filter.getName() + "'";
        else
            sql += " name in('IPhone Se','IPhone 10','IPhone 6s','IPhone 7','IPhone 8')";
        if (!filter.getCapacity().equals("default"))
            sql += " and capacity=" + filter.getCapacity();
        else
            sql +=" and capacity in(32,64,128)";
        sql += " and price>" + filter.getPriceMin() + " and price < " + filter.getPriceMax();
        return jdbcTemplate.query(sql,mapper);
	}
}
