package org.mpp2024.Gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mpp2024.Controllers.LoginController;
import org.mpp2024.Services.ServicesDispacher;


public class LoginGui extends Stage {

    private ServicesDispacher servicesDispacher;
    private String controllerName;

    public LoginGui(ServicesDispacher servicesDispacher, String controllerName) {
        this.servicesDispacher = servicesDispacher;
        this.controllerName = controllerName;
        SetUp(servicesDispacher, controllerName);
    }

    private void SetUp(ServicesDispacher servicesDispacher, String controllerName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerName));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            controller.setService(servicesDispacher);
            controller.setParentStage(this);
            this.setTitle("Login");
            this.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
