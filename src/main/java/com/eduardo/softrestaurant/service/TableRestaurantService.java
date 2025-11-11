package com.eduardo.softrestaurant.service;

import com.eduardo.softrestaurant.dao.TableDAO;
import com.eduardo.softrestaurant.entity.TableRestaurant;
import com.eduardo.softrestaurant.repository.TableRestaurantRepository;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableRestaurantService {

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    @Transactional
    public List<TableDAO> getAllTables() {
        return tableRestaurantRepository.findAll()
                .stream()
                .map(TableDAO::new).collect(Collectors.toList());
    }

    public TableRestaurant getTableById(Long id) {
        return tableRestaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
    }

    public TableRestaurant saveTable(TableRestaurant tableRestaurant) {
        return tableRestaurantRepository.save(tableRestaurant);
    }

    public TableDAO updateTable(Long id, TableRestaurant tableRestaurant) {
        return tableRestaurantRepository.findById(id)
                .map(tableRestaurantUpdated -> {
                    tableRestaurantUpdated.setCapacity(tableRestaurant.getCapacity());
                    tableRestaurantUpdated.setStatus(tableRestaurant.getStatus());
                    tableRestaurantRepository.save(tableRestaurantUpdated);
                    return new TableDAO(tableRestaurantUpdated);
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
