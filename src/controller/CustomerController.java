package controller;

import dao.DBAddress;
import dao.DBAppointment;
import dao.DBCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Customer;
import util.Alerts;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    //populate customer table with all customer information
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        customerTbl.setItems(Customer.customers);
        customerClm.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        lineOneClm.setCellValueFactory(new PropertyValueFactory<>("address"));
        lineTwoClm.setCellValueFactory(new PropertyValueFactory<>("address2"));
        cityClm.setCellValueFactory(new PropertyValueFactory<>("city"));
        postalCodeclm.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneClm.setCellValueFactory(new PropertyValueFactory<>("phone"));

        customerCmb.setItems(Customer.customers);

    }

    @FXML
    private ComboBox<Customer> customerCmb;

    @FXML
    private TableView<Customer> customerTbl;

    @FXML
    private TableColumn<Customer, String> customerClm;

    @FXML
    private TableColumn<Customer, String> lineOneClm;

    @FXML
    private TableColumn<Customer, String> lineTwoClm;

    @FXML
    private TableColumn<Customer, String> cityClm;

    @FXML
    private TableColumn<Customer, String> postalCodeclm;

    @FXML
    private TableColumn<Customer, String> phoneClm;

    //switch to the manage customer screen
    @FXML
    void addCustomerEvent(ActionEvent event) throws IOException {
        //set the mode for the manage customer screen to ad
        ManageCustomerController.setMode("Add");
        new Scenes().setScene(event, "/view/ManageCustomer.fxml");
    }

    //go back to the main screen
    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Main.fxml");
    }

    @FXML
    void deleteCustomerEvent(ActionEvent event) throws SQLException {
        Customer currentCustomer = customerCmb.getValue();
        //validate user has made a selection
        if (currentCustomer == null) {
            Alerts.generateInfoAlert("No Selection", "Please select a customer");
        }
        //confirm user would like to delete customer and all associated appointments
        else {
            boolean confirmation = Alerts.generateConfAlert("Delete Customer Confirmation", "Are you sure you want to delete this customer? This will also delete all associated appointments!");
            if (confirmation) {
                //loop through all appointments in reverse and delete any associated with customer
                int size = Appointment.getAppointments().size() - 1;
                for (int i = size ; i > -1; i--) {
                    Appointment a = Appointment.getAppointments().get(i);
                    if (a.getCustomer().getCustomerID() == currentCustomer.getCustomerID()) {
                        //delete appointment from the databased
                        DBAppointment.deleteAppointment(a.getAppointmentId());
                        //remove appointment form the array
                        Appointment.removeAppointment(a);
                    }
                }
                DBCustomer.deleteCustomer(currentCustomer.getCustomerID());
                DBAddress.deleteAddress(currentCustomer.getAddressID());
                Customer.customers.remove(currentCustomer);
            }
        }
    }

    @FXML
    void updateCustomerEvent(ActionEvent event) throws IOException {
        Customer currentCustomer = customerCmb.getValue();
        //confirm user has made a selection
        if (currentCustomer == null) {
            Alerts.generateInfoAlert("No Selection", "Please select a customer");
        }
        else {
            //set manage customer controller to update mode
            ManageCustomerController.setMode("Update");
            //pass current customer to new controller
            ManageCustomerController.setCurrentCustomer(currentCustomer);
            //switch to manage customer controller
            new Scenes().setScene(event, "/view/ManageCustomer.fxml");
        }
    }
}


