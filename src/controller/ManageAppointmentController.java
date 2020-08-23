package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import util.Scenes;

public class ManageAppointmentController {

    @FXML
    private ComboBox<?> customerSel;

    @FXML
    private TextField title;

    @FXML
    private ComboBox<?> typeSel;

    @FXML
    private DatePicker startDateSel;

    @FXML
    private ComboBox<?> startTimeSel;

    @FXML
    private ComboBox<?> endDateSel;

    @FXML
    private ComboBox<?> endTimeSel;

    @FXML
    void backEvent(ActionEvent event) {
        new Scenes().closeSecStage(event);
    }

    @FXML
    void saveEvent(ActionEvent event) {
        new Scenes().closeSecStage(event);
    }

}
