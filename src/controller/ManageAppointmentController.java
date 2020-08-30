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
    private static Appointment currAppointment;

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
        hours.addAll("08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18");
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
        else if (currentMode == "Update") {
            modeLbl.setText(currentMode);
            title.setText(currAppointment.getTitle());
            customerSel.setItems(Customer.customers);
            customerSel.setValue(currAppointment.getCustomer());
            typeSel.setItems(type);
            typeSel.setValue(currAppointment.getType());

            //start date
            LocalDateTime ldtStart = currAppointment.getStart();
            LocalDate startDate = ldtStart.toLocalDate();
            startDateSel.setValue(startDate);

            //start time
            String startHour = Integer.toString(ldtStart.getHour());
            String startMin = Integer.toString(ldtStart.getMinute());
            startHourSel.setItems(hours);
            startHourSel.setValue(startHour);
            startMinSel.setItems(minutes);
            startMinSel.setValue(startMin);

            //end time
            LocalDateTime ldtEnd = currAppointment.getEnd();
            String endHour = Integer.toString(ldtEnd.getHour());
            String endMin = Integer.toString(ldtEnd.getMinute());

            endHourSel.setItems(hours);
            endHourSel.setValue(endHour);
            endMinSel.setItems(minutes);
            endMinSel.setValue(endMin);
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

    public static Appointment getCurrAppointment() {
        return currAppointment;
    }

    public static void setCurrAppointment(Appointment currAppointment) {
        ManageAppointmentController.currAppointment = currAppointment;
    }

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
            Timestamp tsStart = Timestamp.valueOf(ldtLocStart);

            //End time
            int endHour = Integer.parseInt(endHourSel.getValue());
            int endMin = Integer.parseInt(endMinSel.getValue());
            LocalTime endTime = LocalTime.of(endHour,endMin);

            //End date and time
            LocalDateTime ldtLocEnd = LocalDateTime.of(startDate, endTime);
            Timestamp tsEnd = Timestamp.valueOf(ldtLocEnd);

            int appointmentId = DBAppointment.insertAppointment(customer, apptTitle, apptType, tsStart, tsEnd);
            new Appointment(appointmentId,apptTitle,apptType,ldtLocStart, ldtLocEnd, customer,u);
        }
        else if (currentMode == "Update") {
            int apptId = currAppointment.getAppointmentId();
            Customer customer = customerSel.getValue();
            currAppointment.setCustomer(customer);

            String apptTitle = title.getText();
            currAppointment.setTitle(apptTitle);

            String apptType = typeSel.getValue();
            currAppointment.setType(apptType);

            //Date
            LocalDate startDate = startDateSel.getValue();

            //Start time
            int startHour = Integer.parseInt(startHourSel.getValue());
            int startMin = Integer.parseInt(startMinSel.getValue());
            LocalTime startTime = LocalTime.of(startHour,startMin);

            //Start date and time
            LocalDateTime ldtLocStart = LocalDateTime.of(startDate, startTime);
            currAppointment.setStart(ldtLocStart);

            //convert to UTC
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
            currAppointment.setEnd(ldtLocEnd);

            //convert to UTC
            ZonedDateTime zdtLocEnd = ZonedDateTime.of(ldtLocEnd, ZoneId.systemDefault());
            ZonedDateTime zdtSQLEnd = zdtLocEnd.withZoneSameInstant(ZoneOffset.UTC);
            LocalDateTime ldtSQLEnd = zdtSQLEnd.toLocalDateTime();
            Timestamp tsEnd = Timestamp.valueOf(ldtSQLEnd);

            DBAppointment.updateAppointment(apptId,customer,apptTitle,apptType,tsStart,tsEnd);
        }
        new Scenes().setScene(event, "/view/Calendar.fxml");
    }
}
