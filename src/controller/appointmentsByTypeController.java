package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.manageScenes;

import java.io.IOException;

public class appointmentsByTypeController {

    @FXML
    private Label appointmentsLbl;

    @FXML
    void backBttn(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/reports.fxml");
    }
}