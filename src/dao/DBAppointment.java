package dao;

import controller.CustomerController;
import model.Appointment;
import model.City;
import model.Customer;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DBAppointment {
    private final static String selectStatement = "SELECT * FROM appointment WHERE (start >= NOW());";
    private final static String countStatement = "SELECT COUNT(*) FROM appointment WHERE (start > NOW());";
    private static String insertStatement = "INSERT INTO appointment (customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" +
            "VALUES (?, ?, ?, 'a scheduled appointment', 'location not provided', 'contact not provided', ?, 'url not provided', ?, ?, CURDATE(), ?, CURDATE(), ?);";
    private static  String deleteStatement = "DELETE FROM appointment WHERE appointmentId = ?";
    private static String updateStatement = "UPDATE appointment SET customerId = ?, title =?, type = ?, start = ?, end = ?, lastUpdate = CURDATE(), lastUpdateBy = ? WHERE appointmentId = ?";
    private final static Connection conn = DBConnection.getConnection();

    public static void getAllAppointments() throws SQLException {
        try {
            PreparedStatement statement = conn.prepareStatement(selectStatement);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                int appointmentId = rs.getInt("appointmentId");
                int userId = rs.getInt("userId");
                String title = rs.getString("title");
                String type = rs.getString("type");
                int customerId = rs.getInt("customerId");
                Timestamp start = rs.getTimestamp("start");
                Timestamp end = rs.getTimestamp("end");
                User u = User.getUser(userId);
                String userName = u.getUserName();
                Customer c = Customer.getCustomer(customerId);
                String customerName = c.getCustomerName();

                LocalDateTime ldtStart = start.toLocalDateTime();
                ZonedDateTime zdtStart = ldtStart.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime localStart = zdtStart.toLocalDateTime();

                LocalDateTime ldtEnd = end.toLocalDateTime();
                ZonedDateTime zdtEnd = ldtEnd.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime localEnd = zdtEnd.toLocalDateTime();

                new Appointment(appointmentId,title,type,localStart,localEnd,c,u);

            }
        } catch (SQLException e) {
            System.out.println("SQL Exception");
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int insertAppointment(Customer customer, String title, String type, Timestamp start,Timestamp end) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        User u = User.getCurrentUser();
        statement.setInt(1, customer.getCustomerID());
        statement.setInt(2, u.getUserId());
        statement.setString(3, title);
        statement.setString(4, type);
        statement.setTimestamp(5, start);
        statement.setTimestamp(6, end);
        statement.setString(7, u.getUserName());
        statement.setString(8, u.getUserName());

        statement.execute();

        if (statement.getUpdateCount() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }
        else {
            return 0;
        }
    }

    public static void deleteAppointment(int appointmentId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(deleteStatement);
        statement.setInt(1, appointmentId);
        statement.execute();
    }

    public static void updateAppointment(int appointmentId, Customer customer, String title, String type, Timestamp start, Timestamp end) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(updateStatement);
        User u = User.getCurrentUser();
        statement.setInt(1, customer.getCustomerID());
        statement.setString(2, title);
        statement.setString(3, type);
        statement.setTimestamp(4, start);
        statement.setTimestamp(5, end);
        statement.setInt(6, u.getUserId());
        statement.setInt(7,appointmentId);
        statement.execute();
    }
}
