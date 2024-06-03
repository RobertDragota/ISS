package org.mpp2024.Gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mpp2024.Controllers.AddProgrammerController;
import org.mpp2024.Controllers.AddValidatorController;
import org.mpp2024.Services.ServicesDispacher;

public class AddValidatorGui extends Stage {
    private ServicesDispacher servicesDispacher;
    private String controllerName;

    public AddValidatorGui( ServicesDispacher servicesDispacher, String controllerName) {
        this.servicesDispacher = servicesDispacher;
        this.controllerName = controllerName;
        SetUp(servicesDispacher, controllerName);
    }

    private void SetUp(ServicesDispacher servicesDispacher, String controllerName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerName));
            Parent root = loader.load();
            AddValidatorController controller = loader.getController();
            controller.setService(servicesDispacher);


            controller.setPrimaryStage(this);
            this.setTitle("Admin Terminal");
            this.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
