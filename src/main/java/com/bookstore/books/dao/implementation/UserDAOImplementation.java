package com.bookstore.books.dao.implementation;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bookstore.books.dao.UserDAO;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;
import com.bookstore.books.utils.HibernateUtils;

public class UserDAOImplementation implements UserDAO{

	//Registering user
	@Override
	public User registerUser(User user) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			return user;
		} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		return null;
	}
	//====================================================================

	@Override
	public User loginUser(String username, String password) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();

            if (user != null && user.getPassword().equals(password)) {
                    return user; // Successful login
                }
            }catch (Exception e) {
                e.printStackTrace(); // Log the exception for debugging
            }
            return null;
	}
//==========================================================================
	
	@Override
	public User getUserDetails(int userId) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        User user = session.get(User.class, userId);
	        if (user != null) {
	            // Initialize the lazy collection manually
	            Hibernate.initialize(user.getOrders()); // Initialize orders collection
	            Hibernate.initialize(user.getReviews());
	        }
	        return user;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

//==========================================================================
	
	@SuppressWarnings("deprecation")
	@Override
	public void updateUser(int userId, User updatedUser) {
		Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(updatedUser); // Update user object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}

//==========================================================================

	@Override
	public boolean deleteAccount(int userId) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        User user = session.get(User.class, userId);
	        if (user != null) {
	            session.delete(user); // Delete user object from the database
	            transaction.commit();
	            return true;
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return false;
	}
	
//==========================================================================

	@Override
	public List<Orders> viewOrderHistory(int userId) {
		Transaction transaction = null;
	    List<Orders> orders = null;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // Fetch the orders for the user
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
		return orders;
	}

//==========================================================================

	@Override
	public Orders placeOrder(User user, List<OrderItems> orderItems, Payment payment) {
	    Transaction transaction = null;
	    Orders newOrder = null;

	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Create a new Order and set the user
	        newOrder = new Orders();
	        newOrder.setUser(user);
	        newOrder.setOrderDate(new Date()); // Set the current date as the order date
	        newOrder.setStatus("Pending"); // Set the initial order status

	        // Link order items to the order
	        for (OrderItems orderItem : orderItems) {
	            orderItem.setOrder(newOrder);  // Link the order item to the new order
	            // Save each order item
	            session.save(orderItem);
	        }

	        // Set the payment for the order and save it
	        payment.setOrder(newOrder);  // Associate the payment with the order
	        session.save(payment); // Save the payment details

	        // Save the new order last
	        session.save(newOrder);
	        
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


	@Override
	public Orders viewOrderDetails(int orderId) {
		Transaction transaction = null;
	    Orders order = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Retrieve the order by its ID
	        order = session.get(Orders.class, orderId);
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return order;
	    }

	@Override
	public List<Review> getUserReviews(int userId) {
		Transaction transaction = null;
	    List<Review> reviews = null;
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // Query to fetch all reviews by the user
	        Query<Review> query = session.createQuery("FROM Review r WHERE r.user.id = :userId", Review.class);
	        query.setParameter("userId", userId);
	        reviews = query.getResultList();
	        
	        // Commit the transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return reviews;
	}
	
	@Override
    public User getUserByUsernameAndPassword(String username, String password) {
        Transaction transaction = null;
        User user = null;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            // Create the query to find user by username and password
            String hql = "FROM User WHERE username = :username AND password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);  // Ensure passwords are hashed and matched correctly
            
            user = query.uniqueResult();  // Get the single result or null if not found
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

//=======================================================================

// This method check if username exist or not
public boolean isUsernameTaken(String username) {
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
        Query<User> query = session.createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        User user = query.uniqueResult();
        return user != null; // Return true if a user with the same username exists
    } catch (Exception e) {
        e.printStackTrace(); // Handle exception or log it properly
    }
    return false;
}

}