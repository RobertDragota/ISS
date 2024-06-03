package org.mpp2024.Gui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mpp2024.Controllers.ProgrammerTerminalController;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Services.ServicesDispacher;

public class ProgrammerTermianlGui extends Stage {

    private ServicesDispacher servicesDispacher;
    private String controllerName;
    private final Stage parentStage;
    private ProgrammerTerminalController controller;
    private final Programmer programmer;

    public ProgrammerTermianlGui(Programmer programmer, ServicesDispacher servicesDispacher, String controllerName, Stage parentStage) {

        this.programmer = programmer;
        this.servicesDispacher = servicesDispacher;
        this.controllerName = controllerName;
        this.parentStage = parentStage;
        SetUp(servicesDispacher, controllerName);
    }

    public ProgrammerTerminalController getController() {
        return controller;
    }

    private void SetUp(ServicesDispacher servicesDispacher, String controllerName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerName));
            Parent root = loader.load();
            controller = loader.getController();
            controller.setService(servicesDispacher);
            controller.setProgrammer(programmer);
            controller.setPrimaryStage(this);
            controller.setParentStage(parentStage);
            this.setTitle("Programmer Terminal");
            this.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
