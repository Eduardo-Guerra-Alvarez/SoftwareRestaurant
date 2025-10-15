package com.eduardo.softrestaurant.controller;

import com.eduardo.softrestaurant.config.JwtUtil;
import com.eduardo.softrestaurant.entity.Employee;
import com.eduardo.softrestaurant.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmployeeService employeeService;

    // implementation of Login
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        // get the data from body
        String email = loginData.get("email");
        String password = loginData.get("password");

        // check if employee exist
        Optional<Employee> employee = employeeService.getEmployeeByEmail(email);
        if(employee.isEmpty()) return Map.of("Error", "Invalid credencials");

        String emailEmployee = employee.get().getEmail();
        String passwordEmployee = employee.get().getPassword_hash();
        Long employeeId = employee.get().getId();

        if (email.equals(emailEmployee) && matchPassword(password, passwordEmployee)){
            String token = jwtUtil.generateToken(email, employeeId);
            return Map.of(
                    "token", token,
                    "email", emailEmployee,
                    "employeeId", employeeId
            );
        }

        return Map.of("Error", "Invalid credencials");
    }

    private boolean matchPassword(String loginPassword, String encodePassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(loginPassword, encodePassword);
    }
}
