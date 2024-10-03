package com.bookstore.books.controllers;

import com.bookstore.books.entities.Author;
import com.bookstore.books.services.AuthorService;
import com.bookstore.books.services.implementation.AuthorServiceImplementation;

import java.util.List;

public class AuthorController {

    private AuthorService authorService;

    public AuthorController() {
        this.authorService = new AuthorServiceImplementation();  // Assuming you have an AuthorService class
    }

    // Create a new author
    public Author createAuthor(String authorName, String authorBio) {
        // Create an Author object and pass it to the service for persistence
        Author newAuthor = new Author();
        newAuthor.setName(authorName);
        newAuthor.setBiography(authorBio);
        return authorService.createAuthor(newAuthor);  // Assuming saveAuthor() persists the author and returns it
    }

    // Retrieve all authors
    public List<Author> getAllAuthors() {
        // Fetch all authors using the service
        return authorService.getAllAuthors();
    }

    // Retrieve a single author by their ID
    public Author getAuthorById(int authorId) {
        // Fetch an author by their ID using the service
        return authorService.getAuthorById(authorId);
    }

    // Update an existing author
    public Author updateAuthor(int authorId, String newAuthorName, String newAuthorBio) {
        // Fetch the existing author
        Author existingAuthor = authorService.getAuthorById(authorId);
        if (existingAuthor != null) {
            // Update the author's details
            existingAuthor.setName(newAuthorName);
            existingAuthor.setBiography(newAuthorBio);
            return authorService.updateAuthor(authorId, existingAuthor);  // Assuming updateAuthor() persists changes
        }
        return null;
    }

    // Delete an author by their ID
    public boolean deleteAuthor(int authorId) {
        // Delete the author using the service
        return authorService.deleteAuthor(authorId);
    }
}
