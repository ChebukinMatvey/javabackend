package com.nokinobi.services;

import com.nokinobi.items.Filter;
import com.nokinobi.items.IPhone;
import com.nokinobi.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(propagation= Propagation.REQUIRED)
public class ItemsService {

	@Autowired
	private ItemsRepository repository;

	public List<IPhone> getItems() {
		return repository.getItems();
	}
	
	public List<IPhone> getItems(Filter filter) {
		return repository.getItems(filter);
	}

	public int getId(IPhone p) {
		return repository.getId(p);
	}
}
