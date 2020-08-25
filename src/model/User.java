package model;

public class User {
    int userId;
    String userName;

    private static User currentUser;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        setCurrentUser(this);
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
