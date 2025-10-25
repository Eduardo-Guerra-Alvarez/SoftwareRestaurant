package com.eduardo.softrestaurant.controller;

import com.eduardo.softrestaurant.dao.CreateOrderRequestDAO;
import com.eduardo.softrestaurant.dao.OrderDAO;
import com.eduardo.softrestaurant.dao.OrderSimpleDAO;
import com.eduardo.softrestaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderSimpleDAO> getOrders() {
        return orderService.getAllOrder();
    }

    @GetMapping("/table/{id}")
    public ResponseEntity<List<OrderSimpleDAO>> getOrdersByTable(@RequestParam Long tableId) {
        return ResponseEntity.ok(orderService.getOrderByTable(tableId));
    }

    @GetMapping("/{id}")
    public OrderDAO getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    /* We request CreateOrderReuestDAO
    {
        employeeId: <id>,
        tableId: <id>
    }
     */
    public OrderDAO createOrder(@RequestBody CreateOrderRequestDAO orderRequestDAO) {
        return orderService.createOrder(orderRequestDAO);
    }



}
