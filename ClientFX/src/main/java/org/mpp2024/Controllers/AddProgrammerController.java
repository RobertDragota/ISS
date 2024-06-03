package org.mpp2024.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Services.ServicesDispacher;

public class AddProgrammerController {

    public TextField ProgrammerName;
    public TextField ProgrammerPassword;
    private ServicesDispacher service;
    private Stage primaryStage;

    public void setService(ServicesDispacher service) {
        this.service = service;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void handleAddProgrammer(ActionEvent actionEvent) {
        //var programmer = new Programmer(ProgrammerName.getText(), ProgrammerPassword.getText());
        service.register(ProgrammerName.getText(), ProgrammerPassword.getText(), "Programmer");
        service.notify(service.getServiceProgrammer().findAll(), null, null);
        primaryStage.close();
    }
}
