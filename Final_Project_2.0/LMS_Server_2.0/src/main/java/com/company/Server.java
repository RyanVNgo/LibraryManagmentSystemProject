package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

    // Actively accepts new client and pass them to a thread
    public static void main(String[] args) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        int port = 7111;
        ServerSocket server;
        Socket socket;
        server = new ServerSocket(port);

        while(true) {
            socket = server.accept();
            Thread thread = new userThread(socket);
            thread.start();
            Date date = new Date(System.currentTimeMillis());
            System.out.println(" New Client Connected -> Time: " + formatter.format(date));
        }
    }

    // Passes a client as a new UserInstance (with an ID) through a thread
    private static class userThread extends Thread {
        Socket socket;
        public userThread (Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                System.out.println(" - User Instance Created");
                new UserInstance(socket);
                System.out.println(" - User Instance Terminated");
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

}