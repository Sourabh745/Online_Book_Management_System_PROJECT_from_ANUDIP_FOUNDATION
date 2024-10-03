package com.bookstore.books.dao.implementation;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bookstore.books.dao.PaymentDAO;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.utils.HibernateUtils;

public class PaymentDAOImplementation implements PaymentDAO {

	@Override
	public Payment getPaymentById(int id) {
	    Transaction transaction = null;
	    Payment payment = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the payment by its ID
	        payment = session.get(Payment.class, id);
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return payment;  // Return the retrieved payment
	}


	@Override
	public List<Payment> getAllPayments() {
	    Transaction transaction = null;
	    List<Payment> payments = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Query to retrieve all payments
	        Query<Payment> query = session.createQuery("FROM Payment", Payment.class);
	        payments = query.getResultList();
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return payments;  // Return the list of payments
	}


	@Override
	public List<Payment> getPaymentsByUser(int userId) {
	    Transaction transaction = null;
	    List<Payment> payments = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Query to retrieve all payments for a specific user
	        Query<Payment> query = session.createQuery("FROM Payment p WHERE p.order.user.id = :userId", Payment.class);
	        query.setParameter("userId", userId);
	        payments = query.getResultList();
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return payments;  // Return the list of payments for the user
	}


	@Override
	public boolean deletePayment(int id) {
	    Transaction transaction = null;
	    boolean isDeleted = false;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the payment by its ID
	        Payment payment = session.get(Payment.class, id);
	        if (payment != null) {
	            session.delete(payment);  // Delete the payment
	            isDeleted = true;
	        } else {
	            throw new RuntimeException("Payment not found");
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return isDeleted;  // Return true if the payment was deleted, false otherwise
	}


	@Override
	public Payment updatePayment(int id, String newPaymentMethod, double newAmount) {
	    Transaction transaction = null;
	    Payment payment = null;
	    
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the payment by its ID
	        payment = session.get(Payment.class, id);
	        if (payment != null) {
	            // Update the payment details
	            payment.setPaymentMethod(newPaymentMethod);  // Update the payment method
	            payment.setAmount(newAmount);  // Update the amount
	            
	            // Save the updated payment
	            session.update(payment);
	        } else {
	            throw new RuntimeException("Payment not found with ID: " + id);
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    
	    return payment;  // Return the updated payment object
	}



	@Override
	public Payment createPayment(int orderId, String paymentMethod, double amount) {
		Transaction transaction = null;
	    Payment newPayment = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the order by its ID
	        Orders order = session.get(Orders.class, orderId);
	        if (order == null) {
	            throw new RuntimeException("Order not found");
	        }
	        
	        // Create a new Payment
	        newPayment = new Payment();
	        newPayment.setOrder(order);
	        newPayment.setAmount(order.getTotalCost());  // Assuming Order has a method for total amount
	        newPayment.setPaymentDate(new Date());
	        
	        // Save the payment
	        session.save(newPayment);
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return newPayment;  // Return the newly created payment
	}


}
