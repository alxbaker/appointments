package controller;

import dao.DBAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import util.Alerts;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.*;
import java.util.ResourceBundle;

public class ManageAppointmentController implements Initializable {
    ObservableList<String> type = FXCollections.observableArrayList();

    //this Lambda expression creates a filtered list of appointments for the logged in user
    FilteredList<Appointment> userAppointments = new FilteredList<>(Appointment.appointments, e -> e.getUser() == User.getCurrentUser());

    //this Lambda expresses creates a filtered list of appoints for the logged in user on or after today.
    FilteredList<Appointment> userCurrAppointments = new FilteredList<>(userAppointments, e -> ((e.getStart().toLocalDate().isAfter(LocalDate.now())) || (e.getStart().toLocalDate().isEqual(LocalDate.now()))));

    private static Appointment currAppointment;

    private static String currentMode;
    private static boolean load;
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
                "12", "13", "14", "15", "16", "17");
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
            String startHour = String.format("%02d",ldtStart.getHour());
            String startMin = String.format("%02d",ldtStart.getMinute());
            startHourSel.setItems(hours);
            startHourSel.setValue(startHour);
            startMinSel.setItems(minutes);
            startMinSel.setValue(startMin);

            //end time
            LocalDateTime ldtEnd = currAppointment.getEnd();
            String endHour = String.format("%02d",ldtEnd.getHour());
            String endMin = String.format("%02d",ldtEnd.getMinute());

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
        load = true;
            try {
                User u = User.getCurrentUser();
                String apptTitle = title.getText();
                String apptType = typeSel.getValue();

                Customer customer = customerSel.getValue();
                LocalDate startDate = startDateSel.getValue();

                int startHour = Integer.parseInt(startHourSel.getValue());
                int startMin = Integer.parseInt(startMinSel.getValue());

                LocalTime startTime = LocalTime.of(startHour,startMin);
                LocalDateTime ldtLocStart = LocalDateTime.of(startDate, startTime);
                Timestamp tsStart = Timestamp.valueOf(ldtLocStart);

                int endHour = Integer.parseInt(endHourSel.getValue());
                int endMin = Integer.parseInt(endMinSel.getValue());

                LocalTime endTime = LocalTime.of(endHour,endMin);
                LocalDateTime ldtLocEnd = LocalDateTime.of(startDate, endTime);
                Timestamp tsEnd = Timestamp.valueOf(ldtLocEnd);

                if (apptTitle.isEmpty() || apptType.isEmpty()) {
                    Alerts.generateInfoAlert("Required Field", "The required title or type field is blank");
                }
                else if (ldtLocStart.isBefore(LocalDateTime.now())) {
                    Alerts.generateInfoAlert("Date Error", "You cannot schedule appointments in the past");
                }
                else if (startTime.equals(endTime) || endTime.equals(startTime)) {
                    Alerts.generateInfoAlert("Time Error", "Start and end time cannot be the same");
                }
                else if (startTime.isAfter(endTime) || endTime.isBefore(startTime)) {
                    Alerts.generateInfoAlert("Time Error", "Start time must be before end time");
                }
                else {
                    if (currentMode == "Add") {
                        for (Appointment a : userCurrAppointments) {
                            if (a.getStart().toLocalDate().equals(startDate)
                                    && (startTime.equals(a.getStart().toLocalDate())
                                    || endTime.equals(a.getEnd().toLocalDate())
                                    || (startTime.isAfter(a.getStart().toLocalTime())
                                    && startTime.isBefore(a.getEnd().toLocalTime()))
                                    || (endTime.isAfter(a.getStart().toLocalTime())
                                    && endTime.isBefore(a.getEnd().toLocalTime()))
                                    || (a.getStart().toLocalTime().isAfter(startTime)
                                    && a.getStart().toLocalTime().isBefore(endTime))
                                    || (a.getEnd().toLocalTime().isAfter(startTime))
                                    && a.getEnd().toLocalTime().isBefore(endTime))) {
                                Alerts.generateInfoAlert("Overlap Error", "You cannot schedule overlapping appointments");
                                load = false;
                            }
                        }
                        if (load == true) {
                            int appointmentId = DBAppointment.insertAppointment(customer, apptTitle, apptType, tsStart, tsEnd);
                            new Appointment(appointmentId,apptTitle,apptType,ldtLocStart, ldtLocEnd, customer,u);
                            new Scenes().setScene(event, "/view/Calendar.fxml");
                        }
                    }
                    else if (currentMode == "Update") {
                        for (Appointment a : userCurrAppointments) {
                            if (a.getAppointmentId() != currAppointment.getAppointmentId()
                                && a.getStart().toLocalDate().equals(startDate)
                                && (startTime.equals(a.getStart().toLocalDate())
                                || endTime.equals(a.getEnd().toLocalDate())
                                || (startTime.isAfter(a.getStart().toLocalTime())
                                &&  startTime.isBefore(a.getEnd().toLocalTime()))
                                || (endTime.isAfter(a.getStart().toLocalTime())
                                &&  endTime.isBefore(a.getEnd().toLocalTime()))
                                || (a.getStart().toLocalTime().isAfter(startTime)
                                && a.getStart().toLocalTime().isBefore(endTime))
                                ||  (a.getEnd().toLocalTime().isAfter(startTime))
                                &&  a.getEnd().toLocalTime().isBefore(endTime))) {
                                Alerts.generateInfoAlert("Overlap Error", "You cannot schedule overlapping appointments");
                                load = false;
                            }
                            if (load == true) {
                                int apptId = currAppointment.getAppointmentId();
                                currAppointment.setCustomer(customer);
                                currAppointment.setTitle(apptTitle);
                                currAppointment.setType(apptType);
                                currAppointment.setStart(ldtLocStart);
                                currAppointment.setEnd(ldtLocEnd);
                                DBAppointment.updateAppointment(apptId,customer,apptTitle,apptType,tsStart,tsEnd);
                                //TODO fix null pointer exception for primary stage
                                new Scenes().setScene(event, "/view/Calendar.fxml");
                            }
                        }
                    }
                }

            }
            catch(NumberFormatException e){
                Alerts.generateInfoAlert("Required Field", "A required time field is blank");
            }
            catch(NullPointerException e) {
                Alerts.generateInfoAlert("Required Field", "The required customer or date field is blank");
                e.printStackTrace();
            }
        }
    }

