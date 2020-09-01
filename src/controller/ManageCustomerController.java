package controller;

import dao.DBAddress;
import dao.DBCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.City;
import model.Customer;
import model.User;
import util.Alerts;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageCustomerController implements Initializable {
    private static String currentMode;
    private static Customer currentCustomer;
    private static User currentUser = User.getCurrentUser();

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
            actionLbl.setText(currentMode);
            cityCmb.setItems(City.cities);

            lineOneTxt.setPromptText("Enter address line one");
            lineTwoTxt.setPromptText("Enter address line two");
            cityCmb.setPromptText("Select a city");
            postalCodeTxt.setPromptText("Enter postal code");
            phoneTxt.setPromptText("Enter phone number");
        }
        else if (currentMode == "Update") {
            actionLbl.setText(currentMode);
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
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Customer.fxml");
    }

    @FXML
    void saveEvent(ActionEvent event) throws SQLException, IOException {
        try {
            String customerName = customerTxt.getText();
            String lineOne = lineOneTxt.getText();
            String lineTwo = lineTwoTxt.getText();
            String userName= currentUser.getUserName();
            int cityId = cityCmb.getValue().getCityID();
            String postalCode = postalCodeTxt.getText();
            String phone = phoneTxt.getText();

            if(customerName.isEmpty() || lineOne.isEmpty() || lineTwo.isEmpty() || postalCode.isEmpty() || phone.isEmpty()) {
                Alerts.generateInfoAlert("Required Field", "A required field is blank");
            }
            else {
                if (currentMode == "Add") {
                    int addressId = DBAddress.insertAddress(lineOne, lineTwo, cityId, postalCode, phone, userName);
                    int customerId = DBCustomer.insertCustomer(customerName, addressId, userName);
                    new Customer(customerId, customerName, addressId,lineOne, lineTwo, postalCode, phone, City.getCity(cityId));
                    new Scenes().setScene(event, "/view/Customer.fxml");
                }
                else if (currentMode == "Update") {
                    currentCustomer.setCustomerName(customerName);
                    currentCustomer.setAddress(lineOne);
                    currentCustomer.setAddress2(lineTwo);
                    currentCustomer.setCity(cityCmb.getValue());
                    currentCustomer.setPostalCode(postalCode);
                    currentCustomer.setPhone(phone);

                    DBAddress.updateAddress(currentCustomer.getAddressID(), lineOne, lineTwo, cityId, postalCode, phone, userName);
                    DBCustomer.updateCustomer(customerName, "admin", currentCustomer.getCustomerID());
                    new Scenes().setScene(event, "/view/Customer.fxml");
                }

            }

        }
        catch(NullPointerException e) {
            Alerts.generateInfoAlert("City Required", "Please select a city");
        }
    }
}
