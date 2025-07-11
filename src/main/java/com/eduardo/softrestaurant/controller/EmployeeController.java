package com.eduardo.softrestaurant.controller;

import com.eduardo.softrestaurant.dao.EmployeeDAO;
import com.eduardo.softrestaurant.entity.Employee;
import com.eduardo.softrestaurant.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees")
    @GetMapping
    public List<EmployeeDAO> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @Operation(summary = "Create new Employee")
    @PostMapping
    public EmployeeDAO createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public EmployeeDAO updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return new EmployeeDAO(employeeService.updateEmployee(id, employee));
    }

    public String deleteEmployee(@PathVariable Long id) {
        employeeService.removeEmployee(id);
        return "Employee deleted";
    }
}
