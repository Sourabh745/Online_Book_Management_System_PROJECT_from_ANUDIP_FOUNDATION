package com.bookstore.books.controllers;

import java.util.List;
import java.util.Scanner;

import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.UserService;
import com.bookstore.books.services.implementation.OrderServiceImplementation;
import com.bookstore.books.services.implementation.ReviewServiceImplementation;
import com.bookstore.books.services.implementation.UserServiceImplementation;
import com.bookstore.books.services.OrderService;
import com.bookstore.books.services.ReviewService;

public class UserController {
    private UserService userService;
    private OrderService orderService;
    private ReviewService reviewService;
    private Scanner scanner;

    public UserController() {
        this.userService = new UserServiceImplementation();   // Assuming services are initialized here
        this.orderService = new OrderServiceImplementation();
        this.reviewService = new ReviewServiceImplementation();
        this.scanner = new Scanner(System.in);
    }

    // Registers a new user
    public void registerUser() {
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        
     // Check if the username already exists
        while (userService.isUsernameTaken(username)) {
            System.out.println("Username already exists. Please enter a different username:");
            username = scanner.nextLine();
        }
        
        System.out.println("Enter Password:");  
        //validation
        String password = scanner.nextLine();
        while(password.length() <= 8) {
        	System.out.println("Enter Password again:"); 
        	password = scanner.nextLine();
        }
        
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Address:");
        String address = scanner.nextLine();
        System.out.println("Enter Phone:");
        String phone = scanner.nextLine();

        User newUser = new User(username, password, name, email, address, phone);
        userService.registerUser(newUser);
        System.out.println("User registered successfully.");
    }

    // Login a user
    public User loginUser() {
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        		
        User user = userService.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
        return user;
    }

    // Retrieves details of the logged-in user by their ID
    public void getUserDetails(int userId) {
        User user = userService.getUserDetails(userId);
        if (user != null) {
            System.out.println("User details: " + user);
        } else {
            System.out.println("User not found.");
        }
        return;
    }

    // Updates the logged-in user's information
    public void updateUser(int userId) {
        System.out.println("Enter new Name (leave blank to keep current):"); // just enter don't press space then enter it change the name
        String name = scanner.nextLine();
        System.out.println("Enter new Email (leave blank to keep current):");
        String email = scanner.nextLine();
        System.out.println("Enter new Address (leave blank to keep current):");
        String address = scanner.nextLine();
        System.out.println("Enter new Phone (leave blank to keep current):");
        String phone = scanner.nextLine();

        User updatedUser = userService.getUserDetails(userId);
        if (updatedUser == null) {
            System.out.println("User not found. Update aborted.");
            return;
        }

        if (!name.isEmpty()) updatedUser.setName(name);
        if (!email.isEmpty()) updatedUser.setEmail(email);
        if (!address.isEmpty()) updatedUser.setAddress(address);
        if (!phone.isEmpty()) updatedUser.setPhone(phone);

        userService.updateUser(userId, updatedUser);
        System.out.println("User updated successfully.");
    }

    // Deletes the user's account
    public boolean deleteAccount(int userId) {
        User user = userService.getUserDetails(userId);
        if (user == null) {
            System.out.println("User not found. Deletion aborted.");
            return false;
        }

        // Check for associated orders and remove references
        for (Orders order : user.getOrders()) {
            order.setUser(null); // Break the association with the user
        }

        userService.deleteAccount(userId);
        System.out.println("User account deleted successfully.");
        return true;
    }

    // Displays the user's order history
    public void viewOrderHistory(int userId) {
        List<Orders> orders = orderService.getOrdersByUser(userId);
        if (orders != null && !orders.isEmpty()) {
            System.out.println("Order history:");
            for (Orders order : orders) {
                System.out.println(order);
            }
        } else {
            System.out.println("No orders found.");
        }
    }
    
    // Views details of a specific order
    public void viewOrderDetails(int orderId) {
        Orders order = orderService.getOrderById(orderId);
        if (order != null) {
            System.out.println("Order details: " + order);
        } else {
            System.out.println("Order not found.");
        }
    }

    // Adds a review for a book
    public void addReview(String bookId, int userId, String reviewText, int rating) {
        User user = userService.getUserDetails(userId);
        if (user == null) {
            System.out.println("User not found. Unable to add review.");
            return;
        }

        Review newReview = reviewService.createReview(user, bookId, reviewText, rating);
        System.out.println("Review added successfully: " + newReview);
    }

    // Retrieves all reviews written by the user
    public void getUserReviews(int userId) {
        List<Review> reviews = reviewService.getReviewsByUser(userId);
        if (reviews != null && !reviews.isEmpty()) {
            System.out.println("User reviews:");
            for (Review review : reviews) {
                System.out.println(review);
            }
        } else {
            System.out.println("No reviews found.");
        }
    }
}
