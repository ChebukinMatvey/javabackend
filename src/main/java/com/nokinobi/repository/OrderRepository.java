package com.nokinobi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nokinobi.items.Cart;
import com.nokinobi.items.IPhone;
import com.nokinobi.items.User;

@Repository
public class OrderRepository {

	private static final String InsertOrder="insert into orders(name,capacity,price,login,adress,email)values(?,?,?,?,?,?)";
	
	public int  addOrder(Connection con,Cart cart,User user, String adr, String email) throws SQLException {
		try(PreparedStatement st=con.prepareStatement(InsertOrder)){
			init(st,cart,user,adr,email);
			return Arrays.stream(st.executeBatch()).sum();
		}
	}

	private void init(PreparedStatement st, Cart cart,User user, String adr, String email) throws SQLException {
		for (IPhone p:cart) {
            st.setString(1,p.getName());
            st.setInt(2,p.getCapacity());
            st.setInt(3,p.getPrice());
            st.setString(4,user.getLogin());
            st.setString(5,adr);
            st.setString(6,email);
            st.addBatch();
        }
	}
	
}
