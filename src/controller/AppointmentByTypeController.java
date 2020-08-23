package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.Scenes;

import java.io.IOException;

public class AppointmentByTypeController {

    @FXML
    private Label appointmentsLbl;

    @FXML
    void backBttn(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Report.fxml");
    }
}