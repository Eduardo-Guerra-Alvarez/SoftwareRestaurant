package com.eduardo.softrestaurant.controllerFX;

import com.eduardo.softrestaurant.dao.EmployeeDAO;
import com.eduardo.softrestaurant.entity.Employee;
import com.eduardo.softrestaurant.service.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EmployeeControllerFX implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private ComboBox<String> role;
    @FXML
    private PasswordField password_hash;
    @FXML
    private ComboBox<String> isActive;
    @FXML
    private TableView<EmployeeDAO> employeeTableView;
    @FXML
    private TableColumn<EmployeeDAO, Long> idCol;
    @FXML
    private TableColumn<EmployeeDAO, String> firstNameCol;
    @FXML
    private TableColumn<EmployeeDAO, String> lastNameCol;
    @FXML
    private TableColumn<EmployeeDAO, String> emailCol;
    @FXML
    private TableColumn<EmployeeDAO, String> phoneCol;
    @FXML
    private TableColumn<EmployeeDAO, String> roleCol;
    @FXML
    private TableColumn<EmployeeDAO, String> statusCol;

    @Autowired
    private EmployeeService employeeService;

    private final ObservableList<EmployeeDAO> employeesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //employeeTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumns();
        listEmployees();
    }

    private void setColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("isActive"));
    }

    private void listEmployees() {
        employeesList.clear();
        employeesList.addAll(employeeService.getAllEmployees());
        employeeTableView.setItems(employeesList);
    }

    private void clearFields() {
        name.clear();
        lastName.clear();
        email.clear();
        phone.clear();
        isActive.cancelEdit();
    }
}
