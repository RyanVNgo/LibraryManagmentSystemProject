package edu.sdccd.cisc191.p;

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
            int bookIdArrayLength = bookArrayList.size();
            int[] bookIdArray = new int[bookIdArrayLength];

            int IntBookIdFinal;
            String StringBookIdFinal;

            String bookTitleInput = bookTitleField.getText();
            String authorInput = authorField.getText();
            String genreInput = genreField.getText();

            // add all book ID's to an array
            for (int i = 0; i < bookIdArray.length; i++) {
                int tempBookId = Integer.parseInt(bookArrayList.get(i).getBookID());
                bookIdArray[i] = tempBookId;
            }

            // call method to sort array of ID's from least to greatest
            bookIdArray = SortBookIdArray(bookIdArray);

            // call method to find lowest available book ID
            IntBookIdFinal = LowestBookId(bookIdArray);

            // parse IntBookIdFinal into a string
            StringBookIdFinal = Integer.toString(IntBookIdFinal);

            try {
                ClientMain.AddNewBook(StringBookIdFinal, bookTitleInput, authorInput, genreInput);
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

    // sorts book ID array from least to greatest
    private static int[] SortBookIdArray(int[] arrayInput) {
        for (int i = 1; i < arrayInput.length; i++) {
            int key = arrayInput[i];
            int j = i -1;

            while (j >= 0 && arrayInput[j] > key) {
                arrayInput[j + 1] = arrayInput[j];
                j = j - 1;

            }
            arrayInput[j + 1] = key;
        }
        return arrayInput;
    }

    // finds lowest available book ID
    private static int LowestBookId(int[] arrayInput) {
        int finalBookId = 0;

        // attempts to set final book ID to lowest available ID
        for (int i = 0; i < arrayInput.length; i++) {
            if (arrayInput[i] != i + 1) {
                finalBookId = i;
            }
        }

        // sets final book ID to highest ID plus 1 if there is no available lower one
        if (finalBookId == 0) {
            finalBookId = arrayInput.length + 1;
        }

        return finalBookId;
    }

}
