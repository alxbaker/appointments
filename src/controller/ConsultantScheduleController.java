package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.User;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConsultantScheduleController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cnslCmb.setItems(User.users);
    }

    @FXML
    private ComboBox<User> cnslCmb;

    @FXML
    private TableView<Appointment> cnsltTbl;

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
        new Scenes().setScene(event, "/view/Report.fxml");
    }

    @FXML
    void cnsltEvent(ActionEvent event) {
        User currCnslt = cnslCmb.getValue();
    }

    @FXML
    void cnsltTbl(ActionEvent event) {
        cnsltTbl.setItems(Appointment.appointments);
    }


}
