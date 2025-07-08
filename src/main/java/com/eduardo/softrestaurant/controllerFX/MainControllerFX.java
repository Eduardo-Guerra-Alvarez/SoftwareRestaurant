package com.eduardo.softrestaurant.controllerFX;

import com.eduardo.softrestaurant.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.print.DocFlavor;
import java.io.IOException;
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

            URL fxmlUrl = getClass().getResource("/view/" + viewFXML);
            if(fxmlUrl == null) {
                throw new IllegalArgumentException("No se encontró el archivo FXML: " + viewFXML);
            }
            FXMLLoader loader = new FXMLLoader(fxmlUrl);

            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + viewFXML));
            loader.setControllerFactory(context::getBean);
            Node view = loader.load();
            centralContent.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "", "Error al cargar la vista: " + viewFXML);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "", "Ocurrió un error al mostrar la vista.");

        }
    }

    @FXML
    private void openEmployee() {
        loadView("employee.fxml");
    }

    @FXML
    private void openOrders() {
        loadView("order.fxml");
    }

    @FXML
    private void openTables() {
        loadView("tableRestaurant.fxml");
    }
}
