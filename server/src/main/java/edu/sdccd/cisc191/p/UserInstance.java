package edu.sdccd.cisc191.p;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class UserInstance {

    private static Socket thisSocket;
    private static DataInputStream in;

    // Establish socket and enter control center
    public UserInstance(Socket socket) throws Exception{
        thisSocket = socket;
        UserControlCenter();
    }

    // Universal controller for User -> edu.sdccd.cisc191.p.Server interactions
    private static void UserControlCenter() throws Exception{
        in = new DataInputStream(thisSocket.getInputStream());
        String clientCall = in.readUTF();

        switch (clientCall) {
            case("LOGIN"):
                ValidateLogin();
                break;
            case("VIEW_ALL_USERS"):
                ReturnUsers();
                break;
            case("VIEW_ALL_BOOKS"):
                ReturnBooks();
                break;
            case("ADD_NEW_USER"):
                AddNewUser();
                break;
            case("REMOVE_USER"):
                RemoveExistingUser();
                break;
            case("ADD_NEW_BOOK"):
                AddNewBook();
                break;
            case("REMOVE_BOOK"):
                RemoveExistingBook();
                break;
            case("CLIENT_DISCONNECT"):
                DisconnectClient();
                break;
        }
    }

    // Compare login info with Database info; respond to client
    private static void ValidateLogin() throws Exception {
        in = new DataInputStream(thisSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(thisSocket.getOutputStream());

        String checkType, checkName, checkPass;
        String typeinput, nameInput, passInput;
        boolean valid = false;

        DatabaseHandler dbHandler = new DatabaseHandler();
        ArrayList<User> userList = dbHandler.ObtainAllUsersData();

        typeinput = in.readUTF();
        nameInput = in.readUTF();
        passInput = in.readUTF();

        for (User user : userList) {
            checkType = user.getUserType();
            checkName = user.getUsername();
            checkPass = user.getPassword();
            if (checkType.equals(typeinput) && checkName.equals(nameInput) && checkPass.equals(passInput)) {
                valid = true;
            }
        }
        out.writeBoolean(valid);
        UserControlCenter();
    }

    // Add new user request to DB Handler
    private static void AddNewUser() throws Exception {
        in = new DataInputStream(thisSocket.getInputStream());
        String userType, username, password;

        userType = in.readUTF();
        username = in.readUTF();
        password = in.readUTF();

        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.AddNewUserToDatabase(userType, username, password);
        UserControlCenter();
    }

    // Remove existing user request to DB Handler
    private static void RemoveExistingUser() throws Exception {
        in = new DataInputStream(thisSocket.getInputStream());
        String username;

        username = in.readUTF();
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.RemoveUserFromDatabase(username);
        UserControlCenter();
    }

    // Add new book request to DB Handler
    private static void AddNewBook() throws Exception {
        in = new DataInputStream(thisSocket.getInputStream());
        String bookId, bookTitle, author, genre;

        bookId = in.readUTF();
        bookTitle = in.readUTF();
        author = in.readUTF();
        genre = in.readUTF();

        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.AddNewBookToDatabase(bookId, bookTitle, author, genre);
        UserControlCenter();
    }

    // Remove existing book request to DB Handler
    private static void RemoveExistingBook() throws Exception {
        in = new DataInputStream(thisSocket.getInputStream());
        String bookId;

        bookId = in.readUTF();
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.RemoveBookFromDatabase(bookId);
        UserControlCenter();
    }

    // Collect all user data; return to client as Array List
    private static void ReturnUsers() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(thisSocket.getOutputStream());
        DatabaseHandler dbHandler = new DatabaseHandler();
        ArrayList<User> userArrayList = dbHandler.ObtainAllUsersData();
        objectOutputStream.writeObject(userArrayList);
        UserControlCenter();
    }

    // Collect all book data; return to client as Array List
    private static void ReturnBooks() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(thisSocket.getOutputStream());
        DatabaseHandler dbHandler = new DatabaseHandler();
        ArrayList<Book> bookArrayList = dbHandler.ObtainAllBookData();
        objectOutputStream.writeObject(bookArrayList);
        UserControlCenter();
    }

    // Close client's socket/ disconnects client from server
    private static void DisconnectClient() throws Exception {
        thisSocket.close();
    }

}