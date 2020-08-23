package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //variables
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

    public static Connection openConnection() {
        try {
            conn = (Connection) DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection successful");
        }
        catch(Exception e) {
            System.out.println("Connection refused.");
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
            System.out.print("Connection closed");
        }
        catch (SQLException e){
            System.out.println("Connection could not be closed gracefully");
            System.out.println ("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
