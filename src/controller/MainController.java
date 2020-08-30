package controller;

import dao.DBCities;
import dao.DBCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Appointment;
import model.Customer;
import model.User;
import sun.rmi.runtime.Log;
import util.Scenes;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {

    @FXML
    void calendarEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Calendar.fxml");
    }

    @FXML
    void customerEvent(ActionEvent event) throws IOException, SQLException {
        new Scenes().setScene(event, "/view/Customer.fxml");
    }

    @FXML
    void logoutEvent(ActionEvent event) throws IOException {
        User.setCurrentUser(null);
        Appointment.clearAppointments();
        Customer.clearCustomers();
        User.clearUsers();
        new Scenes().setScene(event, "/view/Login.fxml");
    }

    @FXML
    void reportEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Report.fxml");
    }

}
