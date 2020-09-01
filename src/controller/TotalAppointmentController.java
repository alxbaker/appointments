package controller;

import dao.DBAppointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Appointment;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TotalAppointmentController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int apptCount = Appointment.appointments.size();
        appointmentCountLbl.setText("There are " + apptCount + " total appointment(s)");
    }

    @FXML
    private Label appointmentCountLbl;

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Report.fxml");
    }


}
