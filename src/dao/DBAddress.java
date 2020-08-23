package dao;

import controller.CustomerController;
import model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAddress {
    private final static String selectStatement = "select * from address where addressId = ?";
    private final static Connection conn = DBConnection.getConnection();

    public static ResultSet getAddress(int addressId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(selectStatement);
        statement.setInt(1, addressId);
        statement.execute();
        ResultSet rs = statement.getResultSet();
        return rs;
    }
}
