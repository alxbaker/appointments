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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

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
    private TableColumn<?, ?> customerClm;

    @FXML
    private TableColumn<?, ?> lineOneClm;

    @FXML
    private TableColumn<?, ?> lineTwoClm;

    @FXML
    private TableColumn<?, ?> cityClm;

    @FXML
    private TableColumn<?, ?> postalCodeclm;

    @FXML
    private TableColumn<?, ?> phoneClm;

    @FXML
    void addCustomerEvent(ActionEvent event) throws IOException {
        ManageCustomerController.setMode("Add");
        new Scenes().setScene(event, "/view/ManageCustomer.fxml");
    }

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Main.fxml");
    }

    @FXML
    void deleteCustomerEvent(ActionEvent event) throws IOException, SQLException {
        Customer currentCustomer = customerCmb.getValue();
        if (currentCustomer == null) {
            Alerts.generateInfoAlert("No Selection", "Please select a customer");
        }
        else {
            boolean confirmation = Alerts.generateConfAlert("Delete Customer Confirmation", "Are you sure you want to delete this customer? This will also delete all associated appointments!");
            if (confirmation == true) {
                int size = Appointment.appointments.size() - 1;
                for (int i = size ; i > -1; i--) {
                    Appointment a = Appointment.appointments.get(i);
                    if (a.getCustomer().getCustomerID() == currentCustomer.getCustomerID()) {
                        DBAppointment.deleteAppointment(a.getAppointmentId());
                        Appointment.appointments.remove(a);
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
        if (currentCustomer == null) {
            Alerts.generateInfoAlert("No Selection", "Please select a customer");
        }
        else {
            ManageCustomerController.setMode("Update");
            ManageCustomerController.setCurrentCustomer(currentCustomer);
            new Scenes().setScene(event, "/view/ManageCustomer.fxml");
        }
    }
}


