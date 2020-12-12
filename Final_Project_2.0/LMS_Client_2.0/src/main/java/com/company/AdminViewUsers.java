package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminViewUsers extends JFrame {

    private static ArrayList<User> userArrayList;

    AdminViewUsers() {
        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Viewing Users");
        setLayout(new GridBagLayout());

        JLabel viewUsersLabel = new JLabel("All Users:");

        // Making Table: Start //
        Object[][] tableValues = new Object[userArrayList.size()][3];
        String[] columnHeadings = {"User Type:", "Username:", "Password:"};

        JTable userDataTable = new JTable(tableValues, columnHeadings);
        userDataTable.setEnabled(false);

        for (int i = 0; i < userArrayList.size(); i++) {
            userDataTable.setValueAt(userArrayList.get(i).getUserType(), i, 0);
            userDataTable.setValueAt(userArrayList.get(i).getUsername(), i, 1);
            userDataTable.setValueAt(userArrayList.get(i).getPassword(), i, 2);
        }
        // Making Table: End //

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(10,10,1,10);
        add(viewUsersLabel, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        layoutConst.insets = new Insets(10,10,0,10);
        add(userDataTable.getTableHeader(), layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        layoutConst.insets = new Insets(0,10,10,10);
        add(userDataTable, layoutConst);
    }

    // Admin View Users window main method to call
    public static void AdminViewUsersMain() throws Exception {
        userArrayList = ClientMain.ViewAllUsers();
        AdminViewUsers adminViewUsersFrame = new AdminViewUsers();
        adminViewUsersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminViewUsersFrame.pack();
        adminViewUsersFrame.setVisible(true);
    }

}
