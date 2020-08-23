package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    private static ObservableList<Customer> customers = FXCollections.observableArrayList();

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        customerTbl.setItems(customers);
        customerClm.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        lineOneClm.setCellValueFactory(new PropertyValueFactory<>("address"));
        lineTwoClm.setCellValueFactory(new PropertyValueFactory<>("address2"));
        cityClm.setCellValueFactory(new PropertyValueFactory<>("city"));
        postalCodeclm.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneClm.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

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
        new Scenes().newStage(event, "/view/ManageCustomer.fxml");
    }

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Main.fxml");
    }

    @FXML
    void customerSelEvent(ActionEvent event) throws IOException {
        new Scenes().newStage(event, "/view/ManageCustomer.fxml");
    }

    @FXML
    void deleteCustomerEvent(ActionEvent event) throws IOException {
        new Scenes().newStage(event, "/view/ManageCustomer.fxml");
    }

    @FXML
    void updateCustomerEvent(ActionEvent event) throws IOException {
        new Scenes().newStage(event, "/view/ManageCustomer.fxml");
    }

    public static void addCustomer (Customer customer) {
        customers.add(customer);
    }
}


