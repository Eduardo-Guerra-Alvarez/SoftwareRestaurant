package com.eduardo.softrestaurant.controllerFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class MainControllerFX implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private StackPane centralContent;

    @Autowired
    private ApplicationContext context;

    private void loadView(String viewFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + viewFXML));
            loader.setControllerFactory(context::getBean);
            Node view = loader.load();
            centralContent.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openEmployee() {
        loadView("employee.fxml");
    }

    @FXML
    private void abrirOrdenes() {
        loadView("Ordenes.fxml");
    }

    @FXML
    private void openTables() {
        loadView("tableRestaurant.fxml");
    }
}
