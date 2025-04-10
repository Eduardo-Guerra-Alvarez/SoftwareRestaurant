package com.eduardo.softrestaurant.dao;

import com.eduardo.softrestaurant.entity.Employee;
import com.eduardo.softrestaurant.entity.Order;
import com.eduardo.softrestaurant.entity.OrderDetails;
import com.eduardo.softrestaurant.entity.TableRestaurant;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDAO {
    private Long id;
    private TableRestaurant tableRestaurant;
    private String employee_name;
    private String status; // pending, in_progress, delivered, canceled
    private LocalDateTime created_at;
    private Float total;
    private List<OrderDetails> orderDetails;

    public OrderDAO(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.created_at = order.getCreated_at();
        this.total = order.getTotal();
        this.employee_name = order.getEmployee().getFirstName();
    }
}
