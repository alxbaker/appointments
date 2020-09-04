package dao;

import java.sql.*;

public class DBAddress {
    //SQL statements
    private static String insertStatement = "INSERT INTO address (address,address2,cityId,postalCode,phone,createDate,createdBy,lastUpdate,lastUpdateBy)" +
    "VALUES (?,?,?,?,?,CURDATE(),?,CURDATE(),?)";
    private static  String deleteStatement = "DELETE FROM address WHERE addressId = ?";
    private static String updateStatement = "UPDATE address SET address = ?, address2 = ?, cityId = ?, postalCode =?, phone = ?, lastUpdate = CURDATE(), lastUpdateBy = ? WHERE addressId = ?";

    //database connection
    private final static Connection conn = DBConnection.getConnection();

    //method to insert a new address, returns id
    public static int insertAddress(String address, String address2, int cityId, String postalCode, String phone, String user) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, address);
        statement.setString(2, address2);
        statement.setInt(3, cityId);
        statement.setString(4,postalCode);
        statement.setString(5, phone);
        statement.setString(6, user);
        statement.setString(7, user);
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

    //method to delete an address
    public static void deleteAddress(int addressId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(deleteStatement, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, addressId);
        statement.execute();
    }

    //method to update an address
    public static void updateAddress(int addressId, String address, String address2, int cityId, String postalCode, String phone, String user) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(updateStatement);
        statement.setString(1, address);
        statement.setString(2, address2);
        statement.setInt(3, cityId);
        statement.setString(4,postalCode);
        statement.setString(5, phone);
        statement.setString(6, user);
        statement.setInt(7, addressId);
        statement.execute();
    }
}
