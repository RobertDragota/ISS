package org.iss2024.bug_detection.Controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.iss2024.bug_detection.Gui.ProgrammerTermianlGui;
import org.iss2024.bug_detection.Services.ServicesDispacher;

public class AdminTerminalController {

    private ServicesDispacher service;
    private Stage primaryStage;
    private Stage parentStage;

    public void setService(ServicesDispacher servicesDispacher) {
        this.service = servicesDispacher;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void HandleLogout(ActionEvent actionEvent) {
        primaryStage.close();
        parentStage.show();
    }
}
