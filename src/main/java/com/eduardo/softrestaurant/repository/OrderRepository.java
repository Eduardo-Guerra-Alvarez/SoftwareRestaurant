package com.eduardo.softrestaurant.repository;

import com.eduardo.softrestaurant.dao.OrderDAO;
import com.eduardo.softrestaurant.dao.OrderSimpleDAO;
import com.eduardo.softrestaurant.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<OrderSimpleDAO> findByTableRestaurant_number(Long tableRestaurant_number);
}
