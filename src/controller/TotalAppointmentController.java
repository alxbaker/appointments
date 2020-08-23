package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.Scenes;

import java.io.IOException;

public class TotalAppointmentController {

    @FXML
    private Label appointmentCountLbl;

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Report.fxml");
    }

}
