package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.Scenes;

import java.io.IOException;

public class ReportController {

    //switch to appointments by type screen
    @FXML
    void appointmentEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/AppointmentByType.fxml");
    }

    //switch to main screen
    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Main.fxml");
    }

    //switch to total appointment screen
    @FXML
    void countEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/TotalAppointment.fxml");
    }

    //switch to consultant schedule screen
    @FXML
    void scheduleEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/ConsultantSchedule.fxml");
    }

}
