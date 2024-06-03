package org.mpp2024.Gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mpp2024.Controllers.AddProgrammerController;
import org.mpp2024.Controllers.AdminTerminalController;
import org.mpp2024.Domain.Admin;
import org.mpp2024.Services.ServicesDispacher;

public class AddProgrammerGui extends Stage {
    private ServicesDispacher servicesDispacher;
    private String controllerName;
    private AddProgrammerController controller;

    public AddProgrammerGui( ServicesDispacher servicesDispacher, String controllerName) {
        this.servicesDispacher = servicesDispacher;
        this.controllerName = controllerName;
        SetUp(servicesDispacher, controllerName);
    }

    private void SetUp(ServicesDispacher servicesDispacher, String controllerName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerName));
            Parent root = loader.load();
            controller = loader.getController();
            controller.setService(servicesDispacher);


            controller.setPrimaryStage(this);
            this.setTitle("Admin Terminal");
            this.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
