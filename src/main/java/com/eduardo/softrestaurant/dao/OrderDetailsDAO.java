package com.eduardo.softrestaurant.dao;

import com.eduardo.softrestaurant.entity.Menu;
import com.eduardo.softrestaurant.entity.Order;
import com.eduardo.softrestaurant.entity.OrderDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDAO {
    private Long id;
    private String menu;
    private Integer quantity;
    private Float unit_price;
    private Float subtotal;


    public OrderDetailsDAO(OrderDetails orderDetails) {
        this.id = orderDetails.getId();
        this.quantity = orderDetails.getQuantity();
        this.unit_price = orderDetails.getUnit_price();
        this.subtotal = orderDetails.getSubtotal();
        this.menu = orderDetails.getMenu().getName();
    }
}
