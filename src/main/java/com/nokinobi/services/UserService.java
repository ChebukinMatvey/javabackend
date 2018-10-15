package com.nokinobi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokinobi.items.User;
import com.nokinobi.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired 
	private TransactionManager transactionManager;
	
	public Integer add(final User user) {
		return transactionManager.doTransaction( (con)->{ return repository.add(con, user);} );
	}
	
	public User find(final User user) {
		return transactionManager.doTransaction( (con)->repository.find(con, user) );
	}
	
	
	
}
