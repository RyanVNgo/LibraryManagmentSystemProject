package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginApp extends JFrame implements ActionListener {

    private JTextField userTypeField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton exitButton;

    // Login window GUI
    LoginApp() {

        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Login");

        JLabel userTypeLabel = new JLabel("User Type");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        userTypeField = new JTextField(16);
        userTypeField.setEditable(true);
        usernameField = new JTextField(16);
        usernameField.setEditable(true);
        passwordField = new JTextField(16);
        passwordField.setEditable(true);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        setLayout(new GridBagLayout());
        layoutConst.insets = new Insets(10,10,10,10);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(userTypeLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        add(userTypeField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        add(usernameLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(usernameField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        add(passwordLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        add(passwordField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        add(exitButton, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 3;
        add(loginButton, layoutConst);

    }

    // Button Action
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceEvent = (JButton) e.getSource();

        if (sourceEvent == loginButton) {
            String userTypeInput = userTypeField.getText();
            String usernameInput = usernameField.getText();
            String passwordInput = passwordField.getText();
            try {
                int opt = ClientMain.Login(userTypeInput, usernameInput, passwordInput);
                if (opt == 0) {
                    if (userTypeInput.equals("ADMIN")) {
                        AdminMenuApp.AdminMenuAppMain();
                    } else {
                        MainMenuApp.MenuAppMain();
                    }
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            try {
                ClientMain.Disconnect();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.exit(0);
        }

    }

    // Login window main method to call
    public static void LoginAppMain() {
        LoginApp loginFrame = new LoginApp();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);
    }

}
