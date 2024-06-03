package org.mpp2024.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mpp2024.Services.ServicesDispacher;

public class AddValidatorController {
    public TextField ValidatorName;
    public TextField ValidatorPassword;
    private ServicesDispacher service;
    private Stage primaryStage;

    public void setService(ServicesDispacher service) {
        this.service = service;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void handleAddValidator(ActionEvent actionEvent) {
        service.register(ValidatorName.getText(), ValidatorPassword.getText(), "Validator");
        service.notify(null, service.getServiceValidator().findAll(), null);
        primaryStage.close();
    }
}
