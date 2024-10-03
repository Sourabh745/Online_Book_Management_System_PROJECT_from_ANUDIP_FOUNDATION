package com.bookstore.books.controllers;

import java.util.List;

import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.*;

public class UserController {
    private UserService userService;
    private OrderService orderService;
    private ReviewService reviewService;

    public UserController() {
        this.userService = new UserService();   // Assuming services are initialized here
        this.orderService = new OrderService();
        this.reviewService = new ReviewService();
    }

    // Registers a new user and returns the created User object
    public User registerUser(User user) {
        // TODO: Implement logic to register a new user
        return userService.registerUser(user); // Assuming registerUser() returns the created User
    }

    // Allows a user to log in and returns the User object if successful
    public User loginUser(String username, String password) {
        // TODO: Implement login logic
        return userService.loginUser(username, password); // Assuming loginUser() returns the logged-in User
    }

    // Retrieves details of the logged-in user by their ID
    public User getUserDetails(int userId) {
        // TODO: Implement logic to fetch user details by ID
        return userService.getUserById(userId); // Assuming getUserById() returns a User
    }

    // Allows the logged-in user to update their own information and returns the updated User object
    public User updateUser(int userId, User updatedUser) {
        // TODO: Implement logic to update user information
        return userService.updateUser(userId, updatedUser); // Assuming updateUser() returns the updated User
    }

    // Allows the user to delete their own account and returns a boolean indicating success or failure
    public boolean deleteAccount(int userId) {
        // TODO: Implement logic to delete the user's account
        return userService.deleteUser(userId); // Assuming deleteUser() returns a boolean
    }

    // Displays the logged-in user’s past orders
    public List<Order> viewOrderHistory(int userId) {
        // TODO: Implement logic to fetch order history for the user
        return orderService.getOrdersByUser(userId); // Assuming getOrdersByUser() returns a List of Order
    }

    // Allows the user to place a new order and returns the created Order object
    public Orders placeOrder(User user, List<OrderItems> orderItems, Payment payment) {
        // TODO: Implement logic to place an order
        return orderService.createOrder(user, orderItems, payment); // Assuming createOrder() returns the created Order
    }

    // Allows the user to view details of a specific order they placed
    public Orders viewOrderDetails(int orderId) {
        // TODO: Implement logic to fetch order details
        return orderService.getOrderById(orderId); // Assuming getOrderById() returns an Order
    }

    // Allows the user to add a review for a book and returns the created Review object
    public Review addReview(int bookId, User user, String reviewText, int rating) {
        // TODO: Implement logic to add a review for a book
        return reviewService.addReview(user, bookId, reviewText, rating); // Assuming addReview() returns the created Review
    }

    // Retrieves all reviews written by the user
    public List<Review> getUserReviews(int userId) {
        // TODO: Implement logic to fetch user reviews
        return reviewService.getReviewsByUser(userId); // Assuming getReviewsByUser() returns a List of Review
    }
}

