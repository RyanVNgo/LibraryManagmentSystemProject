package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainAddBook extends JFrame implements ActionListener {

    private static ArrayList<Book> bookArrayList;
    private JTextField bookTitleField;
    private JTextField authorField;
    private JTextField genreField;
    private JButton addBookButton;
    private JButton returnButton;

    MainAddBook() {
        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Add Book");
        setLayout(new GridBagLayout());

        JLabel directionsLabel = new JLabel("Enter the following: ");
        JLabel bookTitleLabel = new JLabel("Book Title: ");
        JLabel authorLabel = new JLabel("Author: ");
        JLabel genreLabel = new JLabel("Genre(s): ");

        bookTitleField = new JTextField(40);
        bookTitleField.setEditable(true);
        authorField = new JTextField(40);
        authorField.setEditable(true);
        genreField = new JTextField(40);
        genreField.setEditable(true);
        addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(this);
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        layoutConst.insets = new Insets(10,10,10,10);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(directionsLabel, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        add(bookTitleLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(bookTitleField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        add(authorLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        add(authorField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        add(genreLabel, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 3;
        add(genreField, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        add(returnButton, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 4;
        add(addBookButton, layoutConst);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceEvent = (JButton) e.getSource();

        if (sourceEvent == addBookButton) {
            int bookId = 0;
            String bookIdFinal;
            String bookTitleInput = bookTitleField.getText();
            String authorInput = authorField.getText();
            String genreInput = genreField.getText();

            // Set new book's ID to highest current ID + 1
            for (Book book : bookArrayList) {
                String tempBookIdString = book.getBookID();
                int bookIdCompare = Integer.parseInt(tempBookIdString);
                if (bookIdCompare > bookId) {
                    bookId = bookIdCompare;
                }
            }
            bookIdFinal = Integer.toString(bookId + 1);

            try {
                ClientMain.AddNewBook(bookIdFinal, bookTitleInput, authorInput, genreInput);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            MainMenuApp.MenuAppMain();
            this.dispose();
            JOptionPane.showMessageDialog(null, "Book Successfully Added");

        } else {
            MainMenuApp.MenuAppMain();
            this.dispose();
        }
    }

    public static void MainAddBookMain() throws Exception {
        bookArrayList = ClientMain.ViewAllBooks();
        MainAddBook mainAddBookFrame = new MainAddBook();
        mainAddBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainAddBookFrame.pack();
        mainAddBookFrame.setVisible(true);
    }

}
