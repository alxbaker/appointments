package dao;

import controller.CustomerController;
import model.City;
import model.Customer;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomer {
    private final static String selectStatement = "SELECT * FROM customer CROSS JOIN address, city WHERE (customer.addressId = address.addressId) AND (address.cityId = city.cityId);";
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
                String cityName = rs.getString("city");
                City customerCity = new City(cityID, cityName);

                //get customer information
                int customerID = rs.getInt("customerId");
                String customerName = rs.getString("customerName");


                //create customer
                Customer customer = new Customer(customerID, customerName, addressId, address1, address2, postalCode, phone, customerCity);
                CustomerController.addCustomer(customer);

            }
        } catch (SQLException e) {
            System.out.println("SQL Exception");
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
