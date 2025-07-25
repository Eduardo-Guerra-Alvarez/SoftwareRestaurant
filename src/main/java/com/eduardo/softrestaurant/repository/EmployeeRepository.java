package com.eduardo.softrestaurant.repository;

import com.eduardo.softrestaurant.dao.EmployeeDAO;
import com.eduardo.softrestaurant.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
