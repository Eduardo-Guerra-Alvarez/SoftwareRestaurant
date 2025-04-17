package com.eduardo.softrestaurant.controller;

import com.eduardo.softrestaurant.dao.OrderDAO;
import com.eduardo.softrestaurant.entity.OrderDetails;
import com.eduardo.softrestaurant.repository.OrderDetailsRepository;
import com.eduardo.softrestaurant.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderdetails/")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/order/{orderId}/menu/{menuId}")
    public OrderDAO createOrderDetails(@PathVariable Long orderId, @PathVariable Long menuId, @RequestBody OrderDetails orderDetails) {
        return orderDetailsService.createOrderDetails(orderId, menuId, orderDetails);
    }

    @DeleteMapping
    public String deleteOrderDetails(@PathVariable Long id) {
        orderDetailsService.deleteOrderDetails(id);
        return "OrderDetail deleted";
    }

}
