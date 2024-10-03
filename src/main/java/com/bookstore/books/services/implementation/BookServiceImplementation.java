package com.bookstore.books.services.implementation;

import java.util.List;

import com.bookstore.books.dao.BookDAO;
import com.bookstore.books.dao.implementation.BookDAOImplementation;
import com.bookstore.books.entities.Book;
import com.bookstore.books.services.BookService;

public class BookServiceImplementation implements BookService {

	private BookDAO bookDAO;
	
	public BookServiceImplementation() {
		super();
		this.bookDAO = new BookDAOImplementation();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Book createBook(Book book) {
		// TODO Auto-generated method stub
		return bookDAO.createBook(book);
	}

	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return bookDAO.getBookById(id);
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book updateBook(int id, Book updatedBook) {
		// TODO Auto-generated method stub
		return bookDAO.updateBook(id, updatedBook);
	}

	@Override
	public boolean deleteBook(int id) {
		// TODO Auto-generated method stub
		return bookDAO.deleteBook(id);
	}

	@Override
	public List<Book> findBooksByAuthor(int authorId) {
		// TODO Auto-generated method stub
		return bookDAO.findBooksByAuthor(authorId);
	}

}
