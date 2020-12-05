package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    // Establish socket, start Login progress
    public static void main(String[] args) throws Exception{
        int port = 7111;
        String host = "localhost";
        socket = new Socket(host, port);
        LoginController();
    }

    // Controls login state
    private static void LoginController() throws Exception {
        int opt;
        opt = Login();
        switch (opt) {
            case (0) -> BaseController();
            case (1) -> LoginController();
            case (2) -> System.exit(0);
        }
    }

    // Input Login data, request server for confirmation
    private static int Login() throws Exception {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        Scanner scnr = new Scanner(System.in);
        String userType, username, password;
        boolean valid;
        int opt;

        System.out.print("Enter User Type: ");
        userType = scnr.next();
        System.out.print("Enter Username: ");
        username = scnr.next();
        System.out.print("Enter Password: ");
        password = scnr.next();

        if (!userType.equals("q")) {
            out.writeUTF(userType);
            out.writeUTF(username);
            out.writeUTF(password);
            valid = in.readBoolean();
            if(valid) { opt = 0; }
            else {
                System.out.println(" ! Invalid Login !");
                opt = 1;
            }
        } else {
            out.writeUTF("q");
            out.writeUTF("q");
            out.writeUTF("q");
            opt = 2;
        }
        return opt;
    }

    // Main client menu controller
    private static void BaseController() {
        System.out.println(" ! Base Controller Reached !");
    }

}