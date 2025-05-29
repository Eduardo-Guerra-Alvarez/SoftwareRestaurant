package com.eduardo.softrestaurant.controllerFX;

import com.eduardo.softrestaurant.dao.EmployeeDAO;
import com.eduardo.softrestaurant.entity.Employee;
import com.eduardo.softrestaurant.service.EmployeeService;
import com.eduardo.softrestaurant.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EmployeeControllerFX implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerFX.class);

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<String> roleBox;
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
    private TableColumn<EmployeeDAO, Boolean> statusCol;

    @Autowired
    private EmployeeService employeeService;

    private final ObservableList<EmployeeDAO> employeesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //employeeTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumns();
        listEmployees();
        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    private void setColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        // Get status True or False and then we change to Activo or Inactivo
        statusCol.setCellFactory(column -> new TableCell<EmployeeDAO, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    if (item) {
                        setText("Activo");
                        setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                    } else {
                        setText("Inactivo");
                        setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                    }
                }
            }
        });
    }

    private void listEmployees() {
        employeesList.clear();
        employeesList.addAll(employeeService.getAllEmployees());
        employeeTableView.setItems(employeesList);
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        isActive.cancelEdit();
    }

    public void addEmployee() {
        Employee newEmployee = new Employee();

        String name = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = password_hash.getText();

        if (name == null || name.isEmpty()) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "error", null, "Ingresa un nombre");
            return;
        }
        if (lastName == null || lastName.isEmpty()) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "error", null, "Ingresa un Apellido");
            return;
        }
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "error", null, "Ingresa un correo valido");
            return;
        }
        if (phone == null || phone.matches("\\d{10}") || phone.length() > 10) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "error", null, "Ingresa 10 numeros");
            return;
        }
        if (password == null || password.length() < 8) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "error", null, "La contraseña debe tener 8 caracteres");
            return;
        }

        newEmployee.setFirstName(name);
        newEmployee.setLastName(lastName);
        newEmployee.setEmail(email);
        newEmployee.setPhone(phone);
        newEmployee.setRole(roleBox.getValue());
        newEmployee.setPassword_hash(password);
        newEmployee.setIsActive(isActive.getValue().equals("Activo"));

        employeeService.saveEmployee(newEmployee);
        AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Exitoso", null, "Se creo el empleado exitosamente");
        listEmployees();
        clearFields();
        logger.info("Empleado creado con exito");
    }
}
