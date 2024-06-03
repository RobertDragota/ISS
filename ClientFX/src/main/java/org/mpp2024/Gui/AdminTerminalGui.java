package org.mpp2024.Gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mpp2024.Controllers.AdminTerminalController;
import org.mpp2024.Domain.Admin;
import org.mpp2024.Services.ServicesDispacher;


public class AdminTerminalGui extends Stage {

    private ServicesDispacher servicesDispacher;
    private String controllerName;
    private final Stage parentStage;
    private final Admin admin;
    private AdminTerminalController controller;

    public AdminTerminalGui(Admin admin, ServicesDispacher servicesDispacher, String controllerName, Stage parentStage) {
        this.servicesDispacher = servicesDispacher;
        this.controllerName = controllerName;
        this.parentStage = parentStage;
        this.admin = admin;
        SetUp(servicesDispacher, controllerName);
    }

    public AdminTerminalController getController() {
        return controller;
    }

    private void SetUp(ServicesDispacher servicesDispacher, String controllerName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerName));
            Parent root = loader.load();
            controller = loader.getController();
            controller.setService(servicesDispacher);
            controller.setAdmin(admin);
            controller.setParentStage(parentStage);
            controller.setPrimaryStage(this);
            this.setTitle("Admin Terminal");
            this.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
