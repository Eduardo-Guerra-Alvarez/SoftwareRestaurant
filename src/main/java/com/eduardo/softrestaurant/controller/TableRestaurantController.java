package com.eduardo.softrestaurant.controller;

import com.eduardo.softrestaurant.dao.TableDAO;
import com.eduardo.softrestaurant.entity.TableRestaurant;
import com.eduardo.softrestaurant.service.TableRestaurantService;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableRestaurantController {
    @Autowired
    private TableRestaurantService tableRestaurantService;

    @GetMapping
    public ResponseEntity<List<TableDAO>> getTables() {
        return ResponseEntity.ok(tableRestaurantService.getAllTables());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableRestaurant> getTable(@PathVariable Long id) {
        return ResponseEntity.ok(tableRestaurantService.getTableById(id));
    }

    @PostMapping
    public ResponseEntity<TableRestaurant> createTable(@RequestBody TableRestaurant tableRestaurant) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tableRestaurantService.saveTable(tableRestaurant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableDAO> updateTable(@PathVariable Long id, @RequestBody TableRestaurant tableRestaurant) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tableRestaurantService.updateTable(id, tableRestaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTable(@PathVariable Long id) {
        return ResponseEntity.ok("Table Deleted");
    }

}
