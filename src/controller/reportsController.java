package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.manageScenes;

import java.io.IOException;

public class reportsController {

    @FXML
    void appointmentEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/appointmentsByType.fxml");
    }

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/main.fxml");
    }

    @FXML
    void countEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/totalAppointments.fxml");
    }

    @FXML
    void scheduleEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/consultantSchedules.fxml");
    }

}
