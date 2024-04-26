package org.iss2024.bug_detection.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.iss2024.bug_detection.Gui.ProgrammerTermianlGui;
import org.iss2024.bug_detection.Services.ServicesDispacher;

public class ProgrammerTerminalController {
    @FXML
    private Button LogoutButton;
    @FXML
    private Button DeleteBugButton;
    private ServicesDispacher service;
    private Stage primaryStage;
    private Stage parentStage;

    public void setService(ServicesDispacher servicesDispacher) {
        service = servicesDispacher;
    }

    public void setParentStage(Stage programmerTermianlGui) {
        this.parentStage = programmerTermianlGui;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void HandleDeleteBug(ActionEvent actionEvent) {
    }

    public void HandleLogout(ActionEvent actionEvent) {
        primaryStage.close();
        parentStage.show();
    }
}
