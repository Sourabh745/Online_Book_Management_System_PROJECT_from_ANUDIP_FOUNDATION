package com.bookstore.books.controllers;

import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.Author;
import com.bookstore.books.services.BookService;
import com.bookstore.books.services.implementation.AuthorServiceImplementation;
import com.bookstore.books.services.implementation.BookServiceImplementation;
import com.bookstore.books.services.AuthorService;

import java.util.Date;
import java.util.List;

public class BookController {

    private BookService bookService;
    private AuthorService authorService;

    public BookController() {
        this.bookService = new BookServiceImplementation();  // Assuming you have a service layer for Book
        this.authorService = new AuthorServiceImplementation();  // Assuming you have a service layer for Author
    }

    // Creates a new Book and returns the created Book object
    public Book createBook(String bookId, String title, int authorId, String description, double price) {
        // Find the author by ID to associate with the book
        Author author = authorService.getAuthorById(authorId);
        if (author == null) {
            System.out.println("Author not found with ID: " + authorId);
            return null;
        }

        // Create the book entity and save it
        Book book = new Book(bookId, title, author, description, price);
        Book createdBook = bookService.createBook(book);
        
        if (createdBook != null) {
            System.out.println("Book created successfully: " + createdBook);
        } else {
            System.out.println("Failed to create book.");
        }
        return createdBook;
    }

    // Returns a Book by its ID
    public Book getBookById(String bookId) {
        Book book = bookService.getBookById(bookId);
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book not found.");
        }
        return book;
    }

    // Returns a list of all Books
    public List<Book> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books != null && !books.isEmpty()) {
            for (Book book : books) {
                System.out.println(book);
            }
        } else {
            System.out.println("No books found.");
        }
        return books;
    }

    // Updates a book and returns the updated Book object
    public Book updateBook(String bookId, String newTitle, String newDescription, double newPrice, int newQuantity) {
        // Fetch the existing book to update
        Book existingBook = bookService.getBookById(bookId);
        if (existingBook == null) {
            System.out.println("Book not found with ID: " + bookId);
            return null;
        }

        // Update the book details
        existingBook.setTitle(newTitle);
        existingBook.setDescription(newDescription);
        existingBook.setPrice(newPrice);
        existingBook.setQuantity(newQuantity);

        // Save the updated book
        Book updatedBook = bookService.updateBook(bookId, existingBook);
        if (updatedBook != null) {
            System.out.println("Book updated successfully: " + updatedBook);
        } else {
            System.out.println("Failed to update book.");
        }
        return updatedBook;
    }

    // Deletes a book by its ID and returns a boolean indicating success or failure
    public boolean deleteBook(String bookId) {
        boolean isDeleted = bookService.deleteBook(bookId);
        if (isDeleted) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Failed to delete book.");
        }
        return isDeleted;
    }
}



//    // Creates a book and returns the created Book object
//    public Book createBook(Book book) {
//        return bookService.addBook(book); // Assuming addBook() returns the created Book
//    }
//
//    // Returns a Book object by its ID
//    public Book getBookById(int id) {
//        // TODO: Implement logic to fetch a book by its ID
//        return bookService.getBookById(id); // Assuming getBookById() returns a Book
//    }
//
//    // Returns a list of all books
//    public List<Book> getAllBooks() {
//        // TODO: Implement logic to fetch all books
//        return bookService.getAllBooks(); // Assuming getAllBooks() returns a List of Book
//    }
//
//    // Updates a book and returns the updated Book object
//    public Book updateBook(int id, Book updatedBook) {
//        // TODO: Implement logic to update a book by its ID
//        return bookService.updateBook(id, updatedBook); // Assuming updateBook() returns the updated Book
//    }
//
//    // Deletes a book by its ID and returns a boolean indicating success or failure
//    public boolean deleteBook(int id) {
//        // TODO: Implement logic to delete a book by its ID
//        return bookService.deleteBook(id); // Assuming deleteBook() returns a boolean
//    }
//
//    // Returns a list of books by a specific author
//    public List<Book> findBooksByAuthor(int authorId) {
//        // TODO: Implement logic to find all books by a specific author ID
//        return bookService.findBooksByAuthor(authorId); // Assuming findBooksByAuthor() returns a List of Book
//    }

//
