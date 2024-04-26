package org.iss2024.bug_detection.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iss2024.bug_detection.Domain.Admin;
import org.iss2024.bug_detection.Domain.Programmer;
import org.iss2024.bug_detection.Domain.Validator;
import org.iss2024.bug_detection.Gui.AdminTerminalGui;
import org.iss2024.bug_detection.Gui.ProgrammerTermianlGui;
import org.iss2024.bug_detection.Gui.RegisterGui;
import org.iss2024.bug_detection.Gui.ValidatorTerminalGui;
import org.iss2024.bug_detection.Services.ServicesDispacher;
import org.iss2024.bug_detection.Utility.Validation.ValidException;

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
            var employee = service.login(username, password);
            if (employee instanceof Programmer) {
                ProgrammerTermianlGui programmerTermianlGui = new ProgrammerTermianlGui(service, "/org/iss2024/bug_detection/ProgrammerTerminal.fxml", parentStage);
                programmerTermianlGui.show();
                parentStage.close();
            } else if (employee instanceof Admin) {
                AdminTerminalGui adminTerminalGui = new AdminTerminalGui(service, "/org/iss2024/bug_detection/AdminTerminal.fxml", parentStage);
                adminTerminalGui.show();
                parentStage.close();
            } else if (employee instanceof Validator) {
                ValidatorTerminalGui validatorTerminalGui = new ValidatorTerminalGui(service, "/org/iss2024/bug_detection/ValidatorTerminal.fxml", parentStage);
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
        RegisterGui registerGui = new RegisterGui(service, "/org/iss2024/bug_detection/Register.fxml", parentStage);
        registerGui.show();
        parentStage.close();
    }
}