package com.bookstore.books.CallingMethods;

import java.util.*;

import com.bookstore.books.controllers.AuthorController;
import com.bookstore.books.entities.Author;

public class manageAuthors {
	static 	Scanner scanner = new Scanner(System.in);
	private AuthorController ac = new AuthorController();

	private void manageAuthor() {
	    System.out.println("Author Menu:");
	    System.out.println("1. Add Author");
	    System.out.println("2. View All Authors");
	    System.out.println("3. View Author by ID");
	    System.out.println("4. Update Author");
	    System.out.println("5. Delete Author");
	    System.out.println("6. Back to Main Menu");
	    System.out.print("Choose an option: ");
	    int choice = scanner.nextInt();
	    scanner.nextLine();

	    switch (choice) {
	        case 1:
	            addAuthor();
	            break;
	        case 2:
	            viewAllAuthors();
	            break;
	        case 3:
	            viewAuthorById();
	            break;
	        case 4:
	            updateAuthor();
	            break;
	        case 5:
	            deleteAuthor();
	            break;
	        case 6:
	            return;
	        default:
	            System.out.println("Invalid option.");
	    }
	}

	private void addAuthor() {
		
	    System.out.print("Enter Author Name: ");
	    String authorName = scanner.nextLine();
	    System.out.print("Enter Author Bio: ");
	    String authorBio = scanner.nextLine();

	    Author author = ac.createAuthor(authorName, authorBio);
	    if (author != null) {
	        System.out.println("Author created: " + author);
	    } else {
	        System.out.println("Failed to create author.");
	    }
	}

	private void viewAllAuthors() {
	    List<Author> authors = ac.getAllAuthors();
	    if (authors != null && !authors.isEmpty()) {
	        for (Author author : authors) {
	            System.out.println(author);
	        }
	    } else {
	        System.out.println("No authors found.");
	    }
	}

	private void viewAuthorById() {
	    System.out.print("Enter Author ID: ");
	    int authorId = scanner.nextInt();
	    Author author = ac.getAuthorById(authorId);
	    if (author != null) {
	        System.out.println(author);
	    } else {
	        System.out.println("Author not found.");
	    }
	}

	private void updateAuthor() {
	    System.out.print("Enter Author ID to update: ");
	    int authorId = scanner.nextInt();
	    scanner.nextLine();  // Consume newline
	    System.out.print("Enter new Author Name: ");
	    String newAuthorName = scanner.nextLine();
	    System.out.print("Enter new Author Bio: ");
	    String newAuthorBio = scanner.nextLine();

	    Author updatedAuthor = ac.updateAuthor(authorId, newAuthorName, newAuthorBio);
	    if (updatedAuthor != null) {
	        System.out.println("Author updated: " + updatedAuthor);
	    } else {
	        System.out.println("Failed to update author.");
	    }
	}

	private void deleteAuthor() {
	    System.out.print("Enter Author ID to delete: ");
	    int authorId = scanner.nextInt();
	    boolean success = ac.deleteAuthor(authorId);
	    if (success) {
	        System.out.println("Author deleted.");
	    } else {
	        System.out.println("Failed to delete author.");
	    }
	}

}
