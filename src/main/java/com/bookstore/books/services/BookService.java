package com.bookstore.books.services;

import java.util.List;

import com.bookstore.books.entities.Book;

public interface BookService{
   

    Book createBook(Book book);

    public Book getBookById(String bookId);

    public List<Book> getAllBooks();

    public Book updateBook(String bookId, Book updatedBook);

    public boolean deleteBook(String id);

    public List<Book> findBooksByAuthor(int authorId);
}

