package com.bookstore.books.CallingMethods;

import java.util.*;

import com.bookstore.books.controllers.OrderController;
import com.bookstore.books.entities.Orders;

public class ManageOrders {

	static 	Scanner scanner = new Scanner(System.in);
	private OrderController oc = new OrderController();

	private void manageOrders() {
	    System.out.println("Order Menu:");
	    System.out.println("1. Create Order");
	    System.out.println("2. View All Orders");
	    System.out.println("3. View Order by ID");
	    System.out.println("4. Update Order");
	    System.out.println("5. Delete Order");
	    System.out.println("6. Back to Main Menu");
	    System.out.print("Choose an option: ");
	    int choice = scanner.nextInt();
	    scanner.nextLine();

	    switch (choice) {
	        case 1:
	            createOrder();
	            break;
	        case 2:
	            viewAllOrders();
	            break;
	        case 3:
	            viewOrderById();
	            break;
	        case 4:
	            updateOrder();
	            break;
	        case 5:
	            deleteOrder();
	            break;
	        case 6:
	            return;
	        default:
	            System.out.println("Invalid option.");
	    }
	}

	private void createOrder() {
	   oc.createOrder();
	}

	private void viewAllOrders() {
	    oc.viewAllOrders();
	    
	}

	private void viewOrderById() {
	    oc.viewOrderById();
	}

	private void updateOrder() {
	    oc.updateOrder();
	}

	private void deleteOrder() {
	    oc.deleteOrder();
	}

}
