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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Repository
public class OrderRepository {


	@PersistenceContext
	private EntityManager entityManager;

	public void  addOrder(Order order) {
		entityManager.persist(order);
		entityManager.flush();
	}

}
