package com.nokinobi.services;

import com.nokinobi.items.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokinobi.items.Cart;
import com.nokinobi.items.User;
import com.nokinobi.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository repository;


    public void addOrder(Order order) {
        repository.addOrder(order);
    }

}
