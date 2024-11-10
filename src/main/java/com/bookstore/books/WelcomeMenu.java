package com.bookstore.books;

import java.util.Scanner;
import com.bookstore.books.controllers.AdminController;
import com.bookstore.books.controllers.UserController;
import com.bookstore.books.entities.User;

public class WelcomeMenu {
    private UserController userController;
    private AdminController adminController;
    private UserMenu userMenu;
    private Scanner scanner;

    public WelcomeMenu() {
        this.userController = new UserController() ;
        this.adminController = new AdminController();
        this.userMenu = new UserMenu();
        this.scanner = new Scanner(System.in);
    }

    //Login and Sign up page
    public User showWelcomeMenu() {
        System.out.println("Welcome to the Online Bookstore!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Admin");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                userController.registerUser();
                break;
            case 2:
                User user = userController.loginUser();
                userMenu.showUserMenu(user);
                break;
            case 3:
                if (adminController.adminLogin()) {
                    adminController.showMenu();
                }
                break;
            case 4:
                System.out.println("Thank you for visiting. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Try again.");
        }
        return null;
    }
}
