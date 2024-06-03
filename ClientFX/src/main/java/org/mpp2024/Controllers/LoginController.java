package org.mpp2024.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mpp2024.Domain.Admin;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Domain.Validator;
import org.mpp2024.Gui.AdminTerminalGui;
import org.mpp2024.Gui.ProgrammerTermianlGui;
import org.mpp2024.Gui.RegisterGui;
import org.mpp2024.Gui.ValidatorTerminalGui;
import org.mpp2024.Services.ServicesDispacher;
import org.mpp2024.Utility.Validation.ValidException;


public class LoginController {
    @FXML
    private Button LoginButton;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField UserNameField;
    @FXML
    private Button RegisterButton;
    private ServicesDispacher service;
    private Stage parentStage;


    public void setService(ServicesDispacher service) {
        this.service = service;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public void HandleLogin(ActionEvent actionEvent) {
        String username = UserNameField.getText();
        String password = PasswordField.getText();

        try {
            var employee = service.getEmployee(username, password);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Employee logged in successfully! + " + service.getLoggedUsers().size());
            alert.showAndWait();
            if (employee instanceof Programmer) {
                ProgrammerTermianlGui programmerTermianlGui = new ProgrammerTermianlGui((Programmer) employee,service, "/ProgrammerTerminal.fxml", parentStage);
                service.login(employee, programmerTermianlGui.getController());
                programmerTermianlGui.show();
                parentStage.close();
            } else if (employee instanceof Admin) {
                AdminTerminalGui adminTerminalGui = new AdminTerminalGui((Admin)employee ,service, "/AdminTerminal.fxml", parentStage);
                service.login(employee, adminTerminalGui.getController());
                adminTerminalGui.show();
                parentStage.close();
            } else if (employee instanceof Validator) {
                ValidatorTerminalGui validatorTerminalGui = new ValidatorTerminalGui((Validator) employee,service, "/ValidatorTerminal.fxml", parentStage);
                service.login(employee, validatorTerminalGui.getController());
                validatorTerminalGui.show();
                parentStage.close();
            }
            UserNameField.clear();
            PasswordField.clear();
        } catch (ValidException e) {

            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    public void HandleRegister(ActionEvent actionEvent) {
        RegisterGui registerGui = new RegisterGui(service, "/Register.fxml", parentStage);
        registerGui.show();
        parentStage.close();
    }
}