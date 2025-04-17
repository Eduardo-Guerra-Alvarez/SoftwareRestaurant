package com.eduardo.softrestaurant.entity;

import com.eduardo.softrestaurant.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_restaurant")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableRestaurant tableRestaurant;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.OPEN; // pending, in_progress, delivered, canceled

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;
    private Float total = 0.0F;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;
}
