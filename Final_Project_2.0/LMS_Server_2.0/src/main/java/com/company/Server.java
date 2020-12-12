package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server extends JFrame implements ActionListener {

    // Actively accepts new client and pass them to a thread
    public static void main(String[] args) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        int port = 7111;

        Socket socket;
        ServerSocket server = new ServerSocket(port);

        Server serverFrame = new Server();
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverFrame.pack();
        serverFrame.setVisible(true);

        while(true) {
            socket = server.accept();
            Thread thread = new userThread(socket);
            thread.start();
            Date date = new Date(System.currentTimeMillis());
            System.out.println(" New Client Connected -> Time: " + formatter.format(date));
        }

    }

    // Passes a client as a new UserInstance through a thread
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

    // Server visualization - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    Server() {
        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Server");
        setLayout(new GridBagLayout());
        layoutConst.insets = new Insets(10,10,10,10);

        JLabel serverLabel = new JLabel("Server Status:");

        JTextField statusField = new JTextField(16);
        statusField.setEditable(false);
        statusField.setText("Online");

        JButton shutdownServerButton = new JButton("Shutdown");
        shutdownServerButton.addActionListener(this);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(serverLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        add(statusField, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(shutdownServerButton, layoutConst);
    }

    // Button action (closes server/program)
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}