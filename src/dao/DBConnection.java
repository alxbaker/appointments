package dao;

import util.Alerts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn = null;

    //JDBC URL Parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress= "//3.227.166.251/U05xDJ";

    //JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress;

    //Auth details
    private static final String username = "U05xDJ";
    private static final String password = "53688636444";

    //method to open a connection
    public static Connection openConnection() {
        try {
            conn = DriverManager.getConnection(jdbcURL, username, password);
        }
        catch(Exception e) {
            e.printStackTrace();
            Alerts.generateInfoAlert("Connection Error", "Connection to the database could not be established.");
        }
        return conn;
    }

    //method to retrieve current connection
    public static Connection getConnection() {
        return conn;
    }

    //method to close a connection
    public static void closeConnection() {
        try {
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            Alerts.generateInfoAlert("Connection Error", "Connection could not be closed gracefully");
        }
    }
}
