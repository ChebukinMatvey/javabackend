package com.nokinobi.services;

import com.nokinobi.items.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokinobi.items.Cart;
import com.nokinobi.items.User;
import com.nokinobi.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

	@Autowired 
	private OrderRepository repository;
	
	@Autowired
	private TransactionManager transactionManager;
	
	public int[] addOrder(List<Order> items) {
		return transactionManager.doTransaction((ds)->  repository.addOrder(ds,items));
	}
	
}
