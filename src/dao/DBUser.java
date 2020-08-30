package dao;

import controller.LoginController;
import model.City;
import model.Customer;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {
    private static String authStatement = "SELECT * FROM user WHERE userName = ? AND password = ?";
    private static String selectStatement = "SELECT * FROM user";
    private final static Connection conn = DBConnection.getConnection();

    public static void getAllUsers() throws SQLException {
        PreparedStatement statement = conn.prepareStatement(selectStatement);
        statement.execute();
        ResultSet rs = statement.getResultSet();

        while (rs.next()) {
            int userId = rs.getInt("userId");
            String userName = rs.getString("userName");
            new User(userId,userName);
        }
    }

    public static boolean authenticateUser(String user, String password) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(authStatement);
        statement.setString(1,user);
        statement.setString(2,password);
        statement.execute();
        ResultSet rs = statement.getResultSet();

        if (rs.next() == false) {
            return false;
        }
        else {
            int userId = rs.getInt("userId");
            User u = User.getUser(userId);
            User.setCurrentUser(u);
            return true;
        }
    }



}
