package com.bookstore.books.services.implementation;

import java.util.List;

import com.bookstore.books.dao.BookDAO;
import com.bookstore.books.dao.implementation.BookDAOImplementation;
import com.bookstore.books.entities.Book;
import com.bookstore.books.services.BookService;

public class BookServiceImplementation implements BookService {

    private BookDAO bookDAO;

    public BookServiceImplementation() {
        this.bookDAO = new BookDAOImplementation();
    }

    @Override
    public Book createBook(Book book) {
        return bookDAO.createBook(book);
    }

    @Override
    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book updateBook(int bookId, Book updatedBook) {
        return bookDAO.updateBook(bookId, updatedBook);
    }

    @Override
    public boolean deleteBook(int bookId) {
        return bookDAO.deleteBook(bookId);
    }

    @Override
    public List<Book> findBooksByAuthor(int authorId) {
        return bookDAO.findBooksByAuthor(authorId);
    }
}
