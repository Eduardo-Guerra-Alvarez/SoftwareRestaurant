package com.eduardo.softrestaurant.dao;

import com.eduardo.softrestaurant.entity.Employee;
import com.eduardo.softrestaurant.entity.Order;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class EmployeeDAO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role; // (admin, waiter, cook, etc)
    private String password_hash;
    private Boolean isActive = true;
    private List<OrderDAO> orders;

    public EmployeeDAO(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.role = employee.getRole();
        this.isActive = employee.getIsActive();
        this.password_hash = employee.getPassword_hash();
        this.orders = employee.getOrders()
                .stream()
                .map(OrderDAO::new)
                .collect(Collectors.toList());
    }
}
