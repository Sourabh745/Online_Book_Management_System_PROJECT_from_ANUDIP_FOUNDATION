package com.bookstore.books.CallingMethods;

import java.util.*;

import com.bookstore.books.controllers.BookController;
import com.bookstore.books.entities.Book;
import java.util.Date;

public class ManageBooks {
    static Scanner scanner = new Scanner(System.in);
    private BookController bookController = new BookController();

    // Manage books method that gives options for CRUD operations
    private void manageBooks() {
        System.out.println("Book Menu:");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. View Book by ID");
        System.out.println("4. Update Book");
        System.out.println("5. Delete Book");
        System.out.println("6. Back to Main Menu");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                viewAllBooks();
                break;
            case 3:
                viewBookById();
                break;
            case 4:
                updateBook();
                break;
            case 5:
                deleteBook();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid option.");
        }
    }

    // Adds a book
    private void addBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Enter Author ID: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Book Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Book Price: ");
        double bookPrice = scanner.nextDouble();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        Book book = bookController.createBook(bookId, bookTitle, authorId, description, bookPrice, quantity);
        if (book != null) {
            System.out.println("Book created: " + book);
        } else {
            System.out.println("Failed to create book.");
        }
    }

    // View all books
    private void viewAllBooks() {
        List<Book> books = bookController.getAllBooks();
        if (books != null && !books.isEmpty()) {
            for (Book book : books) {
                System.out.println(book);
            }
        } else {
            System.out.println("No books found.");
        }
    }

    // View book by ID
    private void viewBookById() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        Book book = bookController.getBookById(bookId);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    // Update book details
    private void updateBook() {
        System.out.print("Enter Book ID to update: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter new Book Title: ");
        String newBookTitle = scanner.nextLine();
        System.out.print("Enter new Book Description: ");
        String newDescription = scanner.nextLine();
        
        System.out.print("Enter new Price: ");
        double newPrice = scanner.nextDouble();
        System.out.print("Enter new Quantity: ");
        int newQuantity = scanner.nextInt();

        Book updatedBook = bookController.updateBook(bookId, newBookTitle, newDescription, newPrice, newQuantity);
        if (updatedBook != null) {
            System.out.println("Book updated: " + updatedBook);
        } else {
            System.out.println("Failed to update book.");
        }
    }

    // Delete book
    private void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine();
        boolean success = bookController.deleteBook(bookId);
        if (success) {
            System.out.println("Book deleted.");
        } else {
            System.out.println("Failed to delete book.");
        }
    }

    public static void main(String[] args) {
        ManageBooks manageBooks = new ManageBooks();
        manageBooks.manageBooks();
    }
}
