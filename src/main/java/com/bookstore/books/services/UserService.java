package com.bookstore.books.services;

import java.util.List;

import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;

public interface UserService  {
    
    public User registerUser(User user);
    
    User authenticateUser(String username, String password);  // Method for user authentication

    // Allows a user to log in and returns the User object if successful
    public User loginUser(String username, String password);

    // Retrieves details of the logged-in user by their ID
    public User getUserDetails(int userId);
    
    // Allows the logged-in user to update their own information and returns the updated User object
    public void updateUser(int userId, User updatedUser);

    // Allows the user to delete their own account and returns a boolean indicating success or failure
    public boolean deleteAccount(int userId);

    public List<Orders> viewOrderHistory(int userId) ;
    
    // Allows the user to place a new order and returns the created Order object
    public Orders placeOrder(User user, List<OrderItems> orderItems, Payment payment);
    
    // Allows the user to view details of a specific order they placed
    public Orders viewOrderDetails(int orderId) ;

    // Allows the user to add a review for a book and returns the created Review object
    public Review addReview(int bookId, User user, String reviewText, int rating);
    
    // Retrieves all reviews written by the user
    public List<Review> getUserReviews(int userId);
    
    public boolean isUsernameTaken(String username) ;
}

