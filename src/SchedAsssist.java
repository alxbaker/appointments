import controller.MainController;
import dao.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/*
My language translation functionality should work in German, Spanish, and English. The username and password are
"test" as specified in the task instructions. The log file is stored in the logs package. Business hours are 8 to 6.
 */

public class SchedAsssist extends Application {
    private static ResourceBundle rb;

    //method to evaluate system language and set resource bundle
    public void setRB() {
        if (!Locale.getDefault().getLanguage().equals("de") && !Locale.getDefault().getLanguage().equals("es")) {
            Locale.setDefault(Locale.ENGLISH);
        }
        this.rb = ResourceBundle.getBundle("util/Nat", Locale.getDefault());
    }

    public void start(Stage primaryStage) throws IOException {
        //evaluate system language
        setRB();
        //set resource bundle on main controller
        MainController.setRb(rb);
        //load log in screen
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"), rb);
        primaryStage.setTitle(rb.getString("title"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.show();
        //open database connection
        DBConnection.openConnection();
    }

    public void stop(){
        //close database connection
        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
