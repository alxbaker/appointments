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

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageCustomerController implements Initializable {

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        actionLbl.setText("Add");
        cityCmb.setItems(City.cities);
        customerTxt.setPromptText("Enter a customer name");
        lineOneTxt.setPromptText("Enter address line one");
        lineTwoTxt.setPromptText("Enter address line two");
        cityCmb.setPromptText("Select a city");
        postalCodeTxt.setPromptText("Enter postal code");
        phoneTxt.setPromptText("Enter phone number");
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
    void saveEvent(ActionEvent event) throws SQLException {
        String customerName = customerTxt.getText();
        String lineOne = lineOneTxt.getText();
        String lineTwo = lineTwoTxt.getText();
        int cityId = cityCmb.getValue().getCityID();
        String postalCode = postalCodeTxt.getText();
        String phone = phoneTxt.getText();

        //FIXME
        //String userName= LoginController.getLoggedInUser().getUserName();
        int addressId = DBAddress.insertAddress(lineOne, lineTwo,cityId,postalCode,phone,"admin");
        DBCustomer.insertCustomer(customerName,addressId,"admin");
        new Scenes().closeSecStage(event);
        //FIXME
        //CustomerController.refreshCustomers();
    }

}
