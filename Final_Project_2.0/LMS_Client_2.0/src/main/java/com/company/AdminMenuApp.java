package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenuApp extends JFrame implements ActionListener {

    private JButton viewUserButton;
    private JButton addUserButton;
    private JButton removeUserButton;
    private JButton signOutButton;

    AdminMenuApp() {
        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Admin Menu");

        viewUserButton = new JButton("View Users");
        viewUserButton.addActionListener(this);
        addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this);
        removeUserButton = new JButton("Remove User");
        removeUserButton.addActionListener(this);
        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(this);

        setLayout(new GridBagLayout());
        layoutConst.insets = new Insets(10,10,10,10);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(viewUserButton, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        add(addUserButton, layoutConst);

        layoutConst.gridx = 2;
        layoutConst.gridy = 0;
        add(removeUserButton, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(signOutButton, layoutConst);
    }

    // Button action
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceEvent = (JButton) e.getSource();

        if (sourceEvent == addUserButton) {
            try {
                AdminAddUser.AdminAddUserMain();
                this.dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (sourceEvent == removeUserButton) {
            try {
                AdminRemoveUser.AdminRemoveUserMain();
                this.dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (sourceEvent == viewUserButton){
            try {
                AdminViewUsers.AdminViewUsersMain();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            LoginApp.LoginAppMain();
            this.dispose();
        }
    }

    // Admin Menu Window main method to call
    public static void AdminMenuAppMain() {
        AdminMenuApp adminMenuFrame = new AdminMenuApp();
        adminMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminMenuFrame.pack();
        adminMenuFrame.setVisible(true);
    }

}
