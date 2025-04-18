package com.eduardo.softrestaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Float price;
    private String category; // food, drink, dessert
    private Boolean isActive;

    @OneToMany(mappedBy = "menu")
    private List<OrderDetails> orderDetails;
}
