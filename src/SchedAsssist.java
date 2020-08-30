import controller.MainController;
import dao.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SchedAsssist extends Application {
    private static ResourceBundle rb;

    public void setRB() {
        if (!Locale.getDefault().getLanguage().equals("de") && !Locale.getDefault().getLanguage().equals("es")) {
            Locale.setDefault(Locale.ENGLISH);
        }
        this.rb = ResourceBundle.getBundle("util/Nat", Locale.getDefault());
    }

    public ResourceBundle getRb() {
        return rb;
    }


    @Override
    public void init() throws SQLException {
        Connection conn = DBConnection.openConnection();
    }

    public void start(Stage primaryStage) throws Exception{
        setRB();
        MainController.setRb(rb);
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"), rb);
        primaryStage.setTitle(rb.getString("title"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public void stop(){
        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        new SchedAsssist();
        launch(args);
    }
}
