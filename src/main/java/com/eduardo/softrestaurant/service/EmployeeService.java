package com.eduardo.softrestaurant.service;

import com.eduardo.softrestaurant.dao.EmployeeDAO;
import com.eduardo.softrestaurant.entity.Employee;
import com.eduardo.softrestaurant.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public List<EmployeeDAO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDAO::new).collect(Collectors.toList());
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public Employee saveEmployee(Employee employee) {
        employee.setPassword_hash(encodePassword(employee.getPassword_hash()));
        return employeeRepository.save(employee);
    }

    public void removeEmployee(Long id) {
        employeeRepository.findById(id)
                .ifPresentOrElse(
                        employeeRepository::delete,
                        () -> {throw new RuntimeException("Employee not found");}
                );
    }

    public Employee updateEmployee(Long id, Employee updateData) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(updateData.getFirstName());
                    employee.setFirstName(updateData.getFirstName());
                    employee.setLastName(updateData.getLastName());
                    employee.setRole(updateData.getRole());
                    employee.setPassword_hash(encodePassword(updateData.getPassword_hash()));
                    employee.setIsActive(updateData.getIsActive());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new RuntimeException("Employee not found"));

    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
