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

public class main extends Application {

    @Override
    public void init() throws SQLException {
        Connection conn = DBConnection.openConnection();
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
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
