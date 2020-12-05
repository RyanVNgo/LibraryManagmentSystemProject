package com.company;

public class User {

    private final String userType;
    private final String username;
    private final String password;

    User(String userType, String username, String password) {
        this.userType = userType;
        this.username = username;
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}