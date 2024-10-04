package com.bookstore.books.dao;

import java.util.List;

import com.bookstore.books.entities.Book;

public interface BookDAO {
	Book createBook(Book book);

    public Book getBookById(int bookId);

    public List<Book> getAllBooks();

    public Book updateBook(int id, Book updatedBook);

    public boolean deleteBook(int id);

    public List<Book> findBooksByAuthor(int authorId);
}
