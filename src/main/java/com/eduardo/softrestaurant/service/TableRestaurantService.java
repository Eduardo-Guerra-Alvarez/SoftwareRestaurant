package com.eduardo.softrestaurant.service;

import com.eduardo.softrestaurant.entity.TableRestaurant;
import com.eduardo.softrestaurant.repository.TableRestaurantRepository;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableRestaurantService {

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    public List<TableRestaurant> getAllTables() {
        return tableRestaurantRepository.findAll();
    }

    public TableRestaurant getTableById(Long id) {
        return tableRestaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
    }

    public TableRestaurant saveTable(TableRestaurant tableRestaurant) {
        return tableRestaurantRepository.save(tableRestaurant);
    }

    public TableRestaurant updateTable(Long id, TableRestaurant tableRestaurant) {
        return tableRestaurantRepository.findById(id)
                .map(tableRestaurantUpdated -> {
                    tableRestaurantUpdated.setCapacity(tableRestaurant.getCapacity());
                    tableRestaurantUpdated.setTable_number(tableRestaurant.getTable_number());
                    tableRestaurantUpdated.setStatus(tableRestaurant.getStatus());
                    return tableRestaurantRepository.save(tableRestaurantUpdated);
                })
                .orElseThrow(() -> new RuntimeException("Tabled not found"));
    }

    public void deleteTable(Long id) {
        tableRestaurantRepository.findById(id).ifPresentOrElse(
                tableRestaurantRepository::delete,
                () -> {throw new RuntimeException("Table not found");}
        );
    }
}
