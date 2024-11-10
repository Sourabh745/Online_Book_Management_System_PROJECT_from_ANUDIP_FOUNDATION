package com.bookstore.books;

import com.bookstore.books.entities.User;

public class App {
    private WelcomeMenu welcomeMenu;
    private UserMenu userMenu;
    private User loggedInUser;

    public App() {
        // Initialize controllers and menus
        this.welcomeMenu = new WelcomeMenu();
        this.userMenu = new UserMenu();
    }

    public static void main(String[] args) {
        App mainApp = new App();
        mainApp.run();
    }

    public void run() {
        while (true) {
            if (loggedInUser == null) {
                loggedInUser = welcomeMenu.showWelcomeMenu();
            } 
            else {
                boolean isLoggedOut = true;//userMenu.showUserMenu(loggedInUser);
                if (isLoggedOut) {
                    loggedInUser = null; // User has logged out
                }
            }
        }
    }
}
