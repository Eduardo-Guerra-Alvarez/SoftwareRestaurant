package com.eduardo.softrestaurant.service;

import com.eduardo.softrestaurant.dao.OrderDAO;
import com.eduardo.softrestaurant.entity.Menu;
import com.eduardo.softrestaurant.entity.Order;
import com.eduardo.softrestaurant.entity.OrderDetails;
import com.eduardo.softrestaurant.repository.MenuRepository;
import com.eduardo.softrestaurant.repository.OrderDetailsRepository;
import com.eduardo.softrestaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuRepository menuRepository;


    public List<OrderDetails> getAllOrdersDetails() {
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrderDetailsById(Long id) {
        return orderDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetails not found"));
    }

    public OrderDAO createOrderDetails(Long orderId, Long menuId, OrderDetails orderDetails) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        if (!menu.getIsActive()) {
            throw new IllegalArgumentException("This menu item is currently inactive");
        }

        orderDetails.setMenu(menu);
        orderDetails.setUnit_price(menu.getPrice());
        orderDetails.setSubtotal(menu.getPrice() * orderDetails.getQuantity());
        OrderDetails orderDetailsSaved = orderDetailsRepository.save(orderDetails);
        order.getOrderDetails().add(orderDetailsSaved);
        orderRepository.save(order);

        return new OrderDAO(order);
    }

    public void deleteOrderDetails(Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        Order order = orderRepository.findById(orderDetails.getOrder().getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.getOrderDetails().remove(orderDetails);
        orderRepository.save(order);
    }
}
