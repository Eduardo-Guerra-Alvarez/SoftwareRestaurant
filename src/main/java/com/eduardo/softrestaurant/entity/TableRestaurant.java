package com.eduardo.softrestaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="tableRestaurant")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TableRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer table_number;
    private Integer capacity;
    private String status = "available"; // available, occupied, reserved

    @OneToMany(mappedBy = "tableRestaurant")
    private List<Order> orders;
}
