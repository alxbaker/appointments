package controller;

import dao.DBAppointment;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.User;
import util.Alerts;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    //this Lambda expression creates a filtered list of appointments for the logged in user
    FilteredList<Appointment> userAppointments = new FilteredList<>(Appointment.getAppointments(), e -> e.getUser() == User.getCurrentUser());

    //this Lambda expression creates a filtered list of appointments for the current user in the current month
    FilteredList<Appointment> monthAppointments = new FilteredList<>(userAppointments, e -> e.getStart().getMonth() == LocalDate.now().getMonth());

    //this Lambda expression creates a filtered list of appointments for the current this week of the year
    FilteredList<Appointment> weekAppointments = new FilteredList<>(userAppointments, e -> e.getStart().get(WeekFields.of(Locale.getDefault()).weekOfYear()) == LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfYear()));

    //set all tables, columns and combo boxes with values
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apptCmb.setItems(userAppointments);
        allTbl.setItems(userAppointments);
        allConsultantClm.setCellValueFactory(new PropertyValueFactory<>("userName"));
        allTitleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
        allTypeClm.setCellValueFactory(new PropertyValueFactory<>("type"));
        allStartClm.setCellValueFactory(new PropertyValueFactory<>("displayStart"));
        allEndClm.setCellValueFactory(new PropertyValueFactory<>("displayEnd"));
        allCustomerClm.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        monthTbl.setItems(monthAppointments);
        monthConsultantClm.setCellValueFactory(new PropertyValueFactory<>("userName"));
        monthTitleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthTypeClm.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthStartClm.setCellValueFactory(new PropertyValueFactory<>("displayStart"));
        monthEndClm.setCellValueFactory(new PropertyValueFactory<>("displayEnd"));
        monthCustomerClm.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        weekTbl.setItems(weekAppointments);
        weekConsultantClm.setCellValueFactory(new PropertyValueFactory<>("userName"));
        weekTitleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekTypeClm.setCellValueFactory(new PropertyValueFactory<>("type"));
        weekStartclm.setCellValueFactory(new PropertyValueFactory<>("displayStart"));
        weekEndClm.setCellValueFactory(new PropertyValueFactory<>("displayEnd"));
        weekCustomerClm.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }

    @FXML
    private TableView<Appointment> allTbl;

    @FXML
    private TableColumn<Appointment, String> allConsultantClm;

    @FXML
    private TableColumn<Appointment, String> allTitleClm;

    @FXML
    private TableColumn<Appointment, String> allTypeClm;

    @FXML
    private TableColumn<Appointment, String> allCustomerClm;

    @FXML
    private TableColumn<Appointment, LocalDateTime> allStartClm;

    @FXML
    private TableColumn<Appointment, LocalDateTime> allEndClm;

    @FXML
    private TableView<Appointment> monthTbl;

    @FXML
    private TableColumn<Appointment, String> monthConsultantClm;

    @FXML
    private TableColumn<Appointment, String> monthTitleClm;

    @FXML
    private TableColumn<Appointment, String> monthTypeClm;

    @FXML
    private TableColumn<Appointment, String> monthCustomerClm;

    @FXML
    private TableColumn<Appointment, LocalDateTime> monthStartClm;

    @FXML
    private TableColumn<Appointment, LocalDateTime> monthEndClm;

    @FXML
    private TableView<Appointment> weekTbl;

    @FXML
    private TableColumn<Appointment, String> weekConsultantClm;

    @FXML
    private TableColumn<Appointment, String> weekTitleClm;

    @FXML
    private TableColumn<Appointment, String> weekTypeClm;

    @FXML
    private TableColumn<Appointment, String> weekCustomerClm;

    @FXML
    private TableColumn<Appointment, LocalDateTime> weekStartclm;

    @FXML
    private TableColumn<Appointment, LocalDateTime> weekEndClm;

    @FXML
    private ComboBox<Appointment> apptCmb;

    //go to the manage appointment screen
    @FXML
    void addAptEvent(ActionEvent event) throws IOException {
        //set the mode for the manager appointment screen
        ManageAppointmentController.setCurrentMode("Add");
        new Scenes().setScene(event, "/view/ManageAppointment.fxml");
    }

    //go back to the main screen
    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Main.fxml");
    }

    @FXML
    void delAptEvent(ActionEvent event) throws SQLException {
        Appointment currentAppointment = apptCmb.getValue();
        //validate user has selected an appointment
        if (currentAppointment == null) {
            Alerts.generateInfoAlert("No Selection", "Please select an appointment.");
        }
        //confirm user would like to delete appointment
        else {
            boolean confirmation = Alerts.generateConfAlert("Delete Appointment Confirmation", "Are you sure you want to delete this appointment?");
            if (confirmation) {
                DBAppointment.deleteAppointment(currentAppointment.getAppointmentId());
                Appointment.removeAppointment(currentAppointment);
            }
        }
    }

    //go to manage appointment screen
    @FXML
    void updateAptEvent(ActionEvent event) throws IOException {
        Appointment currAppointment = apptCmb.getValue();
        //validate user has selected an appointment
        if (currAppointment == null) {
            Alerts.generateInfoAlert("No Selection", "Please select an appointment");
        }
        else {
            //set the mode for the manage appointment controller to update
            ManageAppointmentController.setCurrentMode("Update");
            //pass the current appointment to the next controller
            ManageAppointmentController.setCurrAppointment(currAppointment);
            new Scenes().setScene(event, "/view/ManageAppointment.fxml");
        }
    }
}
