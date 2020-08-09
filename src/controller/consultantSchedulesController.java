package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import util.manageScenes;

import java.io.IOException;

public class consultantSchedulesController {

    @FXML
    private TableColumn<?, ?> cnsltClm;

    @FXML
    private TableColumn<?, ?> titleClm;

    @FXML
    private TableColumn<?, ?> typeClm;

    @FXML
    private TableColumn<?, ?> customerClm;

    @FXML
    private TableColumn<?, ?> startClm;

    @FXML
    private TableColumn<?, ?> endClm;

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new manageScenes().setScene(event, "/view/reports.fxml");
    }

    @FXML
    void cnsltEvent(ActionEvent event) {

    }

    @FXML
    void cnsltTbl(ActionEvent event) {

    }
}
