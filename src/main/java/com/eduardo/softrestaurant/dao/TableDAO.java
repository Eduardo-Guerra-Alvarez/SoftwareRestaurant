package com.eduardo.softrestaurant.dao;

import com.eduardo.softrestaurant.entity.TableRestaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TableDAO {
    private Integer table_number;
    private Integer capacity;
    private String status; // available, occupied, reserved
    private List<OrderSimpleDAO> orders;

    public TableDAO(TableRestaurant tableRestaurant) {
        this.table_number = tableRestaurant.getTable_number();
        this.capacity = tableRestaurant.getCapacity();
        this.status = tableRestaurant.getStatus();
        this.orders = tableRestaurant.getOrders().stream()
                .map(OrderSimpleDAO::new)
                .collect(Collectors.toList());
    }
}
