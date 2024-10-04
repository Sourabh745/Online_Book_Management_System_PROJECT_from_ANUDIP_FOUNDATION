package com.bookstore.books.services.implementation;

import java.util.List;

import com.bookstore.books.dao.UserDAO;
import com.bookstore.books.dao.implementation.UserDAOImplementation;
import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.UserService;

public class UserServiceImplementation implements UserService {

	private UserDAO userDAO;
	
	public UserServiceImplementation() {
		super();
		this.userDAO = new UserDAOImplementation();
		// TODO Auto-generated constructor stub
	}

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.registerUser(user);
	}

	@Override
	public User loginUser(String username, String password) {
		// TODO Auto-generated method stub
		return userDAO.loginUser(username, password);
	}

	@Override
	public User getUserDetails(int userId) {
		// TODO Auto-generated method stub
		return userDAO.getUserDetails(userId);
	}

	@Override
	public void updateUser(int userId, User updatedUser) {
		// TODO Auto-generated method stub
		 userDAO.updateUser(userId, updatedUser);;
	}

	@Override
	public boolean deleteAccount(int userId) {
		// TODO Auto-generated method stub
		return userDAO.deleteAccount(userId);
	}

	@Override
	public List<Orders> viewOrderHistory(int userId) {
		// TODO Auto-generated method stub
		return userDAO.viewOrderHistory(userId);
	}

	@Override
	public Orders placeOrder(User user, List<OrderItems> orderItems, Payment payment) {
		// TODO Auto-generated method stub
		return userDAO.placeOrder(user, orderItems, payment);
	}

	@Override
	public Orders viewOrderDetails(int orderId) {
		// TODO Auto-generated method stub
		return userDAO.viewOrderDetails(orderId);
	}

	@Override
	public Review addReview(int bookId, User user, String reviewText, int rating) {
		// TODO Auto-generated method stub
		return userDAO.addReview(bookId, user, reviewText, rating);
	}

	@Override
	public List<Review> getUserReviews(int userId) {
		// TODO Auto-generated method stub
		return userDAO.getUserReviews(userId);
	}

	@Override
	public User authenticateUser(String username, String password) {
		// TODO Auto-generated method stub
		        User user = userDAO.getUserByUsernameAndPassword(username, password);		        
		        if (user != null) {
		            // Passwords match, return the user
		            return user;
		        }
		        return null;  // Authentication failed
		    }

}
