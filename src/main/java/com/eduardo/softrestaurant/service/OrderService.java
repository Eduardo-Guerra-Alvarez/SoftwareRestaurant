package com.eduardo.softrestaurant.service;

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

    public void deleteById(Long id) {
        orderRepository.findById(id)
                        .ifPresentOrElse(
                                orderRepository::delete,
                                () -> {
                                    throw new RuntimeException("Order not found");
                                }
                        );
    }
}
