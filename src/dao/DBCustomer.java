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
    private final static String selectStatement = "select * from customer";
    private final static Connection conn = DBConnection.getConnection();

    public static void getAllCustomers() throws SQLException {
        try {
            PreparedStatement statement = conn.prepareStatement(selectStatement);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                //get address information
                int addressId = rs.getInt("addressId");
                ResultSet address = DBAddress.getAddress(addressId);
                address.next();
                String address1 = address.getString("address");
                String address2 = address.getString("address2");
                String postalCode = address.getString("postalCode");
                String phone = address.getString("phone");

                //get city information
                int cityID = address.getInt("cityId");
                ResultSet city = DBCity.getCity(cityID);
                city.next();
                String cityName = city.getString("city");
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
