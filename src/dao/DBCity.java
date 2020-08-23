package dao;

import controller.CustomerController;
import model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCity {
    private final static String selectStatement = "select * from city where cityId = ?";
    private final static Connection conn = DBConnection.getConnection();

    public static ResultSet getCity(int cityId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(selectStatement);
        statement.setInt(1, cityId);
        statement.execute();
        ResultSet rs = statement.getResultSet();

        return rs;
        }
    }
