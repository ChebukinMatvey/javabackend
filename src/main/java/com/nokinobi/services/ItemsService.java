package com.nokinobi.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokinobi.items.Filter;
import com.nokinobi.items.IPhone;
import com.nokinobi.repository.ItemsRepository;

@Service
public class ItemsService {

	@Autowired
	private ItemsRepository repository;
	
	@Autowired
	private TransactionManager transactionManager;
	
	public List<IPhone> getItems() {
		return transactionManager.doTransaction((con)->repository.getItems(con));
	}
	
	public List<IPhone> getItems(Filter filter) {
		return transactionManager.doTransaction((con)->repository.getItems(con, filter));
	}
	
}
