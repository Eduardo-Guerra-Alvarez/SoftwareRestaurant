package com.eduardo.softrestaurant.service;

import com.eduardo.softrestaurant.OrderStatus;
import com.eduardo.softrestaurant.dao.CreateOrderRequestDAO;
import com.eduardo.softrestaurant.dao.OrderDAO;
import com.eduardo.softrestaurant.dao.OrderSimpleDAO;
import com.eduardo.softrestaurant.entity.Employee;
import com.eduardo.softrestaurant.entity.Order;
import com.eduardo.softrestaurant.entity.TableRestaurant;
import com.eduardo.softrestaurant.repository.EmployeeRepository;
import com.eduardo.softrestaurant.repository.OrderRepository;
import com.eduardo.softrestaurant.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    public List<OrderSimpleDAO> getAllOrder() {
        return orderRepository.findAll()
                .stream()
                .map(OrderSimpleDAO::new).collect(Collectors.toList());
    }

    public OrderDAO createOrder(CreateOrderRequestDAO orderRequestDAO) {
        // Get Employee with id if existed.
        Employee employee = employeeRepository.findById(orderRequestDAO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        // Get Table with id if existed.
        TableRestaurant tableRestaurant = tableRestaurantRepository.findById(orderRequestDAO.getTableId())
                .orElseThrow(() -> new RuntimeException("Table not found"));

        // Set data and save it.
        Order order = new Order();
        order.setEmployee(employee);
        order.setTableRestaurant(tableRestaurant);
        return new OrderDAO(orderRepository.save(order));
    }

    public OrderDAO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return new OrderDAO(order);
    }

    public OrderDAO getOrderByTableAndStatus(Long tableId, OrderStatus status) {
        Order order = orderRepository.findByTableRestaurant_IdAndStatus(tableId, status)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return new OrderDAO(order);
    }

    public List<OrderDAO> getOrderByTable(Long tableId) {
        return orderRepository.findByTableRestaurant_Id(tableId)
                .stream()
                .map(OrderDAO::new).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        orderRepository.findById(id)
                        .ifPresentOrElse(
                                orderRepository::delete,
                                () -> {
                                    throw new RuntimeException("Order not found");
                                }
                        );
    }

    public OrderDAO closeOrder(Long orderId, Long tableId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        TableRestaurant table = tableRestaurantRepository.findById(tableId)
                        .orElseThrow(() -> new RuntimeException("Table not found"));

        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
        table.setStatus("Disponible");
        tableRestaurantRepository.save(table);
        return new OrderDAO(order);
    }
}
