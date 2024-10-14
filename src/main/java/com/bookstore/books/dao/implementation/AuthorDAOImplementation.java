package com.bookstore.books.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bookstore.books.dao.AuthorDAO;
import com.bookstore.books.entities.Author;
import com.bookstore.books.entities.User;
import com.bookstore.books.utils.HibernateUtils;

public class AuthorDAOImplementation implements AuthorDAO{

	@Override
	public Author createAuthor(Author author) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Save the new author to the database
	        session.save(author);
	        
	        // Commit the transaction
	        transaction.commit();
		    return author;  // Return the created author
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to add author: " + e.getMessage());  // Propagate the exception
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
	public List<Author> getAllAuthors() {

		Transaction transaction = null;
	    List<Author> authors = null;

	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        	        // Fetch all users
	        authors = session.createQuery("from Author", Author.class).list();
	        
	        for (Author author : authors) {
	            Hibernate.initialize(author.getBooks());
	        }

	        transaction.commit();
	        return authors;
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Failed to get all users: " + e.getMessage());
	    }
	}




	@Override
	public Author updateAuthor(int id, Author updatedAuthor) {
	    Transaction transaction = null;
	    Author existingAuthor = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the existing author by ID
	        existingAuthor = session.get(Author.class, id);
	        if (existingAuthor != null) {
	            // Update the author's details
	            existingAuthor.setName(updatedAuthor.getName());
	            existingAuthor.setBiography(updatedAuthor.getBiography());
	            
	            // Save the updated author
	            session.update(existingAuthor);
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return existingAuthor;  // Return the updated author or null if not found
	}


	@Override
	public boolean deleteAuthor(int id) {
	    Transaction transaction = null;
	    boolean isDeleted = false;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the author by ID
	        Author author = session.get(Author.class, id);
	        if (author != null) {
	            // Delete the author
	            session.delete(author);
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
	    return isDeleted;  // Return true if the author was deleted, false otherwise
	}


}
