package com.nokinobi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nokinobi.items.Filter;
import com.nokinobi.items.IPhone;

@Repository
public class ItemsRepository {

	private static final String query = "select * from goods";
	
	public List<IPhone> getItems(Connection con) throws SQLException {
		try(PreparedStatement st=con.prepareStatement(query)){
			return map(st.executeQuery());
		}
	}
	
	public List<IPhone> getItems(Connection con,Filter filter) throws SQLException {
		String sql=query+" where";
		if (!filter.getName().equals("default"))
            sql += " name='" + filter.getName() + "'";
        else
            sql += " name in('IPhone Se','IPhone 10','IPhone 6s','IPhone 7','IPhone 8')";
        if (!filter.getCapacity().equals("default"))
            sql += " and capacity=" + filter.getCapacity();
        else
            sql +=" and capacity in(32,64,128)";
        sql += " and price>" + filter.getPriceMin() + " and price < " + filter.getPriceMax();
        
		try(PreparedStatement st=con.prepareStatement(sql)){
			return map(st.executeQuery());
		}
	}
	
	

	private List<IPhone> map(ResultSet set) throws SQLException {
		List<IPhone> items=new ArrayList<>();
		while (set.next()) {
            items.add(new IPhone(set.getString("name"),
                    set.getString("imgStr"),
                    set.getInt("price"),
                    set.getInt("capacity")));
        }
		return items;
	}

}
