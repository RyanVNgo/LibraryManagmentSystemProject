package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {

    ArrayList<User> userArrayList = new ArrayList<>();
    ArrayList<Book> bookArrayList = new ArrayList<>();

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
    // Returns userArrayList to method caller
    public ArrayList<User> ObtainAllUsersData() {
        String sql = "SELECT userType, username, password FROM UserLoginData";
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

    // Obtains all Book data from DB, creates Book objects, stores object in bookArrayList
    // Return bookArrayList to method caller
    public ArrayList<Book> ObtainAllBookData() {
        String sql = "Select BookID, BookTitle, Author, Genre FROM BookData";
        String bookId, bookTitle, author, genre;
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                bookId = rs.getString("BookID");
                bookTitle = rs.getString("BookTitle");
                author = rs.getString("Author");
                genre = rs.getString("Genre");
                Book tempBook = new Book(bookId, bookTitle, author, genre);
                bookArrayList.add(tempBook);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookArrayList;
    }

    // Adds new user data in DB given input from method caller
    public void AddNewUserToDatabase(String userType, String username, String password) {
        String sql = "INSERT INTO UserLoginData(userType, username, password) VALUES(?,?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userType);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    // Removes existing user from DB given username from method caller
    public void RemoveUserFromDatabase(String username) {
        String sql = "DELETE FROM UserLoginData WHERE username = ?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    // Adds new book data to DB given input from method caller
    public void AddNewBookToDatabase(String bookId, String bookTitle, String author, String genre) {
        String sql = "INSERT INTO BookData(BookID, BookTitle, Author, Genre) VALUES(?,?,?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookId);
            pstmt.setString(2, bookTitle);
            pstmt.setString(3, author);
            pstmt.setString(4, genre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    // Removes existing book from DB given bookId from method caller
    public void RemoveBookFromDatabase(String bookId) {
        String sql = "DELETE FROM BookData WHERE BookID = ?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }

    }


}