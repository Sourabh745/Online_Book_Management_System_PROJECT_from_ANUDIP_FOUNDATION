package com.bookstore.books.services;

import java.util.List;

import com.bookstore.books.entities.Book;

public interface BookService{
   

    Book createBook(Book book);

    public Book getBookById(int bookId);

    public List<Book> getAllBooks();

    public Book updateBook(int bookId, Book updatedBook);

    public boolean deleteBook(int id);

    public List<Book> findBooksByAuthor(int authorId);
}

