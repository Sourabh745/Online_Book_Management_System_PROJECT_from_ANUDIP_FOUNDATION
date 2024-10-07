package com.bookstore.books.dao.implementation;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bookstore.books.dao.ReviewDAO;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;
import com.bookstore.books.utils.HibernateUtils;

public class ReviewDAOImplementation implements ReviewDAO{

	@Override
	public Review createReview(User user, String bookId, String reviewText, int rating) {
	    Transaction transaction = null;
	    Review newReview = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the book by its ID
	        Book book = session.get(Book.class, bookId);
	        if (book == null) {
	            throw new RuntimeException("Book not found");
	        }
	        
	       // Hibernate.initialize(book.getOrderItems());
	        
	        // Create a new Review
	        newReview = new Review();
	        newReview.setUser(user);
	        newReview.setBook(book);
	        newReview.setReviewText(reviewText);
	        newReview.setRating(rating);
	        
	        // Save the review
	        session.save(newReview);
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return newReview;  // Return the created review
	}


	@Override
	public Review getReviewById(int id) {
	    Transaction transaction = null;
	    Review review = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the review by its ID
	        review = session.get(Review.class, id);
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return review;  // Return the review found
	}


	@Override
	public List<Review> getAllReviews() {
	    Transaction transaction = null;
	    List<Review> reviews = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Query to retrieve all reviews
	        Query<Review> query = session.createQuery("FROM Review", Review.class);
	        reviews = query.getResultList();
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return reviews;  // Return the list of reviews
	}


	@Override
	public List<Review> getReviewsForBook(String bookId) {
	    Transaction transaction = null;
	    List<Review> reviews = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Query to retrieve all reviews for the specified book
	        Query<Review> query = session.createQuery("FROM Review r WHERE r.book.id = :bookId", Review.class);
	        query.setParameter("bookId", bookId);
	        reviews = query.getResultList();
	        
	        
	        // Commit the transaction
	        transaction.commit();
	        return reviews;  // Return the list of reviews for the book
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return null;
	}


	@Override
	public List<Review> getReviewsByUser(int userId) {
	    Transaction transaction = null;
	    List<Review> reviews = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Query to retrieve all reviews written by the user
	        Query<Review> query = session.createQuery("FROM Review r WHERE r.user.id = :userId", Review.class);
	        query.setParameter("userId", userId);
	        reviews = query.getResultList();
	        
	        for (Review review : reviews) {
	            Hibernate.initialize(review.getBook());  // Initialize the associated book
	            // If the Book entity has lazily loaded collections, initialize them as well
	            Hibernate.initialize(review.getBook().getOrderItems());  // Initialize orderItems or other collections
	            Hibernate.initialize(review.getBook().getReviews());
	        }
	        // Commit the transaction
	        transaction.commit();
	        return reviews;  // Return the list of reviews written by the user
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return null;
	}


	@Override
	public Review updateReview(int id, String updatedReviewText, int updatedRating) {
	    Transaction transaction = null;
	    Review review = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the review by its ID
	        review = session.get(Review.class, id);
	        if (review != null) {
	            // Update the review details
	            review.setReviewText(updatedReviewText);
	            review.setRating(updatedRating);
	            
	            // Save the updated review
	            session.update(review);
	        } else {
	            throw new RuntimeException("Review not found");
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return review;  // Return the updated review
	}

	@Override
	public boolean deleteReview(int id) {
	    Transaction transaction = null;
	    boolean isDeleted = false;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the review by its ID
	        Review review = session.get(Review.class, id);
	        if (review != null) {
	            session.delete(review);  // Delete the review
	            isDeleted = true;
	        } else {
	            throw new RuntimeException("Review not found");
	        }
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return isDeleted;  // Return true if the review was deleted, false otherwise
	}


}
