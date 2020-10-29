package com.rga.springwebapp.services;

import com.rga.springwebapp.domain.Order;
import com.rga.springwebapp.domain.OrderPosition;
import com.rga.springwebapp.domain.User;
import com.rga.springwebapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrderFromPositions(User user, List<OrderPosition> positions) {
        Order order = new Order();
        order.setPositions( new ArrayList<>());
        order.setUser(user);
        positions.stream().forEach(item -> {
            order.getPositions().add(item);
            item.setOrder(order);
        });
        positions.clear();
        return orderRepository.save(order);
    }
}
