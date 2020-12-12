package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainRemoveBook extends JFrame implements ActionListener {

    private static ArrayList<Book> bookArrayList;
    private JTextField bookIdField;
    private JButton removeBookButton;
    private JButton returnButton;

    MainRemoveBook() {
        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Remove Book");
        setLayout(new GridBagLayout());

        JLabel directionLabel = new JLabel("Enter ID of Book to delete:");

        bookIdField = new JTextField(16);
        bookIdField.setEditable(true);
        removeBookButton = new JButton("Remove Book");
        removeBookButton.addActionListener(this);
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        layoutConst.insets = new Insets(10,10,10,10);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(directionLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        add(bookIdField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        add(returnButton, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(removeBookButton, layoutConst);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceEvent = (JButton) e.getSource();

        if (sourceEvent == removeBookButton) {
            String bookIdInput = bookIdField.getText();
            boolean exists = false;

            // Finds if book exists given ID input
            for (Book book : bookArrayList) {
                String bookIdCompare = book.getBookID();
                if (bookIdInput.equals(bookIdCompare)) {
                    exists = true;
                    break;
                }
            }

            // Execute
            if (exists) {
                try {
                    ClientMain.RemoveBook(bookIdInput);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                MainMenuApp.MenuAppMain();
                this.dispose();
                JOptionPane.showMessageDialog(null, "Book Successfully Removed");
            } else {
                JOptionPane.showMessageDialog(null, "Book Does Not Exist");
            }

        } else {
            MainMenuApp.MenuAppMain();
            this.dispose();
        }
    }

    public static void MainRemoveBookMain() throws Exception {
        bookArrayList = ClientMain.ViewAllBooks();
        MainRemoveBook mainRemoveBookFrame = new MainRemoveBook();
        mainRemoveBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainRemoveBookFrame.pack();
        mainRemoveBookFrame.setVisible(true);
    }

}
