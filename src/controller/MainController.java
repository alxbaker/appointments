package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Appointment;
import model.Customer;
import model.User;
import util.Scenes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController {
    private static ResourceBundle rb;

    //method to set resource bundle if user returns to login screen
    public static void setRb(ResourceBundle rb) {
        MainController.rb = rb;
    }

    //switch to calendar screen
    @FXML
    void calendarEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Calendar.fxml");
    }

    //switch to customer screen
    @FXML
    void customerEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Customer.fxml");
    }

    //return to login screen
    @FXML
    void logoutEvent(ActionEvent event) throws IOException {
        //set current user to null
        User.setCurrentUser(null);
        //clear all appointments, customers, and users
        Appointment.clearAppointments();
        Customer.clearCustomers();
        User.clearUsers();

        //switch to login screen
        new Scenes().setScene(event, "/view/Login.fxml",rb);
    }

    //switch to report screen
    @FXML
    void reportEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Report.fxml");
    }

}
