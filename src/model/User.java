package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    public static ObservableList<User> users = FXCollections.observableArrayList();
    int userId;
    String userName;

    private static User currentUser;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        users.add(this);
    }

    public static User getUser(int id) {
        for (User u : users) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
