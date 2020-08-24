package dao;

import controller.LoginController;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {
    private final static String selectStatement = "SELECT * FROM user WHERE userName = ? AND password = ?";
    private final static Connection conn = DBConnection.getConnection();

    public static boolean authenticateUser(String user, String password) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(selectStatement);
        statement.setString(1,user);
        statement.setString(2,password);
        statement.execute();
        ResultSet rs = statement.getResultSet();

        if (rs.next() == false) {
            return false;
        }
        else {
            int userId = rs.getInt("userId");
            String userName = rs.getString("userName");
            User u = new User(userId, userName);
            LoginController.setLoggedInUser(u);
            return true;
        }
    }



}
