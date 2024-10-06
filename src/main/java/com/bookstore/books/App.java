package com.bookstore.books;

import java.util.Scanner;
import com.bookstore.books.controllers.UserController;
import com.bookstore.books.CallingMethods.ManageOrders;
import com.bookstore.books.controllers.AdminController;
import com.bookstore.books.controllers.OrderController;
import com.bookstore.books.controllers.ReviewController;
import com.bookstore.books.entities.User;

public class App {
    private UserController userController;
    private OrderController orderController;
    private ReviewController reviewController;
    private AdminController adminController; // New AdminController instance
    private User loggedInUser;
    private Scanner scanner;

    public App() {
        // Initialize controllers
        this.userController = new UserController();
        this.orderController = new OrderController();
        this.reviewController = new ReviewController();
        this.adminController = new AdminController(); // Initialize AdminController
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        App mainApp = new App();  // Create an instance of Main
        mainApp.run();              // Start the application
    }

    // Main application loop
    public void run() {
        while (true) {
            if (loggedInUser == null) {
                showWelcomeMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private void showWelcomeMenu() {
        System.out.println("Welcome to the Online Bookstore!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Admin");        
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                userController.registerUser();
                break;
                
            case 2:
               loggedInUser = userController.loginUser();
                break;
                
            case 3:
            	if (adminController.adminLogin()) { // Call admin login method
                    adminController.showMenu();     // Redirect to Admin Menu on successful login
                }
            	break;
            	
            case 4:
                System.out.println("Thank you for visiting. Goodbye!");
                System.exit(0);  // Exit the application
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private void showUserMenu() {
        System.out.println("Welcome, " + loggedInUser.getUsername() + "!");
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. Delete Account");
        System.out.println("4. View Order History");
        System.out.println("5. Place an Order");
        System.out.println("6. Add a Review");
        System.out.println("7. Logout");
        System.out.print("Choose an option: ");

        int choice = 0;
        while(choice < 8) {
        	System.out.println("Please choose an option (1-7 to see user details, 8 to exit):");
            choice = scanner.nextInt(); // Get user input for choice
        switch (choice) {
            case 1:
                userController.getUserDetails(loggedInUser.getUserID());
                break;
            case 2:
                userController.updateUser(loggedInUser.getUserID());
                break;
            case 3:
                if (userController.deleteAccount(loggedInUser.getUserID())) {
                    loggedInUser = null;  // Log out the user after deletion
                }
                break;
            case 4:
               orderController.viewOrderById();//viewOrderHistory(loggedInUser.getUserID());
                break;
            case 5:
                placeOrder();  // Custom method in Main to handle ordering
                break;
            case 6:
                addReview();  // Custom method in Main to handle adding a review
                break;
            case 7:
                loggedInUser = null;
                System.out.println("Logged out successfully.");
                break;
            case 8:
                System.out.println("Exiting...");
                choice = 8; // Exit the loop
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
        showUserMenu();
        }
    }

    private void placeOrder() {
        // Example of interacting with OrderController
        System.out.println("Placing an order...");
//        ManageOrders mo = new ManageOrders();
//        mo.managingOrders();
        orderController.showMenu(loggedInUser);
        // You would gather order details here and pass them to orderController
        // Example: orderController.placeOrder(loggedInUser.getUserID(), orderItems, payment);
    }

    private void addReview() {
        // Example of interacting with ReviewController
        System.out.println("Adding a review...");
//        System.out.print("Enter Book ID: ");
//        int bookId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//        System.out.print("Enter Review Text: ");
//        String reviewText = scanner.nextLine();
//        System.out.print("Enter Rating (1-5): ");
//        int rating = scanner.nextInt();
        reviewController.showMenu();

//        reviewController.addReview(bookId, loggedInUser.getUserID(), reviewText, rating);
    }
}
