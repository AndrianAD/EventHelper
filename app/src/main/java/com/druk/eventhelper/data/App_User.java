package com.druk.eventhelper.data;


public class App_User {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String userLastName;
    private String email;
    private String password;

    public App_User(String userName, String userLastName, String email, String password) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.email = email;
        this.password = password;
    }
}
