package com.bookstore.books.services.implementation;

import java.util.List;

import com.bookstore.books.dao.OrderDAO;
import com.bookstore.books.dao.implementation.OrderDAOImplementation;
import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.OrderService;

public class OrderServiceImplementation implements OrderService{

	private OrderDAO orderDAO;
	
	public OrderServiceImplementation() {
		super();
		this.orderDAO = new OrderDAOImplementation();
	}

	@Override
	public Orders getOrderById(int id) {
		// TODO Auto-generated method stub
		return orderDAO.getOrderById(id);
	}

	@Override
	public List<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		return orderDAO.getAllOrders();
	}

	@Override
	public List<Orders> getOrdersByUser(int userId) {
		// TODO Auto-generated method stub
		return orderDAO.getOrdersByUser(userId);
	}

	

	@Override
	public boolean deleteOrder(int id) {
		// TODO Auto-generated method stub
		return orderDAO.deleteOrder(id);
	}

	@Override
	public List<OrderItems> collectOrderItems() {
		// TODO Auto-generated method stub
		return orderDAO.collectOrderItems();
	}

	@Override
	public Orders updateOrder(int orderId, List<OrderItems> updatedOrderItems, List<Payment> payments,
			double updatedTotalCost, String updatedStatus) {
		// TODO Auto-generated method stub
		return orderDAO.updateOrder(orderId, updatedOrderItems, payments,updatedTotalCost, updatedStatus);
	}

	@Override
	public Orders createOrder(User loggedInUser, List<OrderItems> orderItems, Payment payment, double totalCost,
			String status) {
		// TODO Auto-generated method stub
		return orderDAO.createOrder(loggedInUser, orderItems, payment, totalCost, status);
	}

}
