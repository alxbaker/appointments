package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.Scenes;

import java.io.IOException;

public class ReportController {

    @FXML
    void appointmentEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/AppointmentByType.fxml");
    }

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Main.fxml");
    }

    @FXML
    void countEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/TotalAppointment.fxml");
    }

    @FXML
    void scheduleEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/ConsultantSchedule.fxml");
    }

}
