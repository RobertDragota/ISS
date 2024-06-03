package org.mpp2024.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mpp2024.Services.ServicesDispacher;
import org.mpp2024.Utility.Validation.ValidException;


public class RegisterController {
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private ComboBox<String> RoleComboBox;
    @FXML
    private Button RegisterButton;
    private ServicesDispacher service;
    private Stage parentStage;
    private Stage primaryStage;

    public void HandleRegister(ActionEvent actionEvent) {

        String firstName = FirstNameField.getText();
        String password = PasswordField.getText();

        try {
            String role = RoleComboBox.getValue();
            if (role == null) {
                throw new ValidException("Role is not selected");
            }
           if( service.register(firstName, password, role)!=null) {
               System.out.println(role + " registered successfully!");
               primaryStage.close();
               parentStage.show();
           }

        } catch (ValidException e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

    }

    public void HandleRoleClick(ActionEvent actionEvent) {

    }

    public void setService(ServicesDispacher servicesDispacher) {
        this.service = servicesDispacher;
    }

    public void setPrimaryStage(Stage registerGui) {
        this.primaryStage = registerGui;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public void setRoleComboBox() {
        RoleComboBox.getItems().addAll("Programmer", "Validator");
    }
}
