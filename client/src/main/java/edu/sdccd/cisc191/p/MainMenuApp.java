package edu.sdccd.cisc191.p;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuApp extends JFrame implements ActionListener {

    private JButton addBookButton;
    private JButton removeBookButton;
    private JButton viewBooksButton;
    private JButton signOutButton;

    MainMenuApp() {
        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Main Menu");

        addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(this);

        removeBookButton = new JButton("Remove Book");
        removeBookButton.addActionListener(this);

        viewBooksButton = new JButton("View Books");
        viewBooksButton.addActionListener(this);

        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(this);

        setLayout(new GridBagLayout());
        layoutConst.insets = new Insets(10,10,10,10);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(addBookButton, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        add(removeBookButton, layoutConst);

        layoutConst.gridx = 2;
        layoutConst.gridy = 0;
        add(viewBooksButton, layoutConst);

        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(signOutButton, layoutConst);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceEvent = (JButton) e.getSource();

        if (sourceEvent == addBookButton) {
            try {
                MainAddBook.MainAddBookMain();
                this.dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (sourceEvent == removeBookButton) {
            try {
                MainRemoveBook.MainRemoveBookMain();
                this.dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (sourceEvent == viewBooksButton) {
            try {
                MainViewBooks.MainViewBooksMain();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            LoginApp.LoginAppMain();
            this.dispose();
        }


    }

    // Main Menu Window main method to call
    public static void MenuAppMain() {
        MainMenuApp menuFrame = new MainMenuApp();
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.pack();
        menuFrame.setVisible(true);
    }

}
