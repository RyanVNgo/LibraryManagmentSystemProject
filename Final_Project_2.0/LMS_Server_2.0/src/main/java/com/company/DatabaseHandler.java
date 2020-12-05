package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {

    ArrayList<User> userArrayList = new ArrayList<User>();

    public void ClearArray() {
        userArrayList.clear();
    }

    // Create connection to the database -> LMS_DB.db
    private Connection connect() {
        String url = "jdbc:sqlite:src\\LMS_DB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Obtains all User data from DB, creates User objects, stores objects in userArrayList
    // Returns userArrayList to method that made call
    public ArrayList<User> ObtainAllUsersData() {
        String sql = "SELECT usertype, username, password FROM UserLoginData";
        String userType, username, password;
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                userType = rs.getString("userType");
                username = rs.getString("username");
                password = rs.getString("password");
                User tempUser = new User(userType, username, password);
                userArrayList.add(tempUser);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userArrayList;
    }

}