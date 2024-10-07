package com.bookstore.books.dao.implementation;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bookstore.books.dao.AdminDAO;
import com.bookstore.books.entities.Author;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.User;
import com.bookstore.books.utils.HibernateUtils;

public class AdminDAOImplementation implements AdminDAO {

	@Override
	public Book addBook(Book book) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	    	System.out.println("Opening session...");
	        transaction = session.beginTransaction();
	        session.save(book);
	        System.out.println("Book saved: " + book);
	        transaction.commit();

	        return book;
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to add book: " + e.getMessage());
	    }
	}


	@SuppressWarnings("deprecation")
	@Override
	public Book updateBook(String bookId, Book updatedBook) {
	    Transaction transaction = null;
	    Book existingBook = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        existingBook = session.get(Book.class, bookId);
	        if (existingBook != null) {
	            existingBook.setTitle(updatedBook.getTitle());
	            existingBook.setAuthor(updatedBook.getAuthor());
	            existingBook.setPrice(updatedBook.getPrice());
	            //existingBook.setDescription(updatedBook.getDescription());
	            session.update(existingBook);
	            transaction.commit();
	            return existingBook;
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to update book : " + e.getMessage());
	    }
	    return null;
	}
  

	@Override
	public boolean deleteBook(String bookId) {
	    Transaction transaction = null;
	    boolean isDeleted = false;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        Book book = session.get(Book.class, bookId);
	        if (book != null) {
	            session.delete(book);
	            transaction.commit();
	            isDeleted = true;
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return isDeleted;
	}


	public List<User> getAllUsers() {
	    Transaction transaction = null;
	    List<User> users = null;

	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Fetch all users
	        users = session.createQuery("from User", User.class).list();
	        
	        // Initialize lazy collections (orders and reviews) for each user
	        for (User user : users) {
	            Hibernate.initialize(user.getOrders());
	            Hibernate.initialize(user.getReviews());
	        }

	        transaction.commit();
	        return users;
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to get all users: " + e.getMessage());
	    }
	}



	@Override
	public User getUserById(int userId) {
	    Transaction transaction = null;
	    User user = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	     // First retrieve the User object
	        user = session.get(User.class, userId);

	        if (user != null) {
	            // Now initialize the lazy-loaded collections
	            Hibernate.initialize(user.getOrders());
	            Hibernate.initialize(user.getReviews());
	        }
	        transaction.commit();
	        return user;
	    } catch (Exception e) {
	        if (transaction != null &&  transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to get  user : " + e.getMessage());
	    }
	}
	
	@Override
	public Author getAuthorById(int authorId) {
	    Transaction transaction = null;
	    Author author = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        author = session.get(Author.class, authorId);
	        if(author != null) {
	        	Hibernate.initialize(author.getBooks());
	        }
	        transaction.commit();
	        return author;
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to get author by id : " + e.getMessage());
	    }
	}



	@Override
	public boolean deleteUser(int userId) {
	    Transaction transaction = null;
	    boolean isDeleted = false;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        User user = session.get(User.class, userId);
	        if (user != null) {
	            session.delete(user);
	            transaction.commit();
	            isDeleted = true;
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return isDeleted;
	}


	@Override
	public List<Orders> getAllOrders() {
	   
	    Transaction transaction = null;
	    List<Orders> orders = null;

	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Fetch all users
	        orders = session.createQuery("from Orders", Orders.class).list();
	        
	        // Initialize lazy collections (orders and reviews) for each user
	        for (Orders order : orders) {
	            Hibernate.initialize(order.getOrderItems());
	            Hibernate.initialize(order.getPayments());
	        }

	        transaction.commit();
	        return orders;
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to get all users: " + e.getMessage());
	    }
	}
	

	@Override
	public Orders getOrderById(int orderId) {
	    
	    Transaction transaction = null;
	    Orders order = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        order = session.get(Orders.class, orderId);
	        if(order != null) {
	        	Hibernate.initialize(order.getOrderItems());
	        	Hibernate.initialize(order.getPayments());
	        }
	        transaction.commit();
	        return order;
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to get author by id : " + e.getMessage());
	    }
	}

}
