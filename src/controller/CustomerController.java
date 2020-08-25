package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
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
    void deleteCustomerEvent(ActionEvent event) throws IOException {
        new Scenes().newStage(event, "/view/ManageCustomer.fxml");
    }

    @FXML
    void updateCustomerEvent(ActionEvent event) throws IOException {
        ManageCustomerController.setMode("Update");
        ManageCustomerController.setCurrentCustomer(customerCmb.getValue());
        new Scenes().setScene(event, "/view/ManageCustomer.fxml");

    }


}


