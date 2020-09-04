package dao;

import model.City;
import model.Customer;

import java.sql.*;

public class DBCustomer {
    //SQL statements
    private final static String selectStatement = "SELECT * FROM customer CROSS JOIN address, city WHERE (customer.addressId = address.addressId) AND (address.cityId = city.cityId);";
    private static String insertStatement = "INSERT INTO customer (customerName,addressId,active,createDate,createdBy,lastUpdate,lastUpdateBy)" +
            "VALUES (?,?,1,CURDATE(),?,CURDATE(),?)";
    private static  String deleteStatement = "DELETE FROM customer WHERE customerId = ?";
    private static String updateStatement = "UPDATE customer SET customerName = ?, lastUpdate = CURDATE(), lastUpdateBy = ? WHERE customerId = ?";

    //Database connection
    private final static Connection conn = DBConnection.getConnection();

    //method to get all customers
    public static void getAllCustomers() throws SQLException {
        PreparedStatement statement = conn.prepareStatement(selectStatement);
        statement.execute();
        ResultSet rs = statement.getResultSet();

        while (rs.next()) {
            //get address information
            int addressId = rs.getInt("addressId");
            String address1 = rs.getString("address");
            String address2 = rs.getString("address2");
            String postalCode = rs.getString("postalCode");
            String phone = rs.getString("phone");

            //get city information
            int cityID = rs.getInt("cityId");

            //get customer information
            int customerId = rs.getInt("customerId");
            String customerName = rs.getString("customerName");

            //create customer
            new Customer(customerId, customerName, addressId, address1, address2, postalCode, phone, City.getCity(cityID));

        }
    }

    //method to insert a new customer, returns id
    public static int insertCustomer(String customerName, int addressId, String user) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, customerName);
        statement.setInt(2, addressId);
        statement.setString(3, user);
        statement.setString(4, user);

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

    //method to delete an existing customer
    public static void deleteCustomer(int customerId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(deleteStatement);
        statement.setInt(1, customerId);
        statement.execute();
    }

    //method to update an existing customer
    public static void updateCustomer(String customerName, String user, int customerId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(updateStatement);
        statement.setString(1, customerName);
        statement.setString(2, user);
        statement.setInt(3, customerId);
        statement.execute();
    }
}
