package com.eduardo.softrestaurant.service;

import com.eduardo.softrestaurant.dao.OrderDAO;
import com.eduardo.softrestaurant.dao.OrderDetailsDAO;
import com.eduardo.softrestaurant.entity.Menu;
import com.eduardo.softrestaurant.entity.Order;
import com.eduardo.softrestaurant.entity.OrderDetails;
import com.eduardo.softrestaurant.repository.MenuRepository;
import com.eduardo.softrestaurant.repository.OrderDetailsRepository;
import com.eduardo.softrestaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuRepository menuRepository;


    public List<OrderDetailsDAO> getAllOrdersDetails() {
        return orderDetailsRepository.findAll()
                .stream()
                .map(OrderDetailsDAO::new)
                .collect(Collectors.toList());
    }

    public OrderDetailsDAO getOrderDetailsById(Long id) {
        OrderDetails orderDetail = orderDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetails not found"));
        return new OrderDetailsDAO(orderDetail);
    }

    public List<OrderDetailsDAO> getAllOrdersDetailsByOrder(Long id) {
        return orderDetailsRepository.findByOrder_Id(id)
                .stream()
                .map(OrderDetailsDAO::new)
                .collect(Collectors.toList());
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
        orderDetails.setOrder(order);
        orderDetails.setUnit_price(menu.getPrice());
        orderDetails.setSubtotal(menu.getPrice() * orderDetails.getQuantity());
        order.setTotal(order.getTotal() + orderDetails.getSubtotal());
        orderRepository.save(order);
        orderDetailsRepository.save(orderDetails);

        return new OrderDAO(order);
    }

    public void deleteOrderDetails(Long id) {
        orderDetailsRepository.findById(id)
                .ifPresentOrElse(orderDetailsRepository::delete,
                        () -> {throw new RuntimeException("OrderDetail not found");});
    }
}
