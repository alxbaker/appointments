package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    //an ObservableList for all users
    public static ObservableList<User> users = FXCollections.observableArrayList();
    int userId;
    String userName;

    private static User currentUser;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        users.add(this);
    }

    //method to return a user associated with a specific user id
    public static User getUser(int id) {
        for (User u : users) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }

    //method to clear users ObservableList
    public static void clearUsers(){
        users.clear();
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

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return userName;
    }
}
