package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {
    //SQL statements
    private static String authStatement = "SELECT * FROM user WHERE userName = ? AND password = ?";
    private static String selectStatement = "SELECT * FROM user";

    //Database connection
    private final static Connection conn = DBConnection.getConnection();

    //method to get all users
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

    //method to authenticate a given user
    public static boolean authenticateUser(String user, String password) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(authStatement);
        statement.setString(1,user);
        statement.setString(2,password);
        statement.execute();
        ResultSet rs = statement.getResultSet();

        if (!rs.next()) {
            return false;
        }
        else {
            int userId = rs.getInt("userId");
            User u = User.getUser(userId);

            //set current logged in user
            User.setCurrentUser(u);
            return true;
        }
    }



}
