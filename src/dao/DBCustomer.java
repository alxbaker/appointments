package dao;

import controller.CustomerController;
import model.City;
import model.Customer;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;
import java.sql.*;

public class DBCustomer {
    private final static String selectStatement = "SELECT * FROM customer CROSS JOIN address, city WHERE (customer.addressId = address.addressId) AND (address.cityId = city.cityId);";
    private static String insertStatement = "INSERT INTO customer (customerName,addressId,active,createDate,createdBy,lastUpdate,lastUpdateBy)" +
            "VALUES (?,?,1,CURDATE(),?,CURDATE(),?)";
    private static  String deleteStatement = "DELETE FROM customer WHERE customerId = ?";
    private static String updateStatement = "UPDATE customer SET customerName = ?, lastUpdate = CURDATE(), lastUpdateBy = ? WHERE customerId = ?";
    private final static Connection conn = DBConnection.getConnection();

    public static void getAllCustomers() throws SQLException {
        try {
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
                int customerID = rs.getInt("customerId");
                String customerName = rs.getString("customerName");

                //create customer
                Customer customer = new Customer(customerID, customerName, addressId, address1, address2, postalCode, phone, City.getCity(cityID));

            }
        } catch (SQLException e) {
            System.out.println("SQL Exception");
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

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

    public static void deleteCustomer(int customerId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(deleteStatement, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, customerId);
        statement.execute();
    }

    public static void updateCustomer(String customerName, String user, int customerId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(updateStatement, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, customerName);
        statement.setString(2, user);
        statement.setInt(3, customerId);
        statement.execute();
    }
}
