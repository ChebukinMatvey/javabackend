package com.nokinobi.services;

import java.sql.Connection;
import java.sql.SQLException;

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
	
	public void add(final User user) {
		transactionManager.doTransaction(new Operation<Void>() {
			@Override
			public Void operation(Connection con) throws SQLException {
				repository.add(con, user);
				return null;
			}
		});
	}
	
	public User find(final User user) {
		return transactionManager.doTransaction(new Operation<User>() {
		@Override
		public User operation(Connection con) throws SQLException {
			repository.find(con, user);
			return null;
		}
		});
	}
	
	
	
}
