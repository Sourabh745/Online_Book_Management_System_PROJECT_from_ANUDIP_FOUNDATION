package com.bookstore.books.services;

import java.util.List;

import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.User;

public interface OrderService{
    
    public Orders getOrderById(int id);

    public List<Orders> getAllOrders();

    public List<Orders> getOrdersByUser(int userId);


    public boolean deleteOrder(int id);
    
	List<OrderItems> collectOrderItems();

	public Orders updateOrder(Orders order);

	public Orders createOrder(User loggedInUser, List<OrderItems> orderItems, Payment payment, double totalCost,
			String status);

	public Orders createOrders(User user, List<OrderItems> orderItems, Payment payment);

}
