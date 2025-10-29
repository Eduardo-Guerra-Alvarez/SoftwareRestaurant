package com.eduardo.softrestaurant.controller;

import com.eduardo.softrestaurant.dao.OrderDAO;
import com.eduardo.softrestaurant.dao.OrderDetailsDAO;
import com.eduardo.softrestaurant.entity.OrderDetails;
import com.eduardo.softrestaurant.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping
    public List<OrderDetailsDAO> getOrdersDetails() {
        return orderDetailsService.getAllOrdersDetails();
    }

    @GetMapping("/order/{id}")
    public List<OrderDetailsDAO> getOrderDetailsByOrder(@PathVariable Long id) {
        return orderDetailsService.getAllOrdersDetailsByOrder(id);
    }

    @GetMapping("/{id}")
    public OrderDetailsDAO getOrderDetailById(@PathVariable Long id) {
        return orderDetailsService.getOrderDetailsById(id);
    }

    @PostMapping("/order/{orderId}/menu/{menuId}")
    public OrderDAO createOrderDetails(@PathVariable Long orderId, @PathVariable Long menuId, @RequestBody OrderDetails orderDetails) {
        return orderDetailsService.createOrderDetails(orderId, menuId, orderDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteOrderDetails(@PathVariable Long id) {
        orderDetailsService.deleteOrderDetails(id);
        return "OrderDetail deleted";
    }

}
