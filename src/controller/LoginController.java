package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.Scenes;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    void loginEvent(ActionEvent event) throws IOException {
        new Scenes().setScene(event, "/view/Main.fxml");

    }

}
