package com.bookstore.books.dao;

import java.util.List;

import com.bookstore.books.entities.Author;

public interface AuthorDAO {
	// Creates a new author and returns the created Author object
    public Author createAuthor(Author author) ;

    // Returns an Author object by its ID
    public Author getAuthorById(int id);

    // Returns a list of all authors
    public List<Author> getAllAuthors();

    // Updates an author and returns the updated Author object
    public Author updateAuthor(int id, Author updatedAuthor);

    // Deletes an author by their ID and returns a boolean indicating success or failure
    public boolean deleteAuthor(int id);
}
