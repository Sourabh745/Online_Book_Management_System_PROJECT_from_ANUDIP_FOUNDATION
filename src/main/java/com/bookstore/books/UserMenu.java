package com.bookstore.books;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.bookstore.books.controllers.OrderController;
import com.bookstore.books.controllers.PaymentController;
import com.bookstore.books.controllers.ReviewController;
import com.bookstore.books.controllers.UserController;
import com.bookstore.books.entities.User;
import com.bookstore.books.exceptions.InvalidMenuChoiceException;
import com.bookstore.books.exceptions.UserDeletionException;

public class UserMenu {
    private UserController userController;
    private OrderController orderController;
    private ReviewController reviewController;
    private PaymentController paymentController;
    private Scanner scanner;

    public UserMenu() {
        this.userController = new UserController();
        this.orderController = new OrderController();
        this.reviewController = new ReviewController();
        this.paymentController = new PaymentController();
        this.scanner = new Scanner(System.in);
    }

    public boolean showUserMenu(User loggedInUser) {
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

        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    userController.getUserDetails(loggedInUser.getUserID());
                    break;
                case 2:
                    userController.updateUser(loggedInUser.getUserID());
                    break;
                case 3:
                    if (userController.deleteAccount(loggedInUser.getUserID())) {
                        System.out.println("Account deleted successfully.");
                        return true; // Indicates user should log out
                    } else {
                        throw new UserDeletionException("Failed to delete user account.");
                    }
                case 4:
                    userController.viewOrderHistory(loggedInUser.getUserID());
                    break;
                case 5:
                    orderController.showMenu(loggedInUser);
                    break;
                case 6:
                    reviewController.showMenu(loggedInUser);
                    break;
                case 7:
                    paymentController.createPayment(loggedInUser);
                    break;
                case 8:
                    System.out.println("Logged out successfully.");
                    return true; // Indicates user should log out
                    
                default:
                    throw new InvalidMenuChoiceException("Invalid menu choice: " + choice);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        } catch (InvalidMenuChoiceException | UserDeletionException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        return false; // Indicates user is still logged in
    }
}