package com.eduardo.softrestaurant.dao;

import com.eduardo.softrestaurant.OrderStatus;
import com.eduardo.softrestaurant.entity.Order;
import com.eduardo.softrestaurant.entity.OrderDetails;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderDAO {
    private Long id;
    private Integer tableRestaurant_number;
    private String employee_name;
    private OrderStatus status; // pending, in_progress, delivered, canceled
    private LocalDateTime created_at;
    private Float total;
    private List<OrderDetailsDAO> orderDetails;

    public OrderDAO(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.created_at = order.getCreated_at();
        this.total = order.getTotal();
        this.employee_name = order.getEmployee().getFirstName();
        this.tableRestaurant_number = order.getTableRestaurant().getTable_number();
        this.orderDetails = Optional.ofNullable(order.getOrderDetails())
                .orElse(Collections.emptyList())
                .stream()
                .map(OrderDetailsDAO::new).collect(Collectors.toList());
    }
}
