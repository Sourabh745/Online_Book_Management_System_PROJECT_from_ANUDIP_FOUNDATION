package com.bookstore.books.services;

import java.util.List;

import com.bookstore.books.entities.Author;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.User;

public interface AdminService {
   
	// Allows the admin to add a new book and returns the created Book object
    Book addBook(Book book);

    // Allows the admin to update book details and returns the updated Book object
    Book updateBook(String bookId, Book updatedBook);

    // Allows the admin to delete a book and returns a boolean indicating success or failure
    boolean deleteBook(String bookId);

    // Retrieves a list of all registered users
    List<User> getAllUsers();

    // Retrieves details of a specific user by their ID
    User getUserById(int userId);

    // Deletes a user by their ID and returns a boolean indicating success or failure
    boolean deleteUser(int userId);

    // Retrieves a list of all orders placed
    List<Orders> getAllOrders();  // Changed from viewAllOrders()

    // Retrieves details of a specific order by its ID
    Orders getOrderById(int orderId);  // Changed from viewOrderDetails()
    
	Author getAuthorById(int authorId);

}
