package com.company;

import java.io.Serializable;

public class Book implements Serializable {

    private final String bookID;
    private final String bookTitle;
    private final String bookAuthor;
    private final String bookGenre;

    Book(String bookID, String bookTitle, String bookAuthor, String bookGenre) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
    }

    public String getBookID() {
        return bookID;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public String getBookGenre() {
        return bookGenre;
    }

}
