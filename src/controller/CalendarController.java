package controller;

import dao.DBAddress;
import dao.DBAppointment;
import dao.DBCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Customer;
import model.User;
import util.Alerts;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    //this Lambda expression creates a filtered list of appointments for the logged in user
    FilteredList<Appointment> userAppointments = new FilteredList<>(Appointment.appointments, e -> e.getUser() == User.getCurrentUser());

    //this Lambda expresses creates a filtered list of appoints for the logged in user on or after today.
    FilteredList<Appointment> userCurrAppointments = new FilteredList<>(userAppointments, e -> ((e.getStart().toLocalDate().isAfter(LocalDate.now())) || (e.getStart().toLocalDate().isEqual(LocalDate.now()))));

    //this Lambda expression creates a filtered list of appointments for the current user in the current month
    FilteredList<Appointment> monthAppointments = new FilteredList<>(userAppointments, e -> e.getStart().getMonth() == LocalDate.now().getMonth());

    //this Lambda expression creates a filtered list of appointments for the current this week of the year
    FilteredList<Appointment> weekAppointments = new FilteredList<>(userAppointments, e -> e.getStart().get(WeekFields.of(Locale.getDefault()).weekOfYear()) == LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfYear()));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apptCmb.setItems(userCurrAppointments);
        allTbl.setItems(userCurrAppointments);
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
    private TabPane tabPane;

    @FXML
    private Tab allTab;

    @FXML
    private TableView<Appointment> allTbl;

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
    private TableView<Appointment> monthTbl;

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
    private TableView<Appointment> weekTbl;

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
    private ComboBox<Appointment> apptCmb;

    @FXML
    void addAptEvent(ActionEvent event) throws IOException {
        ManageAppointmentController.setCurrentMode("Add");
        new Scenes().setScene(event, "/view/ManageAppointment.fxml");
    }

    @FXML
    void apptCmbEvent(ActionEvent event) throws IOException {

    }

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Main.fxml");
    }

    @FXML
    void delAptEvent(ActionEvent event) throws IOException, SQLException {
        Appointment currentAppointment = apptCmb.getValue();
        if (currentAppointment == null) {
            Alerts.generateInfoAlert("No Selection", "Please select an appointment.");
        }
        else {
            boolean confirmation = Alerts.generateConfAlert("Delete Appointment Confirmation", "Are you sure you want to delete this appointment?");
            if (confirmation == true) {
                DBAppointment.deleteAppointment(currentAppointment.getAppointmentId());
                Appointment.appointments.remove(currentAppointment);
            }
        }
    }

    @FXML
    void updateAptEvent(ActionEvent event) throws IOException {
        Appointment currAppointment = apptCmb.getValue();
        if (currAppointment == null) {
            Alerts.generateInfoAlert("No Selection", "Please select an appointment");
        }
        else {
            ManageAppointmentController.setCurrentMode("Update");
            ManageAppointmentController.setCurrAppointment(currAppointment);
            new Scenes().setScene(event, "/view/ManageAppointment.fxml");
        }
    }
}
