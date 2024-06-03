package org.mpp2024.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.mpp2024.Domain.Admin;
import org.mpp2024.Domain.Bug;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Domain.Validator;
import org.mpp2024.Gui.AddProgrammerGui;
import org.mpp2024.Gui.AddValidatorGui;
import org.mpp2024.Services.Observer.AppObserverInterface;
import org.mpp2024.Services.ServicesDispacher;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.StreamSupport;


public class AdminTerminalController implements AppObserverInterface, Initializable {

    public TableView<Programmer> ProgrammersTable;
    public TableView<Validator> ValidatorsTable;
    public Button AddPorgrammerButton;
    public TableColumn<Programmer, String> ProgrammerName;
    public TableColumn<Programmer, String> PorgrammerPassword;
    public TableColumn<Validator, String> ValidatorName;
    public TableColumn<Validator, String> ValidatorPassword;
    private ServicesDispacher service;
    private Stage primaryStage;
    private Stage parentStage;
    ObservableList<Programmer> modelProgrammer = FXCollections.observableArrayList();
    ObservableList<Validator> modelValidator = FXCollections.observableArrayList();
    private Admin admin;
    public void setService(ServicesDispacher servicesDispacher) {
        this.service = servicesDispacher;

        initializeTable();
        initialize();
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void HandleLogout(ActionEvent actionEvent) {
        service.logout(admin);
        primaryStage.close();
        parentStage.show();
    }



    public void handleDeletePorgrammer(ActionEvent actionEvent) {

        Programmer selectedProgrammer = ProgrammersTable.getSelectionModel().getSelectedItem();
        if (selectedProgrammer != null) {
            service.getServiceProgrammer().delete(selectedProgrammer);
            modelProgrammer.remove(selectedProgrammer);
            service.notify(service.getServiceProgrammer().findAll(), null, null);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("No Programmer Selected. Please select a Programmer.");
            alert.showAndWait();
        }
    }

    public void handleAddValidator(ActionEvent actionEvent) {
        AddValidatorGui addProgrammerGui = new AddValidatorGui(service, "/AddValidator.fxml");
        addProgrammerGui.show();
    }
    public void handleAddProgrammer(ActionEvent actionEvent) {
        AddProgrammerGui addProgrammerGui = new AddProgrammerGui(service, "/AddProgrammer.fxml");
        addProgrammerGui.show();
    }


    private void setCellFactories() {
        // Set cell factory for ProgrammerName column
        ProgrammerName.setCellFactory(TextFieldTableCell.forTableColumn());
        ProgrammerName.setOnEditCommit(event -> {
            Programmer programmer = event.getRowValue();
            programmer.setUsername(event.getNewValue());
            service.getServiceProgrammer().update(programmer);  // Update in the backend
            service.notify(service.getServiceProgrammer().findAll(), service.getServiceValidator().findAll(), service.getServiceBug().findAll());  // Notify all observers
        });

        // Set cell factory for ProgrammerPassword column
        PorgrammerPassword.setCellFactory(TextFieldTableCell.forTableColumn());
        PorgrammerPassword.setOnEditCommit(event -> {
            Programmer programmer = event.getRowValue();
            programmer.setPassword(event.getNewValue());
            service.getServiceProgrammer().update(programmer);  // Update in the backend
            service.notify(service.getServiceProgrammer().findAll(), service.getServiceValidator().findAll(), service.getServiceBug().findAll());  // Notify all observers
        });

        // Set cell factory for ValidatorName column
        ValidatorName.setCellFactory(TextFieldTableCell.forTableColumn());
        ValidatorName.setOnEditCommit(event -> {
            Validator validator = event.getRowValue();
            validator.setUsername(event.getNewValue());
            service.getServiceValidator().update(validator);  // Update in the backend
            service.notify(service.getServiceProgrammer().findAll(), service.getServiceValidator().findAll(), service.getServiceBug().findAll());  // Notify all observers
        });

        // Set cell factory for ValidatorPassword column
        ValidatorPassword.setCellFactory(TextFieldTableCell.forTableColumn());
        ValidatorPassword.setOnEditCommit(event -> {
            Validator validator = event.getRowValue();
            validator.setPassword(event.getNewValue());
            service.getServiceValidator().update(validator);  // Update in the backend
            service.notify(service.getServiceProgrammer().findAll(), service.getServiceValidator().findAll(), service.getServiceBug().findAll());  // Notify all observers
        });
    }


    public void handleDeleteValidator(ActionEvent actionEvent) {
        Validator selectedValidator = ValidatorsTable.getSelectionModel().getSelectedItem();
        if (selectedValidator != null) {
            service.getServiceValidator().delete(selectedValidator);
            modelValidator.remove(selectedValidator);
            service.notify(null, service.getServiceValidator().findAll(), null);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("No Validator Selected. Please select a Validator.");
            alert.showAndWait();
        }
    }


    @Override
    public void update(Iterable<Programmer> programmers, Iterable<Validator> validators, Iterable<Bug> bugs) {

        Platform.runLater(() -> {


            if (programmers != null) {
                modelProgrammer.clear();
                for (Programmer programmer : programmers) {
                    modelProgrammer.add(programmer);
                }
            }
            if (validators != null) {
                modelValidator.clear();
                for (Validator validator : validators) {
                    modelValidator.add(validator);
                }
            }

        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void initialize() {

        ProgrammerName.setCellValueFactory(new PropertyValueFactory<>("username"));
        PorgrammerPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        ValidatorName.setCellValueFactory(new PropertyValueFactory<>("username"));
        ValidatorPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        ProgrammersTable.setEditable(true);
        ValidatorsTable.setEditable(true);

        // Set cell factories for editable columns
        setCellFactories();

        ProgrammersTable.setItems(modelProgrammer);
        ValidatorsTable.setItems(modelValidator);
    }

    private void initializeTable() {
        List<Programmer> programmers = null;
        programmers = StreamSupport.stream(service.getServiceProgrammer().findAll().spliterator(), false).toList();
        List<Programmer> finalProgrammers = programmers;
        Platform.runLater(() -> {
            modelProgrammer.setAll(finalProgrammers);
        });


        List<Validator> validators = null;
        validators = StreamSupport.stream(service.getServiceValidator().findAll().spliterator(), false).toList();

        List<Validator> finalValidators = validators;
        Platform.runLater(() -> {
            modelValidator.setAll(finalValidators);
        });

    }


}
