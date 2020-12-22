package edu.sdccd.cisc191.p;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminAddUser extends JFrame implements ActionListener {

    private static ArrayList<User> userArrayList;
    private JTextField userTypeField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton addUserButton;
    private JButton returnButton;

    AdminAddUser() {
        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Add User");
        setLayout(new GridBagLayout());

        JLabel directionsLabel = new JLabel("Enter the following: ");
        JLabel userTypeLabel = new JLabel("User Type: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        userTypeField = new JTextField(16);
        userTypeField.setEditable(true);
        usernameField = new JTextField(16);
        usernameField.setEditable(true);
        passwordField = new JTextField(16);
        passwordField.setEditable(true);
        addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this);
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        layoutConst.insets = new Insets(10,10,10,10);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(directionsLabel, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        add(userTypeLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(userTypeField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        add(usernameLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        add(usernameField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        add(passwordLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 3;
        add(passwordField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        add(returnButton, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 4;
        add(addUserButton, layoutConst);
    }

    // Button action
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceEvent = (JButton) e.getSource();

        if (sourceEvent == addUserButton) {
            boolean exists = false;

            String usernameInput = usernameField.getText();
            String userTypeInput = userTypeField.getText();
            String passwordInput = passwordField.getText();

            // Checks if inputted username is already in use
            for (User user : userArrayList) {
                String nameCompare = user.getUsername();
                if (usernameInput.equals(nameCompare)) {
                    exists = true;
                    break;
                }
            }

            // Checks for cases
            if (!exists) {
                try {
                    ClientMain.AddNewUser(userTypeInput, usernameInput, passwordInput);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                AdminMenuApp.AdminMenuAppMain();
                this.dispose();
                JOptionPane.showMessageDialog(null, "User Successfully Added");

            } else {
                JOptionPane.showMessageDialog(null, "Username not available");
            }

        } else {
            AdminMenuApp.AdminMenuAppMain();
            this.dispose();
        }
    }

    // Admin Add User main method to call
    public static void AdminAddUserMain() throws Exception {
        userArrayList = ClientMain.ViewAllUsers();
        AdminAddUser adminAddUserFrame = new AdminAddUser();
        adminAddUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminAddUserFrame.pack();
        adminAddUserFrame.setVisible(true);
    }

}
