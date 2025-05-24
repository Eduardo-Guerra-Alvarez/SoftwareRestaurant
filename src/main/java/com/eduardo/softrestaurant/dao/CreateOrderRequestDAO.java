package com.eduardo.softrestaurant.dao;

import lombok.Data;

@Data
public class CreateOrderRequestDAO {
    private Long employeeId;
    private Long tableId;
}
