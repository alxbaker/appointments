package controller;

import javafx.collections.transformation.FilteredList;
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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ConsultantScheduleController implements Initializable {

    //populate combobox with all user names
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cnslCmb.setItems(User.users);
    }

    @FXML
    private ComboBox<User> cnslCmb;

    @FXML
    private TableView<Appointment> cnsltTbl;

    @FXML
    private TableColumn<Appointment, String> cnsltClm;

    @FXML
    private TableColumn<Appointment, String> titleClm;

    @FXML
    private TableColumn<Appointment, String> typeClm;

    @FXML
    private TableColumn<Appointment, String> customerClm;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startClm;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endClm;

    //go back to the report screen
    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Report.fxml");
    }

    //when the user selects a user, populate table with that user's appointments
    @FXML
    void cnsltEvent(ActionEvent event) {
        User currCnslt = cnslCmb.getValue();

        //this Lambda expression creates a filtered list of appointments for the logged in user
        FilteredList<Appointment> userAppointments = new FilteredList<>(Appointment.getAppointments(), e -> e.getUser() == currCnslt);

        cnsltTbl.setItems(userAppointments);
        cnsltClm.setCellValueFactory(new PropertyValueFactory<>("userName"));
        titleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeClm.setCellValueFactory(new PropertyValueFactory<>("type"));
        startClm.setCellValueFactory(new PropertyValueFactory<>("displayStart"));
        endClm.setCellValueFactory(new PropertyValueFactory<>("displayEnd"));
        customerClm.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }

}
