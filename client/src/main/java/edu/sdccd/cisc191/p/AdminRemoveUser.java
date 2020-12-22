package edu.sdccd.cisc191.p;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminRemoveUser extends JFrame implements ActionListener {

    private static ArrayList<User> userArrayList;
    private JTextField usernameField;
    private JButton removeUserButton;
    private JButton returnButton;

    AdminRemoveUser() {
        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Remove User");
        setLayout(new GridBagLayout());

        JLabel directionLabel = new JLabel("Enter username of User to delete:");

        usernameField = new JTextField(16);
        usernameField.setEditable(true);
        removeUserButton = new JButton("Remove User");
        removeUserButton.addActionListener(this);
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        layoutConst.insets = new Insets(10,10,10,10);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(directionLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        add(usernameField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        add(returnButton, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(removeUserButton, layoutConst);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceEvent = (JButton) e.getSource();

        if (sourceEvent == removeUserButton) {
            String usernameInput = usernameField.getText();
            int numOfAdmin = 0;
            boolean permit = false;
            boolean isAdminRequest = false;

            // Declares the number of Admins and permit value
            for (User user : userArrayList) {
                String typeCompare = user.getUserType();
                String nameCompare = user.getUsername();

                // Counts number of Admins
                if (typeCompare.equals("ADMIN")) {
                    numOfAdmin++;
                }

                // Checks if user exists; declares if it's an admin remove request
                if (usernameInput.equals(nameCompare)) {
                    permit = true;
                    if (typeCompare.equals("ADMIN")) {
                        isAdminRequest = true;
                    }
                }
            }

            // Checks for cases
            if (permit && !isAdminRequest) {
                try {
                    ClientMain.RemoveUser(usernameInput);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                AdminMenuApp.AdminMenuAppMain();
                this.dispose();
                JOptionPane.showMessageDialog(null, "User Successfully Removed");

            } else if (permit && numOfAdmin > 1) {
                try {
                    ClientMain.RemoveUser(usernameInput);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                AdminMenuApp.AdminMenuAppMain();
                this.dispose();
                JOptionPane.showMessageDialog(null, "User Successfully Removed");

            } else if (permit && numOfAdmin == 1) {
                JOptionPane.showMessageDialog(null, "Must Leave One Admin");
            } else if (!permit) {
                JOptionPane.showMessageDialog(null, "User Doesn't Exist");
            }

        } else {
            AdminMenuApp.AdminMenuAppMain();
            this.dispose();
        }
    }

    public static void AdminRemoveUserMain() throws Exception{
        userArrayList = ClientMain.ViewAllUsers();
        AdminRemoveUser adminRemoveUserFrame = new AdminRemoveUser();
        adminRemoveUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminRemoveUserFrame.pack();
        adminRemoveUserFrame.setVisible(true);
    }

}
