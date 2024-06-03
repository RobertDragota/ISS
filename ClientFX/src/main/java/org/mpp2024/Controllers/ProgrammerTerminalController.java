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
import javafx.stage.Stage;
import org.mpp2024.Domain.Bug;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Domain.Validator;
import org.mpp2024.Services.Observer.AppObserverInterface;
import org.mpp2024.Services.ServicesDispacher;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.StreamSupport;


public class ProgrammerTerminalController implements AppObserverInterface, Initializable {
    public TableView<Bug> BugTable;
    public TableColumn<Bug, String> BugColumn;
    public TableColumn<Bug, String> StatusColumn;
    @FXML
    private Button LogoutButton;
    @FXML
    private Button DeleteBugButton;
    private ServicesDispacher service;
    private Stage primaryStage;
    private Stage parentStage;
    private Programmer programmer;
    ObservableList<Bug> modelBug = FXCollections.observableArrayList();

    public void setService(ServicesDispacher servicesDispacher) {
        service = servicesDispacher;
        initializeTable();
        initialize();
    }

    public void setParentStage(Stage programmerTermianlGui) {
        this.parentStage = programmerTermianlGui;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void handleDeleteBug(ActionEvent actionEvent) {
        Bug selectedBug = BugTable.getSelectionModel().getSelectedItem();
        if (selectedBug != null) {
            service.getServiceBug().delete(selectedBug);
            modelBug.remove(selectedBug);
            service.notify(null, null, service.getServiceBug().findAll());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("No Programmer Selected. Please select a Programmer.");
            alert.showAndWait();
        }
    }

    public void handleLogout(ActionEvent actionEvent) {
        service.logout(programmer);
        primaryStage.close();
        parentStage.show();
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

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void initialize() {

        BugColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
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
