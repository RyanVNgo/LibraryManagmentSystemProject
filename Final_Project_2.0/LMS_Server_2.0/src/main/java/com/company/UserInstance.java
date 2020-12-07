package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class UserInstance {

    private static Socket thisSocket;
    private static DataInputStream in;
    private static DataOutputStream out;

    // Establish socket and enter control center
    public UserInstance(Socket socket) throws Exception{
        thisSocket = socket;
        UserControlCenter();
    }

    // Universal controller for User -> Server interactions
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
        }
    }

    // Compare login info with Database info; respond to client
    private static void ValidateLogin() throws Exception {
        in = new DataInputStream(thisSocket.getInputStream());
        out = new DataOutputStream(thisSocket.getOutputStream());
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

    // Collect all user data; return to client as Array List
    private static void ReturnUsers() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(thisSocket.getOutputStream());
        DatabaseHandler dbHandler = new DatabaseHandler();
        ArrayList<User> userArrayList = dbHandler.ObtainAllUsersData();
        objectOutputStream.writeObject(userArrayList);
        UserControlCenter();
    }

    // Collect all book data; return to client as Array List
    public static void ReturnBooks() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(thisSocket.getOutputStream());
        DatabaseHandler dbHandler = new DatabaseHandler();
        ArrayList<Book> bookArrayList = dbHandler.ObtainAllBookData();
        objectOutputStream.writeObject(bookArrayList);
        UserControlCenter();
    }

}