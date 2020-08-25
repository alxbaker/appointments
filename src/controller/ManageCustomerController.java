package controller;

import dao.DBAddress;
import dao.DBCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.City;
import model.Customer;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageCustomerController implements Initializable {
    private static String currentMode;
    private static Customer currentCustomer;

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer customer) {
        currentCustomer = customer;
    }

    public String getMode() {
        return currentMode;
    }

    public static void setMode(String mode) {
        currentMode = mode;
    }

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        if (currentMode == "Add") {
            actionLbl.setText("Add");
            cityCmb.setItems(City.cities);
            customerTxt.setPromptText("Enter a customer name");
            lineOneTxt.setPromptText("Enter address line one");
            lineTwoTxt.setPromptText("Enter address line two");
            cityCmb.setPromptText("Select a city");
            postalCodeTxt.setPromptText("Enter postal code");
            phoneTxt.setPromptText("Enter phone number");
        }
        else if (currentMode == "Update") {
            actionLbl.setText("Update");
            cityCmb.setValue(currentCustomer.getCity());
            customerTxt.setText(currentCustomer.getCustomerName());
            lineOneTxt.setText(currentCustomer.getAddress());
            lineTwoTxt.setText(currentCustomer.getAddress2());
            postalCodeTxt.setText(currentCustomer.getPostalCode());
            phoneTxt.setText(currentCustomer.getPhone());

        }

    }

    @FXML
    private Label actionLbl;

    @FXML
    private TextField customerTxt;

    @FXML
    private TextField lineOneTxt;

    @FXML
    private TextField lineTwoTxt;

    @FXML
    private ComboBox<City> cityCmb;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    void backEvent(ActionEvent event) {
        new Scenes().closeSecStage(event);
    }

    @FXML
    void saveEvent(ActionEvent event) throws SQLException, IOException {
        if (currentMode == "Add") {
            String customerName = customerTxt.getText();
            String lineOne = lineOneTxt.getText();
            String lineTwo = lineTwoTxt.getText();
            int cityId = cityCmb.getValue().getCityID();
            String postalCode = postalCodeTxt.getText();
            String phone = phoneTxt.getText();

            //FIXME
            //String userName= LoginController.getLoggedInUser().getUserName();
            int addressId = DBAddress.insertAddress(lineOne, lineTwo, cityId, postalCode, phone, "admin");
            int customerId = DBCustomer.insertCustomer(customerName, addressId, "admin");
            new Customer(customerId, customerName, addressId,lineOne, lineTwo, postalCode, phone, City.getCity(cityId));
            new Scenes().setScene(event, "/view/Customer.fxml");
        }
        else if (currentMode == "Update") {
            String customerName = customerTxt.getText();
            currentCustomer.setCustomerName(customerName);

            String lineOne = lineOneTxt.getText();
            currentCustomer.setAddress(lineOne);

            String lineTwo = lineTwoTxt.getText();
            currentCustomer.setAddress2(lineTwo);

            int cityId = cityCmb.getValue().getCityID();
            currentCustomer.setCity(cityCmb.getValue());

            String postalCode = postalCodeTxt.getText();
            currentCustomer.setPostalCode(postalCode);

            String phone = phoneTxt.getText();
            currentCustomer.setPhone(phone);

            //FIXME
            //String userName= LoginController.getLoggedInUser().getUserName();
            DBAddress.updateAddress(currentCustomer.getAddressID(), lineOne, lineTwo, cityId, postalCode, phone, "admin");
            DBCustomer.updateCustomer(customerName, "admin", currentCustomer.getCustomerID());
            new Scenes().setScene(event, "/view/Customer.fxml");
        }


    }
}
