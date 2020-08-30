package controller;

import dao.DBCities;
import dao.DBCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Appointment;
import model.Customer;
import model.User;
import sun.rmi.runtime.Log;
import util.Files;
import util.Scenes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController {
    private static ResourceBundle rb;

    public static void setRb(ResourceBundle rb) {
        MainController.rb = rb;
    }

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

        new Scenes().setScene(event, "/view/Login.fxml",rb);
    }

    @FXML
    void reportEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Report.fxml");
    }

}
