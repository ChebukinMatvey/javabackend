package com.nokinobi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokinobi.items.Cart;
import com.nokinobi.items.User;
import com.nokinobi.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired 
	private OrderRepository repository;
	
	@Autowired
	private TransactionManager transactionManager;
	
	public int addOrder(Cart cart,User user,String adr,String email) {
		return transactionManager.doTransaction((con)->  repository.addOrder(con, cart, user, adr, email));
	}
	
}
