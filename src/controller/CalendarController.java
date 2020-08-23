package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.Scenes;

import java.io.IOException;

public class CalendarController {
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab allTab;

    @FXML
    private TableView<?> allTbl;

    @FXML
    private TableColumn<?, ?> allConsultantClm;

    @FXML
    private TableColumn<?, ?> allTitleClm;

    @FXML
    private TableColumn<?, ?> allTypeClm;

    @FXML
    private TableColumn<?, ?> allCustomerClm;

    @FXML
    private TableColumn<?, ?> allStartClm;

    @FXML
    private TableColumn<?, ?> allEndClm;

    @FXML
    private Tab monthTab;

    @FXML
    private TableView<?> monthTbl;

    @FXML
    private TableColumn<?, ?> monthConsultantClm;

    @FXML
    private TableColumn<?, ?> monthTitleClm;

    @FXML
    private TableColumn<?, ?> monthTypeClm;

    @FXML
    private TableColumn<?, ?> monthCustomerClm;

    @FXML
    private TableColumn<?, ?> monthStartClm;

    @FXML
    private TableColumn<?, ?> monthEndClm;

    @FXML
    private Tab weekTab;

    @FXML
    private TableView<?> weekTbl;

    @FXML
    private TableColumn<?, ?> weekConsultantClm;

    @FXML
    private TableColumn<?, ?> weekTitleClm;

    @FXML
    private TableColumn<?, ?> weekTypeClm;

    @FXML
    private TableColumn<?, ?> weekCustomerClm;

    @FXML
    private TableColumn<?, ?> weekStartclm;

    @FXML
    private TableColumn<?, ?> weekEndClm;

    @FXML
    private ComboBox<?> apptCmb;

    @FXML
    void addAptEvent(ActionEvent event) throws IOException {
        new Scenes().newStage(event, "/view/ManageAppointment.fxml");
    }

    @FXML
    void apptCmbEvent(ActionEvent event) throws IOException {

    }

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Main.fxml");
    }

    @FXML
    void delAptEvent(ActionEvent event) throws IOException {
        new Scenes().newStage(event, "/view/ManageAppointment.fxml");
    }

    @FXML
    void updateAptEvent(ActionEvent event) throws IOException {
        new Scenes().newStage(event, "/view/ManageAppointment.fxml");
    }

}
