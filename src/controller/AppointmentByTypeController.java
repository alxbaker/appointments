package controller;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Appointment;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AppointmentByTypeController implements Initializable {
    //this Lambda expression creates a filtered list of appointments in the current month
    FilteredList<Appointment> monthAppointments = new FilteredList<>(Appointment.getAppointments(), e -> e.getStart().getMonth() == LocalDate.now().getMonth());

    //variables to display counts of different appointment types this month
    private int virtualCount;
    private int onsiteCount;
    private int miscCount;

    //getters and setts for appointment type counters
    public int getVirtualCount() { return virtualCount; }

    public void setVirtualCount(int virtualCount) {
        this.virtualCount = virtualCount;
    }

    public int getOnsiteCount() {
        return onsiteCount;
    }

    public void setOnsiteCount(int onsiteCount) {
        this.onsiteCount = onsiteCount;
    }

    public int getMiscCount() {
        return miscCount;
    }

    public void setMiscCount(int miscCount) {
        this.miscCount = miscCount;
    }

    //initialize method sets all counts to 0
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVirtualCount(0);
        setOnsiteCount(0);
        setMiscCount(0);

        //loop through all appointments this month
        for (Appointment a : monthAppointments) {
            if (a.getType().equals("Virtual")){
                //if virtual, add 1
                setVirtualCount(virtualCount += 1);
            }
            else if (a.getType().equals("Onsite")) {
                //if onsite, add 1
                setOnsiteCount(onsiteCount += 1);
            }
            else {
                //otherwise, add 1 to misc
                setMiscCount(miscCount += 1);
            }
        }

        //display total appointment counts on the screen
        appointmentsLbl.setText("Virtual:  " + getVirtualCount() + " Onsite:  " + getOnsiteCount() +  " Misc:  " + getMiscCount());

    }

    @FXML
    private Label appointmentsLbl;

    //go back to the report screen
    @FXML
    void backBttn(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Report.fxml");
    }
}