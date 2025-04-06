package com.eduardo.softrestaurant.repository;

import com.eduardo.softrestaurant.entity.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Long> {
}
