package com.bookstore.books.dao.implementation;

import java.util.List;

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
	    Book savedBook = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(book);
	        transaction.commit();
	        savedBook = book;
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return savedBook;
	}


	@SuppressWarnings("deprecation")
	@Override
	public Book updateBook(int bookId, Book updatedBook) {
	    Transaction transaction = null;
	    Book existingBook = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        existingBook = session.get(Book.class, bookId);
	        if (existingBook != null) {
	            existingBook.setTitle(updatedBook.getTitle());
	            existingBook.setAuthor(updatedBook.getAuthor());
	            existingBook.setPrice(updatedBook.getPrice());
	            existingBook.setDescription(updatedBook.getDescription());
	            session.update(existingBook);
	            transaction.commit();
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return existingBook;
	}
  

	@Override
	public boolean deleteBook(int bookId) {
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


	@Override
	public List<User> getAllUsers() {
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        return session.createQuery("from User", User.class).list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	@Override
	public User getUserById(int userId) {
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        return session.get(User.class, userId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	@Override
	public Author getAuthorById(int authorId) {
	    Transaction transaction = null;
	    Author author = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        author = session.get(Author.class, authorId);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return author;
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
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        return session.createQuery("from Orders", Orders.class).list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	

	@Override
	public Orders getOrderById(int orderId) {
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        return session.get(Orders.class, orderId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
