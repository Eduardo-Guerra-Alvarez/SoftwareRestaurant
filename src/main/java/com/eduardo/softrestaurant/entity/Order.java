package com.eduardo.softrestaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TableRestaurant tableRestaurant;
    @OneToOne
    private Employee employee;
    private String status; // pending, in_progress, delivered, canceled
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;
    private Float total;
}
