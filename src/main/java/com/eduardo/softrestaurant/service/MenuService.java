package com.eduardo.softrestaurant.service;

import com.eduardo.softrestaurant.entity.Menu;
import com.eduardo.softrestaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenu(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateMenu(Long id, Menu updateMenu) {
        return menuRepository.findById(id)
                .map(menu -> {
                    menu.setCategory(updateMenu.getCategory());
                    menu.setName(updateMenu.getName());
                    menu.setPrice(updateMenu.getPrice());
                    menu.setIsActive(updateMenu.getIsActive());
                    menu.setDescription(updateMenu.getDescription());
                    return menuRepository.save(menu);
                }).orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    public void deleteMenu(Long id) {
        menuRepository.findById(id)
                .ifPresentOrElse(
                        menuRepository::delete,
                        () -> {
                            throw new RuntimeException("Menu not found");
                        }
                );
    }
}
