package com.bookstore.books.dao.implementation;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bookstore.books.dao.BookDAO;
import com.bookstore.books.dao.OrderDAO;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.User;
import com.bookstore.books.utils.HibernateUtils;

public class OrderDAOImplementation implements OrderDAO{

	 	private BookDAO bookDAO;  // This should be implemented to fetch book details (e.g., title, price)
	    private Scanner scanner;

	    public OrderDAOImplementation() {
	        this.bookDAO = new BookDAOImplementation();  // Assuming you have a BookService to handle book data
	        this.scanner = new Scanner(System.in);
	    }
	
	    @SuppressWarnings("deprecation")
	    @Override
	    public Orders createOrder(User loggedInUser, List<OrderItems> orderItems, Payment payment, double totalCost, String status) {
	        Transaction transaction = null;
	        Orders newOrder = null;
	        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            
	            // Create a new order
	            newOrder = new Orders();
	            newOrder.setUser(loggedInUser);  // Associate the order with the user
	            newOrder.setOrderDate(new Date());  // Set the current date for the order
	            newOrder.setTotalCost(totalCost);  // Set the total cost
	            newOrder.setStatus(status);  // Set the status of the order
	            
	            // Set the payment(s)
	            payment.setOrder(newOrder);  // Associate the payment with the order
	            session.save(payment);  // Save the payment
	            
	            // Save the order first
	            session.save(newOrder);
	            
	            // Add all order items to the order
	            for (OrderItems item : orderItems) {
	                item.setOrder(newOrder);  // Associate each item with the order
	                session.save(item);  // Save each order item
	            }
	            
	            // Commit the transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return newOrder;  // Return the newly created order
	    }

	
	 @Override
	    public List<OrderItems> collectOrderItems() {
		 	Scanner scanner = new Scanner(System.in);
	        List<OrderItems> orderItemsList = new ArrayList<>();
	        while (true) {
	            System.out.print("Enter book ID (or 0 to finish): ");
	            String bookId = scanner.nextLine();

	            // Fetch the book details (title, price, etc.)
	            Book book = bookDAO.getBookById(bookId);  // You need a method like this in your BookService

	            if (book != null) {
	                System.out.print("Enter quantity for " + book.getTitle() + ": ");
	                int quantity = scanner.nextInt();

	                // Create a new OrderItem
	                OrderItems orderItem = new OrderItems();
	                orderItem.setBook(book);
	                orderItem.setQuantity(quantity);
	                orderItem.setPrice(book.getPrice());  // Set the book price (could be fetched or calculated)

	                // Add the order item to the list
	                orderItemsList.add(orderItem);

	                System.out.println("Added " + quantity + " of " + book.getTitle() + " to your order.");
	            } else {
	                System.out.println("Book with ID " + bookId + " not found. Please try again.");
	            }
	        }
	    }


	@Override
	public Orders getOrderById(int id) {
	    Transaction transaction = null;
	    Orders order = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the order by its ID
	        order = session.get(Orders.class, id);
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return order;  // Return the retrieved order
	}


	@Override
	public List<Orders> getAllOrders() {
	    Transaction transaction = null;
	    List<Orders> orders = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve all orders
	        Query<Orders> query = session.createQuery("FROM Orders", Orders.class);
	        orders = query.getResultList();
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return orders;  // Return the list of all orders
	}


	@Override
	public List<Orders> getOrdersByUser(int userId) {
	    Transaction transaction = null;
	    List<Orders> orders = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Query to find all orders for the user
	        Query<Orders> query = session.createQuery("FROM Orders o WHERE o.user.id = :userId", Orders.class);
	        query.setParameter("userId", userId);
	        orders = query.getResultList();
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return orders;  // Return the list of orders by user
	}


	@Override
	public Orders updateOrder(int orderId, List<OrderItems> updatedOrderItems, List<Payment> payments, double updatedTotalCost, String updatedStatus) {
	    Transaction transaction = null;
	    Orders order = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the order by its ID
	        order = session.get(Orders.class, orderId);
	        
	        if (order != null) {
	            // Update the order status
	            order.setStatus(updatedStatus);
	            
	            // Update the total cost
	            order.setTotalCost(updatedTotalCost);
	            
	            // Update the payments (clear old payments and add new ones)
	            order.getPayments().clear();
	            for (Payment payment : payments) {
	                payment.setOrder(order);  // Associate payment with the order
	                session.saveOrUpdate(payment);  // Save or update each payment
	                order.getPayments().add(payment);
	            }
	            
	            // Update the order items (clear old items and add new ones)
	            order.getOrderItems().clear();
	            for (OrderItems item : updatedOrderItems) {
	                item.setOrder(order);  // Associate each updated item with the order
	                session.saveOrUpdate(item);  // Save or update each order item
	                order.getOrderItems().add(item);
	            }
	            
	            // Save the updated order
	            session.saveOrUpdate(order);
	            
	            // Commit the transaction
	            transaction.commit();
	        } else {
	            System.out.println("Order not found.");
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return order;  // Return the updated order
	}



	@Override
	public boolean deleteOrder(int id) {
	    Transaction transaction = null;
	    boolean isDeleted = false;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the order by its ID
	        Orders order = session.get(Orders.class, id);
	        
	        if (order != null) {
	            // Delete associated order items first (optional, based on your design)
	            for (OrderItems item : order.getOrderItems()) {
	                session.delete(item);
	            }
	            
	            // Then delete the order
	            session.delete(order);
	            isDeleted = true;
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return isDeleted;  // Return true if the order was deleted, false otherwise
	}

	@Override
	public Orders createOrder(User loggedInUser, List<OrderItems> orderItems, Payment payment) {
		Transaction transaction = null;
        Orders newOrder = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            // Create a new order
            newOrder = new Orders();
            newOrder.setUser(loggedInUser);  // Associate the order with the user
            newOrder.setOrderItems(orderItems);
            
            // Set the payment(s)
            payment.setOrder(newOrder);  // Associate the payment with the order
            session.save(payment);  // Save the payment
            
            // Save the order first
            session.save(newOrder);
            
            // Add all order items to the order
            for (OrderItems item : orderItems) {
                item.setOrder(newOrder);  // Associate each item with the order
                session.save(item);  // Save each order item
            }
            
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return newOrder; 
	}


}
