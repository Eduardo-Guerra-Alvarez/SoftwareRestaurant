package com.eduardo.softrestaurant.dao;

import com.eduardo.softrestaurant.entity.TableRestaurant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableDAO {
    private Long id;
    private Integer capacity;
    private String status;

    public TableDAO(TableRestaurant tableRestaurant) {
        this.id = tableRestaurant.getId();
        this.capacity = tableRestaurant.getCapacity();
        this.status = tableRestaurant.getStatus();
    }
}
