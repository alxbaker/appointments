package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;


public class manageScenes {

    private Stage primaryStage;
    private Stage secondaryStage;
    private Parent scene;

    public void setScene(ActionEvent event, String resourcePath) throws IOException {
       primaryStage = (Stage)((Button)event.getSource()).getScene().getWindow();
       scene = FXMLLoader.load(getClass().getResource(resourcePath));
       primaryStage.setScene(new Scene(scene));
       primaryStage.show();

    }

    public void newStage(ActionEvent event, String resourcePath) throws IOException {
        primaryStage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(resourcePath));
        secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(scene));
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.initOwner(primaryStage);
        secondaryStage.show();

    }

    public void closeSecStage(ActionEvent event) {
        secondaryStage = (Stage)((Button)event.getSource()).getScene().getWindow();
        secondaryStage.close();
    }

}
