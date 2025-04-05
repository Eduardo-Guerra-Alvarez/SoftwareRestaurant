package com.eduardo.softrestaurant.service;

import com.eduardo.softrestaurant.model.Employee;
import com.eduardo.softrestaurant.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void removeEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employeeUpdated = optionalEmployee.get();
            employeeUpdated.setFirstName(employee.getFirstName());
            employeeUpdated.setLastName(employee.getLastName());
            employeeUpdated.setRole(employee.getRole());
            employeeUpdated.setPassword_hash(employee.getPassword_hash());
            employeeUpdated.setIsActive(employee.getIsActive());

            return employeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee not found");
        }
    }
}
