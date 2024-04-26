package org.iss2024.bug_detection.Gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.iss2024.bug_detection.Controllers.AdminTerminalController;
import org.iss2024.bug_detection.Controllers.LoginController;
import org.iss2024.bug_detection.Services.ServicesDispacher;

public class AdminTerminalGui extends Stage {

    private ServicesDispacher servicesDispacher;
    private String controllerName;
    private final Stage parentStage;
    public AdminTerminalGui(ServicesDispacher servicesDispacher, String controllerName, Stage parentStage) {
        this.servicesDispacher = servicesDispacher;
        this.controllerName = controllerName;
        this.parentStage = parentStage;
        SetUp(servicesDispacher, controllerName);
    }

    private void SetUp(ServicesDispacher servicesDispacher, String controllerName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerName));
            Parent root = loader.load();
            AdminTerminalController controller = loader.getController();
            controller.setService(servicesDispacher);
            controller.setParentStage(parentStage);
            controller.setPrimaryStage(this);
            this.setTitle("Admin Terminal");
            this.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
