package com.eduardo.softrestaurant.dao;
import com.eduardo.softrestaurant.entity.Menu;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MenuDAO {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private String category; // food, drink, dessert
    private Boolean isActive;

    public MenuDAO(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.description = menu.getDescription();
        this.price = menu.getPrice();
        this.category = menu.getCategory();
        this.isActive = menu.getIsActive();
    }
}
