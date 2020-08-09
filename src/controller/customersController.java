package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.manageScenes;

import java.io.IOException;

public class customersController {

    @FXML
    private TableView<?> customerTbl;

    @FXML
    private TableColumn<?, ?> customerClm;

    @FXML
    private TableColumn<?, ?> lineOneClm;

    @FXML
    private TableColumn<?, ?> lineTwoClm;

    @FXML
    private TableColumn<?, ?> cityClm;

    @FXML
    private TableColumn<?, ?> postalCodeclm;

    @FXML
    private TableColumn<?, ?> phoneClm;

    @FXML
    void addCustomerEvent(ActionEvent event) throws IOException {
        new manageScenes().newStage(event,"/view/manageCustomers.fxml");
    }

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/main.fxml");
    }

    @FXML
    void customerSelEvent(ActionEvent event) throws IOException {
        new manageScenes().newStage(event,"/view/manageCustomers.fxml");
    }

    @FXML
    void deleteCustomerEvent(ActionEvent event) throws IOException {
        new manageScenes().newStage(event,"/view/manageCustomers.fxml");
    }

    @FXML
    void updateCustomerEvent(ActionEvent event) throws IOException {
        new manageScenes().newStage(event,"/view/manageCustomers.fxml");
    }

}


