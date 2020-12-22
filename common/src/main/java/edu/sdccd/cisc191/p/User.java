package edu.sdccd.cisc191.p;

import java.io.Serializable;

public class User implements Serializable {

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