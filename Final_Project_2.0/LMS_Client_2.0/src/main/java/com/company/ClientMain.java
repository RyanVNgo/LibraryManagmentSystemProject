package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMain {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    // Establish socket, start Login progress
    public static void main(String[] args) throws Exception{
        int port = 7111;
        String host = "localhost";
        socket = new Socket(host, port);

        LoginApp.LoginAppMain();
    }

    // Input Login data, request server for confirmation
    public static int Login(String userType, String username, String password) throws Exception {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        boolean valid;
        int opt;
        out.writeUTF("LOGIN");

        out.writeUTF(userType);
        out.writeUTF(username);
        out.writeUTF(password);
        valid = in.readBoolean();
        if(valid) { opt = 0; } else { opt = 1; }
        return opt;
    }

    // requests array of all Users from Server
    public static ArrayList<User> ViewAllUsers() throws Exception {
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("VIEW_ALL_USERS");
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return (ArrayList<User>) objectInputStream.readObject();
    }

    // Requests array of all Books from server
    public static ArrayList<Book> ViewAllBooks() throws Exception{
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("VIEW_ALL_BOOKS");
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return (ArrayList<Book>) objectInputStream.readObject();
    }

}