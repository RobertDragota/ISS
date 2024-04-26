package org.iss2024.bug_detection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.iss2024.bug_detection.Domain.Employee;
import org.iss2024.bug_detection.Domain.Programmer;
import org.iss2024.bug_detection.Domain.Validator;
import org.iss2024.bug_detection.Gui.LoginGui;
import org.iss2024.bug_detection.Repository.*;
import org.iss2024.bug_detection.Services.ServiceAdmin;
import org.iss2024.bug_detection.Services.ServiceProgrammer;
import org.iss2024.bug_detection.Services.ServiceValidator;
import org.iss2024.bug_detection.Services.ServicesDispacher;
import org.iss2024.bug_detection.Utility.DB_Utils;
import org.iss2024.bug_detection.Utility.Validation.EmployeeValidator;
import org.iss2024.bug_detection.Utility.Validation.IValidator;
import org.iss2024.bug_detection.Utility.Validation.ValidException;

import java.io.IOException;

public class StartApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DB_Utils db_utils = new DB_Utils("db.config");
        Repo_Programmer_Interface repoProgrammer = new RepoProgrammer(db_utils);
        Repo_Validator_Interface repoValidator = new RepoValidator(db_utils);
        Repo_Admin_Interface repoAdmin = new RepoAdmin(db_utils);
        IValidator<Employee> employeeValidator= new EmployeeValidator();
        ServiceProgrammer serviceProgrammer = new ServiceProgrammer(repoProgrammer,employeeValidator);
        ServiceValidator serviceValidator = new ServiceValidator(repoValidator,employeeValidator);
        ServiceAdmin serviceAdmin = new ServiceAdmin(repoAdmin,employeeValidator);
        ServicesDispacher servicesDispacher = new ServicesDispacher(serviceProgrammer,serviceValidator,serviceAdmin,employeeValidator);
        LoginGui loginGui = new LoginGui(servicesDispacher, "/org/iss2024/bug_detection/Login.fxml");
        loginGui.show();
    }

    public static void main(String[] args) {
        launch();
    }
}