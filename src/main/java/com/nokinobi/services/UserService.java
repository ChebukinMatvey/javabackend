package com.nokinobi.services;


import com.nokinobi.repository.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokinobi.items.User;
import com.nokinobi.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = RepositoryException.class)
public class UserService {

	@Autowired
	private UserRepository repository;

	public void add(User user) throws RepositoryException {
		repository.add(user);
	}
	
	public User find(User user){
		return repository.find(user);
	}

	public int getId(User user) {
		return repository.getId(user);
	}
}
