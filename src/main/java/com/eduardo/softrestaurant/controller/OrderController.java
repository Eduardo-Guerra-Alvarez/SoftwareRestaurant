package com.eduardo.softrestaurant.controller;

import com.eduardo.softrestaurant.OrderStatus;
import com.eduardo.softrestaurant.dao.CreateOrderRequestDAO;
import com.eduardo.softrestaurant.dao.OrderDAO;
import com.eduardo.softrestaurant.dao.OrderSimpleDAO;
import com.eduardo.softrestaurant.entity.Order;
import com.eduardo.softrestaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<OrderDAO>> getOrdersByTable(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderByTable(id));
    }

    @GetMapping("/order/table/{tableId}/status/{status}")
    public ResponseEntity<OrderDAO> getOrderByTableOpened(@PathVariable Long tableId, @PathVariable OrderStatus status) {
        return ResponseEntity.ok(orderService.getOrderByTableAndStatus(tableId, status));

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

    @GetMapping("closeOrder/{id}/table/{tableId}")
    public ResponseEntity<OrderDAO> closeOrder(@PathVariable Long id, @PathVariable Long tableId) {
        return ResponseEntity.ok(orderService.closeOrder(id, tableId));
    }
}
