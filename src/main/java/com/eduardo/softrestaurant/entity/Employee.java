package com.eduardo.softrestaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role; // (admin, waiter, cook, etc)
    private String password_hash;
    private Boolean isActive = true;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Order> orders;
}
