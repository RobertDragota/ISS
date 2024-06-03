package org.mpp2024;


import javafx.application.Application;
import javafx.stage.Stage;
import org.mpp2024.Domain.Employee;
import org.mpp2024.Gui.LoginGui;
import org.mpp2024.Repository.*;
import org.mpp2024.Services.*;
import org.mpp2024.Utility.Validation.EmployeeValidator;
import org.mpp2024.Utility.Validation.IValidator;

import java.io.IOException;

public class StartApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Repo_Programmer_Interface repoProgrammer = new RepoProgrammer();
        Repo_Validator_Interface repoValidator = new RepoValidator();
        Repo_Admin_Interface repoAdmin = new RepoAdmin();
        Repo_Bug_Interface repoBug = new RepoBug();
        IValidator<Employee> employeeValidator= new EmployeeValidator();
        ServiceProgrammer serviceProgrammer = new ServiceProgrammer(repoProgrammer,employeeValidator);
        ServiceValidator serviceValidator = new ServiceValidator(repoValidator,employeeValidator);
        ServiceAdmin serviceAdmin = new ServiceAdmin(repoAdmin,employeeValidator);
        ServiceBug serviceBug = new ServiceBug(repoBug);
        ServicesDispacher servicesDispacher = new ServicesDispacher(serviceProgrammer,serviceValidator,serviceAdmin,employeeValidator,serviceBug);
        LoginGui loginGui = new LoginGui(servicesDispacher, "/Login.fxml");
        loginGui.show();
        LoginGui loginGui1 = new LoginGui(servicesDispacher, "/Login.fxml");
        loginGui1.show();

    }

    public static void main(String[] args) {
        launch();
    }
}