package com.nokinobi.services;

import com.nokinobi.items.Order;
import com.nokinobi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination orderDestination;

    public void addOrder(Order order) {
        repository.addOrder(order);
        sendOrder(order);
    }

    private void sendOrder(Order order) {
        jmsTemplate.send(orderDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setStringProperty("login", order.getUser().getLogin());
                message.setInt("orderId", order.getId());
                message.setInt("total", order.getTotal());
                return message;
            }
        });
    }


}
