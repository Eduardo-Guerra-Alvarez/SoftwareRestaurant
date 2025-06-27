package com.eduardo.softrestaurant.controllerFX;

import com.eduardo.softrestaurant.entity.TableRestaurant;
import com.eduardo.softrestaurant.service.TableRestaurantService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class TableRestaurantControllerFX implements Initializable {
    @FXML
    private TextField capacity;

    @FXML
    private ComboBox<String> status;

    @FXML
    private TableView<TableRestaurant> tableView;
    @FXML
    private TableColumn<TableRestaurant, Long> tableIdCol;
    @FXML
    private TableColumn<TableRestaurant, String> capacityCol;
    @FXML
    private TableColumn<TableRestaurant, String> statusCol;

    @Autowired
    private TableRestaurantService tableRestaurantService;
    private final ObservableList<TableRestaurant> tableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumns();
        listTables();
    }

    private void setColumns() {
        tableIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    private void listTables() {
        tableList.clear();
        tableList.addAll(tableRestaurantService.getAllTables());
        if(!tableList.isEmpty()){
            tableView.setItems(tableList);
        }
    }

    public void addTable() {

    }

    public void editTable() {

    }
    public void deleteTable() {

    }
}
