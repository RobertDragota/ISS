package org.mpp2024.Gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mpp2024.Controllers.ValidatorTerminalController;
import org.mpp2024.Domain.Validator;
import org.mpp2024.Services.ServicesDispacher;


public class ValidatorTerminalGui extends Stage {

    private ServicesDispacher servicesDispacher;
    private String controllerName;
    private final Stage parentStage;
    private final Validator validator;
    private ValidatorTerminalController controller;

    public ValidatorTerminalGui(Validator validator, ServicesDispacher servicesDispacher, String controllerName, Stage parentStage) {

        this.servicesDispacher = servicesDispacher;
        this.controllerName = controllerName;
        this.parentStage = parentStage;
        this.validator = validator;
        SetUp(servicesDispacher, controllerName);
    }

    public ValidatorTerminalController getController() {
        return controller;
    }

    private void SetUp(ServicesDispacher servicesDispacher, String controllerName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerName));
            Parent root = loader.load();
            controller = loader.getController();
            controller.setService(servicesDispacher);
            controller.setValidator(validator);
            controller.setParentStage(parentStage);
            controller.setPrimaryStage(this);
            this.setTitle("Validator Terminal");
            this.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
