package com.eduardo.softrestaurant.service;

import com.eduardo.softrestaurant.dao.OrderDAO;
import com.eduardo.softrestaurant.entity.Order;
import com.eduardo.softrestaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDAO> getAllOrder() {
        return orderRepository.findAll()
                .stream()
                .map(OrderDAO::new).collect(Collectors.toList());
    }

    public OrderDAO createOrder(Order order) {
        return new OrderDAO(orderRepository.save(order));
    }

}
