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
    FilteredList<Appointment> monthAppointments = new FilteredList<>(Appointment.appointments, e -> e.getStart().getMonth() == LocalDate.now().getMonth());

    private int virtualCount = 0;
    private int onsiteCount = 0;
    private int miscCount = 0;

    public int getVirtualCount() {
        return virtualCount;
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Appointment a : monthAppointments) {
            if (a.getType().equals("Virtual")){
                setVirtualCount(virtualCount =+ 1);
            }
            else if (a.getType().equals("Onsite")) {
                setOnsiteCount(onsiteCount =+ 1);
            }
            else {
                setMiscCount(miscCount =+ 1);
            }
        }

        appointmentsLbl.setText("Virtual: " + virtualCount + " Onsite: " + onsiteCount + " Misc: " + miscCount);

    }

    @FXML
    private Label appointmentsLbl;

    @FXML
    void backBttn(ActionEvent event) throws IOException {
        setMiscCount(0);
        setOnsiteCount(0);
        setVirtualCount(0);
        new Scenes().setScene(event, "/view/Report.fxml");
    }
}