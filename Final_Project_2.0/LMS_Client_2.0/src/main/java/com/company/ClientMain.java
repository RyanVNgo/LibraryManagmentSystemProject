package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMain {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    // Establish socket, start Login progress
    public static void main(String[] args) throws Exception{
        int port = 7111;
        //String host = "localhost";

        InetAddress host = InetAddress.getLocalHost();
        socket = new Socket(host.getHostName(), port);

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

    // Add user request to server with data input
    public static void AddNewUser(String userType, String username, String password) throws Exception {
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("ADD_NEW_USER");
        out.writeUTF(userType);
        out.writeUTF(username);
        out.writeUTF(password);
    }

    // Remove user request to server with data input
    public static void RemoveUser(String username) throws Exception{
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("REMOVE_USER");
        out.writeUTF(username);
    }

    // Add book request to server with data input
    public static void AddNewBook(String bookId, String bookTitle, String author, String genre) throws Exception{
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("ADD_NEW_BOOK");
        out.writeUTF(bookId);
        out.writeUTF(bookTitle);
        out.writeUTF(author);
        out.writeUTF(genre);
    }

    // Remove book request to server with data input
    public static void RemoveBook(String bookId) throws Exception {
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("REMOVE_BOOK");
        out.writeUTF(bookId);
    }

    // Disconnect client from server
    public static void Disconnect() throws Exception{
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("CLIENT_DISCONNECT");
    }


}