package com.bookstore.books.dao.implementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bookstore.books.dao.OrderItemDAO;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.utils.HibernateUtils;

public class OrderItemDAOImplementation implements OrderItemDAO{

	@Override
	public OrderItems addOrderItem(int orderId, String bookId, int quantity) {
	    Transaction transaction = null;
	    OrderItems newOrderItem = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the order by its ID
	        Orders order = session.get(Orders.class, orderId);
	        if (order == null) {
	            throw new RuntimeException("Order not found");
	        }
	        
	        // Retrieve the book by its ID
	        Book book = session.get(Book.class, bookId);
	        if (book == null) {
	            throw new RuntimeException("Book not found");
	        }
	        
	        // Create a new OrderItem
	        newOrderItem = new OrderItems();
	        newOrderItem.setOrder(order);
	        newOrderItem.setBook(book);
	       // newOrderItem.setQuantity(quantity);
	        
	        // Save the new order item
	        session.save(newOrderItem);
	        
	        // Commit the transaction
	        transaction.commit();
	        return newOrderItem;  // Return the newly added order item
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to add item in order: " + e.getMessage());

	    }
	}


	@Override
	public OrderItems getOrderItemById(int id) {
	    Transaction transaction = null;
	    OrderItems orderItem = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the order item by its ID
	        orderItem = session.get(OrderItems.class, id);
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return orderItem;  // Return the retrieved order item
	}


	@Override
	public List<OrderItems> getAllOrderItemsForOrder(int orderId) {
	    Transaction transaction = null;
	    List<OrderItems> orderItems = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Query to retrieve all order items for the given order
	        Query<OrderItems> query = session.createQuery("FROM OrderItems o WHERE o.order.id = :orderId", OrderItems.class);
	        query.setParameter("orderId", orderId);
	        orderItems = query.getResultList();
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return orderItems;  // Return the list of order items for the order
	}


	@Override
	public OrderItems updateOrderItem(int id, int newQuantity) {
	    Transaction transaction = null;
	    OrderItems orderItem = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the order item by its ID
	        orderItem = session.get(OrderItems.class, id);
	        if (orderItem != null) {
	            // Update the quantity
	            orderItem.setQuantity(newQuantity);
	            session.update(orderItem);  // Save the updated order item
	        } else {
	            throw new RuntimeException("Order item not found");
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return orderItem;  // Return the updated order item
	}


	@Override
	public boolean deleteOrderItem(int id) {
	    Transaction transaction = null;
	    boolean isDeleted = false;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the order item by its ID
	        OrderItems orderItem = session.get(OrderItems.class, id);
	        if (orderItem != null) {
	            session.delete(orderItem);  // Delete the order item
	            isDeleted = true;
	        } else {
	            throw new RuntimeException("Order item not found");
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return isDeleted;  // Return true if the item was deleted, false otherwise
	}


}
