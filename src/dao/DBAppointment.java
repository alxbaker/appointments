package dao;

import controller.CustomerController;
import model.City;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAppointment {
    private final static String selectStatement = "select * from appointment";
    private final static Connection conn = DBConnection.getConnection();

    public static void getAllAppointments() throws SQLException {
        try {
            PreparedStatement statement = conn.prepareStatement(selectStatement);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                int appointmentId = rs.getInt("appointmentId");
                int customerId = rs.getInt("customerId");
                int userId = rs.getInt("userId");
                String title = rs.getString("title");
                String type = rs.getString("type");





                //get customer information
                int customerID = rs.getInt("customerId");
                String customerName = rs.getString("customerName");



            }
        } catch (SQLException e) {
            System.out.println("SQL Exception");
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
