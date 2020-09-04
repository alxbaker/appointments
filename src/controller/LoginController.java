package controller;

import dao.DBAppointment;
import dao.DBCities;
import dao.DBCustomer;
import dao.DBUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import util.Alerts;
import util.Files;
import util.Scenes;
import util.Time;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    String errorTitle, errorMsg;

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    //set text values using resource bundle
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        usernameTxt.setPromptText(rb.getString("userprompt"));
        passwordTxt.setPromptText(rb.getString("passwdprompt"));
        userLbl.setText(rb.getString("userlbl"));
        passwdLbl.setText(rb.getString("passwdlbl"));
        schedAssistant.setText(rb.getString("title"));
        loginLbl.setText(rb.getString("loginlbl"));
        setErrorMsg(rb.getString("errormsg"));
        setErrorTitle(rb.getString("errortitle"));

    }

    @FXML
    private Label userLbl;

    @FXML
    private Label passwdLbl;

    @FXML
    private Label schedAssistant;

    @FXML
    private Button loginLbl;

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    void loginEvent(ActionEvent event) throws IOException, SQLException {
        //get all users
        DBUser.getAllUsers();

        String username = usernameTxt.getText();
        String password = passwordTxt.getText();

        if (DBUser.authenticateUser(username, password)) {
            //add entry to the log file
            Files.updateLog(User.getCurrentUser());
            //get all cities
            DBCities.getAllCities();
            //get all customers
            DBCustomer.getAllCustomers();
            //get all appointments
            DBAppointment.getAllAppointments();
            //generate a time alert if appropriate
            Time.timeAlert();

            //switch to main screen
            new Scenes().setScene(event, "/view/Main.fxml");
        }
        else {
            //if user cannot authenticate, display error message
            Alerts.generateInfoAlert(errorTitle, errorMsg);
        }
    }

}
