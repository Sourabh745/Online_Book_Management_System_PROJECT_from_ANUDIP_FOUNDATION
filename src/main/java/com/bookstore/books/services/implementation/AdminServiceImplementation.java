package com.bookstore.books.services.implementation;

import java.util.List;

import com.bookstore.books.dao.AdminDAO;
import com.bookstore.books.dao.implementation.AdminDAOImplementation;
import com.bookstore.books.entities.Author;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.AdminService;

public class AdminServiceImplementation implements AdminService {
	
	private AdminDAO adminDAO;

	public AdminServiceImplementation() {
		super();
		this.adminDAO = new AdminDAOImplementation();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Book addBook(Book book) {
		// TODO Auto-generated method stub
		return adminDAO.addBook(book);
	}

	@Override
	public Book updateBook(String bookId, Book updatedBook) {
		// TODO Auto-generated method stub
		return adminDAO.updateBook(bookId, updatedBook);
	}

	@Override
	public boolean deleteBook(String bookId) {
		// TODO Auto-generated method stub
		return adminDAO.deleteBook(bookId);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return adminDAO.getAllUsers();
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return adminDAO.getUserById(userId);
	}

	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		return adminDAO.deleteUser(userId);
	}


	@Override
	public Orders getOrderById(int orderId) {
		// TODO Auto-generated method stub
		return adminDAO.getOrderById(orderId);
	}

	@Override
	public List<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		return adminDAO.getAllOrders();
	}

	@Override
	public Author getAuthorById(int authorId) {
		// TODO Auto-generated method stub
		return adminDAO.getAuthorById(authorId);
	}

}
