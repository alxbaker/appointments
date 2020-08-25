package dao;

import model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCities {
    private final static String selectStatement = "SELECT * FROM city";
    private final static Connection conn = DBConnection.getConnection();

    public static void getAllCities() throws SQLException {
        try {
            PreparedStatement statement = conn.prepareStatement(selectStatement);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                //get address information
                int cityId = rs.getInt("cityId");
                String cityName = rs.getString("city");
                City city = new City(cityId, cityName);

            }
        } catch (SQLException e) {
            System.out.println("SQL Exception");
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
