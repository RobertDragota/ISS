package org.iss2024.bug_detection.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iss2024.bug_detection.Gui.ProgrammerTermianlGui;
import org.iss2024.bug_detection.Services.ServicesDispacher;

public class ValidatorTerminalController {
    @FXML
    private Button LogoutButton;
    @FXML
    private Button UploadBugButton;
    @FXML
    private TextField BugNameField;
    @FXML
    private TextArea BugDescriptionField;
    private ServicesDispacher service;
    private Stage parentStage;
    private Stage primaryStage;

    public void setService(ServicesDispacher servicesDispacher) {
        service = servicesDispacher;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void HandleUploadBug(ActionEvent actionEvent) {
    }

    public void HandleLogout(ActionEvent actionEvent) {
        primaryStage.close();
        parentStage.show();
    }
}
