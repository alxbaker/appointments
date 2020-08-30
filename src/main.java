import dao.DBAppointment;
import dao.DBCities;
import dao.DBConnection;
import dao.DBCustomer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class main extends Application {

    @Override
    public void init() throws SQLException {
        Connection conn = DBConnection.openConnection();
    }

    public void start(Stage primaryStage) throws Exception{
        if (!Locale.getDefault().getLanguage().equals("de") && !Locale.getDefault().getLanguage().equals("es")) {
            Locale.setDefault(Locale.ENGLISH);
        }

        ResourceBundle rb = ResourceBundle.getBundle("util/language/Nat", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"), rb);
        primaryStage.setTitle("Scheduling Assistant");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public void stop(){
        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
