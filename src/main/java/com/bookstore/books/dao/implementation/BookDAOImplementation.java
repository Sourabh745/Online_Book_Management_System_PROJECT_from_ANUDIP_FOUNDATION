package com.bookstore.books.dao.implementation;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bookstore.books.dao.BookDAO;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.User;
import com.bookstore.books.utils.HibernateUtils;

public class BookDAOImplementation implements BookDAO{

	@Override
	public Book createBook(Book book) {
		Transaction transaction = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        // Save the book
	        session.save(book);
	        
	        // Commit the transaction
	        transaction.commit();
	        
	        return book;  // Return the saved book object
	    } catch (HibernateException e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback(); // Rollback only if transaction is active
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to add book: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle any other exceptions
	        e.printStackTrace();
	        throw new RuntimeException("Failed to add book due to unexpected error: " + e.getMessage());
	    }
	}

	@Override
	public List<Book> getAllBooks() {
		Transaction transaction = null;
	    List<Book> books = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve all books
	        Query<Book> query = session.createQuery("FROM Book", Book.class);
	        books = query.getResultList();
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return books;  // Return the list of books
	}
		
	@Override
	public Book updateBook(String id, Book updatedBook) {
		Transaction transaction = null;
	    Book book = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the book by its ID
	        book = session.get(Book.class, id);
	        
	        if (book != null) {
	            // Update the book fields
	            book.setTitle(updatedBook.getTitle());
	            book.setAuthor(updatedBook.getAuthor());
	            book.setPrice(updatedBook.getPrice());
	            book.setDescription(updatedBook.getDescription());
	            // Update any other fields as required

	            // Save the updated book
	            session.update(book);
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	        return book;  // Return the updated book or null if not found
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to update book: " + e.getMessage());  // Propagate the exception		    

	    }
	}

	@Override
	public boolean deleteBook(String id) {
		Transaction transaction = null;
	    boolean isDeleted = false;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the book by its ID
	        Book book = session.get(Book.class, id);
	        
	        if (book != null) {
	            // Delete the book
	            session.delete(book);
	            isDeleted = true;
	        }
	        else {
	        	System.out.println("Book id is not valid");
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	        return isDeleted;  // Return true if the book was deleted, false otherwise
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to delete book: " + e.getMessage());  // Propagate the exception		    
	    }
	}

	@Override
	public List<Book> findBooksByAuthor(int authorId) {
		Transaction transaction = null;
	    List<Book> books = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Query to find all books by the author
	        Query<Book> query = session.createQuery("FROM Book b WHERE b.author.id = :authorId", Book.class);
	        query.setParameter("authorId", authorId);
	        books = query.getResultList();
	        
	        // Commit the transaction
	        transaction.commit();
	        return books;  // Return the list of books by the author
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to get book: " + e.getMessage());  // Propagate the exception		    
	    }
	}

	@Override
	public Book getBookById(String bookId) {
			Transaction transaction = null;
		    Book book = null;
		    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
		        transaction = session.beginTransaction();
		        
		        // Retrieve the book by its ID
		        book = session.get(Book.class, bookId);
		        
		        // Commit the transaction
		        transaction.commit();
		        return book;  // Return the retrieved book or null if not found
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		        throw new RuntimeException("Failed to get book: " + e.getMessage());  // Propagate the exception		    
		}
}
}
