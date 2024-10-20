package com.bookstore.books;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.bookstore.books.controllers.UserController;
import com.bookstore.books.controllers.AdminController;
import com.bookstore.books.controllers.OrderController;
import com.bookstore.books.controllers.PaymentController;
import com.bookstore.books.controllers.ReviewController;
import com.bookstore.books.entities.User;
import com.bookstore.books.exceptions.InvalidMenuChoiceException;
import com.bookstore.books.exceptions.UserDeletionException;

public class App {
	private UserController userController;
	private OrderController orderController;
	private ReviewController reviewController;
	private PaymentController paymentController;
	private AdminController adminController; // New AdminController instance
	private User loggedInUser;
	private Scanner scanner;

	public App() {
		// Initialize controllers
		this.userController = new UserController();
		this.orderController = new OrderController();
		this.paymentController = new PaymentController();
		this.reviewController = new ReviewController();
		this.adminController = new AdminController(); // Initialize AdminController
		this.scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		App mainApp = new App(); // Create an instance of Main
		mainApp.run(); // Start the application
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
				adminController.showMenu(); // Redirect to Admin Menu on successful login
			}
			break;

		case 4:
			System.out.println("Thank you for visiting. Goodbye!");
			System.exit(0); // Exit the application
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
		System.out.println("7. Payment");
		System.out.println("8. Logout");
		System.out.print("Choose an option: ");

		int choice = 0;
		while (choice < 9) {
		    try {
		        System.out.println("Please choose an option:");
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
		                    loggedInUser = null; // Log out the user after deletion
		                } else {
		                    throw new UserDeletionException("Failed to delete user account.");
		                }
		                break;
		            case 4:
		                userController.viewOrderHistory(loggedInUser.getUserID());
		                break;
		            case 5:
		                placeOrder();
		                break;
		            case 6:
		                addReview();
		                break;
		            case 7:
		                donePayment();
		                break;
		            case 8:
		                loggedInUser = null;
		                System.out.println("Logged out successfully.");
		                return;
		            case 9:
		                System.out.println("Exiting...");
		                choice = 8; // Exit the loop
		                break;
		            default:
		                throw new InvalidMenuChoiceException("Invalid menu choice: " + choice);
		        }
		        
		    } catch (InputMismatchException e) {
		    	
		        System.out.println("Invalid input. Please enter a valid number.");
		        scanner.nextLine(); // Clear the invalid input
		    }
		    catch (InvalidMenuChoiceException e) {
		    	
		        System.out.println(e.getMessage());
		        
		    } catch (UserDeletionException e) {
		    	
		        System.out.println(e.getMessage());
		        
		    } catch (Exception e) {
		    	
		        System.out.println("An unexpected error occurred: " + e.getMessage());
		    }
		}
	}

	private void placeOrder() {

		System.out.println("Placing an order...");

		orderController.showMenu(loggedInUser);

	}

	private void addReview() {
		System.out.println("Adding a review...");

		reviewController.showMenu(loggedInUser);

	}

	private void donePayment() {
		System.out.println("Clear your payment...");

		paymentController.createPayment(loggedInUser);
	}
}
