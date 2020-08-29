package controller;

import dao.DBAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Appointment;
import model.Customer;
import model.User;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

public class ManageAppointmentController implements Initializable {
    ObservableList<String> type = FXCollections.observableArrayList();

    private static String currentMode;
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();

    public static String getCurrentMode() {
        return currentMode;
    }

    public static void setCurrentMode(String currentMode) {
        ManageAppointmentController.currentMode = currentMode;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.addAll("Virtual", "Onsite");
        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00", "15", "30", "45");
        if (currentMode == "Add") {
            modeLbl.setText(currentMode);
            title.setPromptText("Enter a title");
            customerSel.setItems(Customer.customers);
            typeSel.setItems(type);
            startHourSel.setItems(hours);
            startMinSel.setItems(minutes);
            endHourSel.setItems(hours);
            endMinSel.setItems(minutes);
        }
    }
    @FXML
    private Label modeLbl;

    @FXML
    private TextField title;

    @FXML
    private ComboBox<Customer> customerSel;

    @FXML
    private DatePicker startDateSel;

    @FXML
    private ComboBox<String> typeSel;

    @FXML
    private ComboBox<String> startHourSel;

    @FXML
    private ComboBox<String> startMinSel;

    @FXML
    private ComboBox<String> endHourSel;

    @FXML
    private ComboBox<String> endMinSel;

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Calendar.fxml");
    }

    @FXML
    void saveEvent(ActionEvent event) throws IOException, SQLException {
        if (currentMode == "Add") {
            User u = User.getCurrentUser();
            Customer customer = customerSel.getValue();
            String apptTitle = title.getText();
            String apptType = typeSel.getValue();

            //Date
            LocalDate startDate = startDateSel.getValue();

            //Start time
            int startHour = Integer.parseInt(startHourSel.getValue());
            int startMin = Integer.parseInt(startMinSel.getValue());
            LocalTime startTime = LocalTime.of(startHour,startMin);

            //Start date and time
            LocalDateTime ldtLocStart = LocalDateTime.of(startDate, startTime);
            ZonedDateTime zdtLocStart = ZonedDateTime.of(ldtLocStart, ZoneId.systemDefault());
            ZonedDateTime zdtSQLStart = zdtLocStart.withZoneSameInstant(ZoneOffset.UTC);
            LocalDateTime ldtSQLStart = zdtSQLStart.toLocalDateTime();
            Timestamp tsStart = Timestamp.valueOf(ldtSQLStart);

            //End time
            int endHour = Integer.parseInt(endHourSel.getValue());
            int endMin = Integer.parseInt(endMinSel.getValue());
            LocalTime endTime = LocalTime.of(endHour,endMin);

            //End date and time
            LocalDateTime ldtLocEnd = LocalDateTime.of(startDate, endTime);
            ZonedDateTime zdtLocEnd = ZonedDateTime.of(ldtLocEnd, ZoneId.systemDefault());
            ZonedDateTime zdtSQLEnd = zdtLocEnd.withZoneSameInstant(ZoneOffset.UTC);
            LocalDateTime ldtSQLEnd = zdtSQLEnd.toLocalDateTime();
            Timestamp tsEnd = Timestamp.valueOf(ldtSQLEnd);

            int appointmentId = DBAppointment.insertAppointment(customer, apptTitle, apptType, tsStart, tsEnd);
            new Appointment(appointmentId,apptTitle,apptType,ldtLocStart, ldtLocEnd, customer,u);
        }
        else if (currentMode == "Update") {

        }
        new Scenes().setScene(event, "/view/Calendar.fxml");
    }


}
