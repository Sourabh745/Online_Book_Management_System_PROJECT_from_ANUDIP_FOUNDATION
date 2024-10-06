package com.bookstore.books.controllers;
import java.util.List;
import java.util.Scanner;

import com.bookstore.books.entities.Author;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.AdminService;
import com.bookstore.books.services.AuthorService;
import com.bookstore.books.services.BookService;
import com.bookstore.books.services.implementation.AdminServiceImplementation;
import com.bookstore.books.services.implementation.AuthorServiceImplementation;
import com.bookstore.books.services.implementation.BookServiceImplementation;

public class AdminController {
    private AdminService adminService;
    private BookService bookService;
    private AuthorService authorService;
    private Scanner scanner;

    public AdminController() {
        this.adminService = new AdminServiceImplementation();
        this.bookService = new BookServiceImplementation();
        this.authorService = new AuthorServiceImplementation();
        this.scanner = new Scanner(System.in);
    }

    public boolean adminLogin() {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        // Validate the admin credentials (hard-coded or from a database)
        if ("admin".equals(username) && "admin123".equals(password)) {
            System.out.println("Admin login successful.");
            return true;
        } else {
            System.out.println("Invalid credentials. Try again.");
            return false;
        }
    }

    
    public void showMenu() {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add a Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. View All Users");
            System.out.println("5. View User by ID");
            System.out.println("6. Delete User");
            System.out.println("7. View All Orders");
            System.out.println("8. View Order by ID");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    viewAllUsers();
                    break;
                case 5:
                    viewUserById();
                    break;
                case 6:
                    deleteUser();
                    break;
                case 7:
                    viewAllOrders();
                    break;
                case 8:
                    viewOrderById();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Add Book
    private void addBook() {
    	System.out.print("Enter book Id: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        // Ask if the admin wants to use an existing author or create a new one
        System.out.println("Do you want to use an existing author or create a new one?");
        System.out.println("1. Use existing author");
        System.out.println("2. Create new author");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Author author = null;

        if (choice == 1) {
            // Using existing author
            System.out.print("Enter existing author ID: ");
            int authorId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Fetch the Author from the database using the author ID
            author = adminService.getAuthorById(authorId);

            if (author == null) {
                System.out.println("Author not found.");
                return;
            }
        } else if (choice == 2) {
            // Creating a new author
            System.out.print("Enter author name: ");
            String authorName = scanner.nextLine();
            System.out.print("Enter author biography: ");
            String authorBio = scanner.nextLine();

            author = new Author();
            author.setName(authorName);
            author.setBiography(authorBio);

            // Save the new author
            author = authorService.createAuthor(author);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        // Create a new Book and associate it with the Author
        Book newBook = new Book();
        newBook.setBookId(bookId);
        newBook.setTitle(title);
        newBook.setPrice(price);
        newBook.setAuthor(author);  // Set the associated author

        // Save the new book using the service
        //Book addedBook = adminService.addBook(newBook);
        Book addedBook = bookService.createBook(newBook);

        if (addedBook != null) {
            System.out.println("Book added successfully: " + addedBook);
        } else {
            System.out.println("Failed to add book.");
        }
    }


    // Update Book
    private void updateBook() {
        System.out.print("Enter book ID to update: ");
        String bookId = scanner.nextLine();
        scanner.nextLine();  // Consume newline

        // Fetch the current book by ID to update its details
        Book currentBook = bookService.getBookById(bookId);
        if (currentBook == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.print("Enter new book title (leave blank to keep current): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            currentBook.setTitle(title);  // Update title if provided
        }

        // Ask for the author ID, fetch the author, and set it
        System.out.print("Enter new author ID (leave blank to keep current): ");
        String authorIdInput = scanner.nextLine();
        if (!authorIdInput.isEmpty()) {
            int authorId = Integer.parseInt(authorIdInput);
            Author author = adminService.getAuthorById(authorId);  // Fetch the Author by ID
            if (author != null) {
                currentBook.setAuthor(author);  // Update author if found
            } else {
                System.out.println("Author not found.");
                return;
            }
        }

        System.out.print("Enter new book price (leave blank to keep current): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            double price = Double.parseDouble(priceInput);
            currentBook.setPrice(price);  // Update price if provided
        }

        // Save the updated book
        Book updatedBook = bookService.updateBook(bookId, currentBook);  // Call bookService for book-related operations
        if (updatedBook != null) {
            System.out.println("Book updated successfully: " + updatedBook);
        } else {
            System.out.println("Failed to update book.");
        }
    }

    // Delete Book
    private void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        String bookId = scanner.nextLine();

        boolean success = bookService.deleteBook(bookId);  // Call bookService for book-related operations
        if (success) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Failed to delete book.");
        }
    }

    // View all Users
    private void viewAllUsers() {
        List<User> users = adminService.getAllUsers();
        if (users != null && !users.isEmpty()) {
            System.out.println("All registered users:");
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("No users found.");
        }
    }

    // View User by ID
    private void viewUserById() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        User user = adminService.getUserById(userId);
        if (user != null) {
            System.out.println("User details: " + user);
        } else {
            System.out.println("User not found.");
        }
    }

    // Delete User
    private void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        boolean success = adminService.deleteUser(userId);
        if (success) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }

    // View all Orders
    private void viewAllOrders() {
        List<Orders> orders = adminService.getAllOrders();
        if (orders != null && !orders.isEmpty()) {
            System.out.println("All orders:");
            for (Orders order : orders) {
                System.out.println(order);
            }
        } else {
            System.out.println("No orders found.");
        }
    }

    // View Order by ID
    private void viewOrderById() {
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Orders order = adminService.getOrderById(orderId);
        if (order != null) {
            System.out.println("Order details: " + order);
        } else {
            System.out.println("Order not found.");
        }
    }
}
