package com.bookstore.books.services.implementation;

import java.util.List;

import com.bookstore.books.dao.AuthorDAO;
import com.bookstore.books.dao.implementation.AuthorDAOImplementation;
import com.bookstore.books.entities.Author;
import com.bookstore.books.services.AuthorService;

public class AuthorServiceImplementation implements AuthorService{
	
	private AuthorDAO authorDAO;

	public AuthorServiceImplementation() {
		super();
		this.authorDAO = new AuthorDAOImplementation();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Author createAuthor(Author author) {
		// TODO Auto-generated method stub
		return authorDAO.createAuthor(author);
	}

	@Override
	public Author getAuthorById(int id) {
		// TODO Auto-generated method stub
		return authorDAO.getAuthorById(id);
	}

	@Override
	public List<Author> getAllAuthors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author updateAuthor(int id, Author updatedAuthor) {
		// TODO Auto-generated method stub
		return authorDAO.updateAuthor(id, updatedAuthor);
	}

	@Override
	public boolean deleteAuthor(int id) {
		// TODO Auto-generated method stub
		return authorDAO.deleteAuthor(id);
	}

}
