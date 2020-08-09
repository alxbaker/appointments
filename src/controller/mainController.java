package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.manageScenes;

import java.io.IOException;

public class mainController {

    @FXML
    void calendarEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/calendar.fxml");
    }

    @FXML
    void customerEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/customers.fxml");

    }

    @FXML
    void logoutEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/login.fxml");
    }

    @FXML
    void reportEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/reports.fxml");
    }

}
