package org.mpp2024.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.mpp2024.Domain.Bug;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Domain.Validator;
import org.mpp2024.Services.Observer.AppObserverInterface;
import org.mpp2024.Services.ServicesDispacher;

import java.util.List;
import java.util.stream.StreamSupport;


public class ValidatorTerminalController implements AppObserverInterface {
    public TableView<Bug> BugTable;
    public TableColumn<Bug,String> BugColumn;
    public TableColumn<Bug,String> DescriptionColumn;
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
    private Validator validator;
    ObservableList<Bug> modelBug = FXCollections.observableArrayList();

    public void setService(ServicesDispacher servicesDispacher) {
        service = servicesDispacher;
        initializeTable();
        initialize();
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

  

    @Override
    public void update(Iterable<Programmer> programmers, Iterable<Validator> validators, Iterable<Bug> bugs) {

        Platform.runLater(() -> {

            if (bugs != null) {
                modelBug.clear();
                for (Bug bug : bugs) {
                    modelBug.add(bug);
                }
            }

        });
    }

    public void handleUploadBug(ActionEvent actionEvent) {
        String name = BugNameField.getText();
        String description = BugDescriptionField.getText();
        Bug bug = new Bug(name, description, validator);
        service.getServiceBug().save(bug);
        modelBug.add(bug);
        service.notify(null, null, service.getServiceBug().findAll());
    }

    public void handleLogout(ActionEvent actionEvent) {
        service.logout(validator);
        primaryStage.close();
        parentStage.show();
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }


    private void setCellFactories() {
        // Set cell factory for ProgrammerName column
        BugColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        BugColumn.setOnEditCommit(event -> {
            Bug bug = event.getRowValue();
            bug.setName(event.getNewValue());
            service.getServiceBug().update(bug);  // Update in the backend
            service.notify(null, null, service.getServiceBug().findAll());  // Notify all observers
        });

        DescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionColumn.setOnEditCommit(event -> {
            Bug bug = event.getRowValue();
            bug.setDescription(event.getNewValue());
            service.getServiceBug().update(bug);  // Update in the backend
            service.notify(null, null, service.getServiceBug().findAll());  // Notify all observers
        });
    }

    @FXML
    public void initialize() {

        BugColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        BugTable.setEditable(true);

        setCellFactories();

        BugTable.setItems(modelBug);
    }

    private void initializeTable() {
        List<Bug> bugs = null;
        bugs = StreamSupport.stream(service.getServiceBug().findAll().spliterator(), false).toList();
        List<Bug> finalBugs = bugs;
        Platform.runLater(() -> {
            modelBug.setAll(finalBugs);
        });


    }
}
