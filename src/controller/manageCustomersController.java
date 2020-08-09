package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import util.manageScenes;

public class manageCustomersController {

    @FXML
    private TextField customerTxt;

    @FXML
    private TextField lineOneTxt;

    @FXML
    private TextField lineTwoTxt;

    @FXML
    private TextField cityTxt;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    private TextField countryTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    void backEvent(ActionEvent event) {
        new manageScenes().closeSecStage(event);
    }

    @FXML
    void saveEvent(ActionEvent event) {
        new manageScenes().closeSecStage(event);
    }
}
