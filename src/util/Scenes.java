package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;


public class Scenes {
    private Stage primaryStage;
    private Parent scene;

    //method to change scenes without specific resource bundle
    public void setScene(ActionEvent event, String resourcePath) throws IOException {
       primaryStage = (Stage)((Button)event.getSource()).getScene().getWindow();
       scene = FXMLLoader.load(getClass().getResource(resourcePath));
       primaryStage.setScene(new Scene(scene));
       primaryStage.show();
    }

    //method to change scenes with specific resource bundle
    public void setScene(ActionEvent event, String resourcePath, ResourceBundle rb) throws IOException {
        primaryStage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(resourcePath),rb);
        primaryStage.setScene(new Scene(scene));
        primaryStage.show();
    }
}
