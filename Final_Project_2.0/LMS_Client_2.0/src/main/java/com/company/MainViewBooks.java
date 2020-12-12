package com.company;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class MainViewBooks extends JFrame {

    private static ArrayList<Book> bookArrayList;

    MainViewBooks() {
        GridBagConstraints layoutConst = new GridBagConstraints();
        setTitle("Viewing Books");
        setLayout(new GridBagLayout());

        JLabel viewBooksLabel = new JLabel("All Books:");

        // Making Table: Start //
        Object[][] tableValues = new Object[bookArrayList.size()][4];
        String[] columnHeadings = {"Book ID:", "Book Title:", "Author:", "Genre:"};

        JTable userDataTable = new JTable(tableValues, columnHeadings);
        userDataTable.setEnabled(false);

        TableColumnModel columnModel = userDataTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(75);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);

        for (int i = 0; i < bookArrayList.size(); i++) {
            userDataTable.setValueAt(bookArrayList.get(i).getBookID(), i, 0);
            userDataTable.setValueAt(bookArrayList.get(i).getBookTitle(), i, 1);
            userDataTable.setValueAt(bookArrayList.get(i).getBookAuthor(), i, 2);
            userDataTable.setValueAt(bookArrayList.get(i).getBookGenre(), i, 3);
        }
        // Making Table: End //

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(10,10,1,10);
        add(viewBooksLabel, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        layoutConst.insets = new Insets(10,10,0,10);
        add(userDataTable.getTableHeader(), layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        layoutConst.insets = new Insets(0,10,10,10);
        add(userDataTable, layoutConst);

    }

    public static void MainViewBooksMain() throws Exception{
        bookArrayList = ClientMain.ViewAllBooks();
        MainViewBooks mainViewBooksFrame = new MainViewBooks();
        mainViewBooksFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainViewBooksFrame.pack();
        mainViewBooksFrame.setVisible(true);
    }

}
