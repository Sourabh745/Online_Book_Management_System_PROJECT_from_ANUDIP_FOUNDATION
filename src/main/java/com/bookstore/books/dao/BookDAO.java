package com.bookstore.books.dao;

import java.util.List;

import com.bookstore.books.entities.Book;

public interface BookDAO {
	Book createBook(Book book);

    public Book getBookById(String bookId);

    public List<Book> getAllBooks();

    public Book updateBook(String id, Book updatedBook);

    public boolean deleteBook(String id);

    public List<Book> findBooksByAuthor(int authorId);
}
