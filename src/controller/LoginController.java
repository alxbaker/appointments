package controller;

import dao.DBUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import util.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        loggedInUser = loggedInUser;
    }

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        usernameTxt.setPromptText("Enter a user name");
        passwordTxt.setPromptText("Enter a password");
    }

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    void loginEvent(ActionEvent event) throws IOException, SQLException {
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();

        if (DBUser.authenticateUser(username, password) == true) {
            new Scenes().setScene(event, "/view/Main.fxml");
        }
        else {
            System.out.println("User could not be authenticated");
        }
    }

}
