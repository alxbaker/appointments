package dao;

import model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCities {
    //SQL statement
    private final static String selectStatement = "SELECT * FROM city";

    //Database connection
    private final static Connection conn = DBConnection.getConnection();

    //method to return all cities
    public static void getAllCities() throws SQLException{
        PreparedStatement statement = conn.prepareStatement(selectStatement);
        statement.execute();
        ResultSet rs = statement.getResultSet();

        while (rs.next()) {
            int cityId = rs.getInt("cityId");
            String cityName = rs.getString("city");
            new City(cityId, cityName);

        }
    }


}
