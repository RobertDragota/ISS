package org.mpp2024.Gui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mpp2024.Controllers.RegisterController;
import org.mpp2024.Services.ServicesDispacher;

public class RegisterGui extends Stage {
    private ServicesDispacher servicesDispacher;
    private String controllerName;
    private final Stage parentStage;
    public RegisterGui(ServicesDispacher servicesDispacher, String controllerName, Stage parentStage) {

        this.servicesDispacher = servicesDispacher;
        this.controllerName = controllerName;
        this.parentStage = parentStage;
        SetUp(servicesDispacher, controllerName);
    }


    private void SetUp(ServicesDispacher servicesDispacher, String controllerName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerName));
            Parent root = loader.load();
            RegisterController controller = loader.getController();
            controller.setService(servicesDispacher);
            controller.setPrimaryStage(this);
            controller.setParentStage(parentStage);
            controller.setRoleComboBox();
            this.setTitle("Register");
            this.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
