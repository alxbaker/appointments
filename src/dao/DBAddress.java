package dao;

import java.sql.*;

public class DBAddress {
    private static String insertStatement = "INSERT INTO address (address,address2,cityId,postalCode,phone,createDate,createdBy,lastUpdate,lastUpdateBy)" +
    "VALUES (?,?,?,?,?,CURDATE(),?,CURDATE(),?)";
    private final static Connection conn = DBConnection.getConnection();

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

}
