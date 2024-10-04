package com.bookstore.books.utils;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.bookstore.books.entities.Author;
import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;

public class HibernateUtils {
	private static SessionFactory sessionFactory;
	static {
		try {
			//create object of  configuration class
			Configuration config = new Configuration();
			//setting properties of configuration
			config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
			//setting connection url
			config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/online_bookstore");
			//setting user name of mysql
			config.setProperty("hibernate.connection.username", "root");
			//setting password of database
			config.setProperty("hibernate.connection.password", "qwertyu");
			//setting how hibernate handles schema generation
			// firstly it check table through entities then check in database
			config.setProperty("hibernate.hbm2ddl.auto","update");
			// setting property to show sql queries being executed
			config.setProperty("hibernate.show_sql","true");
			// setting query to be displayed formatted
			config.setProperty("hibernate.format_sql", "true");
			//----------------------------
			//  add annotated entities here
			config.addAnnotatedClass(Book.class);
			config.addAnnotatedClass(Author.class);
			config.addAnnotatedClass(OrderItems.class);
			config.addAnnotatedClass(Orders.class);
			config.addAnnotatedClass(User.class);
			config.addAnnotatedClass(Review.class);
			config.addAnnotatedClass(Payment.class);
			
			//=========================================
			//=====creating serviceRegistry class object to register the configuration
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

			sessionFactory = config.buildSessionFactory(serviceRegistry);
			}
			catch(Throwable tw)
			{
				System.err.println("Unable to create session Factory : "+tw);
				throw new ExceptionInInitializerError(tw);
			}
		}

		// method to return session factory object
		public static SessionFactory getSessionFactory() {
			return sessionFactory;
		}
	}
