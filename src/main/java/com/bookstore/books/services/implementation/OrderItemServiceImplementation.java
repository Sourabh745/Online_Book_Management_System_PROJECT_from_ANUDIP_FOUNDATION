package com.bookstore.books.services.implementation;

import java.util.List;

import com.bookstore.books.dao.OrderItemDAO;
import com.bookstore.books.dao.implementation.OrderItemDAOImplementation;
import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.services.OrderItemService;

public class OrderItemServiceImplementation implements OrderItemService{

	private OrderItemDAO orderItemDAO;
	
	public OrderItemServiceImplementation() {
		super();
		this.orderItemDAO = new OrderItemDAOImplementation();
	}

	@Override
	public OrderItems addOrderItem(int orderId, String bookId, int quantity) {
		// TODO Auto-generated method stub
		return orderItemDAO.addOrderItem(orderId, bookId, quantity);
	}

	@Override
	public OrderItems getOrderItemById(int id) {
		// TODO Auto-generated method stub
		return orderItemDAO.getOrderItemById(id);
	}

	@Override
	public List<OrderItems> getAllOrderItemsForOrder(int orderId) {
		// TODO Auto-generated method stub
		return orderItemDAO.getAllOrderItemsForOrder(orderId);
	}

	@Override
	public OrderItems updateOrderItem(int id, int newQuantity) {
		// TODO Auto-generated method stub
		return orderItemDAO.updateOrderItem(id, newQuantity);
	}

	@Override
	public boolean deleteOrderItem(int id) {
		// TODO Auto-generated method stub
		return orderItemDAO.deleteOrderItem(id);
	}

}
